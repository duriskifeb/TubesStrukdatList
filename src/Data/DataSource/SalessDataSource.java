package Data.DataSource;

import Data.Model.User;
import Data.Model.UserRumah;
import Data.AppEnums.AppRumahEnums;
import Util.Encryption;

import java.util.ArrayList;
import java.util.Arrays;

public class SalessDataSource {

    private ArrayList<UserRumah> listRumah = new ArrayList<>(
            Arrays.asList(
                    new UserRumah(
                            "HA01",
                            "adminSales@gmail.com",
                            "sales",
                            AppRumahEnums.userRole.ADMIN,
                            Encryption.hashPassword("rumahadi")

                    ),
                    new UserRumah(
                            "HA02",
                            "userSales@gmail.com",
                            "userSales",
                            AppRumahEnums.userRole.USER,
                            Encryption.hashPassword("dansiaphuni"))));

    public void addNewSales(UserRumah csRumah) {
        listRumah.add(csRumah);
    }

    public void deleteNewSales(UserRumah csRumah) {
        listRumah.remove(csRumah);
    }

    public ArrayList<UserRumah> getListRumah() {
        return listRumah;
    }

    public void editNewSales(int index, UserRumah csRumah) {
        listRumah.set(index, csRumah);
    }

    public void setNewSales(UserRumah csRumah) {
        this.listRumah = listRumah;
    }

    public UserRumah getNewSales(String ID) {
        return listRumah.stream().filter(
                cekUser -> cekUser.getUserID().equals(ID)).findFirst().orElse(null);
    }

    public UserRumah authenticateUser(String emailOrID, String password) {
        return listRumah.stream().filter(
                cekUser -> (cekUser.getEmail().equals(emailOrID) || cekUser.getUserID().equals(emailOrID)) &&
                        cekUser.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}