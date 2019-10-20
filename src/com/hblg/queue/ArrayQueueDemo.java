package com.hblg.queue;

import java.util.Scanner;

/**
 * @author i
 * @create 2019/9/8 18:31
 * @Description 队列
 *    1、队列的常见场景
 *      我们在银行办理银行卡业务的时候，通常都是按序号进行排队进行办理，按照序号的大小从小到大
 *      依次办理业务。这就是我们生活中常见的队列场景。还有相关的 排队打饭等等。
 *    2、队列介绍
 *      1.队列是一个有序列表，可以用数组或是链表来实现
 *      2.遵循先入先出的原则 先存储的数据先取出 后存入的数据后取出。
 *    3、数组模拟队列思路
 *      1.队列本身就是有序列表，若使用数组的结构来存储队列的的数据，则队列数组的声明 maxSize是该队列的最大容量
 *      2.因为对队列的操作是从前端和后端进行操作的。前端(front) 随着数据的输出而改变 后端(rear)随着数据的输入而改变
 *      3.将数据存入队列中的思想，首先需要先判断队列是否已满，如果队列满 则添加数据错误
 *      另外的情况就是 当队列为空和队列中有数据但是不为满的情况，就可以添加数据。
 *      rear++;  front = rear 表示队列为空。
 *
 *      思考？，那就是我们创建的队列，大小都是固定改的，其数值都是没有办法改变 因此 只有一次性的使用功能。
 *       那就是 为什么 队列的头位置，默认会指向的是当前数据的前一个位置呢。
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQuene arrayQuene = new ArrayQuene(5);//初始化队列的大小
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
                        arrayQuene.addQuene(scanner.nextInt());
                    }catch (Exception e){
                        e.printStackTrace();
                    };break;
                case 'G':
                    System.out.println(arrayQuene.getQueneData());
                    break;
                case 'S':
                    arrayQuene.printQuene();
                    break;
                case 't':
                    flag = true;
                    scanner.close();//关闭对io流的操作
                    break;
                case 'H':
                    System.out.println(arrayQuene.headQuene());
                    break;
            }

        }

    }

}

/***
 * 使用数组模拟队列 编写一个ArrayQuene类
 *  1.是否为空
 *  2.是否为满
 *  3.添加数据
 *  4.查询数据
 *  5.遍历队列数据
 */
class ArrayQuene{
    private int maxSize;//表示队列长度的最大值
    private int front;//表示队列的头部，但是这个元素是指向元素的前一个位置。
    private int rear;//表示队列的尾部。指向的队列的尾部。
    private int [] queneArray; //定义一个数组 开辟一定的内存空间 用于存储队列数据

    //构造方法 对对象初始值进行初始化
    public ArrayQuene(int maxSize){
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        queneArray = new int [maxSize];//初始化大小
    }

    //队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //队列是否为满
    public boolean isFull(){
        return maxSize-1 == rear;
    }

    //查询队列数据  查询的是队列的头数据，查询一个数据会自动将front向上移动位置
    public int getQueneData(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空  不能查询数据!");
        }
        return queneArray[++front];
        //对于front 默认是指向当前元素的前一个位置。
    }

    //向队列中添加数据    添加数据会修改rear的位置
    public void addQuene(int data){
        if(isFull()){
            throw  new RuntimeException("队列为满 不能添加数据!");
        }
        rear++;
        queneArray[rear] = data;
    }

    //遍历队列中的数据
    public void printQuene(){
        //在遍历的时候 没有考虑到另一种情况 那就是队列为空
        if(isEmpty()){
            throw  new RuntimeException("数据为空!");
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.print("\t"+queneArray[i]);
        }
    }

    //显示队头的头数据，注意不是取数据 只是查询头数据的大小。
    public int headQuene(){
        //判断是否为空
        if(isEmpty()){
            throw  new RuntimeException("队列空的，没有数据!");
        }
        return queneArray[front+1];
    }

}