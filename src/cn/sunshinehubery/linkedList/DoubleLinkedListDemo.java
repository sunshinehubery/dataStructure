package cn.sunshinehubery.linkedList;

/**
 * @description: 双向链表
 * @author: sunshinehubery
 * @date: 2019/8/24 14:50
 * @Version: 1.0
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2 = new HeroNode2(5,"武松","行者");
        HeroNode2 heroNode3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 heroNode4 = new HeroNode2(8,"鲁智深","花和尚");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.addOrderList(heroNode3);
        linkedList.add(heroNode4);
        linkedList.list();

    }
}
//定义一个双向链表
class DoubleLinkedList{
    //初始化一个头节点，不需要放数据
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }
    //遍历
    public void list(){
        if(head.next == null){
            System.out.println("空链表");
            return;
        }else {
            HeroNode2 temp = head.next;
            while (true){
                if(temp == null){
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }
    //添加方法
    public void add(HeroNode2 newNode){
        HeroNode2 temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.pre = temp;
    }
    //添加并排序
    public void addOrderList(HeroNode2 newNode){
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = newNode;
                newNode.pre = temp;
                break;
            }
            if (temp.next.no > newNode.no) {
                newNode.next = temp.next;
                newNode.pre = temp;
                temp.next.pre = newNode;
                temp.next = newNode;
                break;
            }else if(temp.next.no == newNode.no){
                break;
            }
            temp = temp.next;
        }
    }
    //修改方法
    public void update(HeroNode2 newNode){
        if(head.next == null){
            System.out.println("空链表");
            return;
        }else {
            HeroNode2 temp = head.next;
            boolean flag = false;
            while (true){
                if(temp == null){
                    break;
                }
                if(temp.no == newNode.no){
                    flag = true;
                }
                temp = temp.next;
            }
            if(flag){
                temp.name = newNode.name;
                temp.nickName = newNode.nickName;
            }else {
                System.out.println("没有找到");
            }
        }
    }
    //删除方法
    public void delete(int no){
        if(head.next == null){
            System.out.println("空链表");
            return;
        }else {
            HeroNode2 temp = head.next;
            boolean flag = false;
            while (true){
                if(temp == null){
                    break;
                }
                if(temp.no == no){
                    flag = true;
                }
                temp = temp.next;
            }
            if(flag){
                temp.pre.next = temp.next;
                //判断是不是最后一个节点
                if(temp.next != null){
                    temp.next.pre = temp.pre;
                }
            }else {
                System.out.println("没有找到");
            }
        }
    }
}
//创建节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  //指向下一个节点
    public HeroNode2 pre;  //指向上一个节点
    public HeroNode2(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
