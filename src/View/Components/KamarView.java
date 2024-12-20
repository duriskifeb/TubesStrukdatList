package View.Components;

import Data.AppEnums.AppEnums;
import Data.Model.Kamar;
import Util.Formatting;

import java.util.ArrayList;

public class KamarView {

    public static void kamarTableHeader(){
        System.out.println("====================================================================================================");
        System.out.format("%10s \t%10s \t%15s \t%10s \t%10s\n", "NO KAMAR", "KAPASITAS", "HARGA", "JENIS", "STATUS");
        System.out.println("====================================================================================================");

    }
    public static void viewDataSelectedKamar(Kamar selectedKamar) {
        if (selectedKamar != null) {
            String noKamar = selectedKamar.getNoKamar();
            int kapasitas = selectedKamar.getKapasitas();
            String harga = Formatting.formatRupiah(selectedKamar.getHarga());
            AppEnums.JenisKamar jenis = selectedKamar.getJenisKamar();
            AppEnums.StatusKamar status = selectedKamar.getStatusKamar();
            System.out.format("%10s \t%10s \t%15s \t%10s \t%10s\n", noKamar, kapasitas+" Person(s)", harga, jenis, status);
        }
    }

    public static void viewAllDataKamar(ArrayList<Kamar> listKamar) {
        listKamar
                .stream()
                .iterator()
                .forEachRemaining(
                        kamar -> {
                            String noKamar = kamar.getNoKamar();
                            int kapasitas = kamar.getKapasitas();
                            String harga = Formatting.formatRupiah(kamar.getHarga());
                            AppEnums.JenisKamar jenis = kamar.getJenisKamar();
                            AppEnums.StatusKamar status = kamar.getStatusKamar();
                            System.out.format("%10s \t%10s \t%15s \t%10s \t%10s\n", noKamar, kapasitas+" Person(s)", harga, jenis, status);

                        }
                );
    }
}
