package com.nubiform.idus;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class EncryptionUtils {
    public static String encrypt(String s) {
        log.debug(s);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes());
            String encrypted = byteToHexString(messageDigest.digest());
            log.debug(encrypted);
            return encrypted;
        }
        catch (Exception e) {
            return s;
        }
    }

    public static String byteToHexString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : data) {
            stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
