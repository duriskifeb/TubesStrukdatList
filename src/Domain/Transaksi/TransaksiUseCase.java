package Domain.Transaksi;

import Data.DataSource.CustomerDataSource;
import Data.DataSource.KamarDataSource;
import Data.DataSource.PegawaiDataSource;
import Data.DataSource.TransaksiDataSource;
import Data.AppEnums.AppEnums;
import Data.AppEnums.AppEnums.*;
import Data.Model.Customer;
import Data.Model.Kamar;
import Data.Model.Transaksi;
import Data.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static Util.Formatting.formatMessageOutput;

// act like a cart and its utilities
public class TransaksiUseCase {
    final TransaksiDataSource transaksiDataSource;
    final CustomerDataSource customerDataSource;
    final KamarDataSource kamarDataSource;
    final PegawaiDataSource pegawaiDataSource;

    public TransaksiUseCase(
            TransaksiDataSource transaksiDataSource,
            CustomerDataSource customerDataSource,
            KamarDataSource kamarDataSource,
            PegawaiDataSource pegawaiDataSource
    ) {
        this.transaksiDataSource = transaksiDataSource;
        this.customerDataSource = customerDataSource;
        this.kamarDataSource = kamarDataSource;
        this.pegawaiDataSource = pegawaiDataSource;
    }

    Transaksi currentActiveTransaksi;

    public Transaksi getCurrentActiveTransaksi() {
        return currentActiveTransaksi;
    }

    public ArrayList<Transaksi> getAllTransaksi() {
        return transaksiDataSource.getListTransaksi();
    }


    // logic to create a new instance of Transaksi, and save the transaction
    public void commitTransaksi() {
        if (currentActiveTransaksi != null) {
            int cekIndex = transaksiDataSource.getListTransaksi().indexOf(currentActiveTransaksi);
            if (cekIndex == -1) {
                transaksiDataSource.addNewTransaksi(currentActiveTransaksi);
            } else {
                transaksiDataSource.editTransasi(cekIndex, currentActiveTransaksi);
            }
        }
    }

    public void createInitialTransaksi(Date startDate, Date endDate, String nik, User pegawai, String noKamar, Pembayaran payment) {
        this.currentActiveTransaksi = null;
        Customer customer = customerDataSource.getCustomer(nik);
        Kamar kamar = kamarDataSource.getKamar(noKamar);
        if (customer == null) {
            formatMessageOutput("Data Customer Tidak Ditemukan");
        } else if (kamar.getStatusKamar() != StatusKamar.AVAILABLE) {
            formatMessageOutput("Kamar Sedang Digunakan");
        } else {
            ArrayList<Kamar> listKamar = new ArrayList<Kamar>();
            ArrayList<Customer> listCustomer = new ArrayList<Customer>();

            listKamar.add(kamar);
            updateStatusKamar(kamar, StatusKamar.BOOKED); //

            listCustomer.add(customer);

            this.currentActiveTransaksi = new Transaksi(
                    new Date(),
                    startDate,
                    endDate,
                    StatusTransaksi.PENDING,
                    payment,
                    pegawai,
                    listCustomer,
                    listKamar

            );
        }

    }

    // UPDATES the transaction
    public void addKamar(String noKamar) {
        if (currentActiveTransaksi != null) {
            Kamar kamar = kamarDataSource.getKamar(noKamar);
            if (kamar != null && kamar.getStatusKamar() == StatusKamar.AVAILABLE) {
                ArrayList<Kamar> listKamar = currentActiveTransaksi.getKamarOrdered();
                listKamar.add(kamar);
                currentActiveTransaksi.setKamarOrdered(listKamar);
                updateStatusKamar(kamar, StatusKamar.BOOKED); //
            }

        }
    }

    private void updateStatusKamar(Kamar kamar, StatusKamar statusKamar) {
        Kamar oldData = kamarDataSource.getKamar(kamar.getNoKamar());
        kamar.setStatusKamar(statusKamar);
        int index = kamarDataSource.getListKamar().indexOf(oldData);
        kamarDataSource.editKamar(index, kamar);
    }

    public void removeKamar(String noKamar) {
        if (currentActiveTransaksi != null) {
            Kamar kamar = kamarDataSource.getKamar(noKamar);
            if (kamar != null) {
                ArrayList<Kamar> listKamar = currentActiveTransaksi.getKamarOrdered();
                if (listKamar.size() > 1) {
                    listKamar.remove(kamar);
                    currentActiveTransaksi.setKamarOrdered(listKamar);
                    updateStatusKamar(kamar, StatusKamar.AVAILABLE);
                }
            }
        }
    }

    public void addCustomer(String NIK) {
        if (currentActiveTransaksi != null) {
            Customer customer = customerDataSource.getCustomer(NIK);
            ArrayList<Customer> listPelanggan = currentActiveTransaksi.getCustomers();
            if (listPelanggan.contains(customer)) {
                formatMessageOutput("Data Tamu sudah ada");
                return;
            }
            listPelanggan.add(customer);
            currentActiveTransaksi.setCustomers(listPelanggan);
        }
    }

    public void removeCustomer(String NIK) {
        if (currentActiveTransaksi != null) {
            Customer customer = customerDataSource.getCustomer(NIK);
            ArrayList<Customer> listPelanggan = currentActiveTransaksi.getCustomers();
            if (listPelanggan.size() > 1) {
                listPelanggan.remove(customer);
                currentActiveTransaksi.setCustomers(listPelanggan);
            }
        }
    }

    public void checkOut() {

        if (currentActiveTransaksi != null) {
            if (currentActiveTransaksi.getStatusPembayaran() != StatusTransaksiBayar.LUNAS) {
                formatMessageOutput("Lunasi dulu untuk bisa checkout");

            } else {

                currentActiveTransaksi.setCheckOut(new Date());
                currentActiveTransaksi.setStatusTransaksi(AppEnums.StatusTransaksi.DONE);

                // update all status kamar to cleaniong
                for (Kamar kamar : currentActiveTransaksi.getKamarOrdered()) {
                    updateStatusKamar(kamar, StatusKamar.CLEANING);
                }
                commitTransaksi();

            }

        } else {
            formatMessageOutput("Belum ada data transaksi yang dipilih");
        }
    }

    public void checkIn() {
        // TODO implement
        if (currentActiveTransaksi != null) {
            if (currentActiveTransaksi.getStatusPembayaran() != AppEnums.StatusTransaksiBayar.PENDING_PAYMENT) {
                currentActiveTransaksi.setCheckIn(new Date());
                currentActiveTransaksi.setStatusTransaksi(AppEnums.StatusTransaksi.ONGOING);
                // update all status kamar to occupied
                for (Kamar kamar : currentActiveTransaksi.getKamarOrdered()) {
                    updateStatusKamar(kamar, StatusKamar.OCCUPIED);
                }
                commitTransaksi();

            } else {
                formatMessageOutput("Lakukan pembayaran terlebih dahulu untuk bisa chekc in");
            }
        } else {
            formatMessageOutput("Belum ada data transaksi yang dipilih");
        }
    }

    public void bayar(AppEnums.Pembayaran metodeBayar, double amountBayar) {

        if (currentActiveTransaksi == null) {
            formatMessageOutput("Belum ada data transaksi yang dipilih");
            return;
        }

        if (currentActiveTransaksi.getStatusPembayaran() == StatusTransaksiBayar.LUNAS) {
            formatMessageOutput("Tidak Bisa bayar karena transaksi sudah lunas");
            return;
        }

        // amount bayar tidak valid
        if (amountBayar <= 0) {
            formatMessageOutput("Jumlah bayar tidak valid");
            return;
        }

        currentActiveTransaksi.setPembayaran(metodeBayar);
        currentActiveTransaksi.setStatusTransaksi(AppEnums.StatusTransaksi.ONGOING);

        // hitung sisa yang harus dibayarkan
        double yangHarusDibayarkan = currentActiveTransaksi.getTotal() - currentActiveTransaksi.getPaid();
        double sisatagihan =0;

//        System.out.println("yang harus dibayarkan " + yangHarusDibayarkan);
        if (metodeBayar == Pembayaran.CASH) {

            // hitung kembalian
            double kembalianPembayaran =  yangHarusDibayarkan - amountBayar;

//            System.out.println("kembalian " + kembalianPembayaran);
//            sisatagihan = //Math.abs(currentActiveTransaksi.getPaid() + (amountBayar - yangHarusDibayarkan));
            if (kembalianPembayaran < 0) {
                // kalau ada kembalian
                formatMessageOutput("Kembalian : " + Math.abs(kembalianPembayaran));

                sisatagihan = 0;
                currentActiveTransaksi.setPaid(currentActiveTransaksi.getPaid() + yangHarusDibayarkan);

            }else{
                currentActiveTransaksi.setPaid(
                        yangHarusDibayarkan-kembalianPembayaran
                );
                sisatagihan = kembalianPembayaran;//currentActiveTransaksi.getTotal() - currentActiveTransaksi.getPaid();//yangHarusDibayarkan;// yangHarusDibayarkan - kembalianPembayaran;
            }

//            System.out.println("sisa tagihan " + sisatagihan);

        } else {
            currentActiveTransaksi.setPaid(
                    currentActiveTransaksi.getPaid() + yangHarusDibayarkan
            );
        }




        // update status pembayaran
        currentActiveTransaksi.setStatusPembayaran(StatusTransaksiBayar.PAID);

        formatMessageOutput("Pembayaran Berhasil!!");


        formatMessageOutput("Sisa Tagihan : " + sisatagihan);

        // kalau transaksi lunas
        if (currentActiveTransaksi.getPaid() > 0 && currentActiveTransaksi.getPaid() > currentActiveTransaksi.getTotal()) {
            // lunas
            formatMessageOutput("Pembayaran Berhasil!! Transaksi Lunas");
            currentActiveTransaksi.setStatusPembayaran(StatusTransaksiBayar.LUNAS);
            formatMessageOutput("Kembalian : " + Math.abs(yangHarusDibayarkan - amountBayar));
        }

    }
    // UPDATES the transaction


    public void selectTransaksi(String noTransaksi) {
        this.currentActiveTransaksi = transaksiDataSource.getTransaksiDetail(noTransaksi);
        if (currentActiveTransaksi == null) {
            formatMessageOutput("Data Not Found");
        }
    }


}
