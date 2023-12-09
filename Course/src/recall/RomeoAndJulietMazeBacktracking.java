package recall;

/**
 * 罗密欧与朱丽叶身处一个m×n的迷宫中。每一个方格表示迷宫中的一个房间。这m×n个房间中有一些房间是封闭的，不允许任何人进入。
 * 在迷宫中任何位置均可沿 8个方向进入未封闭的房间。罗密欧位于迷宫的(p，q)方格中，他必须找出一条通向朱丽叶所在的(r，s)方格的路。
 * 在抵达朱丽叶之前，他必须走遍所有未封闭的房间各一次，而且要使到达朱丽叶的转弯次数为最少。
 * 每改变一次前进方向算作转弯一次。请设计一个Java算法帮助罗密欧找出这样一条道路。
 * 未解决
 */
public class RomeoAndJulietMazeBacktracking {
    private int minTurns;
    private final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // 上、左、下、右、右上、右下、左上、左下

    public int findMinTurns(int[][] maze, int p, int q, int r, int s) {
        minTurns = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        backtrack(maze, visited, p, q, r, s, 0, -1);
        return minTurns == Integer.MAX_VALUE ? -1 : minTurns;
    }

    private void backtrack(int[][] maze, boolean[][] visited, int x, int y, int targetX, int targetY, int turns, int prevDirection) {
        if (x == targetX && y == targetY) {
            minTurns = Math.min(minTurns, turns);
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int newX = x + directions[i][0];
            int newY = y + directions[i][1];

            if (isValidMove(maze, visited, newX, newY)) {
                int newTurns = turns;
                if (prevDirection != -1 && i != prevDirection) {
                    newTurns++; // 记录转弯次数
                }

                backtrack(maze, visited, newX, newY, targetX, targetY, newTurns, i);
            }
        }

        visited[x][y] = false;
    }

    private boolean isValidMove(int[][] maze, boolean[][] visited, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0 && !visited[x][y];
    }

    public static void main(String[] args) {
        RomeoAndJulietMazeBacktracking mazeSolver = new RomeoAndJulietMazeBacktracking();
        int[][] maze = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1}
        };
        int p = 0, q = 0, r = 3, s = 3;
        int result = mazeSolver.findMinTurns(maze, p, q, r, s);
        System.out.println("最小转弯次数: " + result);
    }
}
