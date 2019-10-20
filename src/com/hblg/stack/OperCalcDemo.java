package com.hblg.stack;

/**
 * @author i
 * @create 2019/9/26 16:59
 * @Description 思路:3+2*9-9+1 对于这样形式的表达值，我们可以创建两个栈来进行存储，一个是数据栈(专门用来存储数据)。另一个用来存储
 * 符号(也就是符号栈)。设定一个下标默认指向表达式的最前面 也就是3这个位置 依次遍历完
 * 如果是数字 直接入数据栈
 * 如果是符号 1.先判断符号栈中是否有符号 没有。直接入符号栈
 * 2.如果符号栈中有符号 将符号中的末端数据符号弹出，和现有的表达式所指的直接进行判断 优先级高 直接入符号栈
 * 否则 就从数据栈取出两个数据进行运行 符号栈中也弹出一个符号栈，将所计算的表达值在存储到数据栈中。此时 我们所进
 * 表达式所指向的符号直接入符号栈中。依次顺序遍历完
 * 遍历完表达式后，数据栈 和符号栈中 通过依次弹栈计算出表达值 入数据栈。最后栈中存储的就是表达式的值。
 * 遇到的坑:查看栈顶数据 和 pop是不一样的。
 */
public class OperCalcDemo {

    public static void main(String[] args) {
        Stack dataStack = new Stack(10);
        Stack operStack = new Stack(10);
        String str = "1+1-3";//13
        char ch;
        int num1;
        int num2;
        int oper;
        int result;
        String chars = "";//用于多数字计算
        //遍历表达式
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            //判断是数据 还是符号
            if (Stack.isNumOrOper(ch)) {//为符号
                //如果符号栈 为空直接添加
                if (operStack.isEmpty()) {
                    System.out.println("-----");
                    operStack.push(ch);
                } else {
                    //比较表达式当前的符号优先级 和 栈末最后一个  注意点通过peek()和pop是不一样的。
                    if (Stack.priority(ch) <= Stack.priority((char) operStack.peek())) {
                        num1 = dataStack.pop();
                        num2 = dataStack.pop();
                        oper = operStack.pop();
                        result = Stack.calc(num1, num2, (char) oper);
                        dataStack.push(result);
                        operStack.push(ch);//添加符号
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {//为数据  这块考虑如果是两个数字进行计算
//                chars += ch;
//                if (i == str.length() - 1) {
//                    dataStack.push(Integer.parseInt(chars));
//                } else {
//                    //如果下一个是数字 就进行拼接
//                    if (!Stack.isNumOrOper(str.charAt(i + 1))) {
//                        chars += str.charAt(i + 1);
//                        System.out.println("chars:"+chars);
//                        dataStack.push(Integer.parseInt(chars));
//                        chars = "";//清空
//                        i++;
//                    }else {
//                        break;
//                    }
                dataStack.push(Integer.parseInt(ch+""));
            }
        }

        //计算数据栈和符号栈中的数据
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            result = Stack.calc(dataStack.pop(), dataStack.pop(), (char) operStack.pop());
            dataStack.push(result);
        }

        System.out.println("表达式的值:" + dataStack.pop());

    }

}

//栈
class Stack {
    private int[] array;//用数组来模拟一个栈
    private int top = -1;//指向栈数据的顶部
    private int maxSize;

    //进行初始化
    public Stack(int maxSize) {
        array = new int[maxSize];
    }

    public Stack() {

    }

    //查看栈顶的元素 但是top不变
    public int peek() {
        return array[top];
    }

    //栈满
    public boolean isFull() {
        return top == array.length - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int data) {
        //入栈之前先判断是否为栈满 否则不能添加数据
        if (isFull()) {
            throw new RuntimeException("stack is full");
        }
        array[++top] = data;
    }

    //出栈  栈是先进后出
    public int pop() {
        //出栈前先判断是否为空 为空 不能pop数据
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int value = array[top];
        top--;
        return value;
    }

    //栈遍历
    public void print() {
        //遍历之前先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        for (int i = 0; i <= top; i++) {
            System.out.print("array[" + i + "]" + ":" + array[i] + "\t");
        }
    }

    //判断数字还是符号 true 为符号 false 为数字
    public static boolean isNumOrOper(char ch) {
        if (ch == '+' || ch == '-' || ch == '/' || ch == '*')
            return true;
        else if (ch >= '0' || ch <= '9')
            return false;
        else {
            throw new RuntimeException("data is error!");
        }
    }

    //判断优先级  * /  2  + - 1
    public static int priority(char ch) {
        if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '+' || ch == '-')
            return 1;
        else
            throw new RuntimeException("oper is error!");
    }

    //计算方法
    public static int calc(int num1, int num2, char ch) {
        int reslut = 0;
        switch (ch) {
            case '*':
                reslut = num1 * num2;
                break;
            case '/':
                reslut = num2 / num1;
                break;
            case '+':
                reslut = num1 + num2;
                break;
            case '-':
                reslut = num2 - num1;
        }
        return reslut;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
//        System.out.println("栈空否"+stack.isEmpty());
//        System.out.println("栈满否"+stack.isFull());
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//        stack.print();
//        System.out.println("pop value:"+stack.pop());
//
//        stack.print();
//        System.out.println(stack.calc(1,2,'-'));
//        System.out.println(stack.isNumOrOper('-'));
        /*stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());*/
        String str = "10";
        System.out.println(Integer.parseInt(str));

    }
}
