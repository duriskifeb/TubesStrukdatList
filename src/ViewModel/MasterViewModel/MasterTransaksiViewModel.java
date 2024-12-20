package ViewModel.MasterViewModel;

import Data.Model.Transaksi;
import Domain.Master.MasterTransaksi;
import Domain.Transaksi.TransaksiUseCase;
import Util.Formatting;

import java.util.ArrayList;

public class MasterTransaksiViewModel {
    final MasterTransaksi masterTransaksi;
    final TransaksiUseCase transaksiUseCase;

    public MasterTransaksiViewModel(MasterTransaksi masterTransaksi, TransaksiUseCase transaksiUseCase) {
        this.transaksiUseCase = transaksiUseCase;
        this.masterTransaksi = masterTransaksi;
    }
    public Transaksi selectedTransaksi;

    public void selectTranasksi(String noTransaksi) {
        masterTransaksi.selectTransaksi(noTransaksi);
        if (masterTransaksi.getSelectedTransaksi() != null) {
            this.selectedTransaksi  = masterTransaksi.getSelectedTransaksi();
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di edit");
        }
    }

    public void batalkanTransaksi(){
        if (selectedTransaksi != null) {
            masterTransaksi.batalkanTransaksi();
            this.selectedTransaksi = null;
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di delete");
        }
    }
    public void deleteTransaksi() {
        if (selectedTransaksi != null) {
            masterTransaksi.deleteTransaksi(this.selectedTransaksi.getNoTransaksi());
            this.selectedTransaksi = null;
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di delete");
        }

    }

    public ArrayList<Transaksi> getAllTransaksi() {
        return transaksiUseCase.getAllTransaksi();
    }

}


