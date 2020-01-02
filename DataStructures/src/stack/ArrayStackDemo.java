package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("=============");
            System.out.println("show:遍历栈");
            System.out.println("exit:退出");
            System.out.println("push:添加数据");
            System.out.println("pop:取出栈顶数据");
            System.out.println("=============");
            System.out.println("请输入你的选择:");
            key= scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈数据是："+stack.pop());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default :
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int[] stack;//数组模拟栈
    private int maxSize;//栈的大小
    private int top = -1;//栈顶，默认为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，无数据");
            return;
        }
        //需要从栈顶显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}