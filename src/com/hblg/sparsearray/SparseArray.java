package com.hblg.sparsearray;

/**
 * @author i
 * @create 2019/9/8 12:40
 * @Description  稀疏数组使用
 *  1、应用场景:在我们玩五子棋的过程中，难免会遇到暂停棋盘的操作。我们可以很容易将棋盘的棋子分布转换成一个二维数组
 *  但是，二维数组在一定程度上是有问题的。那就是对于棋子相对分布不是多的时候，会存储很多无关的数据。造成对存储空间的浪费
 *  因此，我们就逐渐演变到一个新的概念上来，那就是稀疏数组、
 *  2、稀疏数组的使用
 *      稀疏数组本质上还是一个数组结构，不过其存储都是要用到的数据。
 *      例如:    row  clo  value
 *               11   11    5
 *               0     1    5
 *               3     3    5
 *     稀疏数组就是上面这种存储结构 多维数组的大小是固定的 第一行存储的 有多少行 和多少列  第三个代表有多少值
 *     因此，就可以很容易的将那些冗余的数据给剔除掉
 *  3、代码实现思路
 *      棋盘的棋子分布可以存储到一个二维数组中，问题就是我们如何将二维数组转换成一个稀疏数组
 *      首先 我们可以通过遍历二维数组，获取到二维数组中有多少实际数值  0 代表空值  1 代表 黑子  2 代表白子
 *      通过已有的二维数组，创建一个稀疏数组，以及第一行 行号 列号 以及数据个数
 *      其次 通过遍历二维数组中的已有数据，将数据存储到稀疏数组中
 *      然后，打印稀疏数组，就可以获得相关的数据值、以上，就是对于数据存盘的操作
 *
  */
public class SparseArray {

    private static final Integer ROW = 11;//行号
    private static final Integer CLO = 11;//列号

    public static void main(String[] args) {
        //1.创建一个二维数组
        int [][] array = new int[ROW][CLO];
        //2 初始化数据
        array[1][2] = 1;//黑子
        array[2][4] = 2;//白子
        //3.获取二维数组中有效数据个数
        int totalNum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                    if(array[i][j]!=0){
                        totalNum++;
                    }
            }
        }
        System.out.println("二维数组中有效数据的个数为:"+totalNum);

        //4、创建一个稀疏数组
        int [][] sparseArray = new int [ROW][CLO];
        //初始化 第一行数据
        sparseArray[0][0] = ROW;//行号
        sparseArray[0][1] = CLO;// 列号
        sparseArray[0][2] = totalNum;//总个数
        //对其余元素进行赋值
        int countRow = 0;//记录每一行增加的行数

        for (int i = 0; i <array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j]!=0){
                    countRow++;
                    sparseArray[countRow][0] = i;//行号
                    sparseArray[countRow][1] = j;//列号
                    sparseArray[countRow][2] = array[i][j];//元素值
                }
            }
        }

        //打印稀疏数组元素
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.print("\t"+sparseArray[i][0]);
            System.out.print("\t"+sparseArray[i][1]);
            System.out.print("\t"+sparseArray[i][2]);
            System.out.println();
        }

        //将稀疏数组转换成二维数组
        /***
         * 实现思路  根据稀疏数组创建一个二维数组，根据稀疏数组中的第一行的元素就可以获取到行号和列号
         * 然后 通过遍历稀疏数组中的值，赋值给二维数组元素
         * array[spareseArray[i][0]][spaeseArray[i][1]] = sparseArray[i][2];
         */
        int [][] newArray = new int [sparseArray[0][0]][sparseArray[0][1]];//常见一个二维数组
        //将稀疏数组中的元素赋值给新二维数组元素中
        for (int i = 1; i <sparseArray.length; i++) {
            newArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        System.out.println("打印新数组元素:");
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j <newArray.length; j++) {
                System.out.print("\t"+newArray[i][j]);
            }
            System.out.println();
        }
    }


}
