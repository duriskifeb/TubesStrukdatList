package View.Transaksi;

import Data.AppEnums.AppEnums;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.AuthViewModel.AuthViewModel;
import ViewModel.MasterViewModel.MasterCustomerViewModel;
import ViewModel.MasterViewModel.MasterKamarViewModel;
import ViewModel.TransaksiViewModel.TransaksiViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static Data.AppEnums.AppEnums.StatusTransaksi.DONE;
import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.*;
import static View.Components.KamarView.kamarTableHeader;
import static View.Components.KamarView.viewAllDataKamar;
import static View.Components.TransaksiView.viewAllTransaksi;
import static View.Components.TransaksiView.viewDetailSelectedTransaksi;

public class TransaksiMenu {

    private final TransaksiViewModel transaksiVM;
    private final MasterKamarViewModel kamarVM;
    private final AuthViewModel authViewModel;
    private final MasterCustomerViewModel masterCustomerVM;

    public TransaksiMenu(TransaksiViewModel transaksiVM, MasterKamarViewModel kamarVM, AuthViewModel authViewModel,
            MasterCustomerViewModel masterCustomerVM) {
        this.transaksiVM = transaksiVM;
        this.kamarVM = kamarVM;
        this.authViewModel = authViewModel;
        this.masterCustomerVM = masterCustomerVM;
    }

    String inputUser;

    public void showTransaksiMenu() {
        // # Transaksi #
        // - lihat list transaksi
        // - buat transaksi baru
        // - pilih transaksi

        while (AppRouter.activeRoute == AppRouter.AppRoute.TRANSAKSI) {
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("       MENU TRANSAKSI       ");
            System.out.println("============================");
            System.out.println("1. Lihat List Transaksi");
            System.out.println("2. Buat Transaksi Baru");
            System.out.println("3. Pilih Transaksi");
            System.out.println("0. Kembali");
            System.out.println();

            System.out.print("Masukkan Pilihan : ");
            try {

                inputUser = InputUtilities.input.readLine();
                System.out.println();
                switch (inputUser) {
                    case "1":
                        showAllTransaksi();
                        break;
                    case "2":
                        initNewTransaksi();
                        break;
                    case "3":
                        pilihTransaksi();
                        break;
                    case "0":

                        AppRouter.navigateTo(MAIN_MENU);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
                System.out.println();
            } catch (IOException e) {
                invalidChoice();
            }

        }

    }

    private void showAllTransaksi() {
        InputUtilities.cls();
        viewAllTransaksi(transaksiVM.getAllTransaksi());
        System.out.println(
                "====================================================================================================");
        InputUtilities.pressEnter();
    }

    private void initNewTransaksi() {

        InputUtilities.cls();
        System.out.println("============================");
        System.out.println("     BUAT TRANSAKSI BARU    ");
        System.out.println("============================");
        System.out.println("Format tanggal (DD-MM-YYYY)");
        System.out.println();

        System.out.print("Tanggal Mulai\t : ");
        Date startDate = InputUtilities.getDateFromInput();
        System.out.print("Tanggal Berakhir : ");
        Date endDate = InputUtilities.getDateFromInput();
        String nik = getNIKCustomer();

        if (!nik.isBlank()) {
            choiceKamar(startDate, endDate, nik);
            System.out.println(
                    "====================================================================================================");
        } else {
            formatMessageOutput("Pastikan data pelanggan ada");
            System.out.println("============================");
        }

        InputUtilities.pressEnter();

    }

    private void choiceKamar(Date startDate, Date endDate, String nik) {
        try {
            InputUtilities.cls();

            System.out.println("PILIH KAMAR ");
            kamarTableHeader();
            viewAllDataKamar(kamarVM.getListKamar().stream()
                    .filter(kamar -> kamar.getStatusKamar() == AppEnums.StatusKamar.AVAILABLE)
                    .collect(Collectors.toCollection(ArrayList::new)));
            System.out.println(
                    "====================================================================================================");

            System.out.println();
            System.out.print("Masukkan Nomor Kamar yang dipilih dari yang tersedia diatas : ");
            String noKamar = InputUtilities.input.readLine();
            if (!noKamar.isBlank()) {
                transaksiVM.createInitialTransaksi(
                        startDate,
                        endDate,
                        nik,
                        authViewModel.loggedUser,
                        noKamar,
                        AppEnums.Pembayaran.CASH // default
                );

                if (transaksiVM.currentActiveTransaksi != null) {
                    AppRouter.navigateTo(SUB_TRANSAKSI);
                }
            }

            System.out.println();
        } catch (IOException e) {
            formatMessageOutput(e.getMessage());
            // throw new RuntimeException(e);
        }
    }

    private String getNIKCustomer() {
        String result = "";
        System.out.print("Masukkan NIK\t : ");
        String nik = InputUtilities.readLine();

        masterCustomerVM.selectCustomer(nik);
        if (masterCustomerVM.getSelectedCustomer() != null) {
            result = nik;
        } else {
            System.out.println("Data pelanggan tidak ditemukan");
            System.out.print("Daftarkan pelanggan ? (y/n) : ");
            String confirm = InputUtilities.readLine();
            if (confirm != null && confirm.equalsIgnoreCase("y")) {
                // register new pelanggan
                addNewCustomer();
                System.out.println("==============================");
                InputUtilities.pressEnter();

                result = nik;
            }
        }
        return result;
    }

    private void addNewCustomer() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("       ADD NEW CUSTOMER       ");
            System.out.println("==============================");

            System.out.print("NIK\t: ");
            String nik = InputUtilities.input.readLine();

            System.out.print("Nama\t: ");
            String nama = InputUtilities.input.readLine();

            System.out.print("Email\t: ");
            String email = InputUtilities.input.readLine();

            System.out.print("Telp\t: ");
            String telp = InputUtilities.input.readLine();

            masterCustomerVM.addNewCustomer(nik, nama, email, telp);

        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void pilihTransaksi() {
        InputUtilities.cls();
        System.out.println("==============================");
        System.out.println("       PILIH TRANSAKSI      ");
        System.out.println("==============================");

        System.out.println("Pilih Transaksi ");
        System.out.print("Masukkan No Transaksi : ");
        try {
            String noTransaksi = InputUtilities.input.readLine();
            transaksiVM.selectTranasksi(noTransaksi);
            if (transaksiVM.currentActiveTransaksi != null) {
                AppRouter.navigateTo(SUB_TRANSAKSI);
            }
        } catch (IOException e) {
            invalidChoice();
        }
    }

    private boolean conditionHaveToPay() {
        return transaksiVM.currentActiveTransaksi.getStatusTransaksi() == AppEnums.StatusTransaksi.PENDING
                || transaksiVM.currentActiveTransaksi.getStatusTransaksi() == AppEnums.StatusTransaksi.ONGOING
                || transaksiVM.currentActiveTransaksi.getStatusPembayaran() != AppEnums.StatusTransaksiBayar.LUNAS;
    }

    private boolean conditionCanStillEdit() {
        return transaksiVM.currentActiveTransaksi.getStatusTransaksi() == AppEnums.StatusTransaksi.PENDING;
    }

    private boolean conditionOnGoing() {
        return transaksiVM.currentActiveTransaksi.getStatusTransaksi() == AppEnums.StatusTransaksi.ONGOING;
    }

    private void subTransaksiHeader() {
        System.out.println("============================");
        System.out.println("     SUB MENU TRANSAKSI     ");
        System.out.println("============================");
    }

    public void subTransaksiMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.SUB_TRANSAKSI) {
            // - lihat detail
            // - bayar
            // - check in
            // - check out
            // - batalkan transaksi
            // commit transaksi

            InputUtilities.cls();
            subTransaksiHeader();
            System.out.println("Sub-Menu Transaksi " + transaksiVM.currentActiveTransaksi.getNoTransaksi());
            System.out.println();
            System.out.println("1. Lihat Detail");
            if (conditionHaveToPay()) {
                System.out.println("2. Bayar");
            }
            if (conditionCanStillEdit()) {
                System.out.println("---------------------------");
                System.out.println("3. Ubah Tanggal Mulai");
                System.out.println("4. Ubah Tanggal Selesai");
                System.out.println("---------------------------");
                System.out.println("5. Tambah Tamu");
                System.out.println("6. Kurangi Tamu");
                System.out.println("---------------------------");
                System.out.println("7. Tambah Kamar");
                System.out.println("8. Kurangi Kamar");
                System.out.println("---------------------------");
            }
            if (conditionOnGoing()) {
                System.out.println("9. Check In");
            }

            if (!conditionCanStillEdit() && conditionOnGoing()) {
                System.out.println("10. Check Out");
            }
            System.out.println("11. Commit Transaksi");
            System.out.println("0. Kembali");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                if(inputUser.isBlank()){
                    inputUser = "13";
                }
                boolean conditionCantEdit = !conditionCanStillEdit() && Integer.parseInt(inputUser) <= 3
                        && Integer.parseInt(inputUser) >= 10;
                boolean conditionHaveToPay = !conditionHaveToPay() && inputUser.equals("2");
                boolean conditionAlreadyCheckIn = !conditionHaveToPay() && inputUser.equals("10");
                boolean conditionCantCheckin = (!conditionOnGoing()) && inputUser.equals("9");

                if (conditionCantEdit || conditionHaveToPay || conditionAlreadyCheckIn || conditionCantCheckin) {
                    inputUser = "13";
                }
                InputUtilities.cls();

                switch (inputUser) {
                    case "1":
                        viewDetailSelectedTransaksi(transaksiVM.currentActiveTransaksi);
                        InputUtilities.pressEnter();
                        break;
                    case "2":
                        subTransaksiHeader();
                        bayarTransaksi();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "3":
                        subTransaksiHeader();
                        ubahTanggalMulai();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "4":
                        subTransaksiHeader();
                        ubahTanggalSelesai();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "5":
                        subTransaksiHeader();
                        tambahTamu();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "6":
                        subTransaksiHeader();
                        kurangiTamu();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "7":
                        subTransaksiHeader();
                        tambahKamar();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "8":
                        subTransaksiHeader();
                        kurangiKamar();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "9":
                        subTransaksiHeader();
                        checkIn();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "10":
                        subTransaksiHeader();
                        checkOut();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;

                    case "11":
                        subTransaksiHeader();
                        transaksiVM.commitTransaksi();
                        System.out.println("==============================");
                        InputUtilities.pressEnter();
                        break;
                    case "0":
                        transaksiVM.commitTransaksi();

                        AppRouter.navigateTo(TRANSAKSI);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        System.out.println("==============================");
                        InputUtilities.pressEnter();

                }

            } catch (IOException e) {
                invalidChoice();
            }
        }
    }

    private void checkOut() {
        System.out.println("Check out");
        transaksiVM.checkOut();
    }

    private void checkIn() {
        System.out.println("Check in");
        transaksiVM.checkIn();
    }

    private void kurangiKamar() {
        try {
            System.out.println("Kurangi Kamar");
            System.out.print("Masukkan Nomor Kamar : ");
            String noKamar = InputUtilities.input.readLine();
            transaksiVM.removeKamar(noKamar);
        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void tambahKamar() {
        try {
            System.out.println("Tambakan Kamar");
            System.out.print("Masukkan Nomor Kamar : ");
            String noKamar = InputUtilities.input.readLine();
            transaksiVM.addKamar(noKamar);
        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void kurangiTamu() {
        try {
            System.out.println("Hapus tamu dari transaksi");
            System.out.print("Masukkan NIK : ");
            String nik = InputUtilities.input.readLine();
            transaksiVM.removeCustomer(nik);
        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void tambahTamu() {
        System.out.println("tambahkan tamu ke transaksi");
        String nik = getNIKCustomer();
        if (!nik.isBlank()) {
            transaksiVM.addCustomer(nik);
        }
    }

    private void ubahTanggalSelesai() {
        System.out.println("Ubah Tanggal Selesai");
        System.out.print("Masukkan Tanggal Selesai (dd-MM-yyyy) : ");
        Date date = InputUtilities.getDateFromInput();
        if (date != null) {
            transaksiVM.currentActiveTransaksi.setEndDate(date);
        } else {
            formatMessageOutput("Invalid Date");
        }
    }

    private void ubahTanggalMulai() {
        System.out.println("Ubah Tanggal Mulai");

        System.out.print("Masukkan Tanggal Mulai (dd-MM-yyyy) : ");
        Date date = InputUtilities.getDateFromInput();
        if (date != null) {
            transaksiVM.currentActiveTransaksi.setStartDate(date);
        } else {
            formatMessageOutput("Invalid Date");
        }
    }

    private void bayarTransaksi() {
        try {
            System.out.println("Pembayaran Transaksi");
            double amountBayar;
            System.out.print("Masukkan Metode Bayar (bank/ cash) : ");
            AppEnums.Pembayaran metodebayar = InputUtilities.getMetodeBayarFromInput();

            if (metodebayar == null) {
                formatMessageOutput("Please enter a valid method");
                return;
            }
            if (metodebayar == AppEnums.Pembayaran.CASH) {
                System.out.print("Masukkan Amount Bayar : ");
                amountBayar = Double.parseDouble(InputUtilities.input.readLine());
            } else {
                amountBayar = transaksiVM.currentActiveTransaksi.getTotal();
            }
            transaksiVM.bayar(metodebayar, amountBayar);

        } catch (IOException e) {
            invalidChoice();
        }
    }
}
