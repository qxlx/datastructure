package com.hblg.binarysorttree;

/**
 * @author i
 * @create 2019/10/20 9:35
 * @Description 二叉排序树
 * <p>
 * 1、添加 中序遍历
 *
 *
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int [] array = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : array){
            binarySortTree.add(new Node(value));
        }

        binarySortTree.infixOrder();
    }



}

class BinarySortTree{
    private Node rootNode;

    //添加
    public void add(Node node){
        if (node == null){
            throw new NullPointerException("data is null!");
        }else if (rootNode == null){
            rootNode = node;//如果root节点为空 直接挂载
        }else{
            rootNode.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if (rootNode == null){
            throw new NullPointerException("root node is null!");
        }else {
            rootNode.infixOrder();
        }
    }


}


class Node {
    private int value;//数据
    Node leftNode;//左节点
    Node rightNode;//右节点

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加
    public void addNode(Node node) {
        if (node.value < this.value) {//挂载左边
            if (this.leftNode == null) {//如果当前节点的左子树为空 直接挂载
                this.leftNode = node;
            } else {//否则递归 左子节点
                this.leftNode.addNode(node);
            }
        } else {//添加的节点值大于等于当前节点
            if (this.rightNode == null) {//当前节点的右子树为空 直接挂载
                this.rightNode = node;
            } else {//递归 右子节点
                this.rightNode.addNode(node);
            }
        }
    }


    //中序遍历
    public void infixOrder() {

        if (this.leftNode != null) {
            this.leftNode.infixOrder();//不为空 递归查找左子树
        }

        System.out.println(this);

        if (this.rightNode != null) {
            this.rightNode.infixOrder();//不为空 递归查找右子树
        }
    }

    /***
     * 二叉排序树 删除
     * 1.删除叶子节点
     * 2.删除只有一颗子树的节点
     * 3.删除有两颗子树的节点
     */

    /***
     * 1.删除叶子节点
     *   1.先找到要删除的节点
     *   2.找到眼删除节点的父节点 如果该节点是root节点 则直接删除
     *   3.确定要删除的节点是parentNode的左子节点 还是右子节点
     *   根据前面的情况来应对删除
     *   左子节点 prentNode.left = null;
     *   右子节点 parentNode.right = null;
     */

    /***
     * 2.删除只有一颗子树的节点
     *    1.先找到要删除的节点
     *    2.找到要删除节点的父节点 如果该父节点是root节点 则直接删除
     *    3.确定targetNode是父节点的左子节点还是右子节点
     *    4.如果targetNode是父节点的左子节点
     *    4.1
     *
     *
     */

    /***
     * 3.删除的有两颗子树的节点
     *    1.先找到要删除的节点
     *    2.找到要删除节点的父节点，如果该节点是root节点 直接删除
     *    3.从targetNode的右子树找到最小的节点。
     *    4.用一个临时变量，将最小节点值保存在temp = 11;
     *    5.删除该最小节点
     *    6.targent.value= temp;
     */

    /***
     * 查找要删除的节点
     * @param node
     * @return
     */
    public Node searchDeleteNode(Node node){
        //第一种情况 直接找到
        if(this.value == node.value){
            return node;
        }else if (node.value < this.value){
            if (this.leftNode == null){
                return null;
            }
            return this.leftNode.searchDeleteNode(node);
        }else {
            if (this.rightNode == null){
                return null;
            }
            return this.rightNode.searchDeleteNode(node);
        }
    }

    /***
     * 查找删除节点的父节点
     * @param node
     * @return
     */
    public Node searchDeleteNodeParentNode(Node node){
        //找到
        if ((this.leftNode != null && this.leftNode.value == node.value) || this.rightNode != null && this.rightNode.value == node.value){
            return this;
        }else {
            if (this.leftNode != null && node.value < this.value) {
                //如果要找的值小于当前节点的左子节点的值 向左递归查找
                return this.leftNode.searchDeleteNodeParentNode(node);
            } else if (this.rightNode != null && node.value >= this.value) {
                //如果当前节点的值大于等于右子节点的值 向右递归查找
                return this.rightNode.searchDeleteNodeParentNode(node);
            } else {
                return null;//表示没有找到
            }
        }
    }
}
