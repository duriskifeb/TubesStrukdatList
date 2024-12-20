package View.Master;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;

import static View.AppRouter.AppRoute.*;
import static View.Components.CustomerView.viewAllDataCustomer;
import static View.Components.CustomerView.viewDataSelectedCustomer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Data.Model.Customer;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterCustomerViewModel;

public class MasterCustomerMenu {
    private final MasterCustomerViewModel masterCustomerVM;

    public MasterCustomerMenu(MasterCustomerViewModel masterCustomerVM) {
        this.masterCustomerVM = masterCustomerVM;
    }

    String inputUser;

    public void showMasterCustomerMenu() {
        while (AppRouter.activeRoute == MASTER_CUSTOMER) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        MASTER CUSTOMER       ");
            System.out.println("==============================");
            System.out.println("1. Show all customer");
            System.out.println("2. Find customer");
            System.out.println("3. Choose customer");
            System.out.println("4. Add customer");
            System.out.println("0. Back");
            System.out.println();
            try {
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showAllCustomer();
                        break;
                    case "2":
                        findCustomer();
                        break;
                    case "3":
                        choosingCustomer();
                        break;
                    case "4":
                        addCustomer();
                        break;
                    case "0":

                        AppRouter.navigateTo(MASTER_MAIN_MENU);
                        break;
                    default:
                        invalidChoice();;
                        break;
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void showAllCustomer() {
        InputUtilities.cls();
        System.out.println("SHOW ALL CUSTOMER");

        viewAllDataCustomer(masterCustomerVM.getListCustomers());
        System.out.println("============================================================");

        InputUtilities.pressEnter();
    }

    private void choosingCustomer() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        CHOOSE CUSTOMER       ");
            System.out.println("==============================");
            System.out.print("NIK\t: ");
            String nik = InputUtilities.input.readLine();
            masterCustomerVM.selectCustomer(nik);
            System.out.println("==============================");
            InputUtilities.pressEnter();

            if (masterCustomerVM.getSelectedCustomer() != null) {
                AppRouter.navigateTo(AppRouter.AppRoute.SUB_MASTER_DETAIL_CUSTOMER);
            }

        } catch (IOException e) {
            invalidChoice();
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
            System.out.println("==============================");

            if (masterCustomerVM.getSelectedCustomer() != null) {
                // show detail customer
                detailCustomer();
            } else {
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
                if (!filteredData.isEmpty()) {
                    viewAllDataCustomer(filteredData);
                } else {
                    formatMessageOutput("Data Customer Not Found");
                }

            }

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void addCustomer() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        ADD NEW CUSTOMER      ");
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

//            if (inputUser.equalsIgnoreCase("y")) {
            masterCustomerVM.addNewCustomer(nik, nama, email, telp);
//            } else {
//                formatMessageOutput("Process cancelled");
//            }

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (IOException e) {
            invalidChoice();
        }
    }

    public void showDetailCustomerMenu() {
        while (AppRouter.activeRoute == SUB_MASTER_DETAIL_CUSTOMER) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("      SHOW DETAIL CUSTOMER     ");
            System.out.println("==============================");
            System.out.println("Selected customer : " + masterCustomerVM.getSelectedCustomer().getNama() + " - " + masterCustomerVM.getSelectedCustomer().getNIK());
            System.out.println();
            System.out.println("1. Show detail");
            System.out.println("2. Edit customer");
            System.out.println("3. Delete customer");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        detailCustomer();
                        break;
                    case "2":
                        editCustomer();
                        break;
                    case "3":
                        deleteCustomer();
                        break;
                    case "0":

                        AppRouter.navigateTo(MASTER_CUSTOMER);
                        break;
                    default:
                        invalidChoice();
                        break;
                }
            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void detailCustomer() {
        InputUtilities.cls();
        System.out.println("SHOW DETAIL");

        viewDataSelectedCustomer(masterCustomerVM.getSelectedCustomer());
        System.out.println("============================================================");

        InputUtilities.pressEnter();
    }

    private void editCustomer() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("          EDIT CUSTOMER        ");
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
                masterCustomerVM.editCustomer(nik, nama, email, telp);
                formatMessageOutput("Customer editted");
                System.out.println("==============================");
                InputUtilities.pressEnter();

                AppRouter.navigateTo(MASTER_CUSTOMER);
            } else {
                formatMessageOutput("Process cancelled");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }


        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void deleteCustomer() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("        DELETE CUSTOMER       ");
            System.out.println("==============================");
            System.out.println("Selected pegawai : " + masterCustomerVM.getSelectedCustomer().getNama() + " - " + masterCustomerVM.getSelectedCustomer().getNIK());
            System.out.println();
            System.out.print("Anda yakin ingin menghapus customer ini?(y/n): ");
            inputUser = InputUtilities.input.readLine();
            if (inputUser.equalsIgnoreCase("y")) {
                masterCustomerVM.deleteCustomer(masterCustomerVM.getSelectedCustomer().getNIK());
                formatMessageOutput("Customer deleted");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_CUSTOMER);
            } else {
                System.out.println();
                formatMessageOutput("Process cancelledj");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }

        } catch (IOException e) {
            invalidChoice();
        }
    }


}

