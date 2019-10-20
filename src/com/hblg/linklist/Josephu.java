package com.hblg.linklist;

/**
 * @author i
 * @create 2019/9/20 16:39
 * @Description  约瑟夫问题
 *      约瑟夫问题分析:针对于此类问题，我们可以通过创建一个单项环形列表来进行解决。
 *
 *
 */
public class Josephu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoyNode(5);
//        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);
    }

}

//创建一个环形单向链表
class CircleSingleLinkedList{

    private Boy first = new Boy(0);//创建一个初始节点



    /***
     * 添加固定节点的思路
     *      思路:nodeNum 表示链表创建的节点数，如果节点数不是一个正常值 直接返回。否则继续
     *      通过for循环 创建需要的节点个数、(这里需要注意的是 在每次生成一个节点的时候 都要将每次新的节点和first节点相关联起来)
     *      如果当前是第一个节点，就将新创建的节点first = boy.  first.setNext(first); 当前节点相关联起来、
     *      否则 每次新创建一个节点boy 通过当前所指向的节点 currentBoy.setNext(boy)  boy.setNext(first) 相关联起来
     *      依次循环 currentNode = boy;
     *
     * @param nodeNum 创建的节点数
     */
    public void addBoyNode(Integer nodeNum){
        if(nodeNum<1){
            System.out.println("创建节点数不正确！");
            return;
        }
        Boy currentBoy = null;
        //创建链表个数
        for (int i = 1; i <= nodeNum; i++) {
            Boy boy = new Boy(i);
            if(i == 1){//创建第一个节点
                first = boy;
                first.setNext(first);//节点关联起来
                currentBoy = first;//将当前的节点和currentBoy节点管理起来
            }else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }
    }

    /****
     * 遍历思路:
     *      1.首先先判断空链表的情况 不是的话 继续
     *      2.设置一个当前节点 currentBoy first直接复制给它、
     *      3.通过while循环遍历 当当前节点的下一个节点是first 时候，表名遍历结束 跳出循环
     *        否则的话 将当前节点赋值给下一个节点 currentBoy = currentBoy.next();
     */
    public void showBoy(){
        if(first.getNext() == null){
            System.out.println("链表为空！");
            return;
        }
        Boy currentBoy = first;
        while (true){
            System.out.println("小孩的编号:"+currentBoy.getNo());
            if(currentBoy.getNext() == first){
                break;
            }
            currentBoy = currentBoy.getNext();
        }

    }


    /***
     * 需求:根据用户的输入，计算出小孩出圈的顺序
     *
     *      实现思路:首先我们应该先判断出参数是否属于正常范围。其次，如果要删除2号节点，那么就需要设置一个标记来指向当前要删除
     *      节点的前一个节点。然后，就是如果当前是从3号节点开始数数 那么我们需要将当前标记节点和指向的节点 遍历到对应的3号节点
     *      最后 通过遍历 如果当前节点和first节点相同，那么就剔除了其他 剩余一个节点。
     *
     * @param startNo   从几号开始  必须从2号开始数数
     * @param countNum  要数的数是多少  2号开始 数 5个数
     * @param countBoys  当前这个圈中有多少人 当前的圈数总人数 比如10人
     */
    public void countBoy(Integer startNo,Integer countNum,Integer countBoys){
        //判断不符合这些条件
        if(first.getNo() == null || startNo<1 || startNo >countBoys){
            System.out.println("当前输入的数据有误");
            return;
        }
        //设置一个标记 用来指示要删除的节点的 前一个节点
        Boy flagBoyNode = first;
        while(true){
            if(flagBoyNode.getNext() == first){
                break;
            }
            flagBoyNode = flagBoyNode.getNext();
        }
        //如果当前是从3号节点开始数数 那么我们需要将当前标记节点和指向的节点 遍历到对应的3号节点
        for (int i = 0; i < startNo-1 ; i++) {
            first = first.getNext();
            flagBoyNode = flagBoyNode.getNext();
        }
        //最后依次剔除掉每个节点 剩余最后一个节点
        while (true){
            //遍历到初始节点 代表结束
            if(flagBoyNode == first){
                break;
            }
            //
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                flagBoyNode = flagBoyNode.getNext();
            }
            System.out.println("小孩出圈："+first.getNo());

            //要剔除的节点 将该节点的前后节点关联
            first = first.getNext();
            flagBoyNode.setNext(first);
        }
            System.out.println("剩余孩子："+first.getNo());
    }

}

/***
 * Boy节点:一个no next节点
 */
class Boy{
    private Integer no;//id编号
    private Boy next;//指向下一个节点

    public Boy(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}