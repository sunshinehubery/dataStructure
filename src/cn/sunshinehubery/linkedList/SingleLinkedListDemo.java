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

        //测试链表反转
        System.out.println("反转后链表：");
        revserLinkList(singleLinkedList.getHead());
        singleLinkedList.list();

        //测试修改方法
        System.out.println("修改后的操作：");
        HeroNode nerHeroNode = new HeroNode(2,"小卢","玉麒麟。。。");
        singleLinkedList.update(nerHeroNode);
        singleLinkedList.list();
        System.out.println("链表的节点的有效个数：" + getLength(singleLinkedList.getHead()));

        //测试删除方法
        System.out.println("删除后遍历链表信息：");
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.list();

        System.out.println("链表的节点的有效个数：" + getLength(singleLinkedList.getHead()));
        System.out.println("倒数第一个节点的信息：" + findLastIndexHeroNode(singleLinkedList.getHead(),1));
        System.out.println("倒数第一个节点的信息：" + findLastIndexHeroNode(singleLinkedList.getHead(),2));
    }
    //带头节点的单链表的有效节点的个数（去除头节点）
    //需要传入一个头节点
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //添加辅助节点，遍历
        HeroNode temp = head.next;  //表示去除了头节点
        while(temp != null){
            temp = temp.next;
            length++;
        }
        return length;
    }

    //查找单链表的倒数第k个节点的信息
    //编写一个方法，接收head和k值
    //首先遍历整个链表，获取整个链表的有效节点的个数getLength();
    //定位找到那个节点getLength()-k
    //使用for循环，找到那个节点
    public static HeroNode findLastIndexHeroNode(HeroNode head,int index){
        if(head.next == null){
            return null;
        }
        int size = getLength(head);
        if(index <= 0||index > size){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0;i < size-index;i++){
            temp = temp.next;
        }
        return temp;
    }

    //反转链表
    public static void revserLinkList(HeroNode head){
        //首先判断链表是否为空或者只有一个节点
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助节点
        HeroNode temp = head.next;
        //定义一个当前节点的下一个节点，防止找不到后续链表节点信息
        HeroNode nextNode = null;
        //重新定义一个新的链表并初始化头节点
        HeroNode head2 = new HeroNode(0,"","");
        //遍历原来的链表
        while(temp != null){
            //先保存当前节点的下一个节点
            nextNode = temp.next;
            temp.next = head2.next; //将当前节点的下一个节点指向新链表的最前端
            head2.next = temp; //将当前的节点连接上新链表
            temp = nextNode;
        }
        //将老的头节点连接上
        head.next = head2.next;
    }
}

//定义单链表
class SingleLinkedList{
    //首先我们需要初始化一个头节点，头节点不储存数据，拥有next区域指向下一个节点
    HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

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
