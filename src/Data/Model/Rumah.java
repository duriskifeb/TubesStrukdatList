package Data.Model;

public class Rumah {
    private String alamat;
    private String jenisRumah;
    private double luasBangunan;
    private double luasTanah;
    private int jumlahKamar;
    private double harga;
    private String statusKepemilikan;
    // private List<String> fasilitas;

    public Rumah(String alamat, String jenisRumah, double luasBangunan, double luasTanah, int jumlahKamarTidur,
            int jumlahKamarMandi, int jumlahLantai, double harga, String statusKepemilikan, List<String> fasilitas) {
        this.alamat = alamat;
        this.jenisRumah = jenisRumah;
        this.luasBangunan = luasBangunan;
        this.luasTanah = luasTanah;
        this.jumlahKamar = jumlahKamar;
        this.harga = harga;
        this.statusKepemilikan = statusKepemilikan;
        // this.fasilitas = fasilitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisRumah() {
        return jenisRumah;
    }

    public void setJenisRumah(String jenisRumah) {
        this.jenisRumah = jenisRumah;
    }

    public double getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(double luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public double getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(double luasTanah) {
        this.luasTanah = luasTanah;
    }

    public int getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(int jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
