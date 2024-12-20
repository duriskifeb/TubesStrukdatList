package Data.DataSource;

import Data.Model.ReportModel;

import java.util.ArrayList;

public class ReportDataSource {

    private ArrayList<ReportModel> listReports = new ArrayList<>();

    public ArrayList<ReportModel> getListReports() {
        return listReports;
    }

    public void setListReports(ArrayList<ReportModel> listReports) {
        this.listReports = listReports;
    }

    public ReportModel getReport(String noReport){
        return listReports.stream().filter(
                cekReport -> cekReport.getReportNumber().equals(noReport)
        ).findFirst().orElse(null);
    }

    public void addNewReport(ReportModel report){
        this.listReports.add(report);
    }

    public void removeReport(ReportModel report){
        this.listReports.remove(report);
    }

    public void editReport(int index, ReportModel report){
        this.listReports.set(index, report);
    }
}
