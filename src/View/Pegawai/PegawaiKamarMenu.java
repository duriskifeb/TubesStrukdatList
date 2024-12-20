package View.Pegawai;

import Data.AppEnums.AppEnums;
import Data.Model.Kamar;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterKamarViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.*;
import static View.Components.KamarView.kamarTableHeader;
import static View.Components.KamarView.viewAllDataKamar;

public class PegawaiKamarMenu {
    private final MasterKamarViewModel masterKamarVM;

    public PegawaiKamarMenu(MasterKamarViewModel masterKamarVM) {
        this.masterKamarVM = masterKamarVM;
    }

    private String inputUser;
    public void showMenuKamar() {


//        # Kamar #
//- lihat list kamar  ( beserta status)
//- lihat list kamar tersedia
//- lihat detail kamar (cari kamar)
        while(AppRouter.activeRoute == SUB_PEGAWAI_KAMAR){
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("Menu Kamar Pegawai");
            System.out.println("============================");
            System.out.println("1. Lihat Semua Kamar");
            System.out.println("2. Cari Kamar (berdasarkan status)");
            System.out.println("3. Cari Kamar (berdasarkan tipe)");
            System.out.println("0. Kembali");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        System.out.println();
                        showAllKamar();
                        break;
                    case "2":
                        findKamarByStatus();
                        break;
                    case "3":
                        findKamarBytype();
                        break;
                    case "0":
//                        AppRouter.navigateUp();
                        AppRouter.navigateTo(MAIN_MENU);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void findKamarByStatus() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("        FIND KAMAR       ");
        System.out.println("==============================");
        System.out.println("available".toUpperCase());
        System.out.println("occupied".toUpperCase());
        System.out.println("booked".toUpperCase());
        System.out.println("cleaining".toUpperCase());
        System.out.println("==============================");
        System.out.println();
        System.out.print("Cari kamar berdasarkan status\t: ");
        AppEnums.StatusKamar status = InputUtilities.getStatusKamarFromInput();
        System.out.println("==============================");
        if(status != null){

            final ArrayList<Kamar> filteredData = masterKamarVM
                    .getListKamar()
                    .stream()
                    .filter(
                            kamar -> kamar.getStatusKamar() == status
                    )
                    .collect(
                            Collectors.toCollection(ArrayList::new)
                    );
            if(!filteredData.isEmpty()){
                InputUtilities.cls();
                kamarTableHeader();
                viewAllDataKamar(filteredData);
            }else {
                formatMessageOutput("Data Kamar Not Found");
            }
        }else{
            formatMessageOutput("Status tidak dikenali, menampilkan semua data");
            showAllKamar();
        }
        System.out.println("============================================================");
        InputUtilities.pressEnter();
    }

    private void showAllKamar() {
        InputUtilities.cls();
        System.out.println("SHOW ALL KAMAR");
        kamarTableHeader();
        viewAllDataKamar(masterKamarVM.getListKamar());
        System.out.println("============================================================");
        
        InputUtilities.pressEnter();
    }

    private void findKamarBytype() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("        FIND KAMAR       ");
        System.out.println("==============================");
        System.out.println("single".toUpperCase());
        System.out.println("double".toUpperCase());
        System.out.println("family".toUpperCase());
        System.out.println("business".toUpperCase());
        System.out.println("vip".toUpperCase());
        System.out.println("==============================");
        System.out.println();
        System.out.print("Cari kamar berdasarkan tipe\t: ");
        AppEnums.JenisKamar type = InputUtilities.getJenisKamarFromInput();
        System.out.println("==============================");
        if(type != null){
            final ArrayList<Kamar> filteredData = masterKamarVM
                    .getListKamar()
                    .stream()
                    .filter(
                            kamar -> kamar.getJenisKamar() == type
                    )
                    .collect(
                            Collectors.toCollection(ArrayList::new)
                    );
            if(!filteredData.isEmpty()){
                InputUtilities.cls();
                kamarTableHeader();
                viewAllDataKamar(filteredData);
            }else {
                formatMessageOutput("Data Kamar Not Found");
            }
        }else{
            formatMessageOutput("Status tidak dikenali, menampilkan semua data");
            showAllKamar();
        }
        System.out.println("============================================================");
        InputUtilities.pressEnter();
    }
}
