package com.hblg.tree;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @author i
 * @create 2019/10/15 20:59
 * @Description 堆排序
 */
public class HeapSort {


    public static void main(String[] args) {
        int[] array = {4, 5, 8, 6, 9};
        headSort(array);
    }


    //编写一个堆排序算法
    public static void headSort(int[] array) {
        int temp = 0;
        //完成我们最终代码
        //根据无序序列构建一个堆 根据升序需求来选择大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHead(array, i, array.length);
        }


        /***
         * 将堆顶元素与末尾元素交换，将最大元素 沉到数组末端
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调用+交换 直到整个序列有序
         */
        for (int i = array.length - 1; i > 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHead(array, 0, i);
        }

        System.out.println(Arrays.toString(array));
    }


    /***
     * 将下标为array的值与堆中较大的进行比较，较小的值放到堆顶
     * @param array 数组
     * @param index 下标
     * @param length 长度
     */
    public static void adjustHead(int[] array, int index, int length) {
        int temp = array[index];//获取当前元素

        for (int k = 2 * index + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {//左子节点小于右子节点
                k++;
            }
            if (array[k] > temp) {//如果子节点大于父节点
                array[index] = array[k];//把较大的值赋给当前节点
                index = k;//指向了k
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的数的最大值 放在了最顶部
        array[index] = temp;
    }


}
