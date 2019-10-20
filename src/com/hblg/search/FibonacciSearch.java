package com.hblg.search;

import java.util.Arrays;

/**
 * @author i
 * @create 2019/10/11 17:44
 * @Description 斐波那契查找算法
 */
public class FibonacciSearch {

    private static final Integer MAXSIZE = 20;

    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234};;
        System.out.println("index:"+fibonacciSearch(arr,10));
    }

    /***
     * 创建一个斐波那契数组
     * @return
     */
    private static int []  fib(){
        int [] fibArray = new int [MAXSIZE];
        fibArray[0] = 1;
        fibArray[1] = 1;
        for (int i = 2; i < MAXSIZE; i++) {
            fibArray[i] = fibArray[i-1]+fibArray[i-2];
        }
        return fibArray;
    }

    /***
     * 编写斐波那契算法
     * @param array 数组
     * @param key 找到的值
     * @return 返回对应的下标
     */
    public static int fibonacciSearch(int [] array,int key){
        int low = 0;//最小下标
        int high = array.length-1;//最高下标
        int k = 0;//表示斐波那契数列分隔数值的下标
        int mid = 0;//存放中间数值
        int f[] = fib();//获取到斐波那契额数组
        //获取到斐波那契分隔数值的下标
        while (high>f[k]-1){
            k++;//获取到斐波那契数列的最大值下标
        }

        //因为f[k]数组 可能比我们的数值要大所以 使用工具类进行扩容Arrays
        int [] temp = Arrays.copyOf(array,f[k]);

        for (int i = high+1; i < temp.length; i++) {
            temp[i] = array[high];//将最后的值 依次都是相同。
        }

        //使用while循环 来找到我们的数值
        while (low<=high){
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if ( key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else { //找到
                //需要确定，返回的是哪个下标
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
