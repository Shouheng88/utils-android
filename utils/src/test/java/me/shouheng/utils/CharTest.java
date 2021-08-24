package me.shouheng.utils;

import org.junit.Assert;
import org.junit.Test;

import me.shouheng.utils.data.CharUtils;

/**
 * @Author wangshouheng
 * @Time 2021/8/24
 */
public class CharTest {

    @Test
    public void testToTransform() {
        Assert.assertArrayEquals(new char[]{'a', 'a', '/', '1', '2', '3'},
                CharUtils.toLower('A', 'a', '/', '1', '2', '3'));
        Assert.assertArrayEquals(new char[]{'A', 'A', '/', '1', '2', '3'},
                CharUtils.toUpper('A', 'a', '/', '1', '2', '3'));
    }
}
