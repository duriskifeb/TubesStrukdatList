package Data.DataSource;

import Data.Model.Customer;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerDataSource {
    private ArrayList<Customer> listCustomer = new ArrayList<>(
            Arrays.asList(
                    new Customer(
                            "12012310312",
                            "David",
                            "david-ntb@gmail.com",
                            "0192391023927"
                    ),
                    new Customer(
                            "1201230016",
                            "Ryan",
                            "ryanadika09@gmail.com",
                            "0812386739045"
                    ),
                    new Customer(
                            "1201230018",
                            "Ivan",
                            "ivanahmad@gmail.com",
                            "089123647898"
                    ),
                    new Customer(
                            "1201230039",
                            "Movic",
                            "andrimovic1@gmail.com",
                            "0821783640213"
                    ),
                    new Customer(
                            "1201230042",
                            "Ariana",
                            "arianahechalina0@gmail.com",
                            "0827123447145"
                    ),
                    new Customer(
                           "1201230004",
                           "Dika",
                           "andikaprata1@gmail.com",
                           "08217348902134"
                    ),
                    new Customer(
                            "1201230035",
                            "Pandu",
                            "pandusigar@gmail.com",
                            "0821763211093"
                    ),
                    new Customer(
                            "1201230017",
                            "Faizah",
                            "sitifaizah@gmail.com",
                            "0820985433450"
                    ),
                    new Customer(
                            "1201230031",
                            "Hakim",
                            "hakimbayhaq@gmail.com",
                            "0821773290021"
                    ),
                    new Customer(
                            "1201230002",
                            "Mufid",
                            "mufidnj@gmail.com",
                            "0819234766987"
                    ),
                    new Customer(
                            "1201230013",
                            "Wahuyu",
                            "nurwahyu@gmail.com",
                            "0827880316782"
                    ),
                    new Customer(
                            "1201230010",
                            "Raihan",
                            "raihanaryo@gmail.com",
                            "082700976543"
                    ),
                    new Customer(
                            "1201230032",
                            "Alvian",
                            "alviannovi3@gmail.com",
                            "082199437621"
                    ),
                    new Customer(
                            "1201230001",
                            "Nanda",
                            "ahmadnanda40@gail.com",
                            "0812187355635"
                    ),
                    new Customer(
                            "1201230033",
                            "Syauqi",
                            "syauqiabdillah@gmail.com",
                            "08217783007289"
                    ),
                    new Customer(
                            "1201230036",
                            "Bayu",
                            "adityabayu2@gmail.com",
                            "081927883645"
                    ),
                    new Customer(
                            "1201230020",
                            "Yanto",
                            "bangkithardyanto@gmail.com",
                            "0829172380017"
                    ),
                    new Customer(
                            "1201230014",
                            "Amirul",
                            "amirulmukminin@gmail.com",
                            "0821673849072"
                    ),
                    new Customer(
                            "1201230026",
                            "Rafid",
                            "rafiddamar3@gmail.com",
                            "0821234790761"
                    ),
                    new Customer(
                            "1201230030",
                            "Arap",
                            "firstabdillah1@gmail.com",
                            "0821496467119"
                    )
            )
    );

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    public void addCustomer(Customer customer) {
        listCustomer.add(customer);
    }
    public void removeCustomer(Customer customer){
        listCustomer.remove(customer);
    }
    public void editCustomer(int index , Customer customer){
        listCustomer.set(index, customer);
    }

    public Customer getCustomer(String NIK){
        return listCustomer.stream().filter(
                cekCust -> cekCust.getNIK().equals(NIK)
        ).findFirst().orElse(null);
    }

}
