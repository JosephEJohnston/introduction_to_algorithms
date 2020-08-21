package com.noob.fifteen__Dynamic_Programming;

import java.util.ArrayList;
import java.util.List;

public class Longest_common_subsequence {
    public static void main(String[] args) {
        char[] x = new char[]{'-', 'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] y = new char[]{'-', 'B', 'D', 'C', 'A', 'B', 'A'};
        List<Object> list = lcs_length(x, y);
        int[][] c = (int[][]) list.get(0);
        char[][] b = (char[][]) list.get(1);

        for (char[] chars : b) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }

        for (int[] ints : c) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println();
        print_lcs(b, x, x.length - 1, y.length - 1);
    }

    // 最长公共子序列算法，动态规划
    public static List<Object> lcs_length(char[] x, char[] y) {
        int m = x.length;
        int n = y.length;
        char[][] b = new char[m + 1][n + 1];
        int[][] c = new int[m][n];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = '↖';
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = '↑';
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = '←';
                }
            }
        }

        List<Object> list = new ArrayList<>();
        list.add(c);
        list.add(b);

        return list;
    }

    // 构造 LCS 算法
    public static void print_lcs(char[][] b, char[] x, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (b[i][j] == '↖') {
            print_lcs(b, x, i - 1, j - 1);
            System.out.print(x[i] + " ");
        } else if (b[i][j] == '↑')
            print_lcs(b, x, i - 1, j);
        else
            print_lcs(b, x, i, j - 1);
    }
}
