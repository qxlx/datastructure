package com.hblg.tree.threadedbinarytree;

/**
 * @author i
 * @create 2019/10/14 17:44
 * @Description 线索二叉树的创建和遍历
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        StudentNode root = new StudentNode(1,"root节点");
        StudentNode node2 = new StudentNode(3,"3号节点");
        StudentNode node3 = new StudentNode(6,"6号节点");
        StudentNode node4 = new StudentNode(8,"8号节点");
        StudentNode node5 = new StudentNode(10,"10号节点");
        StudentNode node6 = new StudentNode(14,"14号节点");

        root.setLeftNode(node2);
        root.setRightNode(node3);

        node2.setLeftNode(node4);
        node2.setRightNode(node5);

        node3.setLeftNode(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedBinaryTree2();
        threadedBinaryTree.threadedBinaryList();
    }

}


/***
 * 线索二叉树
 */
class ThreadedBinaryTree{
    private StudentNode root;//根节点
    private StudentNode preNode;//当前节点没有任何前驱节点的信息引用必须指定一个前驱节点的引用，当前节点改变 pre节点改变

    public StudentNode getRoot() {
        return root;
    }

    public void setRoot(StudentNode root) {
        this.root = root;
    }

    public void threadedBinaryTree2(){
        this.threadedBinaryTree(root);
    }

    //遍历线索二叉树的方法
    public void threadedBinaryList(){
        //定义一个遍历 存储当前遍历的节点 从root开始
        StudentNode currentNode = root;

        while (currentNode != null){
            //循环的找到type=1节点 就是8号节点
            while (currentNode.getLeftNodeType() == 1){
                currentNode = currentNode.getLeftNode();
            }

            System.out.println(currentNode);

            while (currentNode.getRightNodeType() == 2){
                //如果当前结点的右指针指向的是后继结点,就一直输出
                currentNode = currentNode.getRightNode();
                System.out.println(currentNode);
            }
            //替换这个遍历的结点
            currentNode = currentNode.getRightNode();
        }


    }

    /***
     * 线索化二叉树---中序遍历
     */
    private void threadedBinaryTree(StudentNode currentNode){
        //如果当前节点为null 则直接返回
        if (currentNode == null){
            return;
        }

        //左子树线索化
        threadedBinaryTree(currentNode.getLeftNode());

        //左子节点设置
        if (currentNode.getLeftNode() == null){
            //如果当前节点为2 设置前驱节点
            currentNode.setLeftNode(preNode);
            //设置当前节点的左子节点为类型2 表示为前驱节点
            currentNode.setLeftNodeType(2);
        }

        //右子节点设置
        if (preNode!= null && preNode.getRightNode() ==null){
            preNode.setRightNode(currentNode);//前驱节点设置当前节点的后继节点
            preNode.setRightNodeType(2);//设置节点类型为后继节点
        }

        preNode = currentNode;//!每处理一个节点后，让当前节点是下一个节点的前驱节点。

        //右子树线索化
        threadedBinaryTree(currentNode.getRightNode());

    }

}

//二叉树节点
class StudentNode{
    private Integer id;
    private String name;
    private StudentNode leftNode;//左子节点
    private StudentNode rightNode;//右子节点

    private Integer leftNodeType = 1;//类型 1 表示左节点为左子节点 2 表示左子节点为前驱节点
    private Integer rightNodeType = 1;//类型 1 表示有节点为右子节点 2 表示右子节点为后继节点

    public StudentNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(StudentNode leftNode) {
        this.leftNode = leftNode;
    }

    public StudentNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(StudentNode rightNode) {
        this.rightNode = rightNode;
    }

    public Integer getLeftNodeType() {
        return leftNodeType;
    }

    public void setLeftNodeType(Integer leftNodeType) {
        this.leftNodeType = leftNodeType;
    }

    public Integer getRightNodeType() {
        return rightNodeType;
    }

    public void setRightNodeType(Integer rightNodeType) {
        this.rightNodeType = rightNodeType;
    }

    @Override
    public String toString() {
        return "StudentNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}