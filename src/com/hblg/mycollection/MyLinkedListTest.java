package com.hblg.mycollection;

/**
 * @author i
 * @create 2019/10/10 22:40
 * @Description
 */
public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }

        myLinkedList.pritfList();
        myLinkedList.remove(3);
        myLinkedList.remove(4);
        myLinkedList.pritfList();

    }

}
