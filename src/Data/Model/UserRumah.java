package Data.Model;

import Data.AppEnums.AppEnums;
import Data.AppEnums.AppRumahEnums;
import Util.Generator;
import Util.GeneratorRumah;

public class UserRumah {
    private String userID, email, nama, password;
    private AppRumahEnums.userRole role;

    public UserRumah(String userID, String email, String nama, AppRumahEnums.userRole role, String password) {
        this.userID = userID;
        this.email = email;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    public UserRumah(String email, String nama, AppRumahEnums.userRole role, String password) {
        this.userID = GeneratorRumah.generateSales(nama, role, email);
        this.email = email;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public AppRumahEnums.userRole getRole() {
        return role;
    }

    public void setRole(AppRumahEnums.userRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
