package org.cadmium.nichijo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private Md5Utils() {
        super();
    }


    public static String encrypt(String msg) {

        byte[] result = null;
        try {
            result = MessageDigest.getInstance("MD5").digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return new BigInteger(1, result).toString(16);
    }

}
