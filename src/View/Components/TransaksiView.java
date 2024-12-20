package View.Components;

import Data.AppEnums.AppEnums;
import Data.Model.Transaksi;
import Util.Formatting;

import java.util.ArrayList;

public class TransaksiView {
    public static void headerViewTransaksi(){
        System.out.println("====================================================================================================");
        System.out.format("%20s %25s %20s %20s\n", "No transaksi", "Customer", "Status", "Tanggal");
        System.out.println("====================================================================================================");
    }

    public static void viewAllTransaksi(ArrayList<Transaksi> listTransaksi) {
        headerViewTransaksi();
        listTransaksi
                .stream()
                .iterator()
                .forEachRemaining(
                        selectedTransaksi -> {
                            String noTransaksi = selectedTransaksi.getNoTransaksi();
                            String user = selectedTransaksi.getCustomers().get(0).getNama();
                            AppEnums.StatusTransaksi status = selectedTransaksi.getStatusTransaksi();
                            String tanggalTransaksi = Formatting.formatDate(selectedTransaksi.getTanggalTransaksi());
                            System.out.format("%20s %25s \t%20s \t%20s\n", noTransaksi, user, status, tanggalTransaksi);

                        }
                );
    }

    public static void viewDetailSelectedTransaksi(Transaksi selectedTransaksi) {
        if (selectedTransaksi != null) {
            String noTransaksi = selectedTransaksi.getNoTransaksi();
            String user = selectedTransaksi.getPegawai().getUserID();
            ;
            AppEnums.StatusTransaksi status = selectedTransaksi.getStatusTransaksi();
            String tanggalTransaksi = Formatting.formatDate(selectedTransaksi.getTanggalTransaksi());

            System.out.println("========================================= DETAIL TRANSAKSI ========================================");
            System.out.println("No Transaksi: " + noTransaksi);
            System.out.println("PIC : " + selectedTransaksi.getPegawai().getNama() + "( " + selectedTransaksi.getPegawai().getUserID() + " )");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("Pelanggan : " + user);
            System.out.println("Status: " + status);
            System.out.println("Tanggal : " + tanggalTransaksi);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("Start : " + Formatting.formatDate(selectedTransaksi.getStartDate()));
            System.out.println("End : " + Formatting.formatDate(selectedTransaksi.getEndDate()));
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("Check In : " + Formatting.formatDate(selectedTransaksi.getCheckIn()));
            System.out.println("Check Out : " + Formatting.formatDate(selectedTransaksi.getCheckOut()));

            System.out.println("========================================= DETAIL PESANAN ==========================================");
            System.out.println("KAMAR : ");
            
            selectedTransaksi.getKamarOrdered().stream().iterator().forEachRemaining(
                    kamar -> {
                        System.out.format("%20s %25s \t%20s \t%20s\n", kamar.getNoKamar(), kamar.getJenisKamar(), kamar.getHarga(), kamar.getKapasitas());
                    }
            );

        }

        System.out.println();

        System.out.println("CUSTOMER : ");
        if (selectedTransaksi != null) {
            selectedTransaksi.getCustomers().stream().iterator().forEachRemaining(
                    customer -> {
                        System.out.format("%20s %25s \t%20s \t%20s\n", customer.getNIK(), customer.getNama(), customer.getTelp(), customer.getEmail());
                    }
            );
        }
        
        System.out.println("========================================= DETAIL BIAYA ============================================");
        System.out.println("TOTAL : " + Formatting.formatRupiah(selectedTransaksi.getTotal()));
        System.out.println("DIBAYARKAN : " + Formatting.formatRupiah(selectedTransaksi.getPaid()));
        System.out.println("Status Pembayaran : " + selectedTransaksi.getStatusPembayaran() + "( " + selectedTransaksi.getPembayaran() + " )");
        System.out.println("========================================= DETAIL TRANSAKSI ========================================");


    }
}
