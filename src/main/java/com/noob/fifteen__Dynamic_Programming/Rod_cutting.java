package com.noob.fifteen__Dynamic_Programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rod_cutting {
    public static void main(String[] args) {
        // 位置 0 不用，n 为切割的长度
        // 位置 i 对应 i 长度钢条的价值
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        /*System.out.println(curRod(p, 1));
        System.out.println(curRod(p, 2));
        System.out.println(curRod(p, 3));
        System.out.println(curRod(p, 4));*/

        /*System.out.println(curRod(p, 5));
        System.out.println(memoized_cut_rod(p, 5));
        System.out.println(bottom_up_cut_rod(p, 5));*/
        System.out.println(cut_rod_with_cost(p, 10, 2));
        print_cut_rod_solution(p, 29);
    }

    /**
     * 1. 递归算法，无动态规划
     * @param p
     * @param n
     * @return
     */
    public static int curRod(int[] p, int n) {
        if (n <= 0)
            return 0;
        int q = Integer.MIN_VALUE;
        // 由于只对右边剩余部分进行切割，所以 i 不需要等于 0（i = n 时涵盖情况）
        // 同时，i = 0 会导致递归无法结束
        for (int i = 1; i <= n && i < p.length; i++) {
            q = Integer.max(q, p[i] + curRod(p, n - i));
        }

        return q;
    }

    /**
     * 2. 递归算法，自顶向下动态规划
     * @param p
     * @param n
     * @return
     */
    public static int memoized_cut_rod(int[] p, int n) {
        int[] r = new int[n + 1];
        Arrays.fill(r, Integer.MIN_VALUE);
        return memoized_cut_rod_aux(p, n, r);
    }

    public static int memoized_cut_rod_aux(int[] p, int n, int[] r) {
        if (r[n] >= 0)
            return r[n];
        int q;
        if (n == 0)
            q = 0;
        else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i < p.length && i <= n; i++) {
                q = Integer.max(q, p[i] + memoized_cut_rod_aux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }

    /**
     * 3. 非递归版本，自底向上动态规划
     * @param p
     * @param n
     * @return
     */
    public static int bottom_up_cut_rod(int[] p, int n) {
        int[] r = new int[n + 1];
        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i && j < p.length; j++) {
                q = Integer.max(q, p[j] + r[i - j]);
            }
            r[i] = q;
        }
        return r[n];
    }

    /**
     * 4. 展示算法
     * @param p
     * @param n
     * @return
     */
    public static List<int[]> extended_bottom_up_cut_rod(int[] p, int n) {
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];

        r[0] = 0;
        for (int i = 1; i <= n ; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i && j < p.length ; j++) {
                if (q < p[j] + r[i - j]) {
                    q = p[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        List<int[]> ret = new ArrayList<>();
        ret.add(r);
        ret.add(s);

        return ret;
    }

    // 展示算法的输出函数
    public static void print_cut_rod_solution(int[] p, int n) {
        List<int[]> tmp = extended_bottom_up_cut_rod(p, n);
        int[] r = tmp.get(0);
        int[] s = tmp.get(1);

        while (n > 0) {
            System.out.print(s[n] + " ");
            n = n - s[n];
        }
        System.out.println();
    }

    // 练习 15.1-3，每次切割钢条需要固定成本 c
    public static int cut_rod_with_cost(int[] p, int n, int c) {
        int[] r = new int[n + 1];
        r[0] = 0;

        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i && j < p.length; j++) {
                q = Integer.max(q, p[j] + r[i - j] - c);
            }
            r[i] = q;
        }
        // n < p.length 时存在完全不切割的情况
        // r[n] + c 是为了补偿多割的一刀，无论割不割都有
        if (n < p.length)
            return Integer.max(r[n] + c, p[n]);
        return r[n] + c;
    }

    // 练习 15.1-4， 返回切割方案
    public static int print_memoized_cut_rod(int[] p, int n) {
        int[] r = new int[n + 1];
        Arrays.fill(r, Integer.MIN_VALUE);
        return memoized_cut_rod_aux(p, n, r);
    }

    public static int print_memoized_cut_rod_aux(int[] p, int n, int[] r) {
        if (r[n] >= 0)
            return r[n];
        int q;
        if (n == 0)
            q = 0;
        else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i < p.length && i <= n; i++) {
                q = Integer.max(q, p[i] + memoized_cut_rod_aux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }
}
