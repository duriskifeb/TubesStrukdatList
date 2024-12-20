package View.Auth;

import Data.Model.User;
import Domain.Auth.AuthUserCase;
import Util.Formatting;

public class AuthViewModelRumah {
    final AuthUserCase authUseCase;

    public AuthViewModelRumah(AuthUserCase userCase) {
        this.authUseCase = userCase;
    }

    public User loggedUser;

    public void doLogin(
            String username, // bisa id bisa email
            String password) {
        authUseCase.doLogin(username, password);
        if (authUseCase.loggedUser != null) {
            this.loggedUser = authUseCase.loggedUser;
        } else {
            Formatting.formatMessageOutput("User Not Found");
        }
    }

    public void doLogout() {
        authUseCase.doLogout();
        this.loggedUser = null;
    }

    public void changePassword(String userID, String oldPassword, String newPassword) {
        // TODO kalau sempat
    }
}
