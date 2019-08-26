package cn.sunshinehubery.stack;

/**
 * @description: 单向链表模拟栈
 * @author: sunshinehubery
 * @date: 2019/8/26 21:26
 * @Version: 1.0
 **/
public class LinkListStackDemo {
    public static void main(String[] args) {
        SingleLinkList singleLinkList = new SingleLinkList();
        NodeValue head1 = new NodeValue(1);
        NodeValue head2 = new NodeValue(2);
        NodeValue head3 = new NodeValue(3);
        NodeValue head4 = new NodeValue(4);
        singleLinkList.push(head1);
        singleLinkList.push(head2);
        singleLinkList.push(head3);
        singleLinkList.push(head4);
        singleLinkList.list();
        int pop1 = singleLinkList.pop();
        System.out.println(pop1);
        int pop2 = singleLinkList.pop();
        System.out.println(pop2);
        int pop3 = singleLinkList.pop();
        System.out.println(pop3);
        int pop4 = singleLinkList.pop();
        System.out.println(pop4);
    }
}

//创建一个单向链表模拟栈
class SingleLinkList {
    //创建一个头节点
    NodeValue head = new NodeValue(-1);

    //入栈
    public void push(NodeValue node) {
        NodeValue temp = null;
        temp = head.getNext();
        head.setNext(node);
        node.setNext(temp);
    }

    //出栈
    public int pop() {
        if (head.getNext() == null) {
            throw new RuntimeException("空栈");
        }
        NodeValue temp = head.getNext();
        head.setNext(temp.getNext());
        return temp.getValue();
    }

    //遍历
    public void list() {
        if (head.getNext() == null) {
            throw new RuntimeException("空栈");
        }
        NodeValue temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }
    }

    public NodeValue getHead() {
        return head;
    }
}

//创建一个节点类Node
class NodeValue{
    private int value;
    private NodeValue next;  //指向下一个节点
    public NodeValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeValue getNext() {
        return next;
    }

    public void setNext(NodeValue next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}