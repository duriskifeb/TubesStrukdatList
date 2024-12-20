package View.Master;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static Util.InputUtilities.getJenisKamarFromInput;
import static Util.InputUtilities.getStatusKamarFromInput;
import static View.AppRouter.AppRoute.MASTER_KAMAR;
import static View.AppRouter.AppRoute.MASTER_MAIN_MENU;
import static View.Components.KamarView.*;

import java.io.IOException;

import Data.AppEnums.AppEnums;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterKamarViewModel;

public class MasterKamarMenu {
    private final MasterKamarViewModel masterKamarVM;

    public MasterKamarMenu(MasterKamarViewModel masterKamarVM) {
        this.masterKamarVM = masterKamarVM;
    }

    String inputUser;

    public void showMasterKamarMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.MASTER_KAMAR) {
//            System.out.println("Inside master kamar " + AppRouter.activeRoute);
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         MASTER KAMAR         ");
            System.out.println("==============================");
            System.out.println("1. Show all kamar");
            System.out.println("2. Choose kamar");
            System.out.println("3. Add kamar");
            System.out.println("0. Back");
            System.out.println();
            try {
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showAllKamar();
                        break;
                    case "2":
                        chosingKamar();
                        break;
                    case "3":
                        addKamar();
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

    private void showAllKamar() {
        InputUtilities.cls();
        System.out.println("SHOW ALL KAMAR");
        kamarTableHeader();
        viewAllDataKamar(masterKamarVM.getListKamar());
        System.out.println("================================================================================");

        InputUtilities.pressEnter();
    }

    private void chosingKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         CHOOSE KAMAR         ");
            System.out.println("==============================");
            System.out.print("No. kamar\t: ");
            String noKamar = InputUtilities.input.readLine();
            masterKamarVM.selectKamar(noKamar);
            System.out.println("==============================");
            InputUtilities.pressEnter();

            if (masterKamarVM.getSelectedKamar() != null) {
                System.out.println();
                formatMessageOutput("Kamar found");
                AppRouter.navigateTo(AppRouter.AppRoute.SUB_MASTER_DETAIL_KAMAR);
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void addKamar() {
        // kalau di ENTER (kosong) semua munculnya aneh
        // kapasitas dan harga error
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         ADD NEW KAMAR        ");
            System.out.println("==============================");

            System.out.print("No. Kamar\t: ");
            String noKamar = InputUtilities.input.readLine();

            System.out.print("Kapasitas\t: ");
            int kapasitas = Integer.parseInt(InputUtilities.input.readLine());


            System.out.print("Jenis\t\t: ");
            AppEnums.JenisKamar jenis = getJenisKamarFromInput();

            System.out.print("Harga\t\t: ");
            double harga = Double.parseDouble(InputUtilities.input.readLine());

            System.out.print("Status\t\t: ");
            AppEnums.StatusKamar status = getStatusKamarFromInput();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                formatMessageOutput("Adding kamar");

                masterKamarVM.addNewKamar(noKamar, kapasitas, jenis, harga, status);
            } else {
                formatMessageOutput("Process cancelled");
            }

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (Exception e) {
            formatMessageOutput("Error: " + e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    public void showDetailKamarMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.SUB_MASTER_DETAIL_KAMAR) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("       SHOW DETAIL KAMAR      ");
            System.out.println("==============================");
            System.out.println("Selected kamar : " + masterKamarVM.getSelectedKamar().getNoKamar());
            System.out.println();
            System.out.println("1. Show detail");
            System.out.println("2. Edit kamar");
            System.out.println("3. Delete kamar");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        detailMasterKamar();
                        break;
                    case "2":
                        editMasterKamar();
                        break;
                    case "3":
                        System.out.println("DELETE KAMAR");
                        deleteMasterKamar();
                        break;
                    case "0":

                        masterKamarVM.clearSelectedKamar();
                        AppRouter.navigateTo(MASTER_KAMAR); //
                        break;
                    default:
                        invalidChoice();
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void detailMasterKamar() {
        InputUtilities.cls();
        System.out.println("SHOW DETAIL");
        kamarTableHeader();
        viewDataSelectedKamar(masterKamarVM.getSelectedKamar());
        System.out.println("================================================================================");

        InputUtilities.pressEnter();
    }

    private void editMasterKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("          EDIT KAMAR          ");
            System.out.println("==============================");
            System.out.println("Kosongi jika tidak ingin mengubah data tertentu");
            System.out.println();

            System.out.print("No Kamar\t: ");
            String noKamar = InputUtilities.input.readLine();

            System.out.print("Kapasitas\t: ");
            String kapasitas = InputUtilities.input.readLine();

            System.out.print("Jenis\t\t: ");
            AppEnums.JenisKamar jenisKamar = getJenisKamarFromInput();

            System.out.print("Harga\t\t: ");
            String harga = InputUtilities.input.readLine();

            System.out.print("Status kamar\t: ");
            AppEnums.StatusKamar statusKamar = getStatusKamarFromInput();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterKamarVM.editKamar(
                        noKamar,
                        kapasitas.isBlank() ? 0 : Integer.parseInt(kapasitas),
                        jenisKamar,
                        harga.isBlank() ? 0 : Double.parseDouble(harga),
                        statusKamar
                );
                formatMessageOutput("Kamar editted");
                System.out.println("==============================");
//                InputUtilities.pressEnter();
//                AppRouter.navigateTo(MASTER_KAMAR);
            } else {
                formatMessageOutput("Process cancelled");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void deleteMasterKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         DELETE KAMAR         ");
            System.out.println("==============================");
            System.out.println("Selected kamar : " + masterKamarVM.getSelectedKamar().getNoKamar());
            System.out.println();
            System.out.print("Anda yakin ingin menghapus kamar ini?(y/n): ");
            inputUser = InputUtilities.input.readLine();
            if (inputUser.equalsIgnoreCase("y")) {
                masterKamarVM.deleteKamar(masterKamarVM.getSelectedKamar().getNoKamar());
                formatMessageOutput("Kamar deleted");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_KAMAR);
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


}
