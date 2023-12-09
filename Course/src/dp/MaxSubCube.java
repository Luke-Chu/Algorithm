package dp;

import java.util.Arrays;

/**
 * 一个长、宽、高分别为 m, n, p的长方体被分割成 m*n*p个小立方体。每个小立方体内有一个整数，
 * 设计一个算法，计算出所给长方体的最大子长方体。子长方体的大小由它所含的所有整数之和确定。
 */
public class MaxSubCube {

    public static int findMaxSubCube(int[][][] cube) {
        int m = cube.length;
        int n = cube[0].length;
        int p = cube[0][0].length;

        // 计算立方体的前缀和
        int[][][] prefixSum = calculatePrefixSum(cube, m, n, p);

        int maxSum = Integer.MIN_VALUE;

        // 遍历所有可能的子长方体
        for (int i1 = 0; i1 < m; i1++) {
            for (int i2 = i1; i2 < m; i2++) {
                for (int j1 = 0; j1 < n; j1++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        for (int k1 = 0; k1 < p; k1++) {
                            for (int k2 = k1; k2 < p; k2++) {
                                // 计算当前子长方体的和
                                int currentSum = calculateSubcubeSum(prefixSum, i1, i2, j1, j2, k1, k2);

                                // 更新最大和
                                maxSum = Math.max(maxSum, currentSum);
                            }
                        }
                    }
                }
            }
        }

        return maxSum;
    }

    // 计算立方体的前缀和
    private static int[][][] calculatePrefixSum(int[][][] cube, int m, int n, int p) {
        int[][][] prefixSum = new int[m][n][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    prefixSum[i][j][k] = cube[i][j][k];

                    if (i > 0) {
                        prefixSum[i][j][k] += prefixSum[i - 1][j][k];
                    }
                    if (j > 0) {
                        prefixSum[i][j][k] += prefixSum[i][j - 1][k];
                    }
                    if (k > 0) {
                        prefixSum[i][j][k] += prefixSum[i][j][k - 1];
                    }

                    if (i > 0 && j > 0) {
                        prefixSum[i][j][k] -= prefixSum[i - 1][j - 1][k];
                    }
                    if (i > 0 && k > 0) {
                        prefixSum[i][j][k] -= prefixSum[i - 1][j][k - 1];
                    }
                    if (j > 0 && k > 0) {
                        prefixSum[i][j][k] -= prefixSum[i][j - 1][k - 1];
                    }

                    if (i > 0 && j > 0 && k > 0) {
                        prefixSum[i][j][k] += prefixSum[i - 1][j - 1][k - 1];
                    }
                }
            }
        }

        return prefixSum;
    }

    // 计算子长方体的和
    private static int calculateSubcubeSum(int[][][] prefixSum, int i1, int i2, int j1, int j2, int k1, int k2) {
        int sum = prefixSum[i2][j2][k2];

        if (i1 > 0) {
            sum -= prefixSum[i1 - 1][j2][k2];
        }
        if (j1 > 0) {
            sum -= prefixSum[i2][j1 - 1][k2];
        }
        if (k1 > 0) {
            sum -= prefixSum[i2][j2][k1 - 1];
        }

        if (i1 > 0 && j1 > 0) {
            sum += prefixSum[i1 - 1][j1 - 1][k2];
        }
        if (i1 > 0 && k1 > 0) {
            sum += prefixSum[i1 - 1][j2][k1 - 1];
        }
        if (j1 > 0 && k1 > 0) {
            sum += prefixSum[i2][j1 - 1][k1 - 1];
        }

        if (i1 > 0 && j1 > 0 && k1 > 0) {
            sum -= prefixSum[i1 - 1][j1 - 1][k1 - 1];
        }

        return sum;
    }

    public static void main(String[] args) {
        // 示例用法
        int m = 3;
        int n = 3;
        int p = 3;

        int[][][] data = {{{0, -1, 2}, {1, 2, 2}, {1, 1, -2}},
                {{-2, -1, -1}, {-3, 3, -2}, {-2, -3, 1}},
                {{-2, 3, 3}, {0, 1, 3}, {2, 1, -3}}};
        int[][][] cube = new int[m][n][p];
        // 在cube中填充你的数据
        System.arraycopy(data, 0, cube, 0, p);
        System.out.println(Arrays.deepToString(cube));

        int result = findMaxSubCube(cube);
        System.out.println("最大子长方体的和为: " + result);
    }
}
