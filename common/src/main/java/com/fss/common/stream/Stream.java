package com.fss.common.stream;

import com.fss.common.function.Consumer;
import com.fss.common.function.Consumer2;
import com.fss.common.function.Function;
import com.fss.common.function.Predicate;
import com.fss.common.function.Supplier;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by $USER_NAME on 2019/1/10.
 */
public class Stream<T> extends AbstractStream<T, Stream<T>> {

    public Stream(Iterable<T> datas) {
        super(datas);
    }

    public Stream(T... datas) {
        super(datas);
    }

    public static <T> Stream<T> streamOf(Iterable<T> datas) {
        return new Stream<>(datas);
    }

    public static <T> Stream<T> streamOf(T... datas) {
        return new Stream<>(datas);
    }

    public static <E> Builder<E> streamBuilderOf(E data) {
        return new Builder<>(data);
    }

    public static class Builder<E> {
        protected Object object;

        public Builder() {
        }

        public Builder(E object) {
            this.object = object;
        }

        @SuppressWarnings("unchecked")
        public <R> Builder<R> map(Function<E, R> function) {
            if (object != null) {
                object = function.apply((E) object);
            }
            return (Builder<R>) this;
        }

        public Builder<E> ifNull(Supplier<E> supplier) {
            if (object == null) {
                object = supplier.get();
            }
            return this;
        }

        public Builder<E> consume(Consumer<E> consumer) {
            consumer.accept((E) object);
            return this;
        }

        public <A> Stream<A> builder(Class<A> aClass) {
            if (object == null) {
                return streamOf();
            } else if (object instanceof Iterable) {
                return streamOf((Iterable) object);
            } else if (object instanceof Object[]) {
                return (Stream<A>) streamOf((Object[]) object);
            }
            return null;
        }
    }

    /**
     * 将当前列表所有元素转化为另一类型的元素，并返回新的Stream对象
     *
     * @param map
     * @param <R>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <R> Stream<R> map(Function<T, R> map) {
        int index = 0;
        for (T item : mData) {
            mData.set(index++, (T) map.apply(item));
        }
        return (Stream<R>) this;
    }

    /**
     * 过滤元素，条件为flase时移除，为true时保留
     *
     * @param filter
     * @return
     */
    public Stream<T> filter(Predicate<T> filter) {
        Iterator<T> iterator = mData.iterator();
        for (; iterator.hasNext(); ) {
            T item = iterator.next();
            if (!filter.test(item)) {
                iterator.remove();
            }
        }
        return this;
    }

    /**
     * 过滤为空的元素
     *
     * @return
     */
    public Stream<T> filterNull() {
        return filter(new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return t != null;
            }
        });
    }

    /**
     * 遍历元素
     *
     * @param consumer
     * @return
     */
    public Stream<T> consumer(Consumer<List<T>> consumer) {
        consumer.accept(mData);
        return this;
    }

    /**
     * 遍历元素
     *
     * @param consumer
     * @return
     */
    public Stream<T> forEach(Consumer<T> consumer) {
        for (T item : mData) {
            consumer.accept(item);
        }
        return this;
    }

    /**
     * 遍历元素，带索引
     *
     * @param consumer
     * @return
     */
    public Stream<T> forEachIndexed(Consumer2<Integer, T> consumer) {
        int index = 0;
        for (T item : mData) {
            consumer.accept(index++, item);
        }
        return this;
    }

    /**
     * 查找第一个满足要求的元素
     *
     * @param condition
     * @return
     */
    public T find(Predicate<T> condition) {
        for (T item : mData) {
            if (condition.test(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 判断是否存在满足某一条件的数据
     *
     * @return
     */
    public boolean any(Predicate<T> predicate) {
        return find(predicate) != null;
    }

    /**
     * 排序
     *
     * @param comparator
     * @return
     */
    public Stream<T> sort(Comparator<T> comparator) {
        if (mData.isEmpty()) return null;
        Collections.sort(mData, comparator);
        return this;
    }

    /**
     * 求取最大项
     *
     * @param comparator
     * @return
     */
    public T min(Comparator<T> comparator) {
        if (mData.isEmpty()) return null;
        return Collections.min(mData, comparator);
    }

    /**
     * 求取最大项
     *
     * @param comparator
     * @return
     */
    public T max(Comparator<T> comparator) {
        if (mData.isEmpty()) return null;
        return Collections.max(mData, comparator);
    }

}
