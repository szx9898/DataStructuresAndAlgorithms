package DivideAndConquer;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    //汉诺塔移动方案(分治算法)
    public static void hanoiTower(int num, char a, char b, char c) {
        //只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //num>=2的时候，总是看作两个盘，最下面一个盘，最上面所有盘
            //1.先把最上面所有盘A->B
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B的盘->C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
