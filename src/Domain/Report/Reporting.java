package Domain.Report;

import Data.DataSource.*;
import Data.Model.ReportModel;
import Data.Model.Transaksi;
import Data.Model.User;
import Util.Formatting;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Reporting {


    final PegawaiDataSource pegawaiDataSource;
    final TransaksiDataSource transaksiDataSource;
    final ReportDataSource reportDataSource;

    public Reporting(
            PegawaiDataSource pegawaiDataSource,
            TransaksiDataSource transaksiDataSource,
            ReportDataSource reportDataSource
    ) {

        this.pegawaiDataSource = pegawaiDataSource;
        this.transaksiDataSource = transaksiDataSource;
        this.reportDataSource = reportDataSource;
    }

    //logic to create, and handle the Reporting Model
    ReportModel generatedReport;
    public ReportModel getGeneratedReport(){
        return generatedReport;
    }
    public void generateReport(Date rangeStart, Date rangeEnd, String picID) {
        Formatting.formatMessageOutput("Generating Report");

        Date dateCreated = new Date();
        User pic = pegawaiDataSource.getPegawai(picID);
        ArrayList<Transaksi> listTransaksi = transaksiDataSource.getListTransaksi().stream().filter(
                transaksi -> transaksi.getTanggalTransaksi().after(rangeStart) && transaksi.getTanggalTransaksi().before(rangeEnd)
        ).collect(Collectors.toCollection(ArrayList::new));

        if(pic != null){
            generatedReport = new ReportModel(
                    dateCreated,
                    listTransaksi,
                    pic,
                    rangeStart,
                    rangeEnd
            );
            Formatting.formatMessageOutput("Report Generated");
        }else{
            Formatting.formatMessageOutput("Data PIC tidak ditemukan");
        }
    }
    public void generateReport(String reportNumber , Date rangeStart, Date rangeEnd, String picID, Date dateCreated) {
        Formatting.formatMessageOutput("Generating Report");

        User pic = pegawaiDataSource.getPegawai(picID);
        ArrayList<Transaksi> listTransaksi = rangeEnd != null  || rangeStart != null ? transaksiDataSource.getListTransaksi().stream().filter(
                transaksi -> transaksi.getTanggalTransaksi().after(rangeStart) && transaksi.getTanggalTransaksi().before(rangeEnd)
        ).collect(Collectors.toCollection(ArrayList::new)) : reportDataSource.getReport(reportNumber).getListTransaksi();

        if(pic != null){
            generatedReport = new ReportModel(
                    reportNumber,
                    dateCreated,
                    new Date(),
                    listTransaksi,
                    pic,
                    rangeStart,
                    rangeEnd
            );

            Formatting.formatMessageOutput("Report Generated");
        }else{
            Formatting.formatMessageOutput("Data PIC tidak ditemukan");
        }

    }
    public void saveReport(){
        Formatting.formatMessageOutput("Saving Report");
        if(this.generatedReport != null){
            ReportModel cekReport = reportDataSource.getReport(this.generatedReport.getReportNumber());
            if(cekReport != null){
                int index = reportDataSource.getListReports().indexOf(cekReport);
                reportDataSource.editReport(index, this.generatedReport);
            }else {
                reportDataSource.addNewReport(this.generatedReport);
            }
            Formatting.formatMessageOutput("Report saved");
        }else {
            Formatting.formatMessageOutput("No generated report found");
        }
    }

}
