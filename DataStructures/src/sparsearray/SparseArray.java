package sparsearray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SparseArray {
    public static void main(String[] args) throws Exception {
        //创建一个原始二维数组11*11
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("原始的二维数组如下：");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //求出原始数据中有效数据
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //其他行
        int count = 0;//记录出现的次数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArray[i][j];
                }
            }
        }
        System.out.println("稀疏数组为下：");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j]+"\t");
            }
            System.out.println();
        }
        FileOutputStream fos = new FileOutputStream("E:\\dataStructure\\sparseArr.txt");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                fos.write(sparseArr[i][j]);
            }
        }
        FileInputStream fis = new FileInputStream("E:\\dataStructure\\sparseArr.txt");

        int [][] initialArr = new int [sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
             initialArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("原来数组如下：");
        for (int i = 0; i < initialArr.length; i++) {
            for (int j = 0; j < initialArr[i].length; j++) {
                System.out.print(initialArr[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
