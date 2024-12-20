package Util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Formatting {

    // date format, currency, etc
    public static String formatRupiah(double nominal) {
        String hasil = String.format("%,.0f", nominal).replaceAll(",", ".");
        return "Rp" + hasil;
    }

    public static String formatDate(Date date) {
        if(date == null){
            return "N/A";
        }
        Locale locale = new Locale("id", "ID");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String dateResult = dateFormat.format(date);
        return dateResult;
    }

    public static void formatMessageOutput(String message) {
        System.out.println();
        System.out.println("[ " + message + " ]");
        System.out.println();
    }

    public static void invalidChoice() {
        formatMessageOutput("Invalid choice");
        System.out.println("==============================");
        InputUtilities.pressEnter();
    }
}
