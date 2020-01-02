package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"ms");
    }

    //基数排序(空间换取时间的算法)
    private static void radixSort(int[] arr) {
        //得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶实际存放了多少个数据，定义一个一位数组来记录每个桶的数据个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每位进行排序
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位数的位置
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序依次取出数据
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {//这里的k小于10就行了
                //如果桶中有数据，则放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组)，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //每轮后都需将bucketElementCounts[k]置为0!!!!!!!!!
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第"+(i+1)+"轮,处理后,arr:"+ Arrays.toString(arr));
        }

    }
}
