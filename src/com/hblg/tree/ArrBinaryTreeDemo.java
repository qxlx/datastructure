package com.hblg.tree;

/**
 * @author i
 * @create 2019/10/13 18:01
 * @Description 顺序存储二叉树
 *
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int [] array = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(array);
        arrBinaryTree.postOrder(0);
    }

}

/***
 * 顺序存储二叉树
 */
class ArrBinaryTree{

    private int [] array;

    public ArrBinaryTree(int[] array) {
        this.array = array;
    }

    //前序遍历
    public void preOrder(int no){
        if (array == null || array.length == 0){
            System.out.println("数组为空 不能进行遍历!");
            return;
        }

        System.out.print(array[no]+"\t");
        //左节点遍历
        if ((2*no+1)<array.length){
            preOrder((2*no+1));
        }
        //右节点遍历
        if ((2*no+2)<array.length){
            preOrder((2*no+2));
        }
    }

    //中序遍历
    public void midOrder(int no){
        if(array == null || array.length == 0){
            System.out.println("数组为空，不能进行遍历");
            return;
        }
        //左节点遍历
        if ((2*no+1)<array.length){
            preOrder((2*no+1));
        }
        System.out.print(array[no]+"\t");
        //右节点遍历
        if ((2*no+2)<array.length){
            preOrder((2*no+2));
        }
    }

    //后续遍历
    public void postOrder(int no){
        if(array == null || array.length == 0){
            System.out.println("数组为空，不能进行遍历");
            return;
        }
        //左节点遍历
        if ((2*no+1)<array.length){
            preOrder((2*no+1));
        }

        //右节点遍历
        if ((2*no+2)<array.length){
            preOrder((2*no+2));
        }
        System.out.print(array[no]+"\t");
    }

}
