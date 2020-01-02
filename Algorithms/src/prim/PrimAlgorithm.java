package prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);
    }
}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写Prim算法，生成最小生成树
    public void prim(MGraph graph, int v) {
        int[] isVisited = new int[graph.verxs];
        //java默认为0，所以可以不写
//        for (int i = 0; i < graph.verxs; i++) {
//            isVisited[i] = 0;
//        }
        //把当前节点标记为已访问
        isVisited[v] = 1;
        //用h1和h2记录两个顶点的下标
        int h1 = 1;
        int h2 = 1;
        int minWeight = 10000;//后来用较小数替换
        for (int k = 1; k < graph.verxs; k++) {//因为n个顶点生成n-1条边，所以从1开始
            //这个是确定每一次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j表示没有被访问过的节点
                    if (isVisited[i] == 1 && isVisited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight（寻找已经访问过的结点和未访问过的结点间的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值:"+minWeight);
            //将h1,h2标记为已访问
            isVisited[h2] = 1;
            //minWeight重新设置为10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，这就是邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
