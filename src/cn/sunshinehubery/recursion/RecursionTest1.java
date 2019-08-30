package cn.sunshinehubery.recursion;

/**
 * @description: 打印问题
 * @author: sunshinehubery
 * @date: 2019/8/30 23:25
 * @Version: 1.0
 **/
public class RecursionTest1 {
    public static void main(String[] args) {
        test(4);
    }
    //当if后面加上else才输出n这个值    打印 n=2
    public static void test(int n){
        if(n > 2){
            test(n -1);
        }
        System.out.println("n=" + n);
    }
}
