package Domain.Master;

import Data.DataSource.TransaksiDataSource;
import Data.AppEnums.AppEnums;
import Data.Model.Transaksi;

import static Util.Formatting.formatMessageOutput;

public class MasterTransaksi {
    final TransaksiDataSource dataSource;

    public MasterTransaksi(TransaksiDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Transaksi selectedTransaksi;

    public void deleteTransaksi(String noTransaksi){
        if(cekTransaksi(noTransaksi)){
            Transaksi transaksi = dataSource.getTransaksiDetail(noTransaksi);
            dataSource.removeTransaksi(transaksi);
        }else {
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    // EDITS TRANSAKSI

    // batalkan

    public void batalkanTransaksi(){
        if(this.selectedTransaksi != null){
            Transaksi newData = this.selectedTransaksi;
            newData.setStatusTransaksi(AppEnums.StatusTransaksi.CANCELLED);
            commitEditDataTransaksi(this.selectedTransaksi, newData);
        }else{
            formatMessageOutput("Tidak Ada Data Transaksi yang diplih");
        }
    }
    private void commitEditDataTransaksi(Transaksi oldDData, Transaksi newDData){
        // find the data's index
        if(cekTransaksi(oldDData.getNoTransaksi())){
            int index = dataSource.getListTransaksi().indexOf(oldDData);
            dataSource.editTransasi(index, newDData);
        }else{
            // data not found
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    // EDITS TRANSAKSI

    public void selectTransaksi(String noTransaksi){
         selectedTransaksi = dataSource.getTransaksiDetail(noTransaksi);
         if(selectedTransaksi == null){
             formatMessageOutput("Data Tidak Ditemukan");
         }
    }
    private boolean cekTransaksi(String reportNumber) {
        Transaksi cek = dataSource.getListTransaksi().stream().filter(
                transaksi -> transaksi.getNoTransaksi().equals(reportNumber)
        ).findFirst().orElse(null);
        return cek != null; // return data is Found when cek is not nul
    }

    public Transaksi getSelectedTransaksi() {
        return this.selectedTransaksi;
    }

}
