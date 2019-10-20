package com.hblg.recursion;

/**
 * @author i
 * @create 2019/9/28 15:09
 * @Description 迷宫回溯
 *
 *  思路:对于这列问题 我们可以使用递归的方法来解决。
 *  使用二维数组模拟谜宫。1表示为墙 2表示可以走 3代表死路 0 代表为空
 *
 */
public class MiGong {

    public static void main(String[] args) {
        int [][] array = new int [8][7];
        //初始化上边和下边的墙
        for (int i = 0; i < 7; i++) {
            array[0][i] = 1;
            array[7][i] = 1;
        }
        
        //初始化左边和右边的墙
        for (int i = 0; i < 8; i++) {
            array [i][0] = 1;
            array [i][6] = 1;
        }

        //遍历数组
        System.out.println("初始化谜宫");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
        //设置障碍墙
        array [3][1] = 1;
        array [3][2] = 1;

        setWay2(array,1,1);
        System.out.println("走完之后");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }

    }

    /***
     *上右下左
     * @param array 数组
     * @param i 行
     * @param j 列
     * @return
     */
    public static boolean setWay(int [][] array,int i,int j) {
        if (array[6][5] == 2) {//说明找到
            return true;
        } else {//按照上右下左走
            if (array[i][j] == 0) {
                array[i][j] = 2;
                if (setWay(array, i - 1, j)) {//上
                    return true;
                } else if (setWay(array, i, j + 1)) {//右
                    return true;
                } else if (setWay(array, i + 1, j)) {//下
                    return true;
                } else if (setWay(array, i, j - 1)) {//左
                    return true;
                } else {
                    array[i][j] = 3;//死路
                    return false;
                }
            } else {
                return false;//1 表示墙 2表
            }
        }
    }

    /***
     *下右上左
     * @param array 数组
     * @param i 行
     * @param j 列
     * @return
     */
    public static boolean setWay2(int [][] array,int i,int j) {
        if (array[6][5] == 2) {//说明找到
            return true;
        } else {//按照上右下左走
            if (array[i][j] == 0) {
                array[i][j] = 2;
                if (setWay2(array, i + 1, j)) {//下
                    return true;
                } else if (setWay2(array, i, j+1)) {//右
                    return true;
                } else if (setWay2(array, i - 1, j)) {//上
                    return true;
                } else if (setWay2(array, i, j -1)) {//左
                    return true;
                } else {
                    array[i][j] = 3;//死路
                    return false;
                }
            } else {
                return false;//1 表示墙 2表
            }
        }
    }


}
