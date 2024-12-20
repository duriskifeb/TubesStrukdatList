package ViewModel.MasterViewModel;

import Data.AppEnums.AppEnums;
import Data.Model.User;
import Domain.Master.MasterPegawai;
import Util.Encryption;

import static Util.Formatting.formatMessageOutput;

import java.util.ArrayList;

public class MasterPegawaiViewModel {
    final MasterPegawai masterPegawai;

    public MasterPegawaiViewModel(MasterPegawai masterPegawai) {
        this.masterPegawai = masterPegawai;
    }

    User selectedPegawai;

    public void selectPegawai(String userId) {
        this.selectedPegawai = masterPegawai.getPegawai(userId);
        if (selectedPegawai == null) {
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public User getSelectedPegawai(){
        return this.selectedPegawai;
    }

    public void addNewPegawai(
            String nama,
            String email,
            String password,
            AppEnums.UserRole role
    ) {
        User newData = new User(email, nama, role, Encryption.hashPassword(password));
        masterPegawai.addNewPegawai(newData);
    }

    public void deletePegawai(String userID) {
        masterPegawai.deletePegawai(userID);
    }

    public void editDataPegawai(
            String nama,
            String email,
            AppEnums.UserRole role
    ) {

        if (selectedPegawai != null) {
            User newData = new User(
                    email.isBlank() ? selectedPegawai.getEmail(): email,
                    nama.isBlank() ? selectedPegawai.getNama() : nama,
                    role == null ?selectedPegawai.getRole() : role,
                    selectedPegawai.getPassword()
            );
            masterPegawai.editDataPegawai(selectedPegawai, newData);
        }
    }

    public ArrayList<User> getListPegawai() {
        return  masterPegawai.getAllPegawai();
    }


}
