package recursion;

public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }

    //编写一个方法，放置第n个皇后
    private void check(int n) {
        if (n == max) {//此时n=8，说明前8个皇后已经放置完毕
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
        for (int i = 0; i < max; i++) {
            //先把当前的这个皇后n，放到该行的第一列
            array[n] = i;
            if (judge(n)) {//不冲突
                //接着放n+1个皇后，然后开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }

    //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {//n为第n个皇后,n从0开始
        for (int i = 0; i < n; i++) {
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            //第二个就是看是否行-行==列-列，相等则说明在对角线
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
