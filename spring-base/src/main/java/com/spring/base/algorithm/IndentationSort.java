package com.spring.base.algorithm;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 缩进排序
 *
 * @author xuweizhi
 * @since 2019/09/30 17:13
 */
@Slf4j
public class IndentationSort {

    /**
     * 数据源
     */
    private static String[] original = new String[]{
            "Nonmetals",
            "    Hydrogen",
            "    Carbon",
            "    Nitrogen",
            "    Oxygen",
            "Inner Transitionals",
            "    Lanthanides",
            "        Europium",
            "        Cerium",
            "    Actinides",
            "        Uranium",
            "        Plutonium",
            "        Curium",
            "Alkali Metals",
            "    Lithium",
            "    Sodium",
            "    Potassium",
    };

    private List<Node> list = new ArrayList<>();

    @Data
    static class Node {
        private String key;
        private String value;
        private List<Node> next;

        public Node(String key, String value, List<Node> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(original);
        String flag = getString(list);
        List<Node> result = addNodes(list, flag);
        List<String> values = new ArrayList<>();
        sortNode(result, values);
        System.out.println("|     Original      |       Sorted      |");
        System.out.println("|-------------------|-------------------|");
        for (int i = 0; i < values.size(); i++) {
            String value = String.format("|%-19s|%-19s|\n", list.get(i), values.get(i));
            System.out.printf(value);
        }
    }

    @NotNull
    private static List<Node> addNodes(List<String> list, String flag) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int level = (list.get(i).lastIndexOf(flag) + 4) / 4;
            addNode(level, list.get(i).trim().toLowerCase(), list.get(i), result);
        }
        return result;
    }

    @NotNull
    private static String getString(List<String> list) {
        String temp;
        int index = 0;
        char placeholder = 'a';
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            boolean flag = StringUtils.isNotEmpty(temp) && (temp.contains(" ") || temp.contains("\t"));
            if (flag) {
                placeholder = temp.charAt(0);
                for (int j = 0; j < temp.toCharArray().length; j++) {
                    if (temp.toCharArray()[j] != placeholder) {
                        index = j;
                        break;
                    }
                }
                break;
            }
        }

        String flag = null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < index; i++) {
            sb.append(placeholder);
        }
        flag = sb.toString();
        return flag;
    }

    public static void addNode(int level, String key, String value, List<Node> list) {
        if (level == 0) {
            list.add(new Node(key, value, new ArrayList<>()));
        } else {
            addNode(level - 1, key, value, list.get(list.size() - 1).getNext());
        }
    }

    public static List<Node> sortNode(List<Node> list, List<String> value) {
        list.stream().sorted(Comparator.comparing(Node::getValue)).map(node -> {
            value.add(node.value);
            return node;
        }).forEach(node -> {
            if (node.next.size() > 0) {
                sortNode(node.next, value);
            }
        });
        return list;
    }

}


