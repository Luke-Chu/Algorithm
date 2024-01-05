package bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击(不能在同行同列同一对角线)。
 * 给一个整数n，返回所有不同的n皇后问题的解决方案。
 * 每一种解法包含一个不同的n皇后问题的棋子放置方案，该方案中 'Q' 和 '-' 分别代表了皇后和空位。
 * 时间复杂度: O(n!)
 * 空间复杂度: O(n)
 */
public class NQueens {
    private static final List<List<String>> res = new ArrayList<>();

    public static void main(String[] args) {
        int n = 4;
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '-');
        }
        backTrack(n, 0, chessboard);
        for (var r : res) {
            System.out.println(r);
        }
    }

    private static void backTrack(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0; col < n; ++col) {
            if (isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row + 1, chessboard);  //递归
                chessboard[row][col] = '-'; //回溯
            }
        }

    }

    public static List<String> Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    private static boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i = 0; i < row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
