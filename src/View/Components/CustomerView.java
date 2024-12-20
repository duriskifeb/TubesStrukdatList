package View.Components;

import Data.Model.Customer;

import java.util.ArrayList;

public class CustomerView {

    public static void headerViewCustomer(){
        System.out.println("=======================================================================================");
        System.out.format("%10s %25s %20s %20s\n", "NIK", "Nama", "No telp", "Email");
        System.out.println("=======================================================================================");
    }

    public static void viewDataSelectedCustomer(Customer selectedCustomer){
        if(selectedCustomer != null){
            headerViewCustomer();
            String idCustomer = selectedCustomer.getNIK();
            String namaCustomer = selectedCustomer.getNama();
            String telp = selectedCustomer.getTelp();
            String email = selectedCustomer.getEmail();
            System.out.format("%10s %25s \t%20s \t%20s\n", idCustomer, namaCustomer, telp, email);
        }
    }
    public static void viewAllDataCustomer(ArrayList<Customer> listCustomer){
        headerViewCustomer();
        listCustomer
                .stream()
                .iterator()
                .forEachRemaining(
                        cust -> {
                            String idCustomer = cust.getNIK();
                            String namaCustomer = cust.getNama();
                            String telp = cust.getTelp();
                            String email = cust.getEmail();
                            System.out.format("%10s %25s \t%20s \t%20s\n", idCustomer, namaCustomer, telp, email);

                        }
                );
    }
}
