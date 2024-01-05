package bt;

import java.util.HashSet;

/**
 * 现有n种不同形状的宝石，每种n颗，共n*n颗。同一形状的n颗宝石分别具有n种不同的颜色c1, c2, …, cn中的一种颜色。
 * 欲将这n*n颗宝石排列成n行n列的一个方阵，使方阵中每一行和每一列的宝石都有n种不同的形状和n种不同颜色。
 * 试设计一个算法，计算出对于给定的n，有多少种不同的宝石排列方案。
 * 结果貌似正确
 */
public class GemArrangement {
    private int count;

    public int countGemArrangements(int n) {
        count = 0;
        int[] arrangement = new int[n * n];
        backtrack(arrangement, n, 0);
        return count;
    }

    private void backtrack(int[] arrangement, int n, int index) {
        if (index == arrangement.length) {
            // 检查排列是否有效
            if (isValid(arrangement, n)) {
                count++;
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
            arrangement[index] = i;
            backtrack(arrangement, n, index + 1);
        }
    }

    private boolean isValid(int[] arrangement, int n) {
        for (int i = 0; i < n; i++) {
            // 检查每一行
            HashSet<Integer> rowSet = new HashSet<>();
            // 检查每一列
            HashSet<Integer> colSet = new HashSet<>();

            for (int j = 0; j < n; j++) {
                rowSet.add(arrangement[i * n + j]);
                colSet.add(arrangement[j * n + i]);
            }
            if (rowSet.size() != n || colSet.size() != n) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GemArrangement gemArrangement = new GemArrangement();
        int n = 3;
        int result = gemArrangement.countGemArrangements(n);
        System.out.println("Number of different gem arrangements for n=" + n + ": " + result);
    }
}
