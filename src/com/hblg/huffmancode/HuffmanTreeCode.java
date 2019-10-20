package com.hblg.huffmancode;

import org.jetbrains.annotations.NotNull;

import java.util.*;


//        1.i like like like java do you like a java  获取字节码
//        2.[105, 32, 108, 105, 107, 101, 32, 108, 105, 107]
//        3.装入map 中 获取该数据 以及权值 key = data value = 次数
//        4.遍历map中的key value 装入list 中 封装到node节点中
//        5.通过list集合创建一个赫夫曼树  获取root节点
//        6.根据赫夫曼树 获取有数据的节点 以及根据路径创建出唯一路径值 转入Map集合中 key
//        7.通过原数据的字节码获得  map中的value 也就是出现的次数  封装成一个byte字节中 就完成了数据的解压工作
//
//        反解压
//        [-88, -65, -56, -65, -56, -65] 补码形式存储在硬盘上。
//        1.获取字节的数据 补码
/**
 * @author i
 * @create 2019/10/18 20:36
 * @Description 1、创建一个节点 包含数据域 权值 左节点 右节点
 * 2、获取数据的字节 i like like like java do you like a java
 */
class HuffmanTreeTest {
    //1.将赫夫曼编码表存入Map<Byte,String> 形式
    // 生成的赫夫曼编码表 {32=01 97=100}等形式
    private static Map<Byte, StringBuilder> dataMap = new HashMap<>();
    //用于拼接 赫夫曼树的路径  左边为0 右边为1
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        //获取字节数组
        byte[] bytes = "i like like like java do you like a java".getBytes();
        byte[] huffmanCode = huffmanCode(bytes);
        System.out.println(decode(dataMap,huffmanCode));
    }

    //编写一个方法，完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,StringBuilder> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<StringBuilder, Byte>  map = new HashMap<StringBuilder,Byte>();
        for(Map.Entry<Byte, StringBuilder> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null) {//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /***
     * 将一个二进制的byte转换成字符串
     * @param flag 是否要进行
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;

        if (flag){
            temp |= 256;
        }

        //将二进制数转换成string
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }


    //根据字节获取
    public static byte[] huffmanCode(byte[] bytes) {
        List<ByteNode> root = getRoot(bytes);
        //创建赫夫曼树
        ByteNode node = createHuffmanTree(root);
        //node.preOrder();
        //将赫夫曼树转换成对应的map集合 key是代表字母或者符号 value代表在源文件中字母或者符号出现的次数
        Map<Byte, StringBuilder> huffmanMap = getCode(node);
        return zip(bytes, huffmanMap);
    }

    /***
     * 编写一个方法，将这个字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码，压缩后的byte[]
     *
     * @return
     */
    public static byte[] zip(byte[] bytes, Map<Byte, StringBuilder> huffmanCodes) {
        StringBuffer stringBuffer = new StringBuffer();
        //System.out.println("bytes:"+Arrays.toString(bytes));
        //根据字节码获取到对应的值添加到StringBuffer
        for (byte b : bytes) {
            stringBuffer.append(huffmanCodes.get(b));
        }

        //将获取到的二进制 通过创建数组添加到数组中去。
        //确定创建数组的大小
        int length = 0;
        if ((stringBuffer.length() / 8) == 0) {
            length = stringBuffer.length() / 8;
        } else if (stringBuffer.length() / 8 != 0) {
            length = stringBuffer.length() / 8 + 1;
        }
        byte[] huffmanByteCode = new byte[length];//创建数组 存放对应的二进制数据
        int index = 0;//记录字节数字下标
        for (int i = 0; i < stringBuffer.length(); i += 8) {
            String str;
            if (i + 8 > stringBuffer.length()) {//超出了范围
                str = stringBuffer.substring(i);
            } else {
                str = stringBuffer.substring(i, i + 8);
            }
            huffmanByteCode[index] = (byte) Integer.parseInt(str, 2);
            index++;
        }
        return huffmanByteCode;
    }


    //为了调用方便，我们重载 getCodes
    public static Map<Byte, StringBuilder> getCode(ByteNode root) {
        if (root == null) {
            return null;
        }

        //左节点
        getCode(root.leftNode, "0", stringBuilder);
        //右节点
        getCode(root.rightNode, "1", stringBuilder);

        return dataMap;
    }

    /***
     * 功能 : 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param root 根节点
     * @param pathCode 路径码 左子节点为0 右子节点为1
     * @param stringBuilder
     */
    public static void getCode(ByteNode root, String pathCode, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(pathCode);
        if (root != null) {//判断当前节点是否为空
            if (root.getData() == 0) {//判断当前节点是否为父节点
                getCode(root.leftNode, "0", stringBuilder2);//左递归
                getCode(root.rightNode, "1", stringBuilder2);//右递归
            } else {
                //将值添加到map中 key 为值 value为路径值
                dataMap.put(root.getData(), stringBuilder2);
            }
        }
    }


    /***
     * 创建赫夫曼树
     * @param lists
     */
    public static ByteNode createHuffmanTree(List<ByteNode> lists) {
        while (lists.size() > 1) {

            //进行排序
            Collections.sort(lists);
            //获取最小的节点
            ByteNode leftNode = lists.get(0);
            ByteNode rightNode = lists.get(1);

            //创建一个新节点
            ByteNode parentNode = new ByteNode(leftNode.getCount() + rightNode.getCount());
            parentNode.leftNode = leftNode;//左节点
            parentNode.rightNode = rightNode;//右节点

            lists.remove(leftNode);
            lists.remove(rightNode);

            lists.add(parentNode);
        }
        return lists.get(0);
    }

    //编写一个方法，将准备构建赫夫曼树的Node 节点放到 List  , 形式 [Node[date=97 ,weight = 5],
    // Node[]date=32,weight = 9]......],  体现 d:1 y:1 u:1 j:2  v:2  o:2  l:4  k:4  e:4 i:5  a:5   :9
    public static List<ByteNode> getRoot(byte[] bytes) {
        //创建map存放字节数组中的数据 以及对应的权重
        Map<Byte, Integer> dataMap = new HashMap<>();

        List<ByteNode> rootNodes = new ArrayList<>();

        //遍历字节数组 将对应的数据 以及权重放到集合中
        for (byte b : bytes) {
            Integer count = dataMap.get(b);
            if (count == null) {
                dataMap.put(b, 1);
            } else {
                dataMap.put(b, count + 1);
            }
        }
        //将获取到的存入List集合中
        //Set<Map.Entry<Byte, Integer>> entries = dataMap.entrySet();
        for (Map.Entry<Byte, Integer> entry : dataMap.entrySet()) {
            rootNodes.add(new ByteNode(entry.getKey(), entry.getValue()));
        }
        return rootNodes;
    }
}


/***
 * 节点
 */
class ByteNode implements Comparable {
    private byte data;//数据域
    private Integer count;//权值
    ByteNode leftNode;
    ByteNode rightNode;

    public ByteNode(int count) {
        this.count = count;
    }

    public ByteNode(byte data, Integer count) {
        this.data = data;
        this.count = count;
    }

    public byte getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ByteNode{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }

    /***
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        //左节点
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        //右节点
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.count - ((ByteNode) o).count;
    }
}