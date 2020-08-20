package com.noob.fifteen__Dynamic_Programming;

import java.util.Arrays;

public class Elements_of_dynamic_programming {
    public static void main(String[] args) {
        int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
        System.out.println(recursive_matrix_chain(p, new int[p.length][p.length], 1, 6));
        System.out.println(memoized_matrix_chain(p));
    }

    // 矩阵链算法，递归非动态规划实现
    public static int recursive_matrix_chain(int[] p, int[][] m, int i, int j) {
        if (i == j)
            return 0;
        m[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1 ; k++) {
            int q = recursive_matrix_chain(p, m, i, k) +
                    recursive_matrix_chain(p, m, k + 1, j) +
                    p[i - 1] * p[k] * p[j];
            if (q < m[i][j])
                m[i][j] = q;
        }

        return m[i][j];
    }


    // 矩阵链算法，递归备忘录实现
    public static int memoized_matrix_chain(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(m[i], Integer.MAX_VALUE);
        }

        return lookup_chain(m, p, 1, n);
    }

    public static int lookup_chain(int[][] m, int[] p, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE)
            return m[i][j];

        if (i == j)
            m[i][j] = 0;
        else {
            for (int k = i; k <= j - 1; k++) {
                int q = lookup_chain(m, p, i, k) +
                        lookup_chain(m, p, k + 1, j) +
                        p[i - 1] * p[k] * p[j];
                if (q < m[i][j])
                    m[i][j] = q;
            }
        }

        return m[i][j];
    }
}
