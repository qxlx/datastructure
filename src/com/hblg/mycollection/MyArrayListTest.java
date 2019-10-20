package com.hblg.mycollection;

/**
 * @author i
 * @create 2019/10/8 22:48
 * @Description
 *
 */
public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayLIst  arrayLIst = new MyArrayLIst(10);
        for (int i = 0; i < 10; i++) {
            arrayLIst.add(i);
        }

        System.out.println("添加数据之后:");
        for (int i = 0; i < arrayLIst.size(); i++) {
            System.out.print(arrayLIst.get(i)+"\t");
        }

        arrayLIst.remove(8);
        System.out.println("删除数据之后:");
        for (int i = 0; i < arrayLIst.size()-1; i++) {
            System.out.print(arrayLIst.get(i)+"\t");
        }

    }

}
