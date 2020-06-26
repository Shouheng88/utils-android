package me.shouheng.utils.data.function;

/**
 * 判断
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 16:52
 */
public interface Judgement<E> {

    /**
     * 判断某个元素
     *
     * @param e 元素
     * @return  true 满足条件
     */
    boolean judge(E e);
}
