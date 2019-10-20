package com.hblg.mycollection;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author i
 * @create 2019/10/8 22:39
 * @Description
 *          ArrayList是一个线性的集合，其内部的元素有序可重复，底层采用数组实现。
 *
 */
public class MyArrayLIst implements Collection {//因为ArrayList是Collection的子类 所以对外提供了公共的接口

    private Object [] datas;//持有一个Object数组来实现存储数据
    private int size;//用以表示集合的大小
    private int modCount = 10;//对应一个标志用以控制

    //初始化
    public MyArrayLIst(int size){
        datas = new Object[size];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /***
     * 添加数组的时候 可以实现动态扩容
     * @param o
     * @return
     */
    @Override
    public boolean add(Object o) {
        ensureCapacity(size);
        datas[size++] = o;
        return true;
    }
    //思路 创建一个比原先数组大2倍的数组 将旧数组中的元素添加到新数组中 最后将新数组的引用直接复制给旧数组引用变量
    private void ensureCapacity(int size){
        if(datas.length<=size){
            Object [] newDatas = new Object[datas.length*2];
            for (int i = 0; i < datas.length; i++) {
                newDatas[i] = datas[i];
            }
            datas = newDatas;
        }
    }

    //删除的方法 将数组中的元素遍历输出找到对应删除的元素 将该元素后边的元素依次往前移动一个位置
    //这个位置要删除的话 要维护数组的长度 但是没有考虑到。逻辑上的删除了 但是物理上是没有删除的。
    @Override
    public boolean remove(Object o) {
        Integer removeIndex = null;
        for (int i = 0; i < datas.length; i++) {
            if(datas[i].equals(o)){
                removeIndex = i;
                break;
            }
        }

        if(removeIndex == null)
            return false;
        for (int i = removeIndex; i < datas.length-1; i++) {
            datas[i] = datas[i+1];
        }
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection c) {
        return false;
    }

    @NotNull
    @Override
    public Object[] toArray(@NotNull Object[] a) {
        return new Object[0];
    }

    public Object get(int index){
        return datas[index];
    }

    //内部类 用以实现集合的操作
    //通过内部类 来实现对集合的迭代操作
    public class MyArrayListIterator implements Iterator{

        private int currentIndex = -1;
        private int expectModifyCount = modCount;
        @Override
        public boolean hasNext() {
            return currentIndex++<size();
        }

        @Override
        public Object next() {
            return datas[currentIndex++];
        }

        @Override
        public void remove() {
            if(expectModifyCount!=modCount){//版本号控制
                throw new ConcurrentModificationException();
            }
            MyArrayLIst.this.remove(datas[currentIndex]);
        }
    }

}
