package me.shouheng.utils.data.collection;

import java.io.Serializable;

import me.shouheng.utils.data.ObjectUtils;

/**
 * A container contains three elements.
 *
 * @author <a href="mailto:shouheng2020@gmail.com">Shouheng Wang</a>
 * @version 2020-06-26 16:37
 */
public final class Triple<L, M, R> implements Comparable<Triple<L, M, R>>, Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 1L;

    /** First element */
    public L left;

    /** Second element */
    public M middle;

    /** Third element. */
    public R right;

    /**
     * <p>Obtains an immutable triple of three objects inferring the generic types.</p>
     *
     * <p>This factory allows the triple to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L> the left element type
     * @param <M> the middle element type
     * @param <R> the right element type
     * @param left  the left element, may be null
     * @param middle the middle element, may be null
     * @param right  the right element, may be null
     * @return a triple formed from the three parameters, not null
     */
    public static <L, M, R> Triple<L, M, R> of(final L left, final M middle, final R right) {
        return new Triple<>(left, middle, right);
    }

    public Triple() {
    }

    public Triple(L left, M second, R third) {
        this.left = left;
        this.middle = second;
        this.right = third;
    }

    /**
     * <p>Compares this triple to another based on the three elements.</p>
     *
     * @param obj  the object to compare to, null returns false
     * @return true if the elements of the triple are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Triple<?, ?, ?>) {
            final Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
            return ObjectUtils.equals(left, other.left)
                    && ObjectUtils.equals(middle, other.middle)
                    && ObjectUtils.equals(right, other.right);
        }
        return false;
    }

    /**
     * <p>Returns a suitable hash code.</p>
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return (left == null ? 0 : left.hashCode()) ^
                (middle == null ? 0 : middle.hashCode()) ^
                (right == null ? 0 : right.hashCode());
    }

    /**
     * <p>Returns a String representation of this triple using the format {@code ($left,$middle,$right)}.</p>
     *
     * @return a string describing this object, not null
     */
    @Override
    public String toString() {
        return "(" + left + "," + middle + "," + right + ")";
    }

    @Override
    public int compareTo(Triple<L, M, R> other) {
        int comparison = ((Comparable) left).compareTo(other.left);
        int secondComparison;
        return comparison != 0 ? comparison
                : ((secondComparison = ((Comparable) middle).compareTo(other.middle)) != 0
                ? secondComparison
                : ((Comparable) right).compareTo(other.right));
    }

    /**
     * <p>Formats the receiver using the given format.</p>
     *
     * <p>This uses {@link java.util.Formattable} to perform the formatting. Three variables may
     * be used to embed the left and right elements. Use {@code %1$s} for the left
     * element, {@code %2$s} for the middle and {@code %3$s} for the right element.
     * The default format used by {@code toString()} is {@code (%1$s,%2$s,%3$s)}.</p>
     *
     * @param format  the format string, optionally containing {@code %1$s}, {@code %2$s} and {@code %3$s}, not null
     * @return the formatted string, not null
     */
    public String toString(final String format) {
        return String.format(format, left, middle, right);
    }
}
