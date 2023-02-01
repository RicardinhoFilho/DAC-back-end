/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author leonardozanotti
 */
public final class Security {
    public static String hash(String password) {
        String finalPassword = null;
        String salt = "bantadsdoscrias";

        try {
            finalPassword = hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return finalPassword;
    }

    private static byte[] digest(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(password.getBytes());
        return bytes;
    }

    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        byte[] bytes = digest(password, salt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return sb.toString();
    }
    
    public static String generateStrongPassword() {
        String password = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String possibleNumbers = "0123456789";
        String possibleSymbols = "!@#$%";
        String possible = possibleChars + possibleNumbers + possibleSymbols;
        for (int i = 0; i < 13; i++) {
            password += possible.charAt((int) Math.floor(Math.random() * possible.length()));
        }
        return password;
    }
}