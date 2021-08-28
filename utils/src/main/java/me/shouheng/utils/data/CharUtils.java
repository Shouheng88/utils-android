package me.shouheng.utils.data;

import java.nio.charset.Charset;

/**
 * Character utils class.
 *
 * @Author wangshouheng
 * @Time 2021/8/24
 */
public final class CharUtils {

    public static int CASE_MASK = 0x20; // 0010 0000

    /**
     * US-ASCII: seven-bit ASCII, the Basic Latin block of the Unicode character set (ISO646-US).
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#US_ASCII} instead.
     */
    public static final Charset US_ASCII = Charset.forName("US-ASCII");

    /**
     * ISO-8859-1: ISO Latin Alphabet Number 1 (ISO-LATIN-1).
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#ISO_8859_1} instead.
     */
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    /**
     * UTF-8: eight-bit UCS Transformation Format.
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#UTF_8} instead.
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * UTF-16BE: sixteen-bit UCS Transformation Format, big-endian byte order.
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#UTF_16BE} instead.
     */
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");

    /**
     * UTF-16LE: sixteen-bit UCS Transformation Format, little-endian byte order.
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#UTF_16LE} instead.
     */
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

    /**
     * UTF-16: sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order
     * mark.
     *
     * <p><b>Note for Java 7 and later:</b> this constant should be treated as deprecated; use {@link
     * java.nio.charset.StandardCharsets#UTF_16} instead.
     */
    public static final Charset UTF_16 = Charset.forName("UTF-16");

    /**
     * Is given char Chinese, Japanese or Korean.
     *
     * @param c the char
     * @return  true if it is.
     */
    public static boolean isCJK(char c) {
        return c >= 0x4E00 && c <= 0x9FA5 // 中日韩统一表意文字
                || c == 0x3007 // 1个汉字
                || c >= 0x30A0 && c <= 0x30FF // jp
                || c >= 0x3041 && c <= 0x309F && c != 0x3097 && c != 0x3098
                || c >= 0x31F0 && c <= 0x31FF
                || c >= 0xAC00 && c <= 0xD7AF // kr
                ;
    }

    /**
     * Is given char number in ASCII.
     *
     * @param c the char
     * @return  true if it is.
     */
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Is given char the upper char in ASCII.
     *
     * @param c the char
     * @return  true if it is.
     */
    public static boolean isUpperChar(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * Is given char the lower char in ASCII.
     *
     * @param c the char.
     * @return  true if it is.
     */
    public static boolean isLowerChar(char c) {
        return c >= 'a' && c <= 'z';
    }

    /**
     * Change from upper to lower char in ASCII.
     *
     * @param c the char.
     * @return  the lower char.
     */
    public static char toLower(char c) {
        return isUpperChar(c) ? (char) (c | CASE_MASK) : c;
    }

    /**
     * Change char array from upper to lower in ASCII.
     *
     * @param cs the char array.
     * @return   the lower char array.
     */
    public static char[] toLower(char...cs) {
        for (int i=cs.length-1; i>=0; i--) {
            cs[i] = toLower(cs[i]);
        }
        return cs;
    }

    /**
     * Change from lower to upper char in ASCII.
     *
     * @param c the char.
     * @return  the upper char.
     */
    public static char toUpper(char c) {
        return isLowerChar(c) ? (char) (c ^ CASE_MASK) : c;
    }

    /**
     * Change char array from lower to upper in ASCII.
     *
     * @param cs the char array.
     * @return   the upper char array.
     */
    public static char[] toUpper(char...cs) {
        for (int i=cs.length-1; i>=0; i--) {
            cs[i] = toUpper(cs[i]);
        }
        return cs;
    }

    /*----------------------------------inner methods--------------------------------------*/

    private CharUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
