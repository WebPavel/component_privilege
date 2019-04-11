package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5Encode(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("不支持的加密算法");
        }
        String md5Code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code = "0" + md5Code;
        }
        return md5Code;
    }
}
