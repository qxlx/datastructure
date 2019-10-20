package com.hblg.sort;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/9/29 19:32
 * @Description 选择排序
 *
 *      每次选出最小值，将最小值放到前面
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {1, 3, 100, 10, 23};
        System.out.println("排序前:" + Arrays.toString(array));
        selectSort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }

    /***
     * 选择排序的思路
     *  1, 3, 100, 10, 23
     *
     * @param array
     */
    public static void selectSort(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int minValue = array[minIndex];
            for (int j = i+1; j < array.length ; j++) {
                if (minValue > array[j]) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            //判断是否有最小值
            if(minIndex!=0){
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }
    }

}
