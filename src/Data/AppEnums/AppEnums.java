package Data.AppEnums;

public class AppEnums {
    // put enums here
    public enum StatusKamar {
        AVAILABLE,
        OCCUPIED,
        BOOKED,
        CLEANING
    }

    public enum JenisKamar{
        SINGLE,
        DOUBLE,
        FAMILY,
        VIP,
        BUSINESS
    }

    public enum UserRole {
        PEGAWAI,
        MANAGER
    }

    public enum Pembayaran{
        BANK,
        CASH
    }

    public enum StatusTransaksi{
        PENDING,
        ONGOING,
        DONE,
        FAILED,
        CANCELLED
    }
    public enum StatusTransaksiBayar{
        PENDING_PAYMENT, // belum dibayar sama sekali
        PAID, // sudah dibayar tapi belum lunas
        LUNAS,
        REFUND, // REFUND
    }

}
