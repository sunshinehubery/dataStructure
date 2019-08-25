package cn.sunshinehubery.linkedList;

/**
 * @description: 约瑟夫问题
 * @author: sunshinehubery
 * @date: 2019/8/25 16:26
 * @Version: 1.0
 **/
public class JosePhu {
    public static void main(String[] args) {
        CircleSingleLinkList circleSingleLinkList = new CircleSingleLinkList();
        circleSingleLinkList.add(5);
        circleSingleLinkList.list();
        circleSingleLinkList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkList{
    //创建一个first节点，为null
    private Boy first = null;

    //添加Boy类的节点
    public void add(int nums){
        if(nums < 1){
            System.out.println("不足以构建单向环形链表");
            return;
        }
        //创建一个辅助指针curBoy，用于构建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= nums;i++ ){
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形单向链表
    public void list(){
        if(first == null){
            System.out.println("空链表");
            return;
        }else {
            Boy curBoy = first;
            while (true){
                System.out.printf("小孩的编号是%d \n",curBoy.getNo());
                if(curBoy.getNext() == first){
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }
    }

    //约瑟夫问题，小孩出圈的顺序

    /**
     * @param startNo  表示从第几个开始报数
     * @param countNum  表示报数几下
     * @param nums     表示环形单向链表总节点数
     */
    public void countBoy(int startNo,int countNum,int nums){
        //校验
        if(first == null||startNo < 1||countNum > nums){
            System.out.println("参数错误，重新输入参数！");
            return;
        }
        //创建一个辅助指针helper，需要指向最后一个节点
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先让helper和first移动至startNo-1的位置
        for (int j = 0;j < startNo - 1;j++){
            helper = helper.getNext();
            first = first.getNext();
        }
        //开始报数，将helper和first移动至countNum -1的位置
        while(true){
            if(helper == first){
                break;
            }
            for (int j = 0;j < countNum-1;j++){
                helper = helper.getNext();
                first = first.getNext();
            }
            //此时first的节点就需要出圈
            System.out.printf("编号为%d的小孩出圈\n",first.getNo());
            //删除节点
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("留在圈中的是编号为%d的小孩\n",first.getNo());
    }
}

//创建一个Boy类用来表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点，默认是null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
