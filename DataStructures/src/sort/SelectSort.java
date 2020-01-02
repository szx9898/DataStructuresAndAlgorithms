package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        //选择排序的时间复杂度是O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"ms");
    }

    //选择排序
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min>arr[j]){
                    minIndex = j;//重置minIndex
                    min = arr[minIndex];//重置min
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"轮后~~");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
