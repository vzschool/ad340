package com.example.ad340;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Security {

    public static String encryptSHA256(String str) {
        String ps = "";
        try {/*from  w  ww  .  j  av a 2  s.  com*/
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            ps = transformHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            Log.e("Simi - StringUtils", e.getMessage());
            return "";
        }
        return ps;
    }

    private static String transformHex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}