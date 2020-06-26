package me.shouheng.utils.data.collection;

/**
 * 能给包含三个元素的容器
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-06-26 16:37
 */
public class Triple<A, B, C> {

    /**
     * 第一个元素
     */
    private A first;

    /**
     * 第二个元素
     */
    private B second;

    /**
     * 第三个元素
     */
    private C third;

    public Triple() {
    }

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

    public C getThird() {
        return third;
    }

    public void setThird(C third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
