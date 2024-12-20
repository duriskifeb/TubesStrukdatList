package Data.DataSource;

import Data.AppEnums.AppEnums;
import Data.Model.Kamar;

import java.util.ArrayList;
import java.util.Arrays;

public class KamarDataSource {
    private ArrayList<Kamar> listKamar = new ArrayList<>(
            Arrays.asList(
                    new Kamar( // SINGLE
                            "0101",
                            AppEnums.JenisKamar.SINGLE,
                            100000.0,
                            1,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar(
                            "0102",
                            AppEnums.JenisKamar.SINGLE,
                            100000.0,
                            1,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar( // DOUBLE
                            "0201",
                            AppEnums.JenisKamar.DOUBLE,
                            150000.0,
                            2,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar(
                            "0202",
                            AppEnums.JenisKamar.DOUBLE,
                            150000.0,
                            2,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar( // FAMILY
                            "0301",
                            AppEnums.JenisKamar.FAMILY,
                            300000.0,
                            4,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar(
                            "0302",
                            AppEnums.JenisKamar.FAMILY,
                            300000.0,
                            4,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar( // VIP
                            "0401",
                            AppEnums.JenisKamar.VIP,
                            400000.0,
                            2,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar(
                            "0402",
                            AppEnums.JenisKamar.VIP,
                            400000.0,
                            2,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar( // BUSINESS
                            "0501",
                            AppEnums.JenisKamar.BUSINESS,
                            700000.0,
                            5,
                            AppEnums.StatusKamar.AVAILABLE),
                    new Kamar(
                            "0502",
                            AppEnums.JenisKamar.BUSINESS,
                            700000.0,
                            5,
                            AppEnums.StatusKamar.AVAILABLE)
            )
    );


    public ArrayList<Kamar> getListKamar() {
        return listKamar;
    }

    public Kamar getKamar(String noKamar) {
        return listKamar.stream().filter(
                selectedKamar -> selectedKamar.getNoKamar().equals(noKamar)
        ).findFirst().orElse(null);
    }
    public void setListKamar(ArrayList<Kamar> listKamar) {
        this.listKamar = listKamar;
    }

    public void addNewKamar(Kamar kamar) {
        this.listKamar.add(kamar);
    }

    public void removeKamar(Kamar kamar) {


        this.listKamar.remove(kamar);

    }

    public void editKamar(int index, Kamar kamar) {
        this.listKamar.set(index, kamar);
    }

}
