package Domain.Master;

import Data.DataSource.CustomerDataSource;
import Data.Model.Customer;

import java.util.ArrayList;

import static Util.Formatting.formatMessageOutput;

public class MasterCustomer {
    final CustomerDataSource dataSource;

    public MasterCustomer(CustomerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<Customer> getListCustomer() {
        return dataSource.getListCustomer();
    }

    public void addCustomer(Customer customer) {
        // check if customer already exists
        if (!cekCustomer(customer.getNIK())) {
            dataSource.addCustomer(customer);
            formatMessageOutput("Data Berhasil Ditambahkan");
        } else {
            formatMessageOutput("Data Pelanggan Sudah Ada");
        }
    }

    public void deleteCustomer(String NIK) {
        if (cekCustomer(NIK)) {
            Customer kamar = getCustomer(NIK);
            dataSource.removeCustomer(kamar);
        } else {
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public void editDataCustomer(Customer oldDData, Customer newDData) {
        // find the data's index
        if (cekCustomer(oldDData.getNIK())) {
            int index = dataSource.getListCustomer().indexOf(oldDData);
            dataSource.editCustomer(index, newDData);
        } else {
            // data not found
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public Customer getCustomer(String NIK) {
        return dataSource.getCustomer(NIK);
    }

    private boolean cekCustomer(String NIK) {
        Customer cek = dataSource.getListCustomer().stream().filter(
                cekKamar -> cekKamar.getNIK().equals(NIK)
        ).findFirst().orElse(null);
        return cek != null; // return kamar is Found when cek is not null
    }
}
