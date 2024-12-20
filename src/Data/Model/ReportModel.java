package Data.Model;

import Util.Formatting;
import Util.Generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;


public class ReportModel {
    private Date rangeEnd;
    private  Date rangeStart;
    private String reportNumber;
    private Date dateCreated, dateUpdated;
    private ArrayList<Transaksi> listTransaksi;
    private User reportPIC;

    public ReportModel(
            String reportNumber,
            Date dateCreated,
            Date dateUpdated,
            ArrayList<Transaksi> listTransaksi,
            User reportPIC,
            Date rangeStart,
            Date rangeEnd
    ) {
        this.reportNumber = reportNumber;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.listTransaksi = listTransaksi;
        this.reportPIC = reportPIC;

        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public ReportModel(
            Date dateCreated,
            ArrayList<Transaksi> listTransaksi,
            User reportPIC,
            Date rangeStart,
            Date rangeEnd
    ) {
        String totalPrice = String.valueOf(
          listTransaksi.stream().mapToDouble(Transaksi::getTotal).sum()
        );
        String transaksiNumberSum = listTransaksi.stream().map(Transaksi::getNoTransaksi).collect(Collectors.joining("-"));

        this.reportNumber = Generator.generateReportNumber(
                reportPIC.getNama(),
                Formatting.formatDate(dateCreated),
                totalPrice,
                transaksiNumberSum
                );
        this.dateCreated = dateCreated;
        this.listTransaksi = listTransaksi;
        this.reportPIC = reportPIC;

        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ArrayList<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(ArrayList<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public User getReportPIC() {
        return reportPIC;
    }

    public void setReportPIC(User reportPIC) {
        this.reportPIC = reportPIC;
    }

    public Date getRangeEnd() {
        return this.rangeEnd;
    }

    public void setRangeEnd(Date rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public Date getRangeStart() {

        return this.rangeStart;
    }

    public void setRangeStart(Date rangeStart) {
        this.rangeStart = rangeStart;
    }
}
