package com.java.prepare.turing.linear.my;

/**
 * 线性表的顺序储存结构的简单数组实现,为了演示方便,设计为不可扩展。
 */
public class MyFinalArrayList<E> {

    /**
     * 底层使用数组来存储数据
     */
    private final Object[] elements;

    /**
     * 当前存储的元素个数
     */
    private int size;


    /**
     * 总容量,数组长度
     */
    private final int capacity;

    /**
     * 构造器中初始化数组
     *
     * @param capacity
     */
    public MyFinalArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * 新增一个元素,默认加在线性表末位
     *
     * @param element 添加的元素
     * @return 添加成功返回true，添加失败返回false
     */
    public boolean add(E element) {
        //线性表是否容量已满
        if (size == capacity) {
            // 添加失败返回false
            return false;
        }
        //添加元素到size索引，并且size自增1
        elements[size++] = element;
        //返回true，添加成功
        return true;
    }


    /**
     * 删除一个元素,默认删除线性表的首位
     *
     * @return 返回被删除的元素或者返回null
     */
    public E delete() {
        //如果链表为空,则返回空
        if (size == 0) {
            return null;
        }
        //需要移动的元素的数量
        int length = size - 1;
        //将要被删除的元素
        Object e = elements[0];
        //移动后续的全部元素
        System.arraycopy(elements, 1, elements, 0, length);
        //原最后一个元素的位置置空
        elements[--size] = null;
        return (E) e;
    }

    /**
     * 删除首个与输入元素相同的元素
     */
    public boolean delete(Object element) {
        if (size < 0) {
            return false;

        }
        for (int index = 0; index < size; index++) {
            if (element == null) {
                if (elements[index] == null) {
                    int numMoved = size - index - 1;
                    System.arraycopy(elements, index + 1, elements, index, numMoved);
                    elements[--size] = null;
                    return true;
                }
            } else {
                if (element.equals(elements[index])) {
                    int numMoved = size - index - 1;
                    System.arraycopy(elements, index + 1, elements, index, numMoved);
                    elements[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据索引删除某个索引处的元素
     */
    public E delete(int index) {
        rangeCheck(index);
        E e = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return e;
    }

    /**
     * 更新某个索引的元素值
     */
    public void set(int index, Object newElement) {
        rangeCheck(index);
        elements[index] = newElement;
    }

    /**
     * 是否包含某个元素
     *
     * @param element 元素
     * @return false 不包含 true 包含
     */
    public boolean contains(Object element) {
        if (size > 0) {
            for (int index = 0; index < size; index++) {
                if (element == null) {
                    if (elements[index] == null) {
                        return true;
                    }
                } else {
                    if (element.equals(elements[index])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 获取指定索引的元素
     *
     * @param index 指定索引
     * @return 指定索引的元素
     */
    public E get(int index) {
        //检查索引越界
        rangeCheck(index);
        return (E) elements[index];
    }

    /**
     * 返回第一个元素
     *
     * @return 第一个元素, 或者null 没有元素
     */
    public E get() {
        if (size > 0) {
            return (E) elements[0];
        }
        return null;
    }

    /**
     * 返回指定元素出现的的首个位置索引
     *
     * @param element 制定元素
     * @return 返回索引, 或者-1,未找到该元素
     */
    public int indexOf(Object element) {
        if (size > 0) {
            for (int index = 0; index < size; index++) {
                if (element == null) {
                    if (elements[index] == null) {
                        return index;
                    }
                } else {
                    //默认通过equals比较元素是否相等
                    if (element.equals(elements[index])) {
                        return index;
                    }
                }
            }
        }
        //未找到元素,返回-1
        return -1;
    }


    /**
     * 返回线性表元素数量
     *
     * @return 线性表元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 重写了toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    /**
     * 检查索引
     */
    private void rangeCheck(int index) {
        if (index >= size) {
            //throw抛出异常
            throw new IndexOutOfBoundsException("索引越界:" + index);
        }
    }

    public static void main(String[] args) {
        System.out.println("初始化线性表,容量为6===============>");
        MyFinalArrayList<Object> objectMyFinalList = new MyFinalArrayList<>(6);
        System.out.println("插入元素===============>");
        objectMyFinalList.add(null);
        objectMyFinalList.add(2);
        objectMyFinalList.add("3");
        objectMyFinalList.add("3");
        objectMyFinalList.add(null);
        objectMyFinalList.add("3");
        //尝试添加第七个元素,不会添加成功
        objectMyFinalList.add("6");
        //输出内部的元素
        System.out.println(objectMyFinalList);
        System.out.println("获取第一个元素===============>");
        //获取第一个元素
        Object o = objectMyFinalList.get();
        System.out.println(o);
        System.out.println("获取size===============>");
        //获取size
        int size1 = objectMyFinalList.size();
        System.out.println(size1);
        System.out.println("删除第一个为null的元素===============>");
        boolean delete = objectMyFinalList.delete(null);
        System.out.println(delete);
        System.out.println("再次获取size===============>");
        int size2 = objectMyFinalList.size();
        System.out.println(size2);
        System.out.println("删除指定索引元素===============>");
        Object delete1 = objectMyFinalList.delete(1);
        //索引越界
        //objectMyFinalList.delete(7);
        System.out.println(delete1);
        System.out.println("再次获取size===============>");
        int size3 = objectMyFinalList.size();
        System.out.println(size3);
        System.out.println("设置指定索引的元素值===============>");
        objectMyFinalList.set(0, "设置值1");
        System.out.println("获取该索引的元素值验证===============>");
        Object o2 = objectMyFinalList.get();
        System.out.println(o2);
        System.out.println("获取指定值所在的第一个索引===============>");
        int index = objectMyFinalList.indexOf("3");
        System.out.println(index);
        System.out.println("输出全部元素===============>");
        System.out.println(objectMyFinalList);
        System.out.println("再次获取size===============>");
        int size4 = objectMyFinalList.size();
        System.out.println(size4);
        System.out.println("是否包含===============>");
        System.out.println(objectMyFinalList.contains(null));

    }
}
