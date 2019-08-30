package cn.sunshinehubery.recursion;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @description: 迷宫问题  //最小路径（可以改变不同的策略，求出数组中设置为2的个数，最少即为最短路径）
 * @author: sunshinehubery
 * @date: 2019/8/30 23:23
 * @Version: 1.0
 **/
public class MiGong {
    public static void main(String[] args) {
        //首先设置一个地图map，使用二维数组
        int[][] map = new int[8][7];
        //使用1代表墙体
        //将map中的上下设为1（墙体）
        for(int i = 0;i < 7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //将map中的左右边设置为1（墙体）
        for(int i = 0;i < 8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //打印显示
        for(int i =0;i < map.length;i++){
            for(int j =0;j < map[i].length;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("探路后：");
        setWay(map,1,1);
        for(int i =0;i < map.length;i++){
            for(int j =0;j < map[i].length;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯找路
    /**
      * @Description 1表示墙体，2表示该坐标可以通，3表示该点走不通，其中map[6][5] == 2表示到达终点
      * @Param [map, i, j]map表示地图，i j表示在map中起点位置
      * @return java.lang.Boolean
      **/
    public static Boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if(map[i][j] == 0){
                //假设该点可以通路
                map[i][j] = 2;
                //设置一个策略，下右上左
                if(setWay(map,i+1,j)){  //向下探索
                    return true;
                }else if(setWay(map,i,j+1)){ //右
                    return true;
                }else if(setWay(map,i-1,j)){ //上
                    return true;
                }else if(setWay(map,i,j-1)){ //左
                    return true;
                }else {
                    return false;
                }
            }else {   //该点已经置为1,2,3
                return false;
            }
        }
    }
}
