package com.hblg.linklist;

/**
 * @author i
 * @create 2019/9/19 20:06
 * @Description  双向链表的实现
 *  双向链表的特点
 *  当前节点指向下一个节点next
 *  当前节点指向上一个节点pre
 *  当前的数据对象
 */
public class DoubleLinkedListDemo {

    //修改  删除不能使用
    public static void main(String[] args) {
        DoubleLikedList doubleLikedList = new DoubleLikedList();

        HeroNode2 heroNode = new HeroNode2(1,"旺财","小旺财");
        HeroNode2 heroNode2 = new HeroNode2(2,"小强","小小强");
        HeroNode2 heroNode3 = new HeroNode2(3,"小鸟","小小鸟");

        doubleLikedList.addHeroNode(heroNode);
        doubleLikedList.addHeroNode(heroNode2);
        doubleLikedList.addHeroNode(heroNode3);

        /*doubleLikedList.print();
        doubleLikedList.updateById(new HeroNode2(3,"小小鸟","小小小鸟"));
        doubleLikedList.print();*/

        doubleLikedList.deleteById(3);
        doubleLikedList.print();
    }



}

//双向链表
class DoubleLikedList{

    private HeroNode2 head = new HeroNode2(0,"","");//初始一个节点

    //返回节点
    public HeroNode2 getHead(){
        return head;
    }

    /***
     * 添加直接添加到末尾
     * 实现实例:通过遍历找到最后一个节点的位置，该节点为newNode
     * newNode.pre = temp;
     * temp.next = newNode;
     * @param heroNode2
     */
    public void addHeroNode(HeroNode2 heroNode2){
        //遍历列表
        HeroNode2 currentNode = head;

        while(true){
            if(currentNode.next == null){
                break;
            }
            currentNode = currentNode.next;
        }
        heroNode2.pre = currentNode;//设置新节点的前一个节点
        currentNode.next = heroNode2;//设置新节点的后一个节点


    }

    /***
     * 根据hNo删除指定的节点
     * 实现思路:遍历双向列表的元素。找到指定的hNo的位置元素
     *
     * @param hNo
     */
    public void deleteById(Integer hNo){
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode2 currentNode = head.next;
        boolean flag = false;//设置标记
        while(true){
            if(currentNode == null){//没有找到
                System.out.println("链表遍历到头没有找到");
                break;
            }else if(currentNode.getHno() == hNo){
                flag = true;
                break;
            }
            currentNode = currentNode.next;
        }

        if(flag){
            currentNode.pre.next = currentNode.next;
            if(currentNode.next != null)//??????? 需要考虑到最后一个元素 如果是最后一个元素、出问题
                currentNode.next.pre = currentNode.pre;
        }else {
            System.out.println("没有找到对应的元素!");
        }
    }


    /***
     * 修改
     * 实现思路:通过首节点可以获取到下一个元素是否存在，如果不存在。直接返回
     * 否则通过遍历链表 一共有2种情况，1.遍历到链表末尾 没有这个id 2.找到该元素的位置
     * 设置一个标志位 如果找到就通过if进行判断，否则的话 就没有找到。
     * @param heroNode2
     */
    public void updateById(HeroNode2 heroNode2){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;//设置标记
        HeroNode2 currentHeroNode = head.next;
        while(true){
            //遍历到最后
            if(currentHeroNode == null){// currentHeroNode.next ????????
                System.out.println("链表遍历到底 没有找到!");
                break;
                //id相等
            }else if(currentHeroNode.getHno() == heroNode2.getHno()){
                flag = true;
                break;
            }
            //继续下一轮
            currentHeroNode = currentHeroNode.next;
        }

        if(flag){//修改信息
            currentHeroNode.setName(heroNode2.getName());
            currentHeroNode.setNickName(heroNode2.getNickName());
        }else {
            System.out.println("没有找到的元素!");
        }

    }

    //遍历
    public void print(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 currentHeroNode  = head.next;
        while(true){
            if(currentHeroNode == null){
                break;
            }
            System.out.println(currentHeroNode);
            currentHeroNode = currentHeroNode.next;
        }
    }



}


//英雄节点
class HeroNode2{

    public HeroNode2 next;//指向的下一个节点
    public HeroNode2 pre;//指向的上一个节点
    private Integer hno;//英雄号
    private String name;//名字
    private String nickName;//昵称

    public HeroNode2(Integer hno, String name, String nickName) {
        this.hno = hno;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    public Integer getHno() {
        return hno;
    }

    public void setHno(Integer hno) {
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

    @Override
    public String toString() {
        return "HeroNode2{" +
                " hno=" + hno +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
