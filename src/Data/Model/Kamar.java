package Data.Model;

import Data.AppEnums.AppEnums;

public class Kamar {
    private String noKamar;
    private AppEnums.JenisKamar jenisKamar;
    private double harga;
    private int kapasitas;
    private AppEnums.StatusKamar statusKamar;

    public Kamar(String noKamar, AppEnums.JenisKamar jenisKamar, double harga, int kapasitas, AppEnums.StatusKamar statusKamar) {
        this.noKamar = noKamar;
        this.jenisKamar = jenisKamar;
        this.harga = harga;
        this.kapasitas = kapasitas;
        this.statusKamar = statusKamar;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public AppEnums.JenisKamar getJenisKamar() {
        return jenisKamar;
    }

    public void setJenisKamar(AppEnums.JenisKamar jenisKamar) {
        this.jenisKamar = jenisKamar;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public AppEnums.StatusKamar getStatusKamar() {
        return statusKamar;
    }

    public void setStatusKamar(AppEnums.StatusKamar statusKamar) {
        this.statusKamar = statusKamar;
    }
}
