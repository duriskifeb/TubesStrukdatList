package Data.DataSource;

import Data.Model.CustomerRumah;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerRumahSource {
    private ArrayList<CustomerRumah> listCustomerRumah = new ArrayList<>(
            Arrays.asList(
                    new CustomerRumah(
                            "1201230037",
                            "Ahmad",
                            "ahmad@gmail.com",
                            "08123456789"),
                    new CustomerRumah(
                            "1201230038",
                            "Budi",
                            "budi@gmail.com",
                            "08987654321"),
                    new CustomerRumah(
                            "1201230039",
                            "Citra",
                            "citra@gmail.com",
                            "08123498765"),
                    new CustomerRumah(
                            "1201230040",
                            "Dewi",
                            "dewi@gmail.com",
                            "08543210987")));

    public ArrayList<CustomerRumah> getListCustomerRumah() {
        return listCustomerRumah;
    }

    public void setListCustomerRumah(ArrayList<CustomerRumah> listCustomerRumah) {
        this.listCustomerRumah = listCustomerRumah;
    }

    public void addCustomerRumah(CustomerRumah csRmh) {
        listCustomerRumah.add(csRmh);
    }

    public void removeCustomerRumah(CustomerRumah csRmh) {
        listCustomerRumah.remove(csRmh);
    }

    public void editCustomerRumah(int index, CustomerRumah csRmh) {
        listCustomerRumah.set(index, csRmh);
    }

    public CustomerRumah getCustomerRumah(String NIK) {
        return listCustomerRumah.stream()
                .filter(cekCust -> cekCust.getNIK().equals(NIK))
                .findFirst()
                .orElse(null);
    }
}
