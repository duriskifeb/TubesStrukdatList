package View.Components;

import Data.AppEnums.AppEnums;
import Data.Model.ReportModel;
import Data.Model.Transaksi;
import Util.Formatting;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static Data.AppEnums.AppEnums.StatusTransaksi.*;
import static Util.Formatting.formatMessageOutput;
import static View.Components.TransaksiView.viewAllTransaksi;

public class ReportingView {

    public static void headerViewReporting(){
        System.out.println("========================================================================================");
        System.out.format("%20s %25s %20s %20s\n", "No Report", "PIC ID", "Date Created", "Date Updated");
        System.out.println("========================================================================================");
    }
    public static void viewAllReports(ArrayList<ReportModel> listReport){
        listReport
                .stream()
                .iterator()
                .forEachRemaining(
                        selectedReport -> {

                            String noReport = selectedReport.getReportNumber();
                            String user = selectedReport.getReportPIC().getUserID();

                            String dateCreated = Formatting.formatDate(selectedReport.getDateCreated());
                            String dateUpdated = Formatting.formatDate(selectedReport.getDateUpdated());
                            System.out.format("%20s %25s \t%20s \t%20s\n", noReport, user, dateCreated, dateUpdated);

                        }
                );
    }
    public static void viewDetailSelectedReport(ReportModel selectedReport){
        if(selectedReport != null){
            // summaries
            Double totalValue = selectedReport.getListTransaksi().stream().mapToDouble(Transaksi::getTotal).sum();

            ArrayList<AppEnums.StatusTransaksi> listStatus = selectedReport
                    .getListTransaksi()
                    .stream()
                    .map(Transaksi::getStatusTransaksi)
                    .collect(Collectors.toCollection(ArrayList::new));
            int successfullTransaksi = (int) listStatus.stream().filter(statusTransaksi -> statusTransaksi == DONE).count();
            int failedTransaksi = (int) listStatus.stream().filter(statusTransaksi -> statusTransaksi == FAILED).count();
            int cancelledTransaksi = (int) listStatus.stream().filter(statusTransaksi -> statusTransaksi == CANCELLED).count();



            System.out.println("========================================= DETAIL LAPORAN ============================================");
            System.out.println("No Report: " + selectedReport.getReportNumber());
            System.out.println("PIC : " +  selectedReport.getReportPIC().getNama() + "( " + selectedReport.getReportPIC().getUserID() + " )");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("Start : " + Formatting.formatDate(selectedReport.getRangeStart()));
            System.out.println("End : " + Formatting.formatDate(selectedReport.getRangeEnd()));
            System.out.println("-----------------------------------------------------------------------------------------------------");

            System.out.println("========================================= DETAIL TRANSAKSI ==========================================");
            System.out.println();
            viewAllTransaksi(selectedReport.getListTransaksi());
            System.out.println();
            System.out.println("=========================================     SUMMARY      ==========================================");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("Successfull Transaksi : " + successfullTransaksi);
            System.out.println("Failed Transaksi : " + failedTransaksi);
            System.out.println("Cancelled Transaksi : " + cancelledTransaksi);
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("=========================================     SUMMARY      ==========================================");

        }
    }
}
