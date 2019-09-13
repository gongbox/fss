package com.gongbo.fss.common.stream;




import com.gongbo.fss.common.function.Predicate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @param <E> 元素类型
 * @param <T> 继承类类型
 */
@SuppressWarnings("unchecked")
public abstract class AbstractStream<E, T extends AbstractStream> {

    protected ArrayList<E> mData = new ArrayList<>();

    public AbstractStream(Iterable<E> datas) {
        if (datas != null) {
            for (E item : datas) {
                mData.add(item);
            }
        }
    }

    public AbstractStream(E... datas) {
        mData.addAll(Arrays.asList(datas));
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public Optional<E> findFirst() {
        return Optional.ofNullable(getFirst());
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public E getFirst() {
        return isEmpty() ? null : mData.iterator().next();
    }

    /**
     * 判断是否包含元素
     *
     * @param item
     * @return
     */
    public boolean contains(E item) {
        return mData.contains(item);
    }


    /**
     * 添加元素
     *
     * @param element
     * @return
     */
    public T add(E element) {
        mData.add(element);
        return (T) this;
    }

    /**
     * 添加多个元素
     *
     * @param elements
     * @return
     */
    public T addAll(E... elements) {
        mData.addAll(Arrays.asList(elements));
        return (T) this;
    }

    /**
     * 添加一个集合
     *
     * @param collection
     * @return
     */
    public T addAll(Collection<? extends E> collection) {
        mData.addAll(collection);
        return (T) this;
    }

    /**
     * 移除元素
     *
     * @param element
     * @return
     */
    public T remove(E element) {
        mData.remove(element);
        return (T) this;
    }

    /**
     * 根据索引移除元素
     *
     * @param index
     * @return
     */
    public T remove(int index) {
        mData.remove(index);
        return (T) this;
    }

    /**
     * 过滤元素，条件为true时移除，为flase时保留
     *
     * @param filter
     * @return
     */
    public T removeIf(Predicate<E> filter) {
        Iterator<E> iterator = mData.iterator();
        while (iterator.hasNext()) {
            E item = iterator.next();
            if (filter.test(item)) {
                iterator.remove();
            }
        }
        return (T) this;
    }


    /**
     * 转化为数组返回
     *
     * @return
     */
    public Object[] toArray() {
        return mData.toArray();
    }

    /**
     * 转化为数组返回
     *
     * @return
     */
    public E[] toArray(Class clazz) {
        E[] array = (E[]) Array.newInstance(clazz, mData.size());
        int index = 0;
        for (E t : mData) {
            array[index++] = t;
        }
        return array;
    }

    /**
     * 转化为List类型返回
     *
     * @param clazz
     * @param <T2>
     * @return
     */
    public <T2 extends List<E>> T2 toList(Class<T2> clazz) {
        if (mData.getClass() == clazz) {
            return (T2) mData;
        }
        T2 results = null;
        try {
            results = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assert results != null;
        results.addAll(mData);
        return results;
    }

    /**
     * 转化为ArrayList
     *
     * @return
     */
    public ArrayList<E> toArrayList() {
        return mData;
    }

    /**
     * LinkedList
     *
     * @return
     */
    public LinkedList<E> toLinkedList() {
        return toList(LinkedList.class);
    }

    /**
     * 转化为ArrayList
     *
     * @return
     */
    public List<E> toList() {
        return mData;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return mData.isEmpty();
    }

    /**
     * 判断是否非空
     *
     * @return
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    /**
     * 返回元素个数
     *
     * @return
     */
    public int size() {
        return mData.size();
    }
}