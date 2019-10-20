package com.hblg.sort;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/9/30 16:54
 * @Description     插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int [] array = {9,10,4,45,29};
        insertSort(array);
    }

    /****
     * 插入排序
     * 9，10，4，45,29
     * @param array
     */
    public static void insertSort(int [] array){
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            System.out.println("第"+i+"轮:"+Arrays.toString(array));
        }
        System.out.println("排好的:"+ Arrays.toString(array));
    }

}
