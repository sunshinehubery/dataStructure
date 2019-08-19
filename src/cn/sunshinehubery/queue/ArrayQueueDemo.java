package cn.sunshinehubery.queue;

import java.util.Scanner;

/**
 * @description: 数组模拟队列
 * @author: sunshinehubery
 * @date: 2019/8/19 11:29
 * @Version: 1.0
 **/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列
        ArrayQueue queue = new ArrayQueue(3);
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

//编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize; //数组最大容量
    private int front; //队列头部
    private int rear; //队列尾部
    private int[] array; //模拟队列的数组

    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        array = new int[maxSize];
        front = -1; //指向队列头部，front是处于队列头部的前一个位置
        rear = -1; //指向队列尾部，rear是处于队列尾部的最后一个数据的位置
    }

    //判断队列是否已经满了
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int num){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列已满，不能添加···");
            return;
        }
        rear++;
        array[rear] = num;
    }

    //从队列中取出数据
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //为空，抛出异常
            throw new RuntimeException("队列为空，没有数据···");
        }
        front++;
        return array[front];
    }

    //显示队列所有数据
    public void showQueue(){
        //判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空，没有数据···");
            return;
        }
        for(int i = 0;i < array.length;i++){
            System.out.printf("array[%d]=%d\n",i,array[i]);
        }
    }

    //显示队列头部的数据
    public int headQueue(){
        //判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据···");
        }
        return array[front+1];
    }

}
