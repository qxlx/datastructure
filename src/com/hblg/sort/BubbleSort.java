package com.hblg.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author i
 * @create 2019/9/29 17:00
 * @Description 冒泡排序
 *
 * 主要思想就是两个之间互相比较 每次都将最大值放到最后。
 *
 *
 *
 */
public class BubbleSort {

    private static final int MAX = 100000;

    public static void main(String[] args) {

        int [] array = new int [MAX];
        for (int i = 0; i < MAX; i++) {
            array[i] = (int)(Math.random()*1000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        System.out.println("排序前:"+str);

        bubbleSort(array);

        Date date1 = new Date();
        String str2 = simpleDateFormat.format(date1);
        System.out.println("排序后:"+str2);
//        System.out.println("排序前:");
//        System.out.println(Arrays.toString(array));
//        bubbleSort(array);
//        System.out.println("排序后:");
//        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int array[]){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j]>array[j+1]){
                    flag = true;//表示进行排序操作
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            //System.out.println("第"+(i+1)+"次"+ Arrays.toString(array));
            if(flag==false){
                break;
            }else {
                flag = false;
            }
        }
    }
}
