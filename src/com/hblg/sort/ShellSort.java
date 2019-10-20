package com.hblg.sort;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/10/9 17:05
 * @Description 希尔排序
 *  实现思路 我们有一个数组8,9,1,7,2,3,5,4,6,0
 *  每次分组 实现数据之间的交换
 *  第一次 10/2 = 5组
 *  第二次 5/2 = 2
 *  第三次 2/2 = 1
 *
 */
public class ShellSort {

    public static void main(String[] args) {
        int [] array = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(array);
    }

    //对交换式的希尔排序进行优化-》》移位法
    public static void shellSort2(int [] array){
        int count = 0;
        for (int gap = array.length/2; gap >0; gap/=2) {
            for (int j = gap; j <array.length ; j++) {
                int k = j;
                int temp = array[k];//标记修改的元素
                if(array[k]<array[k-gap]){
                    while (k-gap>=0&&temp<array[k-gap]){
                        //移动
                        array[k] = array[k-gap];
                        k-=gap;
                    }
                    array[k] = temp;
                }
            }
            System.out.println("第"+(++count)+"轮："+Arrays.toString(array));
        }


    }

    public static void shellSort1(int [] array){
        int count = 0;

        for (int i = array.length/2; i >0 ; i/=2) {//有多少组 每次/2
            int temp = 0;
            for (int j = i; j <array.length; j++) {
                for (int k = j-i; k >=0 ; k-=i) {//i 代表步数
                    if(array[k] > array[k+i]){//如果当前数据大于后边数  就进行交换
                        temp = array[k];
                        array[k] = array[k+i];
                        array[k+i] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"轮："+Arrays.toString(array));
        }

    }


    public static void shellSort(int [] array){
        int temp = 0;
        //希尔排序的第一轮
        //因为第一轮排序
        for (int i = 5; i < array.length; i++) {
            for (int j = i-5; j >=0; j-=5) {
                if(array[j]>array[j+5]){
                    temp = array[j];
                    array[j] = array[j+5];
                    array[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮希尔排序后:"+ Arrays.toString(array));


        //第二轮排序
        for (int i = 2; i < array.length; i++) {
            for (int j = i-2; j >=0; j-=2) {
                if(array[j]>array[j+2]){
                    temp = array[j];
                    array[j] = array[j+2];
                    array[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮希尔排序后:"+Arrays.toString(array));

        //第三轮排序
        for (int i = 1; i < array.length; i++) {
            for (int j = i-1; j >=0; j-=1) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        System.out.println("第三轮希尔排序:"+Arrays.toString(array));
    }

}
