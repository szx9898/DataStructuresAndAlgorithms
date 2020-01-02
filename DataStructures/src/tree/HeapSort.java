package tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //将来数组进行升序，要是用小顶堆
        int[] arr = {1, 4, 5, 6, 7, 8, 9, 2, 3};
//        int[] arr = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
//        }
//        long start = System.currentTimeMillis();
        heapSort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("耗时:"+(end-start)+"ms");
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        System.out.println("堆排序！！");
        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));
        //完成我们最终代码
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {//arr.length/2-1就是第一个非子叶节点
            adjustHeap(arr, i, arr.length);
        }
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("数组：" + Arrays.toString(arr));

    }

    /**
     * 功能：完成对应非叶节点的调整
     *
     * @param arr    待调整数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整，length在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//获取当前非叶节点的值
        //开始调整
        //k是i的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//后面这个用于跳出for
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点小于右子节点
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//子节点的值大于父节点
                arr[i] = arr[k];//把较大的值给父节点
                i = k;//!!! i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}

