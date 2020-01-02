package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertexList;//存储顶点结合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义一个boolean[],记录某个节点是否访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 8;
        //String[] vertexValue = {"A", "B", "C", "D", "E"};
        String[] vertexValue = {"1","2","3","4","5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //添加顶点
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        //添加边
//        graph.insertEdge(0, 1, 1);//A-B
//        graph.insertEdge(0, 2, 1);//A-B
//        graph.insertEdge(1, 2, 1);//B-C
//        graph.insertEdge(1, 3, 1);//B-D
//        graph.insertEdge(1, 4, 1);//B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        //遍历临接矩阵
        graph.showGraph();
        //深度遍历
        System.out.println("深度遍历:");
        graph.dfs();
        System.out.println();
        //广度优先
        System.out.println("广度优先:");
        graph.bfs();

    }


    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u;//队列的头结点对应的下标
        int w;//邻接节点w
        LinkedList<Integer> queue = new LinkedList<>();
        //打印
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列头节点下标
            u = queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }

    //深度优先遍历算法
    //v第一次为0
    public void dfs(boolean[] isVisited, int v) {
        //访问第一个节点
        System.out.print(getValueByIndex(v) + "->");
        //将节点设置为已访问
        isVisited[v] = true;
        //查找节点v的第一个节点w
        int w = getFirstNeighbor(v);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(v, w);
        }
    }

    //遍历所有的节点，都进行广度优先搜索
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
    //对dfs进行重载，遍历所有节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点，进行dfs
        for (int i = 0; i < getNumofVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    //图中常用的方法
    //返回节点的个数
    public int getNumofVertex() {
        return vertexList.size();
    }

    //返回边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }


}
