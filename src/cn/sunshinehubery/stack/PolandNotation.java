package cn.sunshinehubery.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 栈实现逆波兰表达式的计算（后缀表达式）
 * @author: sunshinehubery
 * @date: 2019/8/28 23:08
 * @Version: 1.0
 **/
public class PolandNotation {
    public static void main(String[] args) {
        String expression = "30 4 + 5 * 6 -";
        List<String> listString = getListString(expression);
        System.out.println(listString);
        int sum = calculate(listString);
        System.out.println(sum);
    }

    //首先将逆波兰表达式依次将数值和运算符号放入ArrayList中
    public static List<String> getListString(String expression){
        //首先先处理expression的字符串
        String[] split = expression.split(" ");
        //创建一个list集合
        ArrayList<String> list = new ArrayList<>();
        for(String item:split){
            list.add(item);
        }
        return list;
    }

    //完成逆波兰表达式计算
    //首先遍历list集合，判断是数值还是符号
    //若是数值直接压入栈
    //若是符号，首先将栈顶和次栈顶的值依次弹栈
    //将次栈顶的数值和栈顶的值加上运算符计算（次栈顶的在前）
    //将计算结果压入栈
    public static int calculate(List<String> list){
        //首先创建栈用于存放数值
        Stack<String> stringStack = new Stack<>();
        //遍历list集合
        for(String item:list){
            //判断，是否是数值（用正则表达式）,匹配多位数
            if(item.matches("\\d+")){
                stringStack.push(item);
            }else {
                //运算符号，pop出两个数值
                int num2 = Integer.parseInt(stringStack.pop());
                int num1 = Integer.parseInt(stringStack.pop());
                int result = 0;
                //判断运算符
                if(item.equals("+")){
                    result = num1 + num2;
                }else if (item.equals("-")){
                    result = num1 - num2;
                }else if (item.equals("*")){
                    result = num1 * num2;
                }else if (item.equals("/")){
                    result = num1 / num2;
                }
                stringStack.push("" + result);
            }
        }
        return Integer.parseInt(stringStack.pop());
    }
}

