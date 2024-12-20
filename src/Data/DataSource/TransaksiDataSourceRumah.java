package Data.DataSource;

import Data.Model.Transaksi;
import Data.Model.TransaksiRumah;

import java.util.ArrayList;

public class TransaksiDataSourceRumah {
    private ArrayList<TransaksiRumah> listTransaksi = new ArrayList<>();

    public ArrayList<TransaksiRumah> getListTransaksi() {
        return listTransaksi;
    }

    public TransaksiRumah getTransaksiDetail(String noTransaksi) {
        return listTransaksi.stream().filter(
                cekTransaksi -> cekTransaksi.getNoTransaksi().equals(noTransaksi)).findFirst().orElse(null);
    }

    public void setListTransaksi(ArrayList<TransaksiRumah> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public void addNewTransaksi(TransaksiRumah newTransaksi) {
        this.listTransaksi.add(newTransaksi);
    }

    public void removeTransaksi(TransaksiRumah newTransaksi) {
        this.listTransaksi.remove(newTransaksi);
    }

    public void editTransasi(int index, TransaksiRumah newTransaksi) {
        this.listTransaksi.set(index, newTransaksi);
    }
}
