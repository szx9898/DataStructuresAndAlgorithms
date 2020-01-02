package tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree binaryTree = new ArrBinaryTree(arr);
        //binaryTree.preOrder();
        binaryTree.infixOrder();
        //binaryTree.postOrder();
    }
}

//顺序存储二叉树的遍历
class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        preOrder(0);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void postOrder() {
        postOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前节点
        System.out.println(arr[index]);
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //编写一个方法，完成顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前节点
        System.out.println(arr[index]);
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //编写一个方法，完成顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前节点
        System.out.println(arr[index]);
    }
}
