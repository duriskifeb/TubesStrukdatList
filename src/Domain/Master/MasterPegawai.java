package Domain.Master;

import Data.DataSource.PegawaiDataSource;
import Data.Model.User;

import java.util.ArrayList;

import static Util.Formatting.formatMessageOutput;

public class MasterPegawai {
    final PegawaiDataSource dataSource;

    public MasterPegawai(PegawaiDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<User> getAllPegawai(){
        return dataSource.getListPegawai();
    }

    public void addNewPegawai(User user) {
        // check if user already exists
        if(!cekUser(user)){
            dataSource.addNewPegawai(user);
        }else {
            formatMessageOutput("Data Pegawai Sudah Ada");
        }
    }

    public void deletePegawai(String userId){
        if(cekUser(userId)){
            User usr = dataSource.getPegawai(userId);
            dataSource.deletePegawai(usr);
        }else {
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public User getPegawai(String userId){
        return dataSource.getPegawai(userId);
    }

    public void editDataPegawai(User oldDData, User newDData){
        // find the data's index
        if(cekUser(oldDData)){
            int index = dataSource.getListPegawai().indexOf(oldDData);
            dataSource.editPegawai(index, newDData);
        }else{
            // data not found
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    private boolean cekUser(User user){
        User cek = dataSource.getListPegawai().stream().filter(
                cekUser -> {
                    return cekUser.getNama().equals(user.getNama()) ||
                            cekUser.getEmail().equals(user.getEmail());
                }
        ).findFirst().orElse(null);
        return cek != null; // return userIs Found when cek is not null
    }
    private boolean cekUser(String userId){
        User cek = dataSource.getListPegawai().stream().filter(
                cekUser -> cekUser.getUserID().equals(userId)
        ).findFirst().orElse(null);
        return cek != null; // return userIs Found when cek is not null
    }

}
