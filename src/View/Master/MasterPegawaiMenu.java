package View.Master;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.*;
import static View.Components.PegawaiView.*;

import java.io.IOException;

import Data.AppEnums.AppEnums;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterPegawaiViewModel;

public class MasterPegawaiMenu {
    private final MasterPegawaiViewModel masterPegawaiVM;

    public MasterPegawaiMenu(MasterPegawaiViewModel masterPegawaiVM) {
        this.masterPegawaiVM = masterPegawaiVM;
    }

    String inputUser;

    public void showMasterPegawaiMenu() {
        while (AppRouter.activeRoute == MASTER_PEGAWAI) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         MASTER PEGAWAI         ");
            System.out.println("==============================");
            System.out.println("1. Show all pegawai");
            System.out.println("2. Choose pegawai");
            System.out.println("3. Add pegawai");
            System.out.println("0. Back");
            System.out.println();
            try {
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showAllPegawai();
                        break;
                    case "2":
                        choosingPegawai();
                        break;
                    case "3":
                        addPegawai();
                        break;
                    case "0":

                        AppRouter.navigateTo(MASTER_MAIN_MENU);
                        break;
                    default:
                        invalidChoice();
                        break;
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void showAllPegawai() {
        InputUtilities.cls();
        System.out.println("SHOW ALL PEGAWAI");
        System.out.println("============================================================");
        System.out.println(" USER ID \tNAMA \t\tEMAIL \t\t ROLE");
        System.out.println("============================================================");

        viewAllDataPegawai(masterPegawaiVM.getListPegawai());
        System.out.println("============================================================");

        InputUtilities.pressEnter();
    }

    private void choosingPegawai() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        CHOOSE PEGAWAI        ");
            System.out.println("==============================");
            System.out.print("User ID\t: ");
            String userID = InputUtilities.input.readLine();
            masterPegawaiVM.selectPegawai(userID);
            System.out.println("==============================");
            InputUtilities.pressEnter();

            if (masterPegawaiVM.getSelectedPegawai() != null) {
                System.out.println();
                formatMessageOutput("Pegawai found");
                AppRouter.navigateTo(AppRouter.AppRoute.SUB_MASTER_DETAIL_PEGAWAI);
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void addPegawai() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        ADD NEW PEGAWAI       ");
            System.out.println("==============================");

            System.out.print("Nama\t\t: ");
            String nama = InputUtilities.input.readLine();

            System.out.print("Email\t\t: ");
            String email = InputUtilities.input.readLine();

            System.out.print("Password\t: ");
            String password = InputUtilities.input.readLine();

            System.out.print("Role\t\t: ");
            AppEnums.UserRole role = role();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterPegawaiVM.addNewPegawai(nama, email, password, role);
                formatMessageOutput("Pegawai added");
            } else {
                formatMessageOutput("Process cancelled");
            }

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (IOException e) {
            invalidChoice();
        }
    }

    public void showDetailPegawaiMenu() {
        while (AppRouter.activeRoute == SUB_MASTER_DETAIL_PEGAWAI) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("      SHOW DETAIL PEGAWAI     ");
            System.out.println("==============================");
            System.out.println("Selected pegawai : " + masterPegawaiVM.getSelectedPegawai().getUserID() + " - " + masterPegawaiVM.getSelectedPegawai().getNama());
            System.out.println();
            System.out.println("1. Show detail");
            System.out.println("2. Edit pegawai");
            System.out.println("3. Delete pegawai");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        detailPegawai();
                        break;
                    case "2":
                        editPegawai();
                        break;
                    case "3":
                        deletePegawai();
                        break;
                    case "0":

                        AppRouter.navigateTo(MASTER_PEGAWAI);
                        break;
                    default:
                        invalidChoice();
                        break;
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void detailPegawai() {
        InputUtilities.cls();
        System.out.println("SHOW DETAIL");
        System.out.println("============================================================");
        System.out.println(" USER ID \tNAMA \t\tEMAIL \t\t ROLE");
        System.out.println("============================================================");

        viewSelectedPegawai(masterPegawaiVM.getSelectedPegawai());
        System.out.println("============================================================");

        InputUtilities.pressEnter();
    }

    private void editPegawai() { // setelah mengedit pegawai, User ID berubah secara acak
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("          EDIT PEGAWAI        ");
            System.out.println("==============================");

            System.out.print("Nama\t\t: ");
            String nama = InputUtilities.input.readLine();

            System.out.print("Email\t\t: ");
            String email = InputUtilities.input.readLine();

            System.out.print("Role\t\t: ");
            AppEnums.UserRole role = role();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterPegawaiVM.editDataPegawai(nama, email, role);
                formatMessageOutput("Pegawai editted");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_PEGAWAI);
            } else {
                formatMessageOutput("Process cancelled");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }


        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void deletePegawai() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        DELETE PEGAWAI        ");
            System.out.println("==============================");
            System.out.println("Selected pegawai : " + masterPegawaiVM.getSelectedPegawai().getUserID() + " - " + masterPegawaiVM.getSelectedPegawai().getNama());
            System.out.println();
            System.out.print("Anda yakin ingin menghapus pegawai ini?(y/n): ");
            inputUser = InputUtilities.input.readLine();
            if (inputUser.equalsIgnoreCase("y")) {
                masterPegawaiVM.deletePegawai(masterPegawaiVM.getSelectedPegawai().getUserID());
                formatMessageOutput("Pegawai deleted");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_PEGAWAI);
            } else {
                System.out.println();
                formatMessageOutput("Process cancelledj");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private AppEnums.UserRole role() {
        try {
            String inputRole = InputUtilities.input.readLine();
            return switch (inputRole.toLowerCase()) {
                case "pegawai" -> AppEnums.UserRole.PEGAWAI;
                case "manager" -> AppEnums.UserRole.MANAGER;
                default -> null;
            };

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }
}
