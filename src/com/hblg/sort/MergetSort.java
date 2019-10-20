package com.hblg.sort;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/10/9 21:13
 * @Description 归并排序
 *
 */
public class MergetSort {

    public static void main(String[] args) {
        int [] arr = {8,4,5,7,1,3,6,2};
        int [] temp = new int [arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    public static  void mergeSort(int [] arr,int left,int right,int [] temp){
        if(left<right){
            int mid = (left+right)/2;

            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            mergetSort(arr,left,mid,right,temp);
        }



    }

    /***
     * 合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right  右边索引
     * @param temp  做中转的数组
     */
    public static void mergetSort(int [] arr,int left,int mid,int right,int [] temp){
        int i = left;//初始化i 左边有序序列的初始索引
        int j = mid+1;//初始化j  右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //一
        //先将左右两边(有序)的数据按照规则填充到temp数组中
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i<=mid && j<=right){//继续
            //如果左边的数小于右边的数 将左边的数填充到临时数组中
            if(arr[i]<=arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }else {//反之 如果右边的数小于左边的数 将右边的数填充到temp数组中
                temp[t] = arr[j];
                t+=1;
                j+=1;
            }
        }
        //二
        //将剩余一端的数据 全部移动到临时数组中
        while (i<=mid){//左端的数据没有一处完毕 将左端的数据全部移动到临时数组中
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j<=right){//右端的数据剩余，将右边的数据全部移动到临时数组中
            temp[t] = arr[j];
            t++;
            j++;
        }
        t = 0;
        int tempLeft = left;

        //三
        //将temp数组中的元素拷贝到arr
        while (tempLeft<=right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }
}
