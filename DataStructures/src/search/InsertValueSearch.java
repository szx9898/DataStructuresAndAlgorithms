package search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index = " + index);
    }
    //对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
    //关键字分布不均匀的情况下，该方法不一定比折半查找要好
    //差值查找算法
    private static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("~~~");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (findVal - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }

    }
}
