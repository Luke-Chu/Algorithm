package dp;

import java.util.Arrays;

/**
 * 设有N堆石子排成一排，现要将石子有次序地合并成一堆，
 * 规定每次只能选相邻的2堆合并成新的一堆，并将新的一堆的石子数，记为该次合并的得分。
 * 试设计出一个算法,计算出将N堆石子合并成1堆的最小得分和最大得分。
 * 1.确定状态：dp[i][j]表示从第i堆到第j堆的得分；
 * 2.递推公式：dp[i][j]=dp[i][k]+dp[k+1][j]+sum[i][j] 选择一个k位置断开，问题转化为两堆石头合并
 * 3.初始化：dp[i][i]=0 一堆石子不需要代价（没有得分）
 * 4.遍历顺序：从前往后遍历
 */

public class MergeLinkStones {
    public static void main(String[] args) {
        int[] w = {1, 3, 5, 2};
        int n = w.length;

        int[] s = new int[n]; //前缀和
        int[][] f = new int[n][n]; //从n到n合并成1堆的最小值
        int[][] g = new int[n][n]; //从n到n合并成1堆的最大值

        //计算前缀和
        s[0] = w[0];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + w[i];
        }
        //数组初始化: i堆到i堆合并不需要代价
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
            Arrays.fill(g[i], Integer.MIN_VALUE);
            f[i][i] = 0;
            g[i][i] = 0;
        }
        //状态计算
        for (int len = 2; len <= n; len++) { //区间长度
            for (int l = 0; l + len - 1 < n; l++) { //状态：枚举区间起点
                int r = l + len - 1; //区间终点
                for (int k = l; k < r; k++) { //决策：枚举分割点
                    int temp = l > 0 ? s[l - 1] : 0;
                    f[l][r] = Math.min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - temp);
                    g[l][r] = Math.max(g[l][r], g[l][k] + g[k + 1][r] + s[r] - temp);
                }

            }

        }

        //目标输出: 从0到n-1合并代价（线性）
        int minScore = f[0][n - 1];
        int maxScore = g[0][n - 1];

        System.out.println("最小得分：" + minScore);
        System.out.println("最大得分：" + maxScore);
    }

}
