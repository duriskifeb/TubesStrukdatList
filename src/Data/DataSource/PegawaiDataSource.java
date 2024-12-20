package Data.DataSource;

import Data.AppEnums.AppEnums;
import Data.Model.User;
import Util.Encryption;

import java.util.ArrayList;
import java.util.Arrays;

public class PegawaiDataSource {
    private ArrayList<User> listPegawai = new ArrayList<>(
            Arrays.asList(
                    new User(
                            "ADM01",
                            "admin@gmail.com",
                            "ayam",
                            AppEnums.UserRole.MANAGER,
                            Encryption.hashPassword("ayamgoyeng")
                    ),
                    new User(
                            "ADM02",
                            "admindua@gmail.com",
                            "bebek",
                            AppEnums.UserRole.PEGAWAI,
                            Encryption.hashPassword("bebekgoyeng")
                    )
            )
    );

    public void addNewPegawai(User user) {
        listPegawai.add(user);
    }

    public void deletePegawai(User usr) {
        listPegawai.remove(usr);
    }

    public ArrayList<User> getListPegawai() {
        return listPegawai;
    }

    public void editPegawai(int index, User user) {
        listPegawai.set(index, user);
    }

    public void setListPegawai(ArrayList<User> listPegawai) {
        this.listPegawai = listPegawai;
    }

    public User getPegawai(String ID){
        return listPegawai.stream().filter(
                cekUser -> cekUser.getUserID().equals(ID)
        ).findFirst().orElse(null);
    }

    public User authenticateUser(String emailOrID, String password) {
        return listPegawai.stream().filter(
                cekUser -> (cekUser.getEmail().equals(emailOrID) || cekUser.getUserID().equals(emailOrID)) &&
                        cekUser.getPassword().equals(password)
        ).findFirst().orElse(null);
    }


}
