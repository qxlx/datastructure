package com.hblg.search;

import jdk.nashorn.internal.ir.LiteralNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author i
 * @create 2019/10/10 17:29
 * @Description 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int [] array = {10,20,30,405,405,405,50};
        List<Integer> resIndexList =  binarySearch2(array,0,array.length-1,405);
        System.out.println(resIndexList);
    }

    /***
     *
     * @param array 数组
     * @param left  左边下边
     * @param right 右边下标
     * @param findValue  找到的值
     * @return
     */
    public static int binarySearch(int [] array,int left,int right,int findValue){

        int mid = (left+right)/2;//获取中间下标
        int midValue = array[mid];//中间值
        if(left>right){//如果左边的下标大于 右边的下标 说明找不到 直接返回
            return -1;
        }

        if(findValue>midValue){
            return binarySearch(array,mid+1,right,findValue);
        }else if(findValue<midValue){
            return binarySearch(array,left,mid-1,findValue);
        }else {
            return mid;
        }
    }


    /***
     * {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 	 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     * 	需要在找到的时候 不要立即返回
     *
     * @param array
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static ArrayList<Integer> binarySearch2(int [] array, int left, int right, int findValue){
        ArrayList<Integer> resIndexList = null;
        int mid = (left+right)/2;//获取中间下标
        int midValue = array[mid];//中间值
        if(left>right){//如果左边的下标大于 右边的下标 说明找不到 直接返回
            return resIndexList = new ArrayList<Integer>();
        }

        if(findValue>midValue){
            return binarySearch2(array,mid+1,right,findValue);
        }else if(findValue<midValue){
            return binarySearch2(array,left,mid-1,findValue);
        }else {
            //向左查找
            int temp = mid-1;
            resIndexList = new ArrayList<Integer>();
            while(true){
                if(temp<0||array[temp]!=findValue){
                    break;
                }
                resIndexList.add(temp);
                temp-=1;
            }
            resIndexList.add(mid);
            //向右查找
            temp = mid + 1;
            while (true){
                if(temp>array.length-1 || array[temp]!=findValue){
                    break;
                }
                resIndexList.add(temp);
                temp+=1;
            }
            return resIndexList;
        }
    }


}
