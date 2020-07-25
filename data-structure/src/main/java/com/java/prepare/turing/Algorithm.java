package com.java.prepare.turing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Algorithm
 *
 * @author xuweizhi
 * @since 2020/07/24 22:02
 */
public class Algorithm {

    static Map<Integer, Long> cache = new HashMap<>(64);

    public static void main(String[] args) {
        //get1();
        //Scanner scanner = new Scanner(System.in);
        //getChar(scanner);
        //ss(scanner);

        for (int i = 1; i < 100; i++) {
            long f = f(i);
            cache.put(i, f);
            System.out.println(f);
        }
    }

    public static long f(int n) {
        if (cache.get(n) != null) {
            return cache.get(n);
        }
        if (n == 2 || n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    private static void ss(Scanner scanner) {
        int count = 0;
        double count1 = 0;
        double sum = 0;
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            if (i > 0) {
                sum += i;
                count1++;
            } else {
                count++;
            }
        }
        System.out.println(count);
        if (count == 0) {
            System.out.println(0);
        } else {

            System.out.println(sum / count1);
        }
    }

    private static void getChar(Scanner scanner) {
        String next = scanner.nextLine();
        char[] chars = next.toCharArray();
        int length = chars.length;
        for (int j = 0; j < length / 2; j++) {
            chars[j] ^= chars[length - 1 - j];
            chars[length - 1 - j] ^= chars[j];
            chars[j] ^= chars[length - 1 - j];
        }
        System.out.println(new String(chars));
    }

    private static void get1() {
        Scanner scanner = new Scanner(System.in);
        double v = scanner.nextDouble();
        System.out.println(String.format("%.1f", get(v, 0, v)));
    }

    private static double get(double input, double start, double end) {
        double temp;
        while (true) {
            temp = (start + end) / 2;
            double v = temp * temp * temp;
            if (v == input) {
                return temp;
            }
            if (v < input) {
                start = temp;
                if (input - v < 0.01) {
                    return temp;
                }
            } else {
                if (v - input < 0.01) {
                    return temp;
                }
                end = temp;
            }

        }
    }

    /**
     * 最小公倍数
     */
    private static void mix() {
        Scanner scanner = new Scanner(System.in);
        int i1 = scanner.nextInt();
        int i2 = scanner.nextInt();
        System.out.println((i1 * i2) / method(i1, i2));
    }

    private static int method(int i1, int i2) {
        if (i1 == i2) {
            return i1;
        } else if (i1 > i2) {
            return method(i2, i1 - i2);
        } else {
            return method(i1, i2 - i1);
        }
    }

}
