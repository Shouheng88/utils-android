package me.shouheng.utils.data.collection;

import java.io.Serializable;
import java.util.Map;

import me.shouheng.utils.data.ObjectUtils;

/**
 * <p>A pair consisting of two elements.</p>
 *
 * <p>This class is an abstract implementation defining the basic API.
 * It refers to the elements as 'left' and 'right'. It also implements the
 * {@code Map.Entry} interface where the key is 'left' and the value is 'right'.</p>
 *
 * <p>Subclass implementations may be mutable or immutable.
 * However, there is no restriction on the type of the stored objects that may be stored.
 * If mutable objects are stored in the pair, then the pair itself effectively becomes mutable.</p>
 *
 * @param <L> the left element type
 * @param <R> the right element type
 *
 * @since 3.0
 */
public final class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 4954918890077093841L;

    /** Left object */
    public L left;

    /** Right object */
    public R right;

    /**
     * <p>Obtains an immutable pair of two objects inferring the generic types.</p>
     *
     * <p>This factory allows the pair to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L> the left element type
     * @param <R> the right element type
     * @param left  the left element, may be null
     * @param right  the right element, may be null
     * @return a pair formed from the two parameters, not null
     */
    public static <L, R> Pair<L, R> of(final L left, final R right) {
        return new Pair<>(left, right);
    }

    public Pair() {
    }

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * <p>Gets the key from this pair.</p>
     *
     * <p>This method implements the {@code Map.Entry} interface returning the
     * left element as the key.</p>
     *
     * @return the left element as the key, may be null
     */
    @Override
    public final L getKey() {
        return left;
    }

    /**
     * <p>Gets the value from this pair.</p>
     *
     * <p>This method implements the {@code Map.Entry} interface returning the
     * right element as the value.</p>
     *
     * @return the right element as the value, may be null
     */
    @Override
    public R getValue() {
        return right;
    }

    @Override
    public R setValue(R value) {
        R old = right;
        right = value;
        return old;
    }

    //-----------------------------------------------------------------------
    /**
     * <p>Compares the pair based on the left element followed by the right element.
     * The types must be {@code Comparable}.</p>
     *
     * @param other  the other pair, not null
     * @return negative if this is less, zero if equal, positive if greater
     */
    @Override
    public int compareTo(final Pair<L, R> other) {
        int comparison = ((Comparable) left).compareTo(other.left);
        return comparison != 0 ? comparison : ((Comparable) right).compareTo(other.right);
    }

    /**
     * <p>Compares this pair to another based on the two elements.</p>
     *
     * @param obj  the object to compare to, null returns false
     * @return true if the elements of the pair are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry<?, ?>) {
            final Map.Entry<?, ?> other = (Map.Entry<?, ?>) obj;
            return ObjectUtils.equals(getKey(), other.getKey())
                    && ObjectUtils.equals(getValue(), other.getValue());
        }
        return false;
    }

    /**
     * <p>Returns a suitable hash code.
     * The hash code follows the definition in {@code Map.Entry}.</p>
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // see Map.Entry API specification
        return (getKey() == null ? 0 : getKey().hashCode()) ^
                (getValue() == null ? 0 : getValue().hashCode());
    }

    /**
     * <p>Returns a String representation of this pair using the format {@code ($left,$right)}.</p>
     *
     * @return a string describing this object, not null
     */
    @Override
    public String toString() {
        return "(" + left + ',' + right + ')';
    }

    /**
     * <p>Formats the receiver using the given format.</p>
     *
     * <p>This uses {@link java.util.Formattable} to perform the formatting. Two variables may
     * be used to embed the left and right elements. Use {@code %1$s} for the left
     * element (key) and {@code %2$s} for the right element (value).
     * The default format used by {@code toString()} is {@code (%1$s,%2$s)}.</p>
     *
     * @param format  the format string, optionally containing {@code %1$s} and {@code %2$s}, not null
     * @return the formatted string, not null
     */
    public String toString(final String format) {
        return String.format(format, left, right);
    }

}
