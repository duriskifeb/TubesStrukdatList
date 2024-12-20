package Domain.Master;

import Data.DataSource.ReportDataSource;
import Data.Model.ReportModel;

import java.util.ArrayList;

import static Util.Formatting.formatMessageOutput;

public class MasterReporting {
    final ReportDataSource dataSource;

    public MasterReporting(ReportDataSource dataSource) {
        this.dataSource = dataSource;
    }

    //    public ReportModel selectedReport;
    public ArrayList<ReportModel> getAllReport() {
        return dataSource.getListReports();
    }

    public void addReport(ReportModel report) {
        // check if data already exists
        if (!cekReport(report.getReportNumber())) {
            dataSource.addNewReport(report);
        } else {
            formatMessageOutput("Data laporan Sudah Ada");
        }
    }

    public void deleteReport(String reportNumber) {
        if (cekReport(reportNumber)) {
            ReportModel report = dataSource.getReport(reportNumber);
            dataSource.removeReport(report);
            formatMessageOutput("Data Dihapus");
        } else {
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public void editDataReport(ReportModel oldDData, ReportModel newDData) {
        // find the data's index
        if (cekReport(oldDData.getReportNumber())) {
            int index = dataSource.getListReports().indexOf(oldDData);
            dataSource.editReport(index, newDData);
        } else {
            // data not found
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public ReportModel selectReport(String reportNumber) {
        return dataSource.getReport(reportNumber);
    }

    private boolean cekReport(String reportNumber) {
        ReportModel cek = dataSource.getListReports().stream().filter(
                cekKamar -> cekKamar.getReportNumber().equals(reportNumber)
        ).findFirst().orElse(null);
        return cek != null; // return data is Found when cek is not nul
    }
}
