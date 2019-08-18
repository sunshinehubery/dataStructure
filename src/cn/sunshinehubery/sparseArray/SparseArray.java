package cn.sunshinehubery.sparseArray;

/**
 * 二维数组<=>稀疏数组
 * */
public class SparseArray {
    public static void main(String[] args) {
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
    }
}
