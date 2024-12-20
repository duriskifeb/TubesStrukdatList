package ViewModel.ReportViewModel;

import Data.Model.ReportModel;
import Domain.Report.Reporting;

import java.util.Date;

public class ReportingViewModel {
    final Reporting reporting;

    public ReportingViewModel(Reporting reporting) {
        this.reporting = reporting;
    }

    public ReportModel getGeneratedReport() {
        return reporting.getGeneratedReport();
    }

    public void generateReport(Date rangeStart, Date rangeEnd, String picID) {
        reporting.generateReport(rangeStart, rangeEnd, picID);
    }
    public void generateReport(String reportNumber , Date rangeStart, Date rangeEnd, String picID, Date dateCreated) {
        reporting.generateReport(reportNumber, rangeStart, rangeEnd, picID, dateCreated);

    }
    public void saveReport(){
        reporting.saveReport();
    }

}
