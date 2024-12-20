package View.MainMenu;

import Util.InputUtilities;
import View.AppRouter;
import ViewModel.AuthViewModel.AuthViewModel;

import java.io.IOException;

import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.*;

public class MasterMainMenu {
    private final AuthViewModel authViewModel;

    public MasterMainMenu(AuthViewModel authViewModel) {
        this.authViewModel = authViewModel;
    }

    String inputUser;

    public void showMasterMainMenu() {
        while (AppRouter.activeRoute == MASTER_MAIN_MENU) {
//            System.out.println(AppRouter.activeRoute);
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         MENU MANAJER         ");
            System.out.println("==============================");
            System.out.println("Selamat Datang : " + authViewModel.loggedUser.getUserID() + " - " + authViewModel.loggedUser.getNama());
            System.out.println();
            System.out.println("1. Master Kamar");
            System.out.println("2. Master Pegawai");
            System.out.println("3. Master Customer"); 
            System.out.println("4. Transaksi"); 
            System.out.println("5. Reporting"); // belum, Minggu selesai Amiin . . .
            System.out.println("0. Logout");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        AppRouter.navigateTo(MASTER_KAMAR);
                        break;
                    case "2":
                        AppRouter.navigateTo(MASTER_PEGAWAI);
                        break;
                    case "3":
                        AppRouter.navigateTo(MASTER_CUSTOMER);
                        break;
                    case "4":
                        AppRouter.navigateTo(MASTER_TRANSAKSI);
                        break;
                    case "5":
                        AppRouter.navigateTo(MASTER_REPORTING);
                        break;
                    case "0":
                        authViewModel.doLogout();
                        AppRouter.navigateTo(LOGIN);
                        break;
                    default:
                        invalidChoice();
                    }
                } catch (IOException e) {
                System.out.println("invalid input");
//                    throw new RuntimeException(e);
                }
                
        }
    }

    // TODO @David add main menu master to access the all submenus

}
