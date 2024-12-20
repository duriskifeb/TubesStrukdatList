package View;

import java.util.LinkedList;

import static Util.Formatting.formatMessageOutput;

public class AppRouter {

    public static void navigateTo(AppRoute target) {
        activeRoute = target;
    }

    public static AppRoute activeRoute;

    public enum AppRoute {
        // GLOBAL
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

    };
}
