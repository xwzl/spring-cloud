package com.java.prepare.until.structure.tree.huff;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根

        String content = "ABBCCCDDDDEEEEE";
        byte[] contentBytes = content.getBytes();

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果为：" + Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));
        /**
         List<nodes> list=getNode(contentBytes);
         System.out.println(list);
         nodes Nodes=createHuffman(list);
         preOrder(Nodes);
         Map<Byte,String> huffmanCode=getCodes(Nodes);
         System.out.println("生成的赫夫曼树："+huffmanCode);

         byte[] huffmanCodeBytes=zip(contentBytes,huffmanCode);
         System.out.println("huffmanByte"+Arrays.toString(huffmanCodeBytes));
         **/
    }

    //编写一个方法 完成对压缩文件的解压
    public static void unZipFile(String zipFile, String detFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            //创建文件的输入流
            is = new FileInputStream(zipFile);
            //创建和is相关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取文件信息
            //读取byte数组
            byte[] bytes = (byte[]) ois.readObject();
            //读取赫夫曼编码
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] decode = decode(map, bytes);
            //将bytes写入到目标文件
            os = new FileOutputStream(detFile);
            //写数据到detFile文件
            os.write(decode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Objects.requireNonNull(is).close();
                Objects.requireNonNull(os).close();
                Objects.requireNonNull(ois).close();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    //编写一个方法将文件进行压缩

    /**
     * @param srcFile 传入希望压缩的文件
     * @param desFile 压缩文件要放入的文件夹
     */
    public static void zipFile(String srcFile, String desFile) {

        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建和源文件一样的byte数组
            byte[] bytes = new byte[is.available()];
            //将文件的字节读取到bytes文件中
            is.read(bytes);
            //对bytes数组进行压缩
            byte[] HuffmanBytes = huffmanZip(bytes);
            //创建文件输出流，存放压缩文件
            os = new FileOutputStream(desFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //将压缩后的赫夫曼编码写到流中
            oos.write(HuffmanBytes);
            //将对应的赫夫曼编码也写入到流中，是为了以后我们恢复文件的时候使用
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(is).close();
                Objects.requireNonNull(os).close();
                Objects.requireNonNull(oos).close();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    //使用一个方法，将这些方法封装起来
    public static byte[] huffmanZip(byte[] bytes) {
        List<nodes> node = getNode(bytes);
        //根据node创建赫夫曼树
        nodes huffmanTreeRoot = createHuffman(node);
        //对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩后的赫夫曼编码的字节数组
        return zip(bytes, huffmanCodes);
    }

    //编写一个方法，完成对压缩数据的解码
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBiteString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解为就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增的取出key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    //说明没有匹配到
                    count++;
                } else {
                    //匹配到了
                    flag = false;
                }
            }
            list.add(b);
            i += count;  //i移动到count
        }

        //当for循环结束后，list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个Byte转换成二进制的字符串
     *
     * @param flag 标志是否需要补高位，如果是true，表示需要补，如果是false表示不补,如果是最后一个字节，不需要补
     * @param b    传入的byte
     * @return 是b对应的二进制的字符串
     */
    public static String byteToBiteString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;  //将b转成Int
        //如果是正数，我们还存在补高位
        if (flag) {
            temp |= 256; //按位与256  1 0000 0000 |0000 0001 ->1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将得到的stringBuilder转成byte[]数组
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为每8位对应一个byte，所以步长为8
            String strByte;
            if (i + 8 < stringBuilder.length()) {
                strByte = stringBuilder.substring(i, i + 8);
            } else {
                strByte = stringBuilder.substring(i);
            }

            //将strByte转成一个Byte，放入到huffmanByte
            huffmanByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }
        return huffmanByte;
    }

    //重载getCode方法
    public static Map<Byte, String> getCodes(nodes root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成赫夫曼编码
    //Map用于存放各个字符对应的哈夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * @param node
     * @param code 向左（0）还是向右（1）
     * @param sb
     */
    public static void getCodes(nodes node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        //将传入的code加入到sb2中
        sb2.append(code);
        //如果node.adta==null说明该节点为非叶子节点
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", sb2);
                getCodes(node.right, "1", sb2);
            } else {
                //该节点为叶子结点
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }

    public static List<nodes> getNode(byte[] bytes) {
        //创建一个ArrayList
        List<nodes> nodes = new ArrayList<>();

        //创建一个map用于存放各个字符及其出现的次数
        Map<Byte, Integer> map = new HashMap<>();
        //对bytes进行遍历，统计各个字符出现的次数
        for (byte b : bytes) {
            //Integer count = map.get(b);
            //if (count == null) {
            //    map.put(b, 1);
            //} else {
            //    map.put(b, count + 1);
            //}
            map.merge(b, 1, Integer::sum);
        }

        //对map进行遍历，将字符和次数构建成一个节点
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new nodes(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //创建哈夫曼树
    public static nodes createHuffman(List<nodes> Nodes) {

        while (Nodes.size() > 1) {
            Collections.sort(Nodes);
            //取出最小的二叉树
            nodes LeftNode = Nodes.get(0);
            //取出次小的二叉树
            nodes RightNode = Nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data，只有权值
            nodes parents = new nodes(null, LeftNode.weight + RightNode.weight);
            parents.left = LeftNode;
            parents.right = RightNode;
            //移除最小和次小的二叉树
            Nodes.remove(LeftNode);
            Nodes.remove(RightNode);
            //添加新创建的二叉树
            Nodes.add(parents);
        }

        return Nodes.get(0);
    }

    public static void preOrder(nodes root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空！");
        }
    }

}

//创建节点
class nodes implements Comparable<nodes> {
    Byte data; //存放数据（字符）本身
    int weight; //权值，表示字符出现的次数
    nodes left;
    nodes right;

    public nodes(Byte data, int weight) {

        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", weight=" + weight + "]";
    }

    //升序排序
    @Override
    public int compareTo(nodes o) {
        // TODO 自动生成的方法存根
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }


}

