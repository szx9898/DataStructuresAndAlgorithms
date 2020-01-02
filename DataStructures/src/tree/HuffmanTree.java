package tree;

import java.util.*;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        root.preOrder();
    }
    //数组构建哈夫曼树 WPL最小(所有叶子节点的带权路径之和)
    public static Node createHuffmanTree(int[] arr){
        //数组元素排序
        List<Node> nodes = new ArrayList<>();
        for (int num : arr) {
            nodes.add(new Node(num));
        }
        while (nodes.size()>1){
            Collections.sort(nodes);
            //System.out.println(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从arrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的根节点
        return nodes.get(0);
    }
}
//创建节点
class Node implements Comparable<Node> {
    int value;//节点的权值
    char c;//字符
    Node left;//左子节点
    Node right;//右子节点
    //写一个前序遍历
    public void preOrder(){
        System.out.println(this.value);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大排列，升序
    }
}