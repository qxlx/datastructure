package com.hblg.stack;


import java.util.ArrayList;
import java.util.List;

/**
 * @author i
 * @create 2019/9/27 10:19
 * @Description  逆波兰表达式
 *  (3+4)*5-6 对应的后缀表达式就是3 4 + 5 * 6 -
 *  1.从左到右扫描 如果是数就存储到栈中
 *  2.如果是符号 就将当前栈的最高 和次高的数据 依次取出 用当前的符号 进行算数运算。
 *
 */
public class PolandNotaion {

    public static void main(String[] args) {
        //String suffixExpression = "3 4 + 5 * 6 -";//
        //System.out.println(getExpressionValue(getStrList(suffixExpression)));
        String expression = "1+((2+3)*4)-5";
        System.out.println(getExpressionValue(parseSuffixExpreesionList(toInfixExpressionList(expression))));

    }


    /***
     * 创建两个栈 一个用来存储存储运算符s1 另一个存储中间结果s2
     * 1.如果当前是数 直接存储到s2 中
     * 2.遇到运算符
     *     1.如果s1中是空 则直接添加到s1中
     *     2.若优先级比s1中的优先级高 直接入栈s1中
     *     3.如果s1栈中优先级高于当前的符号的优先级 直接将s1中的符号pop到s2中。
     * 3.遇到括号
     *     1.如果是左括号 “(” 直接入栈s1
     *     2.如果是右括号 ")" 则依次弹出s1中的符号 知道遇到 ")" 将当前括号消失
     * 4最后将s1中的符号依次弹出 存入到s2中。
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls){
        java.util.Stack<String> s1 = new java.util.Stack();//s1存储符号
        List<String> list = new ArrayList<>();//存储数据

        for (String items:ls) {
            if(items.matches("\\d+")){
                //如果是数字 直接添加到List中
                list.add(items);
            }else if(items.equals("(")){
                //如果是 （ 直接添加到s1中
                s1.push(items);
            }else if(items.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    list.add(s1.pop());
                }
                s1.pop();
            }else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while(s1.size() != 0 && Operation.getValue(items)<=Operation.getValue(s1.peek())){
                    list.add(s1.pop());
                }
                s1.push(items);
            }
        }
        //将s1中的符号依次弹出到s2中
        while (s1.size()!=0){
            list.add(s1.pop());
        }

        return list;
    }

    //获取优先级
    public static class Operation{

        private static final int ADD = 1;
        public static final int SUB = 1;
        public static final int MUL = 2;
        public static final int DIV = 2;


        public static int getValue(String operation){
            int result = 0;
            switch (operation){
                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
            }
            return result;
        }


    }

    /***
     * 将中缀表达式存放到list集合中
     * @param str
     * @return
     */
    public  static List<String> toInfixExpressionList(String str){
        List<String> list = new ArrayList<>();
        String strs;
        for (int i = 0; i < str.length(); ) {
            //如果是符号 直接添加
            if(str.charAt(i)<48 || str.charAt(i)>57){
                list.add(str.charAt(i)+"");
                i++;
            }else {
                //如果是数字在依次判断下一个是否是数字
                strs = "";
                while(i<str.length() && str.charAt(i)>=48 && str.charAt(i)<=57){
                    strs+=str.charAt(i);
                    i++;
                }
                list.add(strs);
            }
        }
        return list;
    }

    //将集合中元素 进行运算 [3, 4, +, 5, *, 6, -]
    private static int getExpressionValue(List<String> str){
        java.util.Stack <String>stack = new java.util.Stack();
        for (int i = 0; i < str.size(); i++) {
            String strIndex = str.get(i);
            if(strIndex.matches("\\d+")){//如果是数字 则入栈
                stack.push(strIndex);
            }else {
                //如果是符号 将栈中的最高 和 次高的元素 弹出 进行计算。
                int num = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if(strIndex.equals("+")){
                    result = num + num2;
                }else if(strIndex.equals("-")){
                    result = num2 - num;
                }else if(strIndex.equals("*")){
                    result = num2 * num;
                }else if(strIndex.equals("/")){
                    result = num2 / num;
                }
                stack.push(result+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将当前的字符串转换成String 到List集合中
    private static List<String> getStrList(String str){
        List<String> list = new ArrayList<>();
        String [] strs = str.split(" ");
        for(String string : strs){
            list.add(string);
        }
        return list;
    }

}
