package View.Master;

import ViewModel.AuthViewModel.AuthViewModel;

import static Util.Formatting.invalidChoice;
import static View.Components.KamarView.kamarTableHeader;
import static View.Components.KamarView.viewAllDataKamar;
import static View.Components.TransaksiView.viewAllTransaksi;
import static Util.Formatting.formatMessageOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import Data.AppEnums.AppEnums;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterCustomerViewModel;
import ViewModel.MasterViewModel.MasterKamarViewModel;
import ViewModel.MasterViewModel.MasterTransaksiViewModel;
import ViewModel.TransaksiViewModel.TransaksiViewModel;

public class MasterTransaksiMenu {
    private final MasterTransaksiViewModel masterTransaksiVM;
    private final MasterCustomerViewModel masterCustomerVM;
    private final MasterKamarViewModel masterKamarVM;
    private final AuthViewModel authViewModel;
    private final TransaksiViewModel transaksiViewModel;

    public MasterTransaksiMenu(
            MasterTransaksiViewModel masterTransaksiVM,
            MasterCustomerViewModel masterCustomerVM,
            MasterKamarViewModel masterKamarVM,
            AuthViewModel authViewModel,
            TransaksiViewModel transaksiViewModel) {
        this.masterTransaksiVM = masterTransaksiVM;
        this.masterCustomerVM = masterCustomerVM;
        this.masterKamarVM = masterKamarVM;
        this.authViewModel = authViewModel;
        this.transaksiViewModel = transaksiViewModel;
    }

    String inputUser;

    public void showMasterTransaksiMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.MASTER_TRANSAKSI) {
            InputUtilities.cls();
            System.out.println("============================");
            System.out.println("      MASTER TRANSAKSI      ");
            System.out.println("============================");
            System.out.println("1. Lihat List Transaksi");
            System.out.println("2. Buat Transaksi Baru");
            System.out.println("3. Pilih Transaksi");
            System.out.println("4. Batalkan Transaksi");
            System.out.println("5. Hapus Transaksi");
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
                        chooseTransaksi();
                        break;
                    case "4":
                        batalkanTransaksi();
                        break;
                    case "5":
                        hapusTransaksi();
                        break;
                    case "0":
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_MAIN_MENU);
                        break;
                    default:
                        formatMessageOutput("Invalid Choice");
                }
                System.out.println();
            } catch (IOException e) {
                invalidChoice();
            }

        }
    }

    private void hapusTransaksi() {
        System.out.println("Hapus Transaksi ");
        System.out.print("Masukkan No Transaksi yang ingin dihapus : ");
        try {
            String noTransaksi = InputUtilities.input.readLine();
            masterTransaksiVM.selectTranasksi(noTransaksi);
            if (masterTransaksiVM.selectedTransaksi != null) {
                System.out.print("Apakah anda yakin ingin menghapus transaksi ? (y/n) : ");
                String konfirmasi = InputUtilities.input.readLine();

                if (konfirmasi.equalsIgnoreCase("y")) {
                    masterTransaksiVM.deleteTransaksi();
                } else {
                    formatMessageOutput("Aksi Dibatalkan");
                }

            }
        } catch (IOException e) {
            invalidChoice();
        }
    }

    private void batalkanTransaksi() {
        System.out.println("Batalkan Transaksi ");
        System.out.print("Masukkan No Transaksi yang ingin dibatalkan : ");
        try {
            String noTransaksi = InputUtilities.input.readLine();
            masterTransaksiVM.selectTranasksi(noTransaksi);
            if (masterTransaksiVM.selectedTransaksi != null) {
                System.out.print("Apakah anda yakin ingin membatalkan transaksi ? (y/n) : ");
                String konfirmasi = InputUtilities.input.readLine();

                if (konfirmasi.equalsIgnoreCase("y")) {
                    masterTransaksiVM.batalkanTransaksi();
                } else {
                    formatMessageOutput("Aksi Dibatalkan");
                }

            }
        } catch (IOException e) {
            invalidChoice();
        }

    }

    private void showAllTransaksi() {
        InputUtilities.cls();
        // headerViewTransaksi();
        viewAllTransaksi(masterTransaksiVM.getAllTransaksi());
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
            chooseKamar(startDate, endDate, nik);
            System.out.println("====================================================================================================");
        } else {
            formatMessageOutput("Pastikan data pelanggan ada");
            System.out.println("============================");
        }

        InputUtilities.pressEnter();

    }

    private void chooseKamar(Date startDate, Date endDate, String nik) {
        InputUtilities.cls();
        System.out.println("============================");
        System.out.println("        NEW TRANSAKSI       ");
        System.out.println("============================");

        System.out.println();

        try {
            InputUtilities.cls();

            System.out.println("PILIH KAMAR ");
            kamarTableHeader();
            viewAllDataKamar(masterKamarVM.getListKamar().stream()
                    .filter(kamar -> kamar.getStatusKamar() == AppEnums.StatusKamar.AVAILABLE)
                    .collect(Collectors.toCollection(ArrayList::new)));
            System.out.println("====================================================================================================");

            System.out.println();
            System.out.print("Masukkan Nomor Kamar yang dipilih dari yang tersedia diatas : ");
            String noKamar = InputUtilities.input.readLine();
            
                if (!(noKamar.isBlank())) {
                    transaksiViewModel.createInitialTransaksi(
                            startDate,
                            endDate,
                            nik,
                            authViewModel.loggedUser,
                            noKamar,
                            AppEnums.Pembayaran.CASH // default
                    );

                    if (transaksiViewModel.currentActiveTransaksi != null) {
                        AppRouter.navigateTo(AppRouter.AppRoute.SUB_TRANSAKSI);
                    }
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("============================");
        InputUtilities.pressEnter();

    }

    private void chooseTransaksi() {
        System.out.println("Pilih Transaksi ");
        System.out.print("Masukkan No Transaksi : ");
        try {
            String noTransaksi = InputUtilities.input.readLine();
            transaksiViewModel.selectTranasksi(noTransaksi);
            if (transaksiViewModel.currentActiveTransaksi != null) {
                AppRouter.navigateTo(AppRouter.AppRoute.SUB_TRANSAKSI);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNIKCustomer() {
        String result = "";
        System.out.print("Masukkan NIK : ");
        String nik = InputUtilities.readLine();

        masterCustomerVM.selectCustomer(nik);
        if (masterCustomerVM.getSelectedCustomer() != null) {
            result = nik;
            System.out.println("Customer selected: " + masterCustomerVM.getSelectedCustomer().getNama());
        } else {
            System.out.println("Data pelanggan tidak ditemukan");
            System.out.print("Daftarkan pelanggan ? (y/n) : ");
            String confirm = InputUtilities.readLine();
            if (confirm != null && confirm.equalsIgnoreCase("y")) {
                // register new pelanggan
                addNewCustomer();
                result = nik;
            }
        }
        return result;
    }

    private void addNewCustomer() {
        try {
            System.out.print("NIK\t: ");
            String nik = InputUtilities.input.readLine();

            System.out.print("Nama\t: ");
            String nama = InputUtilities.input.readLine();

            System.out.print("Email\t: ");
            String email = InputUtilities.input.readLine();

            System.out.print("Telp\t: ");
            String telp = InputUtilities.input.readLine();

            masterCustomerVM.addNewCustomer(nik, nama, email, telp);

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
