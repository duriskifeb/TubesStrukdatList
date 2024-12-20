import Util.Formatting;
import View.AppRouter;
import View.Auth.AuthMenu;
import View.MainMenu.MainMenu;
import View.MainMenu.MasterMainMenu;
import View.Master.*;
import View.Pegawai.PegawaiCustomerMenu;
import View.Pegawai.PegawaiKamarMenu;
import View.Reporting.ReportingMenu;
import View.Transaksi.TransaksiMenu;

public class HotelManagementApp {

    // MENUS VIEWS
    AuthMenu authMenu;
    MainMenu mainMenu;

    PegawaiCustomerMenu pegawaiCustomerMenu;
    PegawaiKamarMenu pegawaiKamarMenu;

    // masters menus
    MasterCustomerMenu masterCustomerMenu;
    MasterKamarMenu masterKamarMenu;
    MasterMainMenu masterMainMenu;
    MasterPegawaiMenu masterPegawaiMenu;
    MasterReportingMenu masterReportingMenu;
    MasterTransaksiMenu masterTransaksiMenu;
    // master menus

    TransaksiMenu transaksiMenu;
    ReportingMenu reportingMenu;

    // MENUS VIEWS
    HotelManagementApp() {
        // init the application here
        onCreate();
        onViewCreated();
    }

    private void onCreate() {
        this.authMenu = new AuthMenu(DI.authViewModel);

        this.mainMenu = new MainMenu(DI.authViewModel);
        this.masterMainMenu = new MasterMainMenu(DI.authViewModel);

        this.pegawaiCustomerMenu = new PegawaiCustomerMenu(DI.masterCustomerVM);
        this.pegawaiKamarMenu = new PegawaiKamarMenu(DI.masterKamarVM);

        this.masterCustomerMenu = new MasterCustomerMenu(DI.masterCustomerVM);
        this.masterKamarMenu = new MasterKamarMenu(DI.masterKamarVM);
        this.masterPegawaiMenu = new MasterPegawaiMenu(DI.masterPegawaiVM);
        this.masterReportingMenu = new MasterReportingMenu(DI.masterReportingVM, DI.authViewModel);
        this.masterTransaksiMenu = new MasterTransaksiMenu(
                DI.masterTransaksiVM, DI.masterCustomerVM, DI.masterKamarVM, DI.authViewModel, DI.transaksiVM);

        this.transaksiMenu = new TransaksiMenu(DI.transaksiVM, DI.masterKamarVM, DI.authViewModel, DI.masterCustomerVM);
        this.reportingMenu = new ReportingMenu(DI.reportingVM, DI.authViewModel);
    }

    private void onViewCreated() {
        // initial route move to Login
        AppRouter.navigateTo(AppRouter.AppRoute.LOGIN);
    }

    public void run() {
        // app loop
        while (true) {
            try {
                switch (AppRouter.activeRoute) {
                    case LOGIN:
                        this.authMenu.showLogin();
                        break;

                    case MAIN_MENU:
                        this.mainMenu.showMainMenu();
                        break;

                    case SUB_PEGAWAI_CUSTOMER:
                        this.pegawaiCustomerMenu.showMenuPelanggan();
                        break;

                    case SUB_PEGAWAI_KAMAR:
                        this.pegawaiKamarMenu.showMenuKamar();
                        break;

                    case SUB_TRANSAKSI:
                        this.transaksiMenu.subTransaksiMenu();
                        break;

                    case MASTER_MAIN_MENU:
                        this.masterMainMenu.showMasterMainMenu();
                        break;

                    case MASTER_KAMAR:
                        this.masterKamarMenu.showMasterKamarMenu();
                        break;

                    case SUB_MASTER_DETAIL_KAMAR:
                        this.masterKamarMenu.showDetailKamarMenu();
                        break;

                    case MASTER_PEGAWAI:
                        this.masterPegawaiMenu.showMasterPegawaiMenu();
                        break;

                    case SUB_MASTER_DETAIL_PEGAWAI:
                        this.masterPegawaiMenu.showDetailPegawaiMenu();
                        break;

                    case MASTER_CUSTOMER:
                        this.masterCustomerMenu.showMasterCustomerMenu();
                        break;

                    case SUB_MASTER_DETAIL_CUSTOMER:
                        this.masterCustomerMenu.showDetailCustomerMenu();
                        break;
                    case SUB_MASTER_DETAIL_REPORTING:
                        this.masterReportingMenu.showDetailReportingMenu();
                        break;
                    case MASTER_REPORTING:
                        this.masterReportingMenu.showMasterReportingMenu();
                        break;

                    case MASTER_TRANSAKSI:
                        this.masterTransaksiMenu.showMasterTransaksiMenu();
                        break;

                    case TRANSAKSI:
                        this.transaksiMenu.showTransaksiMenu();
                        break;

                    case REPORTING:
                        this.reportingMenu.showReportingMenu();
                        break;

                    case EXIT:
                        Formatting.formatMessageOutput("Terimakasih telah menggunakan aplikasi kami");
                        Formatting.formatMessageOutput("Exiting");
                        System.exit(0);
                        break;
                    default:
                        Formatting.formatMessageOutput("Invalid Route");
                        break;
                }
            } catch (Exception e) {
                // in case something goes wroong, like the user press CTRL+ c
                Formatting.formatMessageOutput("Woops, something went wrong : " + e.getMessage());
                AppRouter.navigateTo(AppRouter.AppRoute.EXIT);
            }

        }
    }

}
