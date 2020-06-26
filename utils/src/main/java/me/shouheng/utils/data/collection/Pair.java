package me.shouheng.utils.data.collection;

/**
 * 能给包含两个元素的容器
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 16:35
 */
public class Pair<A, B> {

    /**
     * 第一个元素
     */
    private A first;

    /**
     * 第二个元素
     */
    private B second;

    public Pair() {
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
