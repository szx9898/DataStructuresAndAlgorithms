package stack;

import java.util.*;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String infixExpression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);
        System.out.println(calculate(suffixExpressionList));

        /*//(3+4)*5-6
        //String suffixExpression = "3 4 + 5 * 6 -"; //29
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
        List<String> list = getListString(suffixExpression);
        //list.forEach(System.out::println);
        System.out.println(calculate(list));*/
    }

    //将中缀表达式转换为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> infixList) {
        //定义两个栈，一个符号栈，一个存储中间结果栈s2，但是s2全程未有出栈操作，所以用ArrayList代替
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        //遍历infixList
        for (String item : infixList) {
            //如果是一个数，则加入到list
            if (item.matches("\\d+")) {
                list.add(item);
            } else if (item.equals("(")) {
                stack.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                //将(弹出栈
                stack.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    //当前符号的优先级小于等于栈顶符号的优先级
                    list.add(stack.pop());
                }
                //还需要将item压入栈中
                stack.push(item);
            }
        }
        //将stack剩余的符号加入到list中
        while (stack.size() != 0) {
            list.add(stack.pop());
        }
        return list;
    }

    //将中缀表达式转为对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;//返回
    }

    //将一个逆波兰表达式，依次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return Arrays.asList(split);
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //取出两数进行运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                //计算后的结果入栈
                stack.push(res + "");
            }
        }
        //最后返回的时运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类可以返回一个运算符对应优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }
}