package com.fss.common.kotlin;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a triad of values
 * <p>
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * tripleOf exhibits value semantics, i.e. two triples are equal if all three components are equal.
 * An example of decomposing it into values:
 *
 * @sample samples.misc.Tuples.tripleDestructuring
 * @property first First value.
 * @property second Second value.
 * @property third Third value.
 */
public class Triple<A, B, C> implements Serializable {
    public A first;
    public B second;
    public C third;

    public Triple() {
    }

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <A, B, C> Triple<A, B, C> tripleOf(A first, B second, C third) {
        return new Triple<>(first, second, third);
    }

    public <T> List<T> toList() {
        return Arrays.asList((T) first, (T) second, (T) third);
    }

    /**
     * Returns string representation of the [tripleOf] including its [first], [second] and [third] values.
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
