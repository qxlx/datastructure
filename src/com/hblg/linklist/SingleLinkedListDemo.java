package com.hblg.linklist;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * @author i
 * @create 2019/9/11 18:29
 * @Description
 *   链表特点：
 *      链表是有序的链表，
 *     1.链表是以节点的方式来存储的 是链式存储
 *     2.每个节点包含data域，next域 指向下一个节点
 *     3.链表的各个节点不一定是连续存储
 *     4.链表分为带头节点的链表和没有头节点的链表
 *
 *
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();

        singleLinkedList1.addOrderNode(new HeroNode(1,"吴用","智多星"));
        singleLinkedList1.addOrderNode(new HeroNode(2,"林冲","豹子头"));
        singleLinkedList1.addOrderNode(new HeroNode(3,"诸葛亮","孔明"));
        singleLinkedList1.addOrderNode(new HeroNode(4,"曹操","奸雄"));

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addOrderNode(new HeroNode(5,"小吴用","小智多星"));
        singleLinkedList2.addOrderNode(new HeroNode(6,"旺财","小强"));

        SingleLinkedList newSingleLinkedList = mergeList(singleLinkedList1,singleLinkedList2);
        newSingleLinkedList.print();

        /*singleLinkedList.addNode(new HeroNode(1,"吴用","智多星"));
        singleLinkedList.addNode(new HeroNode(2,"林冲","豹子头"));
        singleLinkedList.addNode(new HeroNode(3,"诸葛亮","孔明"));
        singleLinkedList.addNode(new HeroNode(4,"曹操","奸雄"));*/

       /* singleLinkedList.addOrderNode(new HeroNode(1,"吴用","智多星"));
        singleLinkedList.addOrderNode(new HeroNode(4,"林冲","豹子头"));
        singleLinkedList.addOrderNode(new HeroNode(2,"诸葛亮","孔明"));
        singleLinkedList.addOrderNode(new HeroNode(3,"曹操","奸雄"));*/

//        singleLinkedList.print();
        //链表中的元素 进行逆序输出
       // System.out.println("--------------");
        //reverseList(singleLinkedList.getHead());

        //reversetList(singleLinkedList.getHead());
        //singleLinkedList.print();

        //singleLinkedList.update(new HeroNode(2,"小诸葛","小孔明"));
       /* singleLinkedList.delete(4);
        singleLinkedList.print();
        System.out.println(getLength(singleLinkedList.getHead()));
        System.out.println(getLinkListForLastNode(singleLinkedList.getHead(),1));*/
    }

    /***
     *
     *  有问题。第二个链表只能添加一个。
     * 合并两个有序的单链表的  合并之后的链表任然是一个有序的链表
     * 实现思路:1、创建一个新的链表 存放两个链表中的元素
     *         2、通过遍历 将链表元素添加到新链表中。每次添加都比较大小 放在较小的那个元素之后
     * @param singleLinkedList1
     * @param singleLinkedList2
     */
    public static SingleLinkedList mergeList(SingleLinkedList singleLinkedList1,SingleLinkedList singleLinkedList2){
        SingleLinkedList newSingleLikedList = new SingleLinkedList();//新链表存放数据
        HeroNode currentHeroNode = null;
        newSingleLikedList = singleLinkedList1;

        currentHeroNode = singleLinkedList2.getHead().next;
        while (currentHeroNode!= null){
            newSingleLikedList.addOrderNode(currentHeroNode);
            currentHeroNode = currentHeroNode.next;
        }
        return newSingleLikedList;
    }

    /***
     * 从头到尾打印链表
     * 实现思路:使用栈来进行操作
     * @param heroNode
     */
    public static void reverseList(@NotNull HeroNode heroNode){
        if(heroNode.next == null){//链表为空 不进行
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode currentNode = heroNode.next;
        //遍历链表中元素，存入栈中
        while(currentNode != null){
            stack.push(currentNode);
            currentNode = currentNode.next;
        }

        //将栈中元素遍历出来  因为栈是先进后出
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }


    }

    /***
     *  需求单链表反转  ？？ while
     *
     * @param heroNode
     */
    public static void reversetList(HeroNode heroNode){
        //如果当前的节点的下一个 或者 当前节点的下一个的下一个 为空表示不需要进行链表的反转
        if(heroNode.next == null || heroNode.next.next == null){
            return;
        }

        HeroNode cur = heroNode.next;//当前节点的下一个
        HeroNode next = null;
        HeroNode reverseHead  = new HeroNode(0,"","");//创建一个新的节点
        //遍历原来的链表 每遍历一个节点 就将其取出，并放在新的链表reverseHead 的最前端
        while(cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }

        heroNode.next = reverseHead.next;
    }

    /***
     *  需求:查找单链表中倒数第K个结点
     *  根据单链表的结构 我们可以分析出
     *  1.得到链表的长度 length
     *  2.length-index 就是当前的元素
     * @param index
     * @return
     */
    public static HeroNode getLinkListForLastNode(HeroNode heroNode,int index){
        if(heroNode.next == null){
            return null;
        }
        int length = getLength(heroNode);//获取长度
        if(index>length || index<0){
            return null;
        }
        //查找到对应的元素
        for (int i = 0; i < length-index; i++) {
            heroNode = heroNode.next;
        }
        return heroNode;
    }


    /***
     * //求单链表中有效节点的个数
     * 实现思路:首先 我们应该先获取到第一个节点 也就是首节点 来一次判断后续是否有数据
     * @return
     */
    public static int  getLength(HeroNode heroNode){
        //先判断是否为空
        if(heroNode.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = heroNode.next;
        while(cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }

}
//单链表
class SingleLinkedList{
    //先初始化一个头节点 头结点不动 不放具体的数据 就是一个开头
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead(){
        return head;
    }
    //第一种方法 添加的时候 直接添加到尾部
    /***
     * 主要实现思路，我们假设第一个节点是作为我们使用后边节点的初始点，当添加节点的时候，将初始节点赋值给一个三方变量
     * 通过一个while循环来实现对最后一个节点的查找，如果不为空 就不是该节点 通过当前对象引用赋值给下一个节点 否则就是该节点
     * @param heroNode
     */
    public void addNode(HeroNode heroNode){
        //因为第一个是不能改变的，
        HeroNode temp = head;
        //判断第一个是否是空  不为空 就指向下一个节点 如此反复 当退出while循环的时候，可以知道就是最后一个节点
        while(true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /***
     * 按照英雄id进行有序的插入
     * 主要实现思路:
     *  1.在添加的时候 会出现以下几种情况 第一 就是当已经存在了当前的英雄id是不能添加的
     *  第二种就是当前
     * @param heroNode
     */
    public void addOrderNode(HeroNode heroNode){
        HeroNode temp = head;//将首元素赋值给第一个
        boolean flag = false;//用以表示 如果出现重复的id
        while(true){
            if(temp.next==null){//到链表的最后了 直接添加就可以
                break;
            }else if(temp.getHno()==heroNode.getHno()){
                flag = true;
                break;
            }else if(temp.next.getHno()>heroNode.getHno()){
                break;
            }
            temp = temp.next;//循环到下一个节点上
        }
        //1 要么就是找到了对应的位置 要么就是不能添加
        if(flag){
            System.out.println("重复id存在 不能添加:"+heroNode.getHno());
        }else {
            //找到了对应的位置
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /***
     * 根据英雄id 修改对应信息
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){
        //1.判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空 不能修改!");
            return;
        }
        boolean  flag = false;//设置一个标志 如果找到对应的链表位置
        HeroNode heroNode = head.next;
        //2.遍历列表 找到对应的no值 进行修改
        while(true){
            if(heroNode==null){//表示已经遍历到了链表最后
                System.out.println("遍历到低 不能在修改元素了!");
                break;
            }else if(heroNode.getHno()==newHeroNode.getHno()){//如果
                flag = true;
                break;
            }
            heroNode = heroNode.next;//进行下一个元素迭代
        }
        if(flag){//进行修改
            heroNode.setName(newHeroNode.getName());
            heroNode.setNickName(newHeroNode.getNickName());
        }else {
            System.out.println("对应的id不存在!"+newHeroNode.getHno());
        }

    }

    /***
     * 根据no删除指定的节点元素
     * @param no
     */
    public void delete(int no){
        HeroNode heroNode = head;
        if(heroNode.next==null){
            System.out.println("链表为空！");
            return ;
        }
        boolean flag = false;//标识找到删除的位置
        while(true){
            if(heroNode.next==null){//遍历到链表的最后
                break;
            }else if(heroNode.next.getHno()==no){//找到对应位置元素 这个元素为当前元素的下一个位置
                flag = true;
                break;
            }
            heroNode = heroNode.next;//依次遍历
        }
        //删除元素 删除一个元素 其实就是将这个元素的前后两个元素的next 互相关联一下 被删除的元素不在这个链表中 就代表删除了
        if(flag){
            heroNode.next = heroNode.next.next;
        }else {
            System.out.println("没有找到对应元素 删除失败!"+no);
        }

    }

    //打印链表
    public void print(){
        if(head.next==null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

}


//英雄对象
class HeroNode{
    private int hno;//唯一id
    private String name;//名字
    private String nickName;//昵称
    public HeroNode next;//指向下一个节点

    //构造方法用于初始化对象
    public HeroNode(int id,String name,String nickName){
        hno = id;
        this.name = name;
        this.nickName = nickName;
    }

    public int getHno() {
        return hno;
    }

    public void setHno(int hno) {
        this.hno = hno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "hno=" + hno +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}