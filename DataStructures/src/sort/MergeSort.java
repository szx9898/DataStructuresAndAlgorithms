package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)的数
        }
        long start = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"ms");
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left<right){
            int mid = (left+right)/2;//中间索引
            //向左进行递归分解
            mergeSort(arr,left,mid,temp);
            //向右进行递归分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }


    }

    //合并
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引
        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列得当前元素，小于等于右边有序序列的当前元素
            //将当前元素拷贝到temp数组
            //然后i，t都后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        //把有剩余数据的一边的数据依次全部填充到temp
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //System.out.println("tempLeft="+tempLeft+" right="+right);
        while (tempLeft <= right) {//第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
