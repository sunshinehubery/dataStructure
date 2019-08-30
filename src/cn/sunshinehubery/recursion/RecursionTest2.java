package cn.sunshinehubery.recursion;

/**
 * @description: 阶乘问题
 * @author: sunshinehubery
 * @date: 2019/8/30 23:28
 * @Version: 1.0
 **/
public class RecursionTest2 {
    public static void main(String[] args) {
        int sum = factorial(5);   //1*2*3*4*5
        System.out.println(sum);
    }
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
