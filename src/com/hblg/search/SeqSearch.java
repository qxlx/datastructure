package com.hblg.search;

/**
 * @author i
 * @create 2019/10/10 17:26
 * @Description 顺序查找
 */
public class SeqSearch {

    public static void main(String[] args) {
        int [] array = {12,30,40,50,45};
        System.out.println(search(array,50));
    }

    public static int  search(int [] array,int findValue){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == findValue){
                return i;
            }
        }
        return -1;
    }

}
