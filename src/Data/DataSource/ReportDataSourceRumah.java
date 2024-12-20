package Data.DataSource;

import Data.Model.ReportModelRumah;

import java.util.ArrayList;

public class ReportDataSourceRumah {

    private ArrayList<ReportModelRumah> listReports = new ArrayList<>();

    public ArrayList<ReportModelRumah> getListReports() {
        return listReports;
    }

    public void setListReports(ArrayList<ReportModelRumah> listReports) {
        this.listReports = listReports;
    }

    public ReportModelRumah getReport(String noReport) {
        return listReports.stream().filter(
                cekReport -> cekReport.getReportNumber().equals(noReport)).findFirst().orElse(null);
    }

    public void addNewReport(ReportModelRumah report) {
        this.listReports.add(report);
    }

    public void removeReport(ReportModelRumah report) {
        this.listReports.remove(report);
    }

    public void editReport(int index, ReportModelRumah report) {
        this.listReports.set(index, report);
    }
}
