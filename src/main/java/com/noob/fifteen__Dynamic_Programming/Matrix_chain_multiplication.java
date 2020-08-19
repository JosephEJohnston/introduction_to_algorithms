package com.noob.fifteen__Dynamic_Programming;

import java.util.ArrayList;
import java.util.List;

public class Matrix_chain_multiplication {
    public static void main(String[] args) {
        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
        //System.out.println(martix_chain_order(p));

        List<int[][]> list = martix_chain_order(p);
        int[][] s = list.get(1);
        print_optimal_parens(s, 1, 6);

    }

    // 1. 矩阵链算法的动态规划实现
    public static List<int[][]> martix_chain_order(int[] p) {
        int n = p.length - 1;
        // m[i][j] 指代 A_i * A_i+1 ... A_j
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        // 矩阵链长度从 2 到 n
        for (int l = 2; l <= n ; l++) {
            // 为什么是等于？请看表格
            for (int i = 1; i <= n - l + 1; i++) {
                // j - i + 1 为子矩阵链长度
                int j = i + l - 1;
                // k 为切割点选取
                m[i][j] = Integer.MAX_VALUE;
                int q;
                for (int k = i; k <= j - 1; k++) {
                    q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        //m[1][n] 为答案
        List<int[][]> list = new ArrayList<>();
        list.add(m);
        list.add(s);

        return list;
    }

    // 括号化方案输出
    public static void print_optimal_parens(int[][] s, int i, int j) {
        if (i == j)
            System.out.print("A_" + i + " ");
        else {
            System.out.print("(");
            print_optimal_parens(s, i, s[i][j]);
            // 切割点是 s[i][j]，以此递归输出
            print_optimal_parens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
