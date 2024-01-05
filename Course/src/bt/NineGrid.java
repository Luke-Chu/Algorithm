package bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 九宫格问题是指在3×3盘面上填写1-9个不重复的数字，并满足每一行、每一列相加等于15，1-9每个数字在每一行、每一列和每一宫中都只出现一次。
 * 要求输出九宫格问题的可行解
 * 时间复杂度: O(n!)
 */
public class NineGrid {
    private static final int[][] grid = new int[3][3];
    private static final boolean[] used = new boolean[10];
    private static final List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        backTracking(0);
        System.out.println("最终结果有多少种：" + res.size());
        for (var item: res){
            System.out.println(item);
        }
    }

    private static void backTracking(int n) {
        //递归终止条件
        if (n == 9) {
            //判断该九宫格是否满足条件
            if (isValid()) {
                res.add(Arrays.deepToString(grid));
            }
            return;
        }
        // 1-9个数字依次填充
        for (int i = 1; i < 10; i++) {
            //判断数字i是否被使用
            if (!used[i]) {
                used[i] = true;
                grid[n / 3][n % 3] = i; //将数字i填充到第n个格子
                backTracking(n + 1); //递归
                used[i] = false; //回溯
            }
        }
    }

    private static boolean isValid() {
        //判断行和列是否满足条件等于15
        for (int i = 0; i < 3; i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < 3; j++) {
                sumRow += grid[i][j];
                sumCol += grid[j][i];
            }
            if (sumRow != 15 || sumCol != 15) return false;
        }
        //判断对角线是否满足15
        int sumDiag1 = grid[0][0] + grid[1][1] + grid[2][2];
        int sumDiag2 = grid[0][2] + grid[1][1] + grid[2][0];
        //对角线都满足了，则该方案是合理的
        return sumDiag1 == 15 && sumDiag2 == 15;
    }
}
