package me.shouheng.utils.store;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.shouheng.utils.data.StringUtils;

/**
 * 用来处理单个文件的读写
 *
 * @author WngShhng 2019-05-08 21:30
 */
public final class IOUtils {

    private static final int BUFFER_SIZE = 8192;

    /**
     * 从输入流中读取字符串列表
     *
     * @param is 输入流
     * @param charsetName 字符集名称
     * @return 字符串列表
     */
    public static List<String> is2List(final InputStream is, final String charsetName) {
        BufferedReader reader = null;
        try {
            List<String> list = new ArrayList<>();
            if (StringUtils.isSpace(charsetName)) {
                reader = new BufferedReader(new InputStreamReader(is));
            } else {
                reader = new BufferedReader(new InputStreamReader(is, charsetName));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            safeCloseAll(reader);
        }
    }

    /**
     * 从输入流中读取出一个字节数组
     *
     * @param is 输入流
     * @return 字节数组
     */
    public static byte[] is2Bytes(final InputStream is) {
        if (is == null) return new byte[0];
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            byte[] b = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
                os.write(b, 0, len);
            }
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            safeCloseAll(is, os);
        }
    }

    /**
     * 安全关闭输入和输出流等
     *
     * @param closeables 可以关闭的对象，输入流和输出流
     */
    public static void safeCloseAll(Closeable...closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*-------------------------------------inner methods----------------------------------------*/

    private IOUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
