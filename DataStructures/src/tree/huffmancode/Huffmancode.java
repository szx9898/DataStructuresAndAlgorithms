package tree.huffmancode;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Huffmancode {
    public static void main(String[] args) {
        //测试压缩文件
        //zipFile("D:\\BaiduNetdiskDownload\\jdk api 1.8_google.CHM", "D:\\BaiduNetdiskDownload\\7788.zip");
        //System.out.println("编码完成");
        //unZipFile("D:\\BaiduNetdiskDownload\\7788.zip","D:\\BaiduNetdiskDownload\\jdk2 api 1.8_google.CHM");
        //System.out.println("解码完成");
        /*String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        byte[] huffmanZip = huffmanZip(contentBytes);
        byte[] sourceByte = decode(huffmanCodes, huffmanZip);
        System.out.println( new String(sourceByte));*/
    }

    //编写一个方法完成对压缩文件的解压
    public static void unZipFile(String zipFile, String targetFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] sourceBytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(targetFile);
            os.write(sourceBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                ois.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //编写一个方法对一个文件进行压缩
    public static void zipFile(String sourceFile, String targetFile) {
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(sourceFile);
            //创建和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件,获得源文件的字节数组
            is.read(b);
            //文件经过赫夫曼编码得到压缩后的字节数组,在压缩中已经得到了赫夫曼编码表huffmanCoeds
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(targetFile);
            //创建一个与文件输出流相关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //写入赫夫曼编码表，为了读取时使用
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 编码前的二进制字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        //System.out.println(stringBuilder.toString());
        //把字符串按照对应的赫夫曼编码表解码
        //把赫夫曼编码表进行调换，因为要反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println(map);
        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个bit
                String key = stringBuilder.substring(i, i + count);//i不动count动，知道匹配到一个字符
                b = map.get(key);
                if (b == null) {//未匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count位置
        }
        byte[] binaryByte = new byte[list.size()];
        for (int i = 0; i < binaryByte.length; i++) {
            binaryByte[i] = list.get(i);
        }
        return binaryByte;
    }
    //完成数据的解压
    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"

    /**
     * 讲一个byte转成一个二进制字符串
     *
     * @param b    经赫夫曼编码压缩后的byte数组
     * @param flag true则补高位，false
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//将b转为int
        //如果是正数我们还存在补高位的问题
        if (flag) {
            temp |= 256;//按位或256 1 0000 0000 | 0000 0001 =》 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);//取后8位
        } else {
            return str;
        }
    }

    /**
     * 赫夫曼编码
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过赫夫曼编码后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //字节数组转list
        List<Node> nodes = getNodes(bytes);
        //通过创建对应赫夫曼编码树，并返回根节点
        Node root = createHuffmanTree(nodes);
        //将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes集合，获得哈夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(root);
        //原始字符串的字节数组通过赫夫曼表编码(查找对应赫夫曼树)，得到压缩后的字节数组
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }
    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]

    /**
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //将huffmanCodes转换为和赫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将“10101000....” 转成byte数组
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {//步长为8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            by[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return by;
    }

    //生成赫夫曼对应的赫夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", new StringBuilder());
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes集合
     *
     * @param node          传入接点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     * @return
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是否是叶子节点
            if (node.data == null) {
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //通过list创建对应哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    //字节数组转list
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        //将数据和权值存储到map中
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

//创建node，带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身，比如'a'=>97,' '=>32
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
