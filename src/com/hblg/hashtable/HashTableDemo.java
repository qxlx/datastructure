package com.hblg.hashtable;

/**
 * @author i
 * @create 2019/10/12 10:04
 * @Description 哈希表
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        //添加
        hashTable.add(new Student(1,"张三","电大"));
        hashTable.add(new Student(12,"李四","武大"));
        hashTable.add(new Student(22,"李四2","华科"));
        hashTable.add(new Student(32,"李四3","清华"));
        hashTable.add(new Student(52,"李四5","山大"));
        hashTable.add(new Student(11,"李四6","中南"));
        hashTable.add(new Student(21,"李四7","贝丽达"));
        hashTable.add(new Student(31,"李四8","武大"));
        hashTable.delete(31);
        hashTable.uodate(new Student(1,"张三","华北理工"));
        hashTable.printList();
    }

}


/***
 *
 */
class HashTable{

    private StudentLinkedList [] studentLinkedList = null;

    private int size;

    public HashTable(int size){
        this.size = size;
        studentLinkedList = new StudentLinkedList [size];
        //必须进行初始化
        for (int i = 0; i < size ; i++) {
            studentLinkedList[i] = new StudentLinkedList();
        }
    }

    //添加
    public void add(Student student){
        if (student!=null){
            int index = hash(student.getId());
            studentLinkedList[index].add(student);
        }else {
            throw new NullPointerException("数据为空!");
        }
    }
    //遍历
    public void printList(){
        for (int i = 0; i < studentLinkedList.length; i++) {
            studentLinkedList[i].printList();
        }
    }
    //查找
    public void findStudentById(int id){
        //先找到对应的数组
        int index = hash(id);
        studentLinkedList[index].find(id);
    }
    //删除
    public void delete(int id){
        int index = hash(id);
        studentLinkedList[index].delete(id);
    }

    //修改
    public void uodate(Student student){
        int index = hash(student.getId());
        studentLinkedList[index].uodateStudentById(student);
    }

    //散列函数
    private  int hash(int id){// 2%3 2
        return id%size;
    }

}

/***
 * 单链表
 */
class StudentLinkedList{

    private Student head;//头结点

    //添加
    public void add(Student student){
        if(head == null){//当前链表head为空
            head = student;
            return;
        }
        Student currentNode = head;
        //遍历获取链表的最后
        while (true){
            if(currentNode.next == null){
                break;
            }
            //遍历
            currentNode = currentNode.next;
        }
        currentNode.next = student;
    }

    //遍历
    public void printList(){
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        Student studentNode = head;//获取到头结点 进行遍历
        while (true){
            System.out.print("id:"+studentNode.getId()+" name:"+studentNode.getName()+" school:"+studentNode.getSchool()+"\t ->");
            if(studentNode.next==null){
                break;
            }
            studentNode = studentNode.next;
        }
        System.out.println();
    }

    //查找
    public void find(int id){
        if (head == null){
            throw new NullPointerException("linkedList is null!");
        }
        Student currentNode = head;
        while (true){
            if(currentNode.getId() == id){//找到
                System.out.println("find it is info :"+currentNode);
                break;
            }
            if (currentNode.next == null){//遍历到链表最后
                System.out.println("没有找到");
                break;
            }
            currentNode = currentNode.next;
        }
    }

    //删除
    public void delete(int id){
        if(head == null){
            throw  new RuntimeException("没有找到");
        }

        Student currentNode = head;
        while (true){
            if(currentNode.next.getId() == id){
                currentNode.next = currentNode.next.next;
                break;
            }
            if(currentNode.next == null){
                System.out.println("没有找到");
                break;
            }
            currentNode = currentNode.next;
        }
    }

    //修改  根据id修改用户信息
    public void uodateStudentById(Student student){
        if (head == null){
            throw new RuntimeException("没有找到");
        }

        Student currentNode = head;
        while (true){
            if(currentNode.getId() == student.getId()){
                currentNode.setName(student.getName());
                currentNode.setSchool(student.getSchool());
                System.out.println("currentNode:"+currentNode);
                System.out.println("student:"+student);
                break;
            }

            if(currentNode.next == null){
                System.out.println("没有找到");
                break;
            }
            currentNode = currentNode.next;
        }
    }

}

/***
 * id
 * name
 * school
 */
class Student{
    private int id;
    private String name;
    private String school;
    Student next;
    public Student() {
    }

    public Student(int id,String name, String school) {
        this.name = name;
        this.id = id;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

}