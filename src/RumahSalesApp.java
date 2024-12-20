import static View.AppRouter.AppRoute.EXIT;
import static View.AppRouter.AppRoute.LOGIN;

import Util.Formatting;
import View.AppRouter;
import View.AppRouterRumah;
import View.AppRouterRumah.AppRouteRumah;
import View.Auth.AuthMenu;
import View.Auth.AuthMenuRumah;
import View.MainMenu.MainMenu;
import View.MainMenu.MasterMainMenu;
import View.Master.*;
import View.Pegawai.PegawaiCustomerMenu;
import View.Pegawai.PegawaiKamarMenu;
import View.Reporting.ReportingMenu;
import View.Transaksi.TransaksiMenu;

public class RumahSalesApp {

    // MENUS VIEWS
    AuthMenuRumah authMenuRumah;
    MainMenu mainMenu;

    // TODO handle exception

    public void run() {
        // application looping here
        while (true) {
            try {
                switch (AppRouterRumah.activeRoute) {
                    case LOGIN:
                        this.authMenuRumah.showLogin();
                        break;
                    case EXIT:
                        Formatting.formatMessageOutput("Terimakasih dan sampai jumpa kembali");
                        Formatting.formatMessageOutput("EXiting . . .");
                        System.exit(0);
                        break;
                    default:
                        Formatting.formatMessageOutput("Mohon maaf ada kesalahan, Nice Try : ( ");
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                Formatting.formatMessageOutput("Woops, something went wrong : " + e.getMessage());
                AppRouterRumah.navigateTo(AppRouterRumah.AppRouteRumah.EXIT);
            }
        }
    }
}
