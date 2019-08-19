package cn.sunshinehubery.queue;

import java.util.Scanner;

/**
 * @description: 环形队列,一般需要循环都需要使用%取模
 * @author: sunshinehubery
 * @date: 2019/8/19 15:23
 * @Version: 1.0
 **/
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列,由于空出一个空间，所以maxSize：4，但是有效空间是3
        CircleArrayQueue queue = new CircleArrayQueue(4);
        //用于接收用户输入
        char key = ' ';
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("h(head)：取出队列头部数据");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取出数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.printf("队列头部数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输入一个数据");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default :
                    break;
            }
        }
    }
}
//编写一个CircleArrayQueue类
class CircleArrayQueue{
    private int maxSize; //数组的最大容量
    private int front; //队列的头部，调整front指向队列的第一个数据的位置，front -1 -> 0
    private int rear; //队列的尾部，调整rear指向最后一个元素的后一个位置，空一空间作为约定 rear -1 -> 0
    private int[] circleArray; //模拟环形队列

    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        circleArray = new int[maxSize];
        //int 类型的默认值是0，不处理front和rear
    }

    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列中
    public void addQueue(int num){
        if (isFull()){
            System.out.println("队列已满，不能添加···");
            return;
        }
        circleArray[rear] = num;
        //rear后移，但我们需要考虑取模
        rear = (rear+1)%maxSize;
    }

    //从队列中取出数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据···");
        }
        int value = circleArray[front];
        //循环也要考虑取模
        front = (front+1)%maxSize;
        return value;
    }

    //显示队列中所有的数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据···");
            return;
        }
        //我们需要从front开始遍历，由于是环形，所以我们需要知道队列中的有效数据
        for (int i = front;i < front + size();i++){
            System.out.printf("circleArray[%d]=%d\n",i%maxSize,circleArray[i%maxSize]);
        }
    }

    //获取队列的有效数据
    public int size(){
        //考虑到空出一个空间，所以我们最好也是取模
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头部的数据
    public int headQueue(){
        //判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据···");
        }
        return circleArray[front+1];
    }
}
