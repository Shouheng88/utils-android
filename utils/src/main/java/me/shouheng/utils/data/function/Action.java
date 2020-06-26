package me.shouheng.utils.data.function;

/**
 * 动作
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 16:43
 */
public interface Action<T> {

    /**
     * 要做的动作
     *
     * @param t 元素
     */
    void act(T t);
}
