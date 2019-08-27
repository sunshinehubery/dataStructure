package cn.sunshinehubery.stack;

/**
 * @description: 栈实现综合计算器
 * @author: sunshinehubery
 * @date: 2019/8/27 22:27
 * @Version: 1.0
 **/
public class Calculator {
    public static void main(String[] args) {
        //创建一个计算表达式（只适合个位数的计算）
        String expression = "7+8-5*9-1+3";
        //创建两个栈，一个存放数值，一个存放符号
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' '; //将每次获得保存在ch
        while(true){
            //依次获得expression中的每个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是字符还是数值
            if(operStack.isOper(ch)){
                //判断该栈是否为空栈
                if(operStack.isEmpty()){
                    //空栈就直接添加
                    operStack.push(ch);
                }else {
                    //判断栈顶的符号和需要存放的符号的优先级
                    //如果该符号的优先级小于或者等于栈顶的优先级
                    //从数值栈取出两个值和取出栈顶的符号进行计算，计算结果存入数值栈，需要存放的符号存放符号栈
                    if(operStack.priority(operStack.peek()) >= operStack.priority(ch)){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1,num2,oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }
            }else {
                //数值就直接入栈,由于是ASCII所以转化为数字需要-48
                numStack.push(ch - 48);
            }
            index++;
            //判断是否遍历完expression
            if(index >= expression.length()){
                break;
            }
        }
        //计算全部
        while(true){
            //当符号栈中为空表示计算完成
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1,num2,oper);
            numStack.push(result);
        }
        result = numStack.pop();
        System.out.printf("表达式%s = %d",expression,result);
    }
}

//创建一个栈类
class ArrayStack2{
    private int maxSize;  //表示栈的容量
    private int[] stack; //数组模拟栈
    private int top = -1;  //表示栈顶，初始化为-1

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈是否满
    public Boolean isFull(){
        return top == maxSize -1;
    }

    //判断栈是否为空
    public Boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        //判断栈是否为空
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        //判断栈是否为空
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top;i >= 0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回栈顶的数据，但不是pop操作
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，返回的优先级用数字表示
    //返回的数字越大优先级越大
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            //假设目前运算符只有 + - * /
            return -1;
        }
    }

    //判断是否为一个运算符
    public Boolean isOper(char ch){
        return ch == '+' || ch == '-'|| ch == '*'|| ch == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int result = 0;
        switch (oper){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
                default:
                    break;
        }
        return result;
    }
}
