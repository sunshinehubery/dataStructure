package cn.sunshinehubery.linkedList;

/**
 * @description: 带头节点的单向链表
 * @author: sunshinehubery
 * @date: 2019/8/21 0:30
 * @Version: 1.0
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        singleLinkedList.list();
    }
}

//定义单链表
class SingleLinkedList{
    //首先我们需要初始化一个头节点，头节点不储存数据，拥有next区域指向下一个节点
    HeroNode head = new HeroNode(0,"","");

    //向该链表添加节点数据
    public void add(HeroNode heroNode){
        //由于头节点不能动，所以我们需要一个辅助的节点
        HeroNode temp = head;
        //添加节点数据是在链表的尾部添加，所以判断.next是否为空
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }
    //遍历链表
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("该链表是空链表");
        }else {
            //由于头节点不能动，所以也需要一个辅助节点
            HeroNode temp = head.next;
            while (true) {
                if(temp == null){
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }
}

//创建节点,每个节点储存数据（水浒传英雄）data区域和指向下一个节点的next区域
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个节点
    //构造函数
    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //打印信息
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
