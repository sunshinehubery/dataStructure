package cn.sunshinehubery.sparseArray;

import java.io.*;

/**
 * 二维数组<=>稀疏数组
 * */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个二维数组
        int array[][] = new int[11][11];
        array[1][4] = 1;
        array[2][5] = 2;
        array[4][5] = 2;
        //遍历二维数组
        System.out.println("二维数组：");
        for(int i = 0;i < array.length;i++){
            for(int j = 0;j < array[i].length;j++){
                System.out.printf("%d\t",array[i][j]);
            }
            System.out.println();
        }
        //创建稀疏数组，获取有效数值的个数
        int sum = 0;
        for(int i = 0;i < array.length;i++){
            for(int j = 0;j < array[i].length;j++){
                if(array[i][j] != 0){
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        //添加信息，二维数组的行和列以及有限数值的个数
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;
        //添加二维数组的有效值的坐标以及数值
        int count = 0;
        for(int i =0;i < array.length;i++ ){
            for(int j = 0;j < array[i].length;j++){
                if(array[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        //遍历稀疏数组
        System.out.println("稀疏数组：");
        for(int i = 0;i < sparseArray.length;i++){
            for(int j = 0;j < sparseArray[i].length;j++){
                System.out.printf("%d\t",sparseArray[i][j]);
            }
            System.out.println();
        }
        //稀疏数组转二维数组
        int array2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        //第一行是记录行数和列数以及有效值数量，所以i从1开始
        for(int i = 1;i < sparseArray.length;i++){
            array2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //遍历array2
        System.out.println("稀疏数组转二维数组：");
        for(int i = 0;i < array2.length;i++){
            for(int j = 0;j < array2[i].length;j++){
                System.out.printf("%d\t",array2[i][j]);
            }
            System.out.println();
        }
        //将稀疏数组存到磁盘中
        //创建一个存放数组的文件
        File data = new File("D:\\sparseArray.data");
        //使用字符流将数据写入
        Writer os = null;
        os = new FileWriter(data);
        //拷贝数据
        for(int i =0;i < sparseArray.length;i++){
            for(int j = 0;j < sparseArray[i].length;j++){
                os.write(sparseArray[i][j] + "\t");
            }
            os.write("\r\n");
        }
        //释放资源
        os.close();
        //将磁盘中文件的数据存放到稀疏数组中
        File data2 = new File("D:\\sparseArray.data");
        //创建流
        BufferedReader in = new BufferedReader(new FileReader(data2));
        int row = 0;
        String line;
        while ((line = in.readLine()) != null){
            row++;
        }
        int sparseArray2[][] = new int[row][3];
        int rowtmp = 0;
        //读取完整个文件，重启流
        in.close();
        in = new BufferedReader(new FileReader(data2));
        while((line = in.readLine()) != null){
            String temp[] = line.split("\t");
            for(int j = 0;j < temp.length;j++){
                sparseArray2[rowtmp][j] = Integer.parseInt(temp[j]);
            }
            rowtmp++;
        }
        //释放资源
        in.close();
        //验证是否正确
        System.out.println("从文件中读取到的数据：");
        for (int[] sparse:sparseArray2) {
            for (int s:sparse) {
                System.out.printf("%d\t",s);
            }
            System.out.println();
        }
    }
}
