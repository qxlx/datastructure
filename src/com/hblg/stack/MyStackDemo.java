package com.hblg.stack;

import java.util.Scanner;

/**
 * @author i
 * @create 2019/9/20 20:47
 * @Description 用数组来实现栈的push pop
 */
public class MyStackDemo {

    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String strValue = null;
        while(flag){
            System.out.println("show()显示栈数据");
            System.out.println("exit()程序退出");
            System.out.println("pop()数据弹栈");
            System.out.println("push()数据入栈");

            strValue = scanner.next();
            switch (strValue){
                case "show":
                    myStack.show();
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                case "push":
                    System.out.println("请输入一个数据");
                    int value = scanner.nextInt();
                    myStack.push(value);
                    break;
                case "pop":
                    System.out.println(myStack.pop());
                    break;
            }
            System.out.println("程序执行完毕！");
        }

    }

}

/***
 * 用数组实现栈的操作
 */
class MyStack{

    private int [] array = null;//用数组来存储数据
    private Integer maxSize;
    private Integer top = -1;//表示栈底
    //初始化数组大小
    public MyStack(Integer maxSize){
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(Integer data){
        if(isFull()){
            System.out.println("栈满 无法添加~");
        }
        array[++top] = data;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw  new RuntimeException("栈空 无法查看~");
        }
        int value = array[top];
        top--;
        return value;
    }

    //遍历
    public void show(){
        if(isEmpty()){
            System.out.println("占空 无法遍历~");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n",i,array[i]);
        }
    }


}