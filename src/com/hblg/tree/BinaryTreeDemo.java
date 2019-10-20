package com.hblg.tree;

/**
 * @author i
 * @create 2019/10/12 17:33
 * @Description 二叉树
 *   二叉树
 *   前序遍历
 *         父-》左-》右
 *   中序遍历
 *         左-》父-》右
 *   后续遍历
 *         左-》右-》父
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        StudentNode root = new StudentNode(1,"宋江");
        StudentNode node2 = new StudentNode(2,"吴用");
        StudentNode node3 = new StudentNode(3,"罗俊毅");
        StudentNode node4 = new StudentNode(4,"林冲");
        StudentNode node5 = new StudentNode(5,"张飞");

        binaryTree.setRootNode(root);//设置根节点
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
//        //前序遍历 父左右
//        System.out.println("前序遍历:"); // 1.2.3.4
//        binaryTree.preOrder();
//
//        //中序遍历 左 父 右   2134
//        System.out.println("中序遍历");
//        binaryTree.midOrder();
//
//        //后序遍历 左 右 父  2431
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
        //删除节点
//        binaryTree.delStudentNode(5);
//        binaryTree.preOrder();//前序遍历  1 2
        binaryTree.delStudentNode(5);
        binaryTree.postOrder();//后序遍历
    }

}

/***
 * 二叉树
 */
class BinaryTree{

    private StudentNode rootNode;//根节点

    public StudentNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(StudentNode rootNode) {
        this.rootNode = rootNode;
    }

    //前序遍历 父左右
    public void preOrder(){
        if (this.rootNode != null){
            this.rootNode.preOrder();
        }else {
            System.out.println("二叉树不存在~");
        }
    }
    //中序遍历 左父右
    public void midOrder(){
        if(this.rootNode != null){
            this.rootNode.midOrder();
        }else {
            System.out.println("二叉树不存在~");
        }
    }
    //后序遍历 左右父
    public void postOrder(){
        if (this.rootNode != null){
            this.rootNode.postOrder();
        }else {
            System.out.println("二叉树不存在~");
        }
    }

    //前序查找
    public StudentNode preOrderSearch(int no){
        if (this.rootNode != null){
            return rootNode.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序遍历 左父右
    public StudentNode midOrderSearch(int no){
        if(this.rootNode != null){
            return rootNode.midOrderSearch(no);
        }else {
            return null;
        }
    }

    //后序遍历 左右 父
    public StudentNode postOrderSearch(int no){
        if (this.rootNode != null){
            return rootNode.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //删除节点
    public void delStudentNode(int no){
        if (this.rootNode != null){
            if (this.rootNode.getId() == no){
                this.rootNode  = null;//删除就是根节点
            }else {
                this.rootNode.delStudentNode(no);//删除子节点 递归查找删除
            }
        }else {
            System.out.println("没有找到!");
        }
    }

}

/***
 * 节点类
 */
class StudentNode{

    private Integer id;
    private String name;
    private StudentNode left;//左节点
    private StudentNode right;//右节点

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

    public StudentNode getLeft() {
        return left;
    }

    public void setLeft(StudentNode left) {
        this.left = left;
    }

    public StudentNode getRight() {
        return right;
    }

    public void setRight(StudentNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "StudentNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    //前序遍历 父左右
    public void preOrder(){
        //父节点
        System.out.println(this);
        //左子节点遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //右子节点遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历 左 父 右
    public void midOrder(){
        //左节点遍历
        if(this.left != null){
            this.left.midOrder();
        }
        //父节点
        System.out.println(this);
        //右节点
        if (this.right != null){
            this.right.midOrder();
        }
    }

    //后序遍历 左 右 父
    public void postOrder(){
        //左节点遍历
        if (this.left != null){
            this.left.postOrder();
        }
        //右节点
        if (this.right != null){
            this.right.postOrder();
        }
        //父节点
        System.out.println(this);
    }


    /***
     * 使用前序 中序 后序的方式来查询指定的节点
     * 前序查找思路
     *  1.先判断当前节点的no是否等于要查找的
     *  2.如果是相等，则返回当前节点
     *  3.如果不等，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
     *  4.如果左递归前序查找，找到节点，则返回 否则继续判断，
     *  当前的节点的右子节点是否为空，如果不空，则继续向右递归前序查找
     *
     */
    public StudentNode preOrderSearch(int no){
        //1、先判断当前节点no是否是要查找的
        if(this.getId() == no){
            return this;
        }
        StudentNode currentNode = null;
        //2、如果不等，则判断当前节点的左子节点是否为空
        if(this.left!=null){
            currentNode = this.left.preOrderSearch(no);//递归前序查找
        }
        //说明左子树找到
        if (currentNode != null){
            return currentNode;
        }
        //3.判断右子节点是否相等
        if (this.right != null){
            currentNode = this.right.preOrderSearch(no);//递归前序查找
        }
        return currentNode;
    }


    /***
     * 中序遍历
     *   1.判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
     *   2.如果找到，则返回，如果没有找到，就和当前节点做比较，如果是返回当前节点，否则继续进行向右递归中序查找
     *   3.如果右递归中序查找，找到就返回 否则返回null
     * @param no
     * @return
     */
    public StudentNode midOrderSearch(int no){
        StudentNode currentNode = null;
        //1.判断当前节点的左子节点是否为空，如果不为空 则递归中序查找
        if (this.left != null){
            currentNode = this.left.midOrderSearch(no);
        }
        //2.找到直接返回
        if (currentNode != null){
            return currentNode;
        }
        //3.如果找到 直接返回
        if (this.getId() == no){
            return this;
        }
        //4.否则进行向右递归查找
        if (this.right != null){
            currentNode = this.right.midOrderSearch(no);
        }
        return currentNode;
    }


    /***
     *  后序遍历
     *  1.判断当前节点的左子节点是否为空，如果不为空，则递归后查找
     *  2.如果找到，就返回 如果没有找到，就判断当前节点的右子节点是否为空，如果不为空 则右递归进行后续查找，如果找到，就返回
     *  3.就和当前节点进行，比如，如果是则返回，否则返回null.
     * @param no
     * @return
     */
    public StudentNode postOrderSearch(int no){
        StudentNode currentNode = null;
        if (this.left != null){//左子树查找
            currentNode = this.left.postOrderSearch(no);
        }
        if (currentNode != null){//找到 直接返回
            return currentNode;
        }
        if (this.right != null){//右子树查找
            currentNode = this.right.postOrderSearch(no);
        }
        if (currentNode != null){//找到 直接返回
            return currentNode;
        }

        if (this.getId() == no){
            return  this;
        }
        return currentNode;
    }

    /***
     * 递归删除节点
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     * 思路:1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除的节点
     *  2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null;并且就返回(结束递归删除)
     *  3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点 就将this.right = null;并且就返回(结束递归删除)
     *  4.如果第2步和第三部没有删除节点，那么我们就需要向左子节点树进行递归删除
     *  5.如果第4步也没有删除节点，则应当向右子树进行递归删除。
     *  @param no
     */
    public void delStudentNode(int no){
        //2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null
        if (this.left != null && this.left.getId()== no){
            this.left = null;
            return;
        }
        //3.如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right = null
        if (this.right != null && this.right.getId() == no){
            this.right = null;
            return;
        }
        //4.递归左子树
        if (this.left != null){
            this.left.delStudentNode(no);
        }

        //5.递归右子树
        if (this.right != null){
            this.right.delStudentNode(no);
        }

    }

}

