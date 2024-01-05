package bt;

/**
 * 罗密欧与朱丽叶身处一个m×n的迷宫中。每一个方格表示迷宫中的一个房间。这m×n个房间中有一些房间是封闭的，不允许任何人进入。
 * 在迷宫中任何位置均可沿 8个方向进入未封闭的房间。罗密欧位于迷宫的(p，q)方格中，他必须找出一条通向朱丽叶所在的(r，s)方格的路。
 * 在抵达朱丽叶之前，他必须走遍所有未封闭的房间各一次，而且要使到达朱丽叶的转弯次数为最少。
 * 每改变一次前进方向算作转弯一次。请设计一个Java算法帮助罗密欧找出这样一条道路。
 * 假设迷宫中有p=m*n-k个未封闭的房间，每个房间都有8个方向可以选择，那么最坏情况下的时间复杂度为O(8^p)
 */
public class RoAndJu {
    //定义迷宫大小，定义k个禁止访问房间
    private static final int m = 3, n = 4, k = 2;
    //定义罗密欧和朱丽叶的位置
    private static final int[] ro = {0, 1}, ju = {2, 3};
    //定义迷宫和禁止访问的数组(maze中false代表未访问，ban中true代表禁止访问)
    private static final boolean[][] maze = new boolean[m][n];
    private static final boolean[][] ban = new boolean[m][n];
    //定义最小转弯次数
    private static int minTurns = Integer.MAX_VALUE;
    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // 上、左、下、右、右上、右下、左上、左下

    private static void backTrack(int depth, int[] curPos, int prevDir, int turns) {
        //循环终止条件：遍历完所有房间，最终房间为朱丽叶位置。
        if (depth == m * n - k && curPos[0] == ju[0] && curPos[1] == ju[1]) {
            minTurns = Math.min(minTurns, turns);
            return;
        }
        //8个方向逐一访问
        for (int i = 0; i < 8; i++) {
            //定义下一个位置
            int[] nextPos = {curPos[0] + directions[i][0], curPos[1] + directions[i][1]};
            if (isValidMove(nextPos)) {
                maze[nextPos[0]][nextPos[1]] = true;    //访问置为true
                backTrack(depth + 1, nextPos, i, prevDir == i ? turns : turns + 1); //递归调用
                maze[nextPos[0]][nextPos[1]] = false;   //回溯
            }
        }
    }
    private static boolean isValidMove(int[] pos) {
        int x = pos[0], y = pos[1];
        return x >= 0 && x < m && y >= 0 && y < n && !maze[x][y] && !ban[x][y];
    }

    public static void main(String[] args) {
        //定义有k个房间禁止访问
        ban[0][0] = true;
        ban[1][1] = true;
        //起始位置已访问所以深度为1；起始前一个方向没有为-1
        backTrack(1, ro, -1, 0);
        System.out.println("最小转弯次数: " + minTurns);
    }
}
