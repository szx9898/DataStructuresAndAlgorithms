package hashtable;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

//创建HashTable管理多个链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;//存放多个链表的数组
    private int size;//共表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化数组
        empLinkedListArray = new EmpLinkedList[size];
        //初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id，得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定要去哪个列表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d条链表中找到雇员id=%d\n", (empLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员~~");
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList {
    private Emp head;//头结点，默认为空

    //添加雇员到链表
    //说明
    //1. 假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则用一个辅助指针定位到最后的位置
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到了最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出时直接将emp加入到链表中
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表信息为");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明当前节点是最后的节点
                break;
            }
            curEmp = curEmp.next;//后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("该链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//退出
                break;//这使curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历整个链表未找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//移位
        }
        return curEmp;
    }
}
