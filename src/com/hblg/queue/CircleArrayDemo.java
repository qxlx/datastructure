package com.hblg.queue;

import java.util.Scanner;

/**
 * @author i
 * @create 2019/9/9 19:19
 * @Description  用数组实现环形队列的思路整理
 *      环形队列结构分析:
 *      我们可以得知数组队列在一定程度上，是只有一次使用权限，当队列中数据处于满状态，就没有办法使用
 *      因此，环形队列从某种角度上可以解决这个问题。
 *      1.做一个约定那就是当尾索引的下一个为头索引时，表示队列满，预留出一个位置用于存放
 *          所以队列满=:(rear+1)%maxSize = front;
 *      2.队列为空时
 *              rear = front
 *      3.队列中有效数据的计算
 *              (rear+maxSize-front)%maxSize
 *              1+3-0%3  1
 *              2+4-2%4  0
 *      4.在取数据和添加数据的时候 我们要考虑到front和rear的变化，有一种极端的情况
 *      就是当队列数据为满的情况下，循环队列从头开始存储数据，因此 这块和数组队列上是一个很大的不同点
 *      也就是实现循环队列的前提条件。
 */
public class CircleArrayDemo {

    public static void main(String[] args) {
        CircleArray circleArray = new CircleArray(5);
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);//获取键盘输入的数据
        while(flag){
            System.out.println("A(Add) 添加数据");
            System.out.println("G(Get) 获取数据");
            System.out.println("S(Show) 展示数据");
            System.out.println("T(Exit) 退出程序");
            System.out.println("H(Head) 查询队头数据");
            char chars =  scanner.next().charAt(0);//获取键盘上的数据
            switch (chars){
                case 'A':
                    try {
                        //添加数据
                        circleArray.addDataQuene(scanner.nextInt());
                    }catch (Exception e){
                        e.printStackTrace();
                    };break;
                case 'G':
                    System.out.println(circleArray.getHeadData());
                    break;
                case 'S':
                    circleArray.printAllData();
                    break;
                case 't':
                    flag = true;
                    scanner.close();//关闭对io流的操作
                    break;
                case 'H':
                    System.out.println(circleArray.getHeadValue());
                    break;
            }

        }
    }
}

/***
 * 用数组实现环形队列，
 */
class CircleArray{

    private int maxSize;//队列最大空间
    private int front;//队列头部
    private int rear;//队列尾部
    private int [] array;

    //构造方法进行初始化
    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        rear = 0;
        front = 0;
        array = new int [maxSize];
    }

    //是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //是否为满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    //向队列中添加数据
    public void addDataQuene(int data){
        //先判断是否为满
        if(isFull()){
            throw  new RuntimeException("队列为满，不能添加数据");
        }
        array[rear] = data;
        rear = (rear+1)%maxSize;
        //这里必须要进行对maxSize进行取模，如果当前超出了队列范围，就需要从头开始 覆盖掉之前的数据，从而形成一个新的队列
    }

    //取出队头数据
    public int getHeadData(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空，不能查!");
        }
        int value = array[front];
        front = (front+1)%maxSize;//这里和rear是一样的
        return value;
    }

    //打印所有数据
    public void printAllData(){
        for (int i = front; i < front+getArrayQueneLength(); i++) {
            System.out.print(array[i%maxSize]+"\t");
            //i%maxSize 含义 当前数据位于front位置，因此 我们要考虑到
        }
    }

    //查询队头数据 不取出
    public int getHeadValue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空 不能查看!");
        }
        return array[front];
    }

    //获取当前队列中的数据个数
    private int getArrayQueneLength(){
        return (rear+maxSize-front)%maxSize;
    }

}
