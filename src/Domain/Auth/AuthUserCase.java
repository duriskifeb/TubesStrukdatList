package Domain.Auth;

import Data.DataSource.PegawaiDataSource;
import Data.Model.User;
import Util.Encryption;

import static Util.Formatting.formatMessageOutput;

public class AuthUserCase {
    final PegawaiDataSource dataSource;

    public AuthUserCase(PegawaiDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User loggedUser;

    public void doLogin(String userId, String password) {
        String hashedPass = Encryption.hashPassword(password);
        loggedUser = dataSource.authenticateUser(userId, hashedPass);
        if (loggedUser != null) {
            formatMessageOutput("Login Success");
        } else {
            formatMessageOutput("Login Failed Account Not Found");
        }
    }

    public void doLogout() {
        loggedUser = null;
    }

}
