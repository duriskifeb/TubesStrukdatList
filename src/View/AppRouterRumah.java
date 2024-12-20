package View;

import java.util.LinkedList;

import static Util.Formatting.formatMessageOutput;
import static View.AppRouter.AppRoute.EXIT;
import static View.AppRouter.AppRoute.LOGIN;

public class AppRouterRumah {

    public static void navigateTo(AppRouteRumah target) {
        activeRoute = target;
    }

    public static AppRouteRumah activeRoute;

    public enum AppRouteRumah {
        // this global
        LOGIN,
        EXIT,

        TRANSAKSI,
        REPORTING,
        SUB_TRANSAKSI,

        // PEGAWAI
        MAIN_MENU, // main menu pegawai,

        SUB_PEGAWAI_KAMAR,
        SUB_PEGAWAI_CUSTOMER,

        // MANAGER
        MASTER_MAIN_MENU, // main menu manager
        MASTER_PEGAWAI,
        MASTER_CUSTOMER,
        MASTER_KAMAR,
        SUB_MASTER_DETAIL_KAMAR,
        SUB_MASTER_DETAIL_PEGAWAI,
        SUB_MASTER_DETAIL_CUSTOMER,
        SUB_MASTER_DETAIL_REPORTING,
        MASTER_TRANSAKSI,
        MASTER_REPORTING,
        // SUB_MASTER_TRANSAKSI,

        // TODO LIST SOMETHING HERE
    }
}
