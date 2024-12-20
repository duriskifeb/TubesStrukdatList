package Util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
    public static String hashPassword(String password){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder enc = Base64.getEncoder();

//        System.out.printf("hash: %s%n", enc.encodeToString(hash));

        return enc.encodeToString(hash);
    }

    public static String hashID(String data, int length){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder enc = Base64.getEncoder();

//        System.out.printf("hash: %s%n", enc.encodeToString(hash));

        return enc.encodeToString(hash).substring(0, length);
    }
}
