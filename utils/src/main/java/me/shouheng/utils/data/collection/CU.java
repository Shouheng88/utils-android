package me.shouheng.utils.data.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import me.shouheng.utils.data.function.Action;
import me.shouheng.utils.data.function.Judgement;

/**
 * 容器相关的工具类
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 16:40
 */
public final class CU {

    /**
     * 每个元素执行的操作
     *
     * @param action 动作
     * @param c      容器
     * @param <E>    元素类型
     */
    public static <E> void forEach(Action<E> action, Collection<E> c) {
        for (E e : c) {
            action.act(e);
        }
    }

    public static <E> void forEach(Action<E> action, E...es) {
        for (E e : es) {
            action.act(e);
        }
    }

    /**
     * 按照要求过滤出所有的元素
     *
     * @param j   条件
     * @param c   容器
     * @param <E> 元素类型
     * @return    结果
     */
    public static <E> Collection<E> filter(Judgement<E> j, Collection<E> c) {
        Iterator<E> it = c.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (!j.judge(e)) {
                it.remove();
            }
        }
        return c;
    }

    public static <E> E[] filter(final Judgement<E> j, E...es) {
        final List<E> list = new ArrayList<>();
        forEach(new Action<E>() {
            @Override
            public void act(E e) {
                if (j.judge(e)) {
                    list.add(e);
                }
            }
        }, es);
        return (E[]) list.toArray();
    }

    /*----------------------------------inner methods--------------------------------------*/

    public CU() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
