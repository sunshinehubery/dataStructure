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
        singleLinkedList.add(heroNode4);
        singleLinkedList.list();

        System.out.println("排序后的链表遍历：");
        //测试根绝no排序
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.list();

        //测试修改方法
        System.out.println("修改后的操作：");
        HeroNode nerHeroNode = new HeroNode(2,"小卢","玉麒麟。。。");
        singleLinkedList.update(nerHeroNode);
        singleLinkedList.list();

        //测试删除方法
        System.out.println("删除后遍历链表信息：");
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
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

    //添加节点，按照no值的大小来排列
    public void addByOrder(HeroNode newHeroNode){
        //由于头节点是不能改动，所以我们需要一个辅助节点
        HeroNode temp = head;
        boolean flag = false;  //表示添加的节点的no的信息和链表中是否有相同
        while(true){
            if(temp.next == null){
                //表示已经到了链表的末端
                break;
            }
            if(temp.next.no > newHeroNode.no){
                //表示已经找到
                break;
            }else if(temp.next.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;//用于遍历
        }
        if(flag){
            System.out.printf("链表存在编号为%d的信息\n",newHeroNode.no);
        }else {
            newHeroNode.next = temp.next;  //表示将后一个的信息放在插入的后面
            temp.next = newHeroNode; //表示前一个的后一个是插入的节点
        }
    }

    //修改链表中某节点信息（no不做修改）
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("这是一个空链表");
            return;
        }
        //由于头节点是不能改动，需要辅助节点
        HeroNode temp = head;
        boolean flag = false;  //表示链表中是否存在这么一个节点
        while(true){
            if(temp.next == null){
                System.out.println("不存在这么一个节点的链表！");
                break;
            }
            if(temp.next.no == newHeroNode.no){
                //表示存在这么一个节点
                temp.next.name = newHeroNode.name;
                temp.next.nickName = newHeroNode.nickName;
                break;
            }
            temp = temp.next;
        }
    }

    //根据节点的no信息删除链表节点（先找到该节点的前一个节点，将前一个节点的next修改为需要删除节点的后一个节点信息）
    //在Java中当该节点没有被应用就会通过回收机制回收，释放空间
    public void delete(int no){
        //创建一个辅助节点
        HeroNode temp = head;
        boolean flag = false; //用于判断是否找到该节点
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //删除找到的节点
            temp.next = temp.next.next;
        }else {
            System.out.printf("不存在编号为%d的一个节点\n",no);
        }
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
