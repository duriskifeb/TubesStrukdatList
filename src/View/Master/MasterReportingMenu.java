package View.Master;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static Util.InputUtilities.pressEnter;
import static View.AppRouter.navigateTo;
import static View.AppRouter.AppRoute.MASTER_MAIN_MENU;
import static View.AppRouter.AppRoute.MASTER_REPORTING;
import static View.AppRouter.AppRoute.SUB_MASTER_DETAIL_REPORTING;
import static View.Components.ReportingView.*;
// import static View.AppRouter.AppRoute.MASTER_MAIN_MENU;

import java.io.IOException;
import java.util.Date;

import Util.InputUtilities;
import View.AppRouter;
import ViewModel.AuthViewModel.AuthViewModel;
import ViewModel.MasterViewModel.MasterReportingViewModel;

public class MasterReportingMenu {
    private final MasterReportingViewModel masterReportingVM;
    private final AuthViewModel authViewModel;

    public MasterReportingMenu(MasterReportingViewModel masterReportingVM, AuthViewModel authViewModel) {
        this.masterReportingVM = masterReportingVM;
        this.authViewModel = authViewModel;
    }

    String inputUser;

    public void showMasterReportingMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.MASTER_REPORTING) {
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("      MASTER REPORTING      ");
            System.out.println("============================");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Buat Laporan");
            System.out.println("3. Lihat Generated Laporan");
            System.out.println("4. Simpan Laporan");
            System.out.println("5. Pilih Laporan");
            System.out.println("0. Kembali");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {

                inputUser = InputUtilities.input.readLine();
                System.out.println();
                switch (inputUser) {
                    case "1":
                        showAllReport();
                        break;
                    case "2":
                        InputUtilities.cls();
                        if (masterReportingVM.getGeneratedReport() != null) {
                            System.out.println("Ada data yang sudah di generate");
                            System.out.print("Apakah anda ingin generate ulang? (y/n): ");
                            inputUser = InputUtilities.input.readLine();
                            if (inputUser.equalsIgnoreCase("n")) {
                                break;
                            }
                        }
                        generateLaporan();
                        break;
                    case "3":
                        InputUtilities.cls();
                        lihatGeneratedLaporan();
                        InputUtilities.pressEnter();
                        break;
                    case "4":
                        System.out.println("==============================");
                        simpanLaporan();
                        InputUtilities.pressEnter();
                        break;
                    case "5":
                        pilihLaporan();
                        break;
                    case "0":
                        navigateTo(MASTER_MAIN_MENU);
                        // AppRouter.navigateUp();

                        break;
                    default:
                        formatMessageOutput("Invalid Choice");
                }
                System.out.println();
            } catch (IOException e) {
                invalidChoice();
            }

        }
    }

    private void showAllReport() {
        InputUtilities.cls();
        headerViewReporting();
        viewAllReports(masterReportingVM.getAllReport());
        InputUtilities.pressEnter();
    }

    private void pilihLaporan() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("         CHOOSE REPORT        ");
        System.out.println("==============================");

        System.out.println("Pilih Laporan");
        System.out.print("Nomor Laporan\t: ");
        try {
            String noLaporan = InputUtilities.input.readLine();
            masterReportingVM.selectReport(noLaporan);
            if (masterReportingVM.getSelectedReport() != null) {
                AppRouter.navigateTo(SUB_MASTER_DETAIL_REPORTING);
            }
        } catch (IOException e) {
            formatMessageOutput(e.getMessage());
        }
        InputUtilities.pressEnter();
    }

    private void generateLaporan() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("      GENERATE NEW REPORT     ");
        System.out.println("==============================");
        System.out.println("Format tanggal (DD-MM-YYYY)");
        System.out.print("Masukkan Range Tanggal Awal\t: ");
        Date dateStart = InputUtilities.getDateFromInput();

        System.out.print("Masukkan Range Tanggal Akhir\t: ");
        Date dateEnd = InputUtilities.getDateFromInput();

        if (dateStart != null && dateEnd != null) {
            masterReportingVM.generateNewReport(dateStart, dateEnd, authViewModel.loggedUser.getUserID());
        } else {
            formatMessageOutput("Invalid Date");
        }

        System.out.println("==============================");
        InputUtilities.pressEnter();

    }

    private void lihatGeneratedLaporan() {
        viewDetailSelectedReport(masterReportingVM.getGeneratedReport());
    }

    private void simpanLaporan() {
        masterReportingVM.saveNewGeneratedReport();

    }

    public void showDetailReportingMenu() {
        // delete and edit
        while (AppRouter.activeRoute == SUB_MASTER_DETAIL_REPORTING) {
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("      DETAIL REPORTING      ");
            System.out.println("============================");
            System.out.println("1. Lihat Detail");
            System.out.println("2. Edit Laporan");
            System.out.println("3. Hapus Laporan");
            System.out.println("0. Kembali");

            System.out.print("Masukkan Pilihan : ");
            try {

                inputUser = InputUtilities.input.readLine();
                System.out.println();
                switch (inputUser) {
                    case "1":
                        headerViewReporting();
                        viewDetailSelectedReport(masterReportingVM.getSelectedReport());
                        InputUtilities.pressEnter();
                        break;
                    case "2":
                        editLaporan();
                        break;

                    case "3":
                        hapusLaporan();
                        break;
                    case "0":
                        AppRouter.navigateTo(MASTER_REPORTING);
                        // AppRouter.navigateUp();
                        break;
                    default:
                        formatMessageOutput("Invalid Choice");
                }
                System.out.println();
            } catch (IOException e) {
                invalidChoice();
            }
        }

    }

    private void editLaporan() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("          EDIT REPORT         ");
        System.out.println("==============================");
        System.out.println("Kosongi input jika tidak ingin mengedit data");
        try {
            System.out.print("Tanggal range awal (dd-mm-yyyy) : ");
            Date rangeStart = InputUtilities.getDateFromInput();

            System.out.print("Tanggal range akhir (dd-mm-yyyy) : ");
            Date rangeEnd = InputUtilities.getDateFromInput();

            System.out.print("Pic ID : ");
            String picID = InputUtilities.input.readLine();

            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterReportingVM.editReport(rangeStart, rangeEnd, picID);
            }
        } catch (IOException e) {
            invalidChoice();
        }
        System.out.println("==============================");
        InputUtilities.pressEnter();
    }

    private void hapusLaporan() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("         DELETE REPORT        ");
        System.out.println("==============================");
        System.out.print("Apa anda yakin ingin menghapus laporan?(y/n): ");
        try {
            inputUser = InputUtilities.input.readLine();
            if (inputUser.equalsIgnoreCase("y")) {
                masterReportingVM.deleteReport(masterReportingVM.getSelectedReport().getReportNumber());
                if (masterReportingVM.getSelectedReport() == null) {
                    AppRouter.navigateTo(MASTER_REPORTING);
                    // AppRouter.navigateUp();
                }

            } else {
                System.out.println("Operasi Dibatalkan");
            }
        } catch (IOException e) {
            invalidChoice();
        }
    }
}
