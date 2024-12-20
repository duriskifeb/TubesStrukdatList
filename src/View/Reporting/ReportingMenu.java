package View.Reporting;

import Util.InputUtilities;
import View.AppRouter;
import ViewModel.AuthViewModel.AuthViewModel;
import ViewModel.ReportViewModel.ReportingViewModel;

import java.io.IOException;
import java.util.Date;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.MAIN_MENU;
import static View.AppRouter.activeRoute;
import static View.Components.ReportingView.viewDetailSelectedReport;

public class ReportingMenu {
    private final ReportingViewModel reportingVM;
    private final AuthViewModel authViewModel;

    public ReportingMenu(ReportingViewModel reportingVM, AuthViewModel authViewModel) {
        this.reportingVM = reportingVM;
        this.authViewModel = authViewModel;
    }

    private String inputUser;
    public void showReportingMenu() {

//        # Laporan #
//- Buat  (generate) Laporan
//- Lihat generated laporan
//- Simpan Laporan
        while (activeRoute == AppRouter.AppRoute.REPORTING){

            System.out.println();
            System.out.println("============================");
            System.out.println("Menu Reporting Pegawai");
            System.out.println("============================");
            System.out.println("1. Generate Laporan");
            System.out.println("2. Lihat Generated Laporan");
            System.out.println("3. Simpan Laporan");
            System.out.println("0. Kembali");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        System.out.println();
                        if(reportingVM.getGeneratedReport() != null) {
                            System.out.println("Ada data yang sudah di generate");
                            System.out.print("Apakah anda ingin generate ulang? (y/n): ");
                            inputUser = InputUtilities.input.readLine();
                            if (inputUser.equalsIgnoreCase("n")){
                                break;
                            }
                        }
                        generateLaporan();
                        System.out.println();
                        break;
                    case "2":
                        System.out.println();
                        lihatLaporan();
                        System.out.println();
                        break;
                    case "3":
                        System.out.println();
                        simpanLaporan();
                        System.out.println();
                        break;
                    case "0":

                        AppRouter.navigateTo(MAIN_MENU);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (IOException e) {
                invalidChoice();
            }

        }
    }

    private void generateLaporan() {

        System.out.println("Generating report...");

        System.out.print("Masukkan Range Tanggal Awal (dd-MM-yyyy) : ");
        Date dateStart = InputUtilities.getDateFromInput();

        System.out.print("Masukkan Range Tanggal Akhir (dd-MM-yyyy) : ");
        Date dateEnd = InputUtilities.getDateFromInput();

        if(dateStart != null && dateEnd != null){
            reportingVM.generateReport(dateStart, dateEnd, authViewModel.loggedUser.getUserID());
        }else {
            formatMessageOutput("Invalid Date");
        }

    }

    private void lihatLaporan() {
        viewDetailSelectedReport(reportingVM.getGeneratedReport());
    }

    private void simpanLaporan() {
        reportingVM.saveReport();

    }

}
