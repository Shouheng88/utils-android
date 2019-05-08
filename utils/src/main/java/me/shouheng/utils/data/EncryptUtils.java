package me.shouheng.utils.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/5/7 23:21
 */
public final class EncryptUtils {

    public static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
