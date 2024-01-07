package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 一个长、宽、高分别为 m, n, p的长方体被分割成 m*n*p个小立方体。每个小立方体内有一个整数，
 * 设计一个算法，计算出所给长方体的最大子长方体。子长方体的大小由它所含的所有整数之和确定。
 */
public class MaxSubCube {
    public static int maxSum1D(int[] nums, int m) {
        int sums = 0;
        int[] temp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            if (nums[i] >= 0) {
                temp[i] = temp[i - 1] + nums[i];
            } else {    //将负数当成0看
                temp[i] = temp[i - 1];
            }
            if (temp[i] > sums) { //取一维中最大的
                sums = temp[i];
            }
        }
        return sums;
    }

    public static int maxSum2D(int[][] nums, int m, int n) {
        int sums = 0;
        for (int i = 1; i <= n; i++) {//二维宽
            int[] temp = new int[m + 1];
            int j = i;  //宽递增
            while (j <= n) {
                for (int k = 1; k <= m; k++) {
                    temp[k] += nums[k][j];
                }
                int tempSum = maxSum1D(temp, m);    //调用一维求最大和
                if (tempSum > sums) {
                    sums = tempSum;
                }
                j++;
            }
        }
        return sums;
    }

    public static int maxSum3D(int[][][] nums, int m, int n, int p) {
        int sums = 0;
        for (int i = 1; i <= p; i++) {  //i代表高：
            int[][] temp = new int[m + 1][n + 1];
            int j = i;  //j代表高，不断递增：
            while (j <= p) {    //j最大为p
                for (int k = 1; k <= m; k++) {  //开始遍历长宽：从1开始遍历
                    for (int l = 1; l <= n; l++) {  //宽小于等于n
                        temp[k][l] += nums[k][l][j];
                    }
                }
                int tempSum = maxSum2D(temp, m, n);
                if (tempSum > sums) {
                    sums = tempSum;
                }
                j++;
            }
        }
        return sums;
    }

    public static void main(String[] args) throws IOException {
        //文件代表着m*n行，每行p个整数，每一个m先输入n行
        BufferedReader br = new BufferedReader(new FileReader("Course\\src\\dp\\input.txt"));
        String[] data = br.readLine().trim().split("\\s+");
        int m = Integer.parseInt(data[0]);
        int n = Integer.parseInt(data[1]);
        int p = Integer.parseInt(data[2]);

        int[][][] dp = new int[m + 1][n + 1][p + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                data = br.readLine().trim().split("\\s+");
                for (int k = 1; k <= p; k++) {
                    dp[i][j][k] = Integer.parseInt(data[k - 1]);
                }
            }
        }
        System.out.println("The answer is " + maxSum3D(dp, m, n, p));
    }
}