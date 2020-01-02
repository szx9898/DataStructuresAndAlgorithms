package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1,2,5,6,4};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
//        }
//        long start = System.currentTimeMillis();
//        quickSort(arr, 0, arr.length - 1);
//        long end = System.currentTimeMillis();
//        System.out.println("耗时:" + (end - start) + "ms");
    }

    //快速排序
    private static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int temp = 0;//临时变量，用于交换
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            //在pivot的左边找，一直找到一个大于pivot的值退出
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot的右边找，一直找到一个小于pivot的值退出
            while (arr[r] > pivot) {
                r--;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l] == pivot值相等 ，r--前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个arr[r] == pivot值相等 ，l++后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果l == r，l++,r--,否则栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
