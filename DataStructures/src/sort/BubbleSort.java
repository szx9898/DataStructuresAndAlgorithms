package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, 20};
        //测试一下冒泡排序的速度O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是:"+date1Str);
        bubbleSort(arr);
        Date date2 = new Date();
        String date2Str = format.format(date2);
        System.out.println("排序后的时间是:"+date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println(Arrays.toString(arr));
            if (!flag) {//一次都没有交换过
                break;
            }else {
                flag = false;//重置，进行下次判断
            }
        }
    }
}
