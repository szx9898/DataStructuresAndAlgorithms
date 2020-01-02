package queue;

import java.util.Arrays;

public class ArrayQueue {
    private int maxsize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxsize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = -1;//指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;//指向队列尾部，包含最后一个数据
    }

    //判断队列是否满了
    public boolean isFull() {
        return rear == maxsize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列空间
        if (isFull()) {
            System.out.println("队列已满，不能加入");
            return;
        }
        rear++;//后移
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++;//front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据~~");
            return;
        }
        Arrays.toString(arr);
    }

    //显示队列的头数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据~~");
        }
        return arr[front + 1];
    }

}
