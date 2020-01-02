package dynamic;

import javax.sound.midi.Soundbank;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数
        //创建二维数组表示动态规划过程
        //表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];
        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//第一列设置为0
        }
        for (int j = 0; j < v[0].length; j++) {
            v[0][j] = 0;//第一行设置为0
        }
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[i].length; j++) {//不处理第一列
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //path[i][j] = 1;
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用主if-else来体现公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前记录记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放到背包中\n", i);
                j -= w[i - 1];
            }
            i--;
        }
        for (int o = 0; o < path.length; o++) {
            for (int p = 0; p < path[o].length; p++) {
                System.out.print(path[o][p]+"\t");
            }
            System.out.println();
        }
        //不对
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j]==1){
//                    System.out.printf("第%d个商品放到背包中\n",i);
//                }
//            }
//        }
    }
    //背包问题(动态规划算法)
}
