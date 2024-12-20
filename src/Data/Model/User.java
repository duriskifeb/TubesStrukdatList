package Data.Model;

import Data.AppEnums.AppEnums;
import Util.Generator;

public class User {
    private String userID, email, nama, password;
    private AppEnums.UserRole role;

    public User(String userID, String email, String nama, AppEnums.UserRole role, String password) {
        this.userID = userID;
        this.email = email;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    public User(String email, String nama, AppEnums.UserRole role, String password) {
        this.userID = Generator.generatePegawaiID(nama, role, email);
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

    public AppEnums.UserRole getRole() {
        return role;
    }

    public void setRole(AppEnums.UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
