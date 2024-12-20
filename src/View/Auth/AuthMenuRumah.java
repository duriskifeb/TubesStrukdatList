package View.Auth;

import Util.InputUtilities;
import ViewModel.AuthViewModel.AuthViewModel;
import View.AppRouter;
import View.AppRouterRumah;

import java.io.IOException;

import static Util.Formatting.invalidChoice;
import static View.AppRouterRumah.AppRouteRumah.LOGIN;
import static View.Components.MainView.appHeader;

public class AuthMenuRumah {
    private final AuthViewModel authViewModel;

    public AuthMenuRumah(AuthViewModel authViewModel) {
        this.authViewModel = authViewModel;
    }

    String inputUser;

    public void showLogin() {
        while (AppRouterRumah.activeRoute == LOGIN) {
            InputUtilities.cls();
            appHeader();
            System.out.println("1. Login");
            System.out.println("0. EXIT");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showLoginInputs();
                        break;
                    case "0":
                        AppRouter.navigateTo(AppRouter.AppRoute.EXIT);
                        break;
                    default:
                        invalidChoice();
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    // misal sudah login dan berhasil terus logout, kalau login lagi dengan input
    // yang salah, malah berhasil login dengan akun sebelumnya (Tanpa keluar
    // aplikasi)
    private void showLoginInputs() {
        InputUtilities.cls(); // clear terminal
        System.out.println("==============================");
        System.out.println("          LOGIN MENU Rumah         ");
        System.out.println("==============================");
        try {
            System.out.print("Masukkan ID atau Email\t: ");
            String userIDEmail = InputUtilities.input.readLine();
            System.out.print("Masukkan Password\t: ");
            String userPass = InputUtilities.input.readLine();

            this.authViewModel.doLogin(userIDEmail, userPass);
            if (this.authViewModel.loggedUser != null) {
                switch (this.authViewModel.loggedUser.getRole()) {
                    case MANAGER:
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_MAIN_MENU);
                        break;
                    case PEGAWAI:
                        AppRouter.navigateTo(AppRouter.AppRoute.MAIN_MENU);
                        break;
                    default:
                        System.out.println("HAH!?");
                        break;
                }
            }
        } catch (IOException e) {
            invalidChoice();
        }
        System.out.println("==============================");
        InputUtilities.pressEnter();
    }
}
