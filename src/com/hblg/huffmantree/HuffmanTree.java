package com.hblg.huffmantree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author i
 * @create 2019/10/17 19:27
 * @Description 赫夫曼树创建
 *
 * 主要实现思路:1.从小到达进行排序，将每一个数据，每个数据都是一个节点，每个节点都可以看成是一颗最简单的二叉树，
 * 2.取出根节点权值最小的两颗二叉树。
 * 3.组成一课新的二叉树，该树的二叉树的根节点的权值是前面两颗二叉树的根节点权值的值
 * 4.在将这颗新的二叉树，以根节点的权值大小 在此排序，不断重复 直至数列中，所有的数据被处理，就得到了一颗赫夫曼树。
 */
public class HuffmanTree {

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] array = {13, 7, 8, 1};
        Node rootNode = createHuffmanTree(array);
        preHuffmanTree(rootNode);
    }

    /***
     * 遍历赫夫曼树 - 前序遍历
     * @param rootNode
     */
    public static void preHuffmanTree(Node rootNode) {
        if (rootNode != null) {
            rootNode.preOrder();
        } else {
            System.out.println("空树！");
        }
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] array) {
        //数组转换成指定的赫夫曼树
        List<Node> nodes = new ArrayList<>();
        //遍历 将数组中的值添加到集合中
        for (Integer value : array) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {

            //将集合中的元素，进行排序 从小到大
            Collections.sort(nodes);

            System.out.println(nodes);

            //获取最小权值的数
            Node leftNode = nodes.get(0);
            //获取次小权值的数
            Node rightNode = nodes.get(1);

            //添加新的节点 最小权值和次小权值的总数和
            Node parentNode = new Node(leftNode.getValue() + rightNode.getValue());
            parentNode.leftNode = leftNode;
            parentNode.rightNode = rightNode;

            //删除 最小权值节点 和 次小权值的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加到父节点上
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }


}

//创建一个节点
class Node implements Comparable {
    private Integer value;
    public Node leftNode;
    public Node rightNode;

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }

    public Node(Integer value, Node leftNode, Node rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /***
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。
    @Override
    public int compareTo(@NotNull Object o) {
        return this.value - ((Node) o).value;
    }
}
