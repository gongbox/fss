package com.fss.common;


import com.fss.common.function.Consumer;
import com.fss.common.function.Consumer2;
import com.fss.common.function.Function;
import com.fss.common.function.Function2;
import com.fss.common.function.Predicate;
import com.fss.common.function.Supplier;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class common {
    public static <T> boolean contains(T[] datas, T t, Function2<T, T, Boolean> condition) {
        for (T item : datas) {
            if (condition.apply(item, t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] datas, T t) {
        if (t == null) {
            for (T item : datas)
                if (item == null) return true;
        } else {
            for (T item : datas)
                if (t.equals(item)) return true;

        }
        return false;
    }

    public static <T> boolean contains(Iterable<T> datas, T
            t, Function2<T, T, Boolean> condition) {
        for (T item : datas) {
            if (condition.apply(item, t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(Iterable<T> datas, T t) {
        if (t == null) {
            for (T item : datas)
                if (item == null) return true;
        } else {
            for (T item : datas)
                if (t.equals(item)) return true;

        }
        return false;
    }

    public static <T> T getIfNull(T t1, T t2) {
        return t1 == null ? t2 : t1;
    }

    public static <T> T getIfNull(T t1, Supplier<T> supplier) {
        return t1 == null ? supplier.get() : t1;
    }

    public static <T> List<T> filter(Iterable<T> datas, Predicate<T> filter) {
        List<T> results = new ArrayList<>();
        if (datas == null) return results;
        for (T item : datas) {
            if (filter.test(item)) {
                results.add(item);
            }
        }
        return results;
    }

    public static <T> List<T> filter(T[] datas, Predicate<T> filter) {
        List<T> results = new ArrayList<>();
        if (datas == null) return results;
        for (T item : datas) {
            if (filter.test(item)) {
                results.add(item);
            }
        }
        return results;
    }

    public static <T> T find(Iterable<T> datas, Predicate<T> filter) {
        if (datas == null) return null;
        for (T item : datas) {
            if (filter.test(item)) {
                return item;
            }
        }
        return null;
    }

    public static <T> T find(T[] datas, Predicate<T> filter) {
        if (datas == null) return null;
        for (T item : datas) {
            if (filter.test(item)) {
                return item;
            }
        }
        return null;
    }

    public static <T> int findIndex(Iterable<T> datas, Predicate<T> predicate) {
        if (datas == null) return -1;
        int index = 0;
        for (T t : datas) {
            if (predicate.test(t)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static <T, R> List<R> map(Iterable<T> datas, Function<T, R> map) {
        List<R> results = new ArrayList<>();
        if (datas == null) return results;
        for (T item : datas) {
            results.add(map.apply(item));
        }
        return results;
    }

    public static <T, R> List<R> map(T[] datas, Function<T, R> map) {
        List<R> results = new ArrayList<>();
        if (datas == null) return results;
        for (T item : datas) {
            results.add(map.apply(item));
        }
        return results;
    }

    public static <
            T, R> List<R> mapAndFilter(Iterable<T> datas, Function<T, R> map, Predicate<R> filter) {
        return common.filter(common.map(datas, map), filter);
    }

    public static <
            T, R> List<R> filterAndMap(Iterable<T> datas, Predicate<T> filter, Function<T, R> map) {
        return common.map(common.filter(datas, filter), map);
    }

    public static <T> T[] toArray(Iterable<T> datas, Class clazz) {
        if (datas == null) return null;
        int size = 0;
        for (T ignored : datas) {
            size++;
        }
        T[] array = (T[]) Array.newInstance(clazz, size);
        int index = 0;
        for (T t : datas) {
            array[index++] = t;
        }
        return array;
    }

    public static <T> List<T> toList(Iterable<T> datas) {
        List<T> results = new ArrayList<>();
        if (datas == null) return results;
        for (T item : datas) {
            results.add(item);
        }
        return results;
    }

    public static <T> void forEach(T[] datas, Consumer<T> consumer) {
        if (datas != null) {
            for (T t : datas) {
                consumer.accept(t);
            }
        }
    }

    public static <T> void forEach(Iterable<T> datas, Consumer<T> consumer) {
        if (datas != null) {
            for (T t : datas) {
                consumer.accept(t);
            }
        }
    }

    public static <T> void forEachIndexed
            (Iterable<T> datas, Consumer2<Integer, T> consumer) {
        int index = 0;
        if (datas != null) {
            for (T t : datas) {
                consumer.accept(index++, t);
            }
        }
    }

    public static <T> void forEachIndexed(T[] datas, Consumer2<Integer, T> consumer) {
        int index = 0;
        if (datas != null) {
            for (T t : datas) {
                consumer.accept(index++, t);
            }
        }
    }

    public static <T> void forEachWithBreak(Iterable<T> datas, Predicate<T> predicate) {
        if (datas != null) {
            for (T t : datas) {
                if (predicate.test(t)) {
                    break;
                }
            }
        }
    }

    public static <T> void forEachWithBreak(T[] datas, Predicate<T> predicate) {
        if (datas != null) {
            for (T t : datas) {
                if (predicate.test(t)) {
                    break;
                }
            }
        }
    }

    public static <T> void forEachWithBreak
            (Iterable<T> datas, Function2<Integer, T, Boolean> function) {
        int index = 0;
        if (datas != null) {
            for (T t : datas) {
                if (function.apply(index++, t)) {
                    break;
                }
            }
        }
    }

    public static <T> void forEachWithBreak(T[]
                                                    datas, Function2<Integer, T, Boolean> function) {
        int index = 0;
        if (datas != null) {
            for (T t : datas) {
                if (function.apply(index++, t)) {
                    break;
                }
            }
        }
    }

    public static <T> void sort(T[] datas, Comparator<T> comparator) {
        Arrays.sort(datas, comparator);
    }

    public static <T> void sort(List<T> datas, Comparator<T> comparator) {
        Collections.sort(datas, comparator);
    }

    public static <T> boolean in(T value, Predicate<T> predicate, T... objs) {
        return find(Arrays.asList(objs), predicate) != null;
    }

    public static <T> boolean in(final T value, T... objs) {
        return in(value, new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return (value == t) || (value != null && value.equals(t));
            }
        }, objs);
    }

    public static <T> T clone(Object object) {
        if (object != null) {
            try {
                Method method = object.getClass().getMethod("clone");
                return (T) method.invoke(object);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> boolean isEmpty(Iterable<T> t) {
        if (t == null) return true;
        return !t.iterator().hasNext();
    }

    public static <T> boolean isEmpty(T[] t) {
        return t == null || t.length == 0;
    }

    public static boolean isEmpty(String t) {
        return t == null || t.isEmpty();
    }

    public static <T> T getFirstOrNull(T[] datas) {
        return isEmpty(datas) ? null : datas[0];
    }

    public static <T> T getFirstOrNull(Iterable<T> datas) {
        return isEmpty(datas) ? null : datas.iterator().next();
    }
}
