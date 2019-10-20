package com.hblg.mycollection;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author i
 * @create 2019/10/9 22:22
 * @Description
 *  特点:LinkedList是一个链式的存储结构，内存不连续。每个节点之间通过引用相互连接。
 */
public class MyLinkedList implements Collection {

    private Node pHead;//头结点
    private Node pTail;//指向尾节点

    private static class Node<E>{
        private E data;//数据越
        private Node next;//指向下一个节点域

        public Node() {

        }

        public Node(E data, Node node) {
            this.data = data;
            this.next = node;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            this.next = node;
        }
    }

    @Override
    public int size() {
        return 0;
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

    @Override
    public boolean add(Object o) {
        Node newNode = new Node(o,null);
        if(pHead == null){
            pTail = pHead = newNode;
            return true;
        }
        pTail.next = newNode;
        pTail = newNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node cur = pHead;//当前的头节点指向他
        Node preNode = null;//临时节点
        while (cur!=null){
            if(preNode.data.equals(o)){//说明找到
                if(preNode == null){
                    pHead = pHead.next;
                    return true;
                }
                preNode.next = cur.next;
                return true;
            }
            preNode = cur;
            cur = cur.next;
        }
        return false;
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

    public void pritfList(){
        Node cur = pHead;
        while(cur!=null){
            System.out.println(cur.data);
            cur = cur.next;
        }

    }

    private class MyLinkedListIterator implements  Iterator{

        private Node cur = pHead;//指向了头结点
        private Node curPre = null;
        @Override
        public boolean hasNext() {
            return cur!=null;
        }

        @Override
        public Object next() {
            curPre = cur;
            cur = cur.next;
            return curPre.data;
        }

        public void remove(){
            MyLinkedList.this.remove(curPre.data);
        }
    }

}
