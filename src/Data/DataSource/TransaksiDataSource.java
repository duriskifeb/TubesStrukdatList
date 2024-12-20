package Data.DataSource;

import Data.Model.Transaksi;

import java.util.ArrayList;

public class TransaksiDataSource {
    private ArrayList<Transaksi> listTransaksi = new ArrayList<>();

    public ArrayList<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public Transaksi getTransaksiDetail(String noTransaksi) {
        return listTransaksi.stream().filter(
                cekTransaksi -> cekTransaksi.getNoTransaksi().equals(noTransaksi)).findFirst().orElse(null);
    }

    public void setListTransaksi(ArrayList<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public void addNewTransaksi(Transaksi newTransaksi) {
        this.listTransaksi.add(newTransaksi);
    }

    public void removeTransaksi(Transaksi newTransaksi) {
        this.listTransaksi.remove(newTransaksi);
    }

    public void editTransasi(int index, Transaksi newTransaksi) {
        this.listTransaksi.set(index, newTransaksi);
    }
}
