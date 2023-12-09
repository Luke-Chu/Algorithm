package dp;

/**
 * 关于整数的 2元圈乘运算⊙定义为 (X⊙Y)= 10进制整数X的各位数字之和 * 10进制整数Y的最大数字 + Y的最小数字。
 * 例如: (9⊙30)=9*3+0=27。对于给定的10进制整数 X和 K，由 X和⊙运算可以组成各种不同的表达式。
 * 试设计一个算法，计算出由 X和⊙运算组成的值为 K的表达式最少需用多少个⊙运算。
 */
public class CircleMultiplication {

    private static int minOperations(int X, int K) {
        int[][] dp = new int[10][K + 1];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i <= 9; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= K; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (j - k >= 0 && dp[i - 1][j - k] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + 1);
                    }
                }
            }
        }

        return dp[9][K] == Integer.MAX_VALUE ? -1 : dp[9][K];
    }

    public static void main(String[] args) {
        // 示例用法
        int X = 3;
        int K = 12;
        System.out.println("X = " + X + ", K = " + K);

        int result = minOperations(X, K);
        System.out.println("最少需要 " + result + " 次 ⊙ 运算");
    }
}
