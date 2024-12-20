package Data.Model;

import Data.AppEnums.AppEnums;
import Util.Formatting;
import Util.Generator;

import java.util.ArrayList;
import java.util.Date;

public class Transaksi {

    private String noTransaksi;
    private Date tanggalTransaksi, checkIn, checkOut, startDate, endDate;
    private AppEnums.StatusTransaksi statusTransaksi;
    private AppEnums.Pembayaran pembayaran;
    private AppEnums.StatusTransaksiBayar statusPembayaran;
    private User pegawai;
    private ArrayList<Customer> customers; // get(0) customers is the one who order
    private double total;
    private ArrayList<Kamar> kamarOrdered;
    private double paid=0;



    public Transaksi(
            String noTransaksi,
            Date tanggalTransaksi,
            Date startDate,
            Date endDate,
            AppEnums.StatusTransaksi statusTransaksi,
            AppEnums.Pembayaran pembayaran,
            User pegawai,
            ArrayList<Customer> customers,
            ArrayList<Kamar> kamarOrdered
    ) {
        this.noTransaksi = noTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.pembayaran = pembayaran;
        this.pegawai = pegawai;
        this.customers = customers;
        this.kamarOrdered = kamarOrdered;
        this.startDate = startDate;
        this.endDate = endDate;

//        calculateTotal();

    }


    public Transaksi(
            Date tanggalTransaksi,
            Date startDate,
            Date endDate,
            AppEnums.StatusTransaksi statusTransaksi,
            AppEnums.Pembayaran pembayaran,
            User pegawai,
            ArrayList<Customer> customers,
            ArrayList<Kamar> kamarOrdered

    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customers = customers;
        this.kamarOrdered = kamarOrdered;

        this.noTransaksi = Generator.generateTransaksiID(
                Formatting.formatDate(tanggalTransaksi),
                String.valueOf(this.getTotal()),
                customers.get(0).getNama(),
                pegawai.getNama(),
                pembayaran
        );

        this.tanggalTransaksi = tanggalTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.pembayaran = pembayaran;
        this.pegawai = pegawai;

//        calculateTotal();

    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public AppEnums.StatusTransaksi getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(AppEnums.StatusTransaksi statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public AppEnums.Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(AppEnums.Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public User getPegawai() {
        return pegawai;
    }

    public void setPegawai(User pegawai) {
        this.pegawai = pegawai;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public double getTotal() {
        calculateTotal();
        return total;
    }

    public void calculateTotal() {
        // sum the total
        this.total = 0;

        long diff = endDate.getTime() - startDate.getTime();
         long intervalDay = (diff / (1000 * 60 * 60 * 24)%365) ; //endDate.compareTo(startDate);

//        System.out.println("Interval: " + intervalDay);
        for(Kamar kamar : this.kamarOrdered){
            this.total += kamar.getHarga()*intervalDay;
        }

    }

    public void setKamarOrdered(ArrayList<Kamar> kamarOrdered) {
        this.kamarOrdered = kamarOrdered;
    }

    public void addKamar(Kamar kamar){
        this.kamarOrdered.add(kamar);
    }

    public void removeKamar(Kamar k){
        this.kamarOrdered.remove(k);
    }
    public ArrayList<Kamar> getKamarOrdered() {
        return kamarOrdered;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public AppEnums.StatusTransaksiBayar getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(AppEnums.StatusTransaksiBayar statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
