package View.Components;

import Data.AppEnums.AppEnums;
import Data.Model.User;

import java.util.ArrayList;

public class PegawaiView {
    public static void viewAllDataPegawai(ArrayList<User> listPegawai){
        listPegawai
                .stream()
                .iterator()
                .forEachRemaining(
                        user -> {
                            String idPegawai = user.getUserID();
                            String nama = user.getNama();
                            String email = user.getEmail();
                            AppEnums.UserRole role = user.getRole();
                            System.out.format("%10s %10s \t%15s \t%10s\n", idPegawai, nama, email, role);

                        }
                );

    }

    public static void viewSelectedPegawai(User selectedPegawai){
        if(selectedPegawai != null){
            String idPegawai = selectedPegawai.getUserID();
            String nama = selectedPegawai.getNama();
            String email = selectedPegawai.getEmail();
            AppEnums.UserRole role = selectedPegawai.getRole();
            System.out.format("%10s %10s \t%15s \t%10s\n", idPegawai, nama, email, role);
        }
    }
}
