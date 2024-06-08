package com.apep.cleaningbuddy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptor {
    public static byte[] hashPassword(byte[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
