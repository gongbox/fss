package com.gongbo.fss.common.kotlin;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a generic pairOf of two values.
 * <p>
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Pair exhibits value semantics, i.e. two pairs are equal if both components are equal.
 * <p>
 * An example of decomposing it into values:
 *
 * @sample samples.misc.Tuples.pairDestructuring
 * @property first First value.
 * @property second Second value.
 * @constructor Creates a new instance of Pair.
 */
public class Pair<A, B> implements Serializable {
    public A first;
    public B second;

    public Pair() {
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> pairOf(A first, B second) {
        return new Pair<>(first, second);
    }


    public <T> List<T> toList() {
        return Arrays.asList((T) first, (T) second);
    }

    /**
     * Returns string representation of the [Pair] including its [first] and [second] values.
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
