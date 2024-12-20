package View.Pegawai;

import Data.Model.Customer;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterCustomerViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.*;
import static View.Components.CustomerView.viewAllDataCustomer;
import static View.Components.CustomerView.viewDataSelectedCustomer;

public class PegawaiCustomerMenu {
    private final MasterCustomerViewModel masterCustomerVM;
    String inputUser;

    public PegawaiCustomerMenu(MasterCustomerViewModel masterCustomerVM) {
        this.masterCustomerVM = masterCustomerVM;
    }

    public void showMenuPelanggan() {

        //        # Pelanggan #
//        - lihat list pelanggan
//        - register pelanggan
//                - lihat data detail pelanggan (cari pelanggan)
        while(AppRouter.activeRoute == SUB_PEGAWAI_CUSTOMER){
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("        MENU CUSTOMER       ");
            System.out.println("============================");
            System.out.println("1. Lihat Semua Pelanggan");
            System.out.println("2. Register Pelanggan");
            System.out.println("3. Cari Pelanggan");
            System.out.println("0. Kembali");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        System.out.println();
                        showAllCustomer();
                        break;
                    case "2":
                        registerCustomer();
                        break;
                    case "3":
                        findCustomer();
                        break;
                    case "0":
                        AppRouter.navigateTo(MAIN_MENU);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void findCustomer() {
        System.out.println();
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        FIND CUSTOMER       ");
            System.out.println("==============================");
            System.out.print("NIK atau Nama\t: ");
            String nik = InputUtilities.input.readLine();
            masterCustomerVM.selectCustomer(nik);
            
            if (masterCustomerVM.getSelectedCustomer() != null) {
                // show detail customer
                detailCustomer();
            }else{
                formatMessageOutput("Search By Name");
                // filter by name
                final ArrayList<Customer> filteredData = masterCustomerVM
                        .getListCustomers()
                        .stream()
                        .filter(
                                customer -> customer.getNama().toLowerCase().contains(nik.toLowerCase())
                        )
                        .collect(
                                Collectors.toCollection(ArrayList::new)
                        );
                if(!filteredData.isEmpty()){
                    viewAllDataCustomer(filteredData);
                }else {
                    formatMessageOutput("Data Customer Not Found");
                }
                System.out.println("==============================");
                InputUtilities.pressEnter();
                
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }
    private void detailCustomer() {
        InputUtilities.cls();
        System.out.println("SHOW DETAIL");

        viewDataSelectedCustomer(masterCustomerVM.getSelectedCustomer());
        System.out.println("=======================================================================================");

        InputUtilities.pressEnter();
    }

    private void registerCustomer() {
        System.out.println();
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("   REGISTER NEW CUSTOMER      ");
            System.out.println("==============================");

            System.out.print("NIK\t: ");
            String nik = InputUtilities.input.readLine();

            System.out.print("Nama\t: ");
            String nama = InputUtilities.input.readLine();

            System.out.print("Email\t: ");
            String email = InputUtilities.input.readLine();

            System.out.print("Telp\t: ");
            String telp = InputUtilities.input.readLine();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterCustomerVM.addNewCustomer(nik, nama, email, telp);
            } else {
                formatMessageOutput("Process cancelled");
            }

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (IOException e) {
            invalidChoice();
        }
    }
    private void showAllCustomer() {
        InputUtilities.cls();
        System.out.println("SHOW ALL CUSTOMER");

        viewAllDataCustomer(masterCustomerVM.getListCustomers());
        System.out.println("=======================================================================================");

        InputUtilities.pressEnter();
    }
}
