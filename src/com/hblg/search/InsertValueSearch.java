package com.hblg.search;

/**
 * @author i
 * @create 2019/10/11 16:24
 * @Description 插值查找
 *  插值查找原理:插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找。
 */
public class InsertValueSearch {

    public static void main(String[] args) {

        int [] array = new int[100];

        for (int i = 0; i < 100; i++) {
            array[i] = i+1;
        }
        System.out.println(insertValueSearch(array,0,array.length-1,50));
    }


    /***
     *
     * @param array 数组
     * @param minIndex  最小下标
     * @param maxIndex  最大下标
     * @param findValue  需要找的目标值
     * @return  找到返回对应的下标值 找不到返回-1
     */
    private static int insertValueSearch(int [] array,int minIndex,int maxIndex,int findValue) {
        if(minIndex>maxIndex || findValue <array[minIndex] || findValue >array[maxIndex]){
            return -1;
        }
        //插值查找找到中间的mid下标值
        int mid = minIndex + (maxIndex - minIndex) * (findValue - array[minIndex])/(array[maxIndex] - array[minIndex]);
        int midValue = array[mid];
        if(findValue>midValue){//向右查找
            return insertValueSearch(array,mid+1,maxIndex,findValue);
        }else if(findValue < midValue){//向左移动
            return insertValueSearch(array,minIndex,mid-1,findValue);
        }else {
            return mid;
        }
    }
}
