package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};//左旋转
        //int[] arr = {10,12,8,9,7,6};//有旋转
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().left);
        System.out.println(avlTree.getRoot().right);
    }
}

//创建AVL树
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //2.找出父节点
            Node parent = searchParent(value);
            //2.1删除节点为叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }

            } else if (targetNode.left != null && targetNode.right != null) {
                //2.2删除节点有两个子节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
                //int maxValue = delLeftTreeMax(targetNode.left);
                //targetNode.value = maxValue;
            } else {
                //2.3删除节点只有一个子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果 targetNode是 parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            // targetNode是 parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 找出以node节点为根节点的树的最小值并删除返回
     *
     * @param node 根节点
     * @return 最小值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        //这时targetNode就指向了最小值
        delNode(target.value);
        return target.value;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 找出以node节点为根节点的树的最大值并删除返回
     *
     * @param node 根节点
     * @return 最大值
     */
    public int delLeftTreeMax(Node node) {
        Node target = node;
        while (target.right != null) {
            target = target.right;
        }
        //这时targetNode就指向了最大值
        delNode(target.value);
        return target.value;
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

}

class Node {
    int value;
    Node left;
    Node right;

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以当前节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    private void leftRotate() {
        //创建新节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值换为右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置为新节点
        this.left = newNode;
    }

    //右旋转
    private void rightRotate() {
        //创建新节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = this.left.right;
        //把当前节点的值换为左子节点的值
        this.value = this.left.value;
        //把当前节点的右子树设置成左子树的左子树
        this.left = this.left.left;
        //把当前节点的右子树设置为新节点
        this.right = newNode;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //添加节点的方法
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断大小
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完一个节点后，如果：（右子树高度-左子树高度）>1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //先对当前这个结点的右节点进行右旋转
                this.right.rightRotate();
                //再对当前结点进行右旋转的操作即可
                leftRotate();
            }else {
                leftRotate();
            }
            return;//必须要！！
        }
        //当添加完一个节点后，如果：（左子树高度-右子树高度）>1，左旋转
        if (this.left != null && leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (this.left.rightHeight() > this.left.leftHeight()) {
                //先对当前这个结点的左节点进行左旋转
                this.left.leftRotate();
                //再对当前结点进行右旋转的操作即可
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }
}