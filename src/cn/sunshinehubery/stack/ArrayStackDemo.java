package cn.sunshinehubery.stack;

import java.util.Scanner;

/**
 * @description: 数组模拟栈
 * @author: sunshinehubery
 * @date: 2019/8/26 20:47
 * @Version: 1.0
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        Boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:表示遍历栈");
            System.out.println("exit:表示退出程序");
            System.out.println("push:表示入栈");
            System.out.println("pop:表示出栈");
            System.out.println("请输入你的选择:");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("输入需要入栈的数值:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("弹栈的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                    default:
                        break;
            }
        }
    }
}

class ArrayStack{
    private int maxSize;  //设置容量
    private int[] stack;  //数组，模拟栈
    private int top = -1; //表示栈顶，初始值为-1

    public ArrayStack(int size){
        this.maxSize = size;
        stack = new int[this.maxSize];
    }

    //判断栈是否满
    public Boolean isFull(){
        return top == maxSize - 1;
    }

    //判断栈是否为空
    public Boolean isEmpty(){
        return top == -1;
    }

    //入栈操作
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈操作
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("空栈，没有数据。。。");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("空栈，没有数据。。。");
            return;
        }
        for (int i = top;i >= 0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
