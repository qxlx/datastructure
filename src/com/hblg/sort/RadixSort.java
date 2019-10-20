package com.hblg.sort;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;

/**
 * @author i
 * @create 2019/10/10 15:51
 * @Description 基数排序
 *      基数排序也叫桶排序
 *      初始化10个桶 将每个数进行遍历 获取到个位数 按照个位数来进行划分 如果是1 就分在第一个桶中
 *       第二次按照十位数来进行划分，
 *       第三次按照百位数来进行划分

 */
public class RadixSort {

    public static void main(String[] args) {

        int [] array = {53,3,542,748,14,214};
        radixSort(array);
        System.out.println(Arrays.toString(array));

    }

    /***
     *  基数排序算法
     * @param array
     */
    public static void radixSort(int [] array){
        //得到数组中最大的数
        int maxNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if(maxNum<array[i]){
                maxNum = array[i];
            }
        }
        //得到最大数的长度
        int numLength = (maxNum+"").length();


        //定义一个二维数组，表示10个通 每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候,数据溢出，每个数组的长度定义为array.length
        //3.一定要明确 基数排序时空间换时间的经典算法
        int [][] bucket = new int [10][array.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组 来存放每个桶放入的个数
        int [] bucketNumCount = new int [10];
        //完成将数据放到桶中
        for (int i = 0,n = 1; i < numLength; i++,n*=10) {
            //针对于每次一处理的数据是不同的，
            for (int j = 0; j < array.length; j++) {
                //获取到每个元素的个位数 十位数 百位数。。。。
                int digit = array[j]/n%10; //2
                bucket[digit][bucketNumCount[digit]] = array[j];//bucket[digit]//对应那个桶下标 bucketNumCount[digit] 数字为digit的桶中有多少个数字
                bucketNumCount[digit]++;
            }
        }

        //将桶中的数据全部存放到原来的数组中
        int index = 0;
        //遍历每一个桶 并将桶中的数据 全部存入到原数组中
        for (int i = 0; i < bucketNumCount.length; i++) {
            //如果桶中 有数据  我们才放入到原数组
            if(bucketNumCount[i]!=0){
                //循环该桶既第K个桶
                for (int j = 0; j < bucketNumCount[i]; j++) {
                    array[index++] = bucket[i][j];
                }
            }
            //第i+1轮后 需要将每个bucketNumCount[i] = 0
            bucketNumCount[i] = 0;
        }
        
    }


}
