package me.shouheng.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.shouheng.utils.data.collection.CU;
import me.shouheng.utils.data.function.Action;
import me.shouheng.utils.data.function.Judgement;

/**
 * Collection utils test
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 17:07
 */
public class CUTest {

    @Test
    public void testForEach() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        CU.forEach(new Action<String>() {
            @Override
            public void act(String s) {
                System.out.println(s);
            }
        }, list);
        CU.forEach(new Action<String>() {
            @Override
            public void act(String s) {
                System.out.println(s);
            }
        }, "F", "G", "H", "I", "J");
    }

    @Test
    public void testFilter() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        CU.forEach(new Action<Integer>() {
            @Override
            public void act(Integer i) {
                System.out.println(i);
            }
        }, CU.filter(new Judgement<Integer>() {
            @Override
            public boolean judge(Integer i) {
                return i >= 3;
            }
        }, list));
        CU.forEach(new Action<Integer>() {
            @Override
            public void act(Integer integer) {
                System.out.println(integer);
            }
        }, CU.filter(new Judgement<Integer>() {
            @Override
            public boolean judge(Integer i) {
                return i >= 8;
            }
        }, 6, 7 , 8, 9, 10));
    }
}
