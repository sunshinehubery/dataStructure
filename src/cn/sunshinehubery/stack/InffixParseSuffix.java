package cn.sunshinehubery.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 中缀表达式转后缀表达式
 * @author: sunshinehubery
 * @date: 2019/8/29 0:36
 * @Version: 1.0
 **/
public class InffixParseSuffix {
    public static void main(String[] args) {
        String inffixExpression = "323+5-((4+2)*4)+6";
        List<String> stringList = inffixExpressionToList(inffixExpression);
        System.out.println(stringList);
        List<String> parseSuffixList = parseSuffixList(stringList);
        System.out.println(parseSuffixList);
        //计算后缀表达式的结果（但是不包含携带小数以及中缀表达式中含有空白字符（空格，制表符，换行符））
        int result = PolandNotation.calculate(parseSuffixList);
        System.out.println(result);
    }

    //将中缀表达式的list集合的数据转为后缀表达式
    public static List<String> parseSuffixList(List<String> stringList){
        //首先创建一个栈用于接收运算符
        Stack<String> stack = new Stack<>();
        //由于创建栈存放最后的结果需要的后缀表达式需要逆序打印出来，所以list集合接收
        List<String> list = new ArrayList<>();
        //遍历传过来的list集合
        for (String s:stringList){
            //如果是数值直接加入到list集合
            if(s.matches("\\d+")){
                list.add(s);
            }else if(s.equals("(")) {
                stack.push(s);
            }else if (s.equals(")")){
                //需要将栈中的运算符弹栈然后依次添加到list集合中，直到栈顶是“(”为止
                while(!stack.peek().equals("(")){
                    list.add(stack.pop());
                }
                stack.pop();  //消除字符串中的括号
            }else {
                //当是运算符时，若栈中为空直接入栈，若需要入栈的运算符与栈顶的运算符相比，优先级小于等于栈顶的
                //将栈顶的弹栈后加入到list集合，继续与栈顶运算符相比，重复操作
                if(stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(s)){
                    list.add(stack.pop());
                }
                stack.push(s);
            }
        }
        //将栈中剩下的所有的运算符依次添加到集合list中
        while (stack.size() != 0){
            list.add(stack.pop());
        }
        return list;
    }

    //首先先将中缀表达式放在list集合中
    public static List<String> inffixExpressionToList(String inffixExpression){
        //创建一个list集合用于存放数据
        List<String> list = new ArrayList<>();
        //创建一个指针，用于指向字符位置
        int i= 0;
        //创建一个字符串，用于存放多个数值时拼接
        String key = "";
        //遍历后存放在char中
        char ch = ' ';
        do{
            //判断字符是否是运算符号，是便直接存放到list集合,通过ASCII判断
            if((ch = inffixExpression.charAt(i)) < 48 || (ch = inffixExpression.charAt(i)) > 57){
                list.add(""+ch);
                i++;
            }else {
                //需要考虑多位数
                //首先将字符串置空
                key = "";
                while (i < inffixExpression.length() && (ch = inffixExpression.charAt(i)) >= 48 && (ch = inffixExpression.charAt(i)) <= 57){
                    key += ch;
                    i++;
                }
                list.add(key);
            }
        }while (i < inffixExpression.length());
        return list;
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回相应的数字
    public static int getValue(String s){
        int result = 0;
        switch (s){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
                default:
                    System.out.println("不存在该运算符！");
                    break;
        }
        return result;
    }

}


