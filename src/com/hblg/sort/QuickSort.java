package com.hblg.sort;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/10/9 19:46
 * @Description 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int [] array = {-9,78,0,23,-567,70};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    /***
     *
     * @param array 数组
     * @param minIndex 最小下标
     * @param maxIndex 最大下标
     */
    public static void quickSort(int [] array,int minIndex,int maxIndex){
        int left = minIndex;//最小下标
        int right = maxIndex;//最大下标
        int pivot = array[(maxIndex+minIndex)/2];//获取中间下标 以及中间基准值
        int temp = 0;//临时变量
        while(left<right){
            //在左边找到一个比pivot大的值
            while(array[left]<pivot){
                left++;
            }

            //在右边找到一个比pivot小的值
            while(array[right]>pivot){
                right--;
            }

            //如果l>=r说明pivot的左右两的值 已经按照左边全都是
            //小于等于pivot值，右边全都是大于等于pivot
            if(left>=right){
                break;
            }

            //交换
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            //如果交换完后，发现这个arr[l] = pivot值 相等r-- 前移动
            if(array[left] == pivot){
                right--;
            }

            //如果交换完后，发现这个arr[right]==pivot值 相等 l++ 后移动
            if(array[right] == pivot){
                left++;
            }
        }

        //如果left = right 必须left ++ right -- 否则栈溢出
        if(left == right){
            left++;
            right--;
        }

        //向左递归
        if(minIndex<right){
            quickSort(array,minIndex,right);
        }

        //向右递归
        if(maxIndex>left){
            quickSort(array,left,maxIndex);
        }

    }

}
