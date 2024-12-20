package Data.Model;

import Data.AppEnums.AppEnums;
import Data.AppEnums.AppRumahEnums;
import Util.Formatting;
import Util.Generator;
import Util.GeneratorRumah;

import java.util.ArrayList;
import java.util.Date;

public class TransaksiRumah {

    private String noTransaksi;
    private Date tanggalTransaksi, checkIn, checkOut, startDate, endDate;
    private AppRumahEnums.StatusTransaksi statusTransaksi;
    private AppRumahEnums.Pembayaran pembayaran;
    private AppRumahEnums.StatusTransaksiBayar statusPembayaran;
    private User pegawai;
    private ArrayList<CustomerRumah> customers; // get(0) customers is the one who order
    private double total;
    private ArrayList<Rumah> rumahOrdered;
    private double paid = 0;

    public TransaksiRumah(
            String noTransaksi,
            Date tanggalTransaksi,
            Date startDate,
            Date endDate,
            AppRumahEnums.StatusTransaksi statusTransaksi,
            AppRumahEnums.Pembayaran pembayaran,
            User pegawai,
            ArrayList<CustomerRumah> customers,
            ArrayList<Rumah> rumahOrdered) {
        this.noTransaksi = noTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.pembayaran = pembayaran;
        this.pegawai = pegawai;
        this.customers = customers;
        this.rumahOrdered = rumahOrdered;
        this.startDate = startDate;
        this.endDate = endDate;

        // calculateTotal();

    }

    public TransaksiRumah(
            Date tanggalTransaksi,
            Date startDate,
            Date endDate,
            AppRumahEnums.StatusTransaksi statusTransaksi,
            AppRumahEnums.Pembayaran pembayaran,
            User pegawai,
            ArrayList<CustomerRumah> customerRumah,
            ArrayList<Rumah> rumahOrdered

    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customers = customerRumah;
        this.rumahOrdered = rumahOrdered;

        this.noTransaksi = GeneratorRumah.generateTransaksiID(
                Formatting.formatDate(tanggalTransaksi),
                String.valueOf(this.getTotal()),
                customers.get(0).getNama(),
                pegawai.getNama(),
                pembayaran);

        this.tanggalTransaksi = tanggalTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.pembayaran = pembayaran;
        this.pegawai = pegawai;

        // calculateTotal();

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

    public AppRumahEnums.StatusTransaksi getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(AppRumahEnums.StatusTransaksi statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public AppRumahEnums.Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(AppRumahEnums.Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public User getPegawai() {
        return pegawai;
    }

    public void setPegawai(User pegawai) {
        this.pegawai = pegawai;
    }

    public ArrayList<CustomerRumah> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<CustomerRumah> customers) {
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
        long intervalDay = (diff / (1000 * 60 * 60 * 24) % 365); // endDate.compareTo(startDate);

        // System.out.println("Interval: " + intervalDay);
        for (Rumah umah : this.rumahOrdered) {
            this.total += umah.getHarga() * intervalDay;
        }

    }

    public void setKamarOrdered(ArrayList<Rumah> rumahOrdered) {
        this.rumahOrdered = rumahOrdered;
    }

    public void addKamar(Rumah umah) {
        this.rumahOrdered.add(umah);
    }

    public void removeKamar(Rumah umah) {
        this.rumahOrdered.remove(umah);
    }

    public ArrayList<Rumah> getKamarOrdered() {
        return rumahOrdered;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public AppRumahEnums.StatusTransaksiBayar getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(AppRumahEnums.StatusTransaksiBayar statusPembayaran) {
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
