import Data.DataSource.*;
import Domain.Auth.AuthUserCase;
import Domain.Master.*;
import Domain.Report.Reporting;
import Domain.Transaksi.TransaksiUseCase;
import ViewModel.AuthViewModel.AuthViewModel;
import ViewModel.MasterViewModel.*;
import ViewModel.ReportViewModel.ReportingViewModel;
import ViewModel.TransaksiViewModel.TransaksiViewModel;

import static Util.Formatting.formatMessageOutput;

public class DI {

    // Datasources
    public static PegawaiDataSource pegawaiDataSource;
    public static CustomerDataSource customerDataSource;
    public static KamarDataSource kamarDataSource;
    public static TransaksiDataSource transaksiDataSource;
    public static ReportDataSource reportDataSource;

    //Domains
    public static AuthUserCase authUserCase;
    public static MasterPegawai masterPegawai;
    public static MasterKamar masterKamar;
    public static MasterTransaksi masterTransaksi;
    public static MasterReporting masterReporting;
    public static MasterCustomer masterCustomer;
    public static Reporting reporting;
    public static TransaksiUseCase transaksiUseCase;

    // ViewModel
    public static AuthViewModel authViewModel;
    public static MasterKamarViewModel masterKamarVM;
    public static MasterPegawaiViewModel masterPegawaiVM;
    public static MasterTransaksiViewModel masterTransaksiVM;
    public static MasterReportingViewModel masterReportingVM;
    public static MasterCustomerViewModel masterCustomerVM ;
    public static ReportingViewModel reportingVM;
    public static TransaksiViewModel transaksiVM;

    public static void init() {
        formatMessageOutput("Initializing Dependency Injection (Don't you dare to interrupt)");
        // Datasources
        pegawaiDataSource = new PegawaiDataSource();
        customerDataSource = new CustomerDataSource();
        kamarDataSource = new KamarDataSource();
        transaksiDataSource = new TransaksiDataSource();
        reportDataSource = new ReportDataSource();

        //Domains usecase
        authUserCase = new AuthUserCase(DI.pegawaiDataSource);
        masterPegawai = new MasterPegawai(DI.pegawaiDataSource);
        masterKamar = new MasterKamar(DI.kamarDataSource);
        masterTransaksi = new MasterTransaksi(DI.transaksiDataSource);
        masterReporting = new MasterReporting(DI.reportDataSource);
        masterCustomer = new MasterCustomer(DI.customerDataSource);
        reporting = new Reporting(
                DI.pegawaiDataSource,
                DI.transaksiDataSource,
                DI.reportDataSource
        );
        transaksiUseCase = new TransaksiUseCase(
                DI.transaksiDataSource,
                DI.customerDataSource,
                DI.kamarDataSource,
                DI.pegawaiDataSource
        );

        // ViewModel
        authViewModel = new AuthViewModel(DI.authUserCase);
        masterKamarVM = new MasterKamarViewModel(DI.masterKamar);
        masterPegawaiVM = new MasterPegawaiViewModel(DI.masterPegawai);
        masterTransaksiVM = new MasterTransaksiViewModel(DI.masterTransaksi, DI.transaksiUseCase);
        masterReportingVM = new MasterReportingViewModel(DI.masterReporting, DI.reporting);
        masterCustomerVM = new MasterCustomerViewModel(DI.masterCustomer);
        reportingVM = new ReportingViewModel(DI.reporting);
        transaksiVM = new TransaksiViewModel(DI.transaksiUseCase);

        formatMessageOutput("Dependencies are ready and loaded");
    }


}
