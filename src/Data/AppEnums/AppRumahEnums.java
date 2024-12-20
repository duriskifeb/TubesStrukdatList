package Data.AppEnums;

import java.security.PublicKey;

public class AppRumahEnums {
    // put enums here
    public enum StatusRumah {
        AVAILABLE,
        OCCUPIED,
        BOOKED,
        CLEANING
    }

    public enum TypeRumah {
        VILLA,
        RUKO,
        LANCAR
    }

    public enum FasilitasRumah {
        KAMERA,
        AC,
        TV,
        KITCHEN,
        BATHROOM,
    }

    public enum TipeKamar {
        DELUXE,
        DELICATO,
        STANDARD
    }

    public enum JenisKamar {
        SINGLE,
        DOUBLE,
        FAMILY,
        VIP,
        BUSINESS
    }

    public enum userRole {
        ADMIN,
        USER,
    }

    public enum Pembayaran {
        BANK,
        CASH
    }

    public enum StatusTransaksi {
        PENDING,
        ONGOING,
        DONE,
        FAILED,
        CANCELLED
    }

    public enum StatusTransaksiBayar {
        PENDING_PAYMENT, // durung bayar blass iki
        PAID, // sudah bayar tapi belom lunas
        CANCELED,
        REFFUND // kembalaikan uanng
    }
}
