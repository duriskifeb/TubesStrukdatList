package Util;

import Data.AppEnums.AppRumahEnums;

public class GeneratorRumah {
    public static String generateSales(String name, AppRumahEnums.userRole role, String email) {
        String result;

        result = name + "-" + email;
        result = Encryption.hashID(result, 5);
        switch (role) {
            case ADMIN:
                result = result + "-admin";
                break;
            case USER:
                result = result + "-user";
                break;
            // case RESERVATION_AGENT:
            // result = result + "-ra";
            // break;
            // case PAYMENT_AGENT:
            // result = result + "-pa";
            // break;
            // case CLEANING_AGENT:
            // result = result + "-ca";
            // break;
        }
        return result.toLowerCase();

    }

    public static String generateReportNumber(String picName, String dateCreated, String totalPrice,
            String transaksiNumberSum) {
        String result;
        result = picName + "-" + dateCreated + "-" + totalPrice + "-" + transaksiNumberSum;
        result = Encryption.hashID(result, 10);
        return result.toUpperCase();
    }

    public static String generateTransaksiID(String tanggalTransaksi, String totalPrice, String orderName,
            String picNmae, AppRumahEnums.Pembayaran payment) {
        String result;
        result = picNmae + "-" + tanggalTransaksi + "-" + totalPrice + "-" + "-" + orderName + "-" + payment;
        result = Encryption.hashID(result, 10);
        return result.toLowerCase();
    }
}
