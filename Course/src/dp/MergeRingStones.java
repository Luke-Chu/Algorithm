package dp;

import java.util.Arrays;

/**
 * 在一个圆形操场的四周摆放N堆石子，现要将石子有次序地合并成一堆，
 * 规定每次只能选相邻的2堆合并成新的一堆，并将新的一堆的石子数，记为该次合并的得分。
 * 试设计出一个算法,计算出将N堆石子合并成1堆的最小得分和最大得分。
 */

public class MergeRingStones {
    public static void main(String[] args) {
        int[] piles = {4, 5, 9, 4};
        int n = piles.length;

        int[] w = new int[2 * n];
        int[] s = new int[2 * n]; //前缀和
        int[][] f = new int[2 * n][2 * n]; //从n到n合并成1堆的最小值
        int[][] g = new int[2 * n][2 * n]; //从n到n合并成1堆的最大值

        //环路变成链路
        for (int i = 0; i < 2 * n; i++) {
            w[i] = piles[i % n];
        }
        //计算前缀和
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0) s[i] = w[i];
            else s[i] = s[i - 1] + w[i];
        }
        //数组初始化
        for (int i = 0; i < 2 * n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
            Arrays.fill(g[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < 2 * n; i++) {
            f[i][i] = 0;
            g[i][i] = 0;
        }
        //状态计算
        for (int len = 2; len <= n; len++) { //阶段：枚举区间长度
            for (int l = 1; l + len - 1 < 2 * n; l++) { //状态：枚举区间起点
                int r = l + len - 1; //区间终点
                for (int k = l; k < r; k++) { //决策：枚举分割点
                    f[l][r] = Math.min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - s[l - 1]);
                    g[l][r] = Math.max(g[l][r], g[l][k] + g[k + 1][r] + s[r] - s[l - 1]);
                }

            }

        }

        //目标输出
        int minScore = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            minScore = Math.min(minScore, f[i][i + n - 1]);
            maxScore = Math.max(maxScore, g[i][i + n - 1]);
        }

        System.out.println("最小得分：" + minScore);
        System.out.println("最大得分：" + maxScore);
    }
}
