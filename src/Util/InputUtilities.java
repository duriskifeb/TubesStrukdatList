package Util;

import Data.AppEnums.AppEnums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputUtilities {

    public static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine(){
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace(); // handle Exception
            return null;
        }
    }

    public static int readInt(){
        try {
            return Integer.parseInt(input.readLine());
        } catch (NumberFormatException | IOException e) {
            System.err.println("Input tidak valid. masukkan Integer");
            return readInt(); // rekursif
        }
    }

    public static void closeReader() {
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace(); // handle Exception
        }
    }

    public static void cls() {
        // wont work on IDE
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressEnter(){
        System.out.println();
        System.out.print("Press ENTER to continue");
        try {
            InputUtilities.input.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static AppEnums.JenisKamar getJenisKamarFromInput() {
        try {
            String inputJenis = InputUtilities.input.readLine();
            return switch (inputJenis.toLowerCase()) {
                case "single" -> AppEnums.JenisKamar.SINGLE;
                case "double" -> AppEnums.JenisKamar.DOUBLE;
                case "family" -> AppEnums.JenisKamar.FAMILY;
                case "vip" -> AppEnums.JenisKamar.VIP;
                case "business" -> AppEnums.JenisKamar.BUSINESS;
                default -> null;
            };
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static AppEnums.StatusKamar getStatusKamarFromInput() {
        try {
            String inputStatus = InputUtilities.input.readLine();
            return switch (inputStatus.toLowerCase()) {
                case "available" -> AppEnums.StatusKamar.AVAILABLE;
                case "booked" -> AppEnums.StatusKamar.BOOKED;
                case "cleaning" -> AppEnums.StatusKamar.CLEANING;
                case "occupied" -> AppEnums.StatusKamar.OCCUPIED;
                default -> null;
            };
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static AppEnums.Pembayaran getMetodeBayarFromInput(){
        try {
            String inputStatus = InputUtilities.input.readLine();
            return switch (inputStatus.toLowerCase()) {
                case "cash" -> AppEnums.Pembayaran.CASH;
                case "bank" ->AppEnums.Pembayaran.BANK;
                default -> null;
            };
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public static Date getDateFromInput() {
        try {
            String inputDate = InputUtilities.input.readLine();
            // Replace "/" with "-" in the input string
            inputDate = inputDate.replaceAll("/", "-");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Parse the formatted string to a Date object
            return dateFormat.parse(inputDate);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
}
