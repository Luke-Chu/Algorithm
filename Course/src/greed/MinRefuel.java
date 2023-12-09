package greed;

/**
 * 一辆虚拟汽车在加满油之后可以行驶 n km。旅途中有 k个加油站。设计一个有效Java算法，
 * 使得沿途加油的次数最少。终端输入正整数 n和 k，表示汽车加满油后可行驶 n km，
 * 且旅途中有 k个加油站。接下来输入 k+1个整数，表示第 k个加油站与第 k-1个加油站之间的距离。
 * 第0个加油站表示出发地，汽车已加满油。第 k+1个加油站表示目的地。
 */
public class MinRefuel {
    public static void main(String[] args) {
        int n = 7;
        int k = 7;

        int[] distances = new int[]{1, 2, 3, 4, 5, 1, 6, 6};

        int numRefuel = 0;
        int currentRefuel;

        //第一个加油站肯定能走到
        currentRefuel = distances[0];
        //考虑能不能走到下一个加油站k(共0-k个，最后一个是目的地)，能就继续走，不能就变成子问题
        for (int i = 0; i < k; i++) {
            currentRefuel += distances[i + 1];
            if (currentRefuel >= n){
                numRefuel++;   //不能走到下一个所以加油一次（当前加油站）
                currentRefuel = distances[i + 1];   //从当前加油站到下一个加油站的距离
            }
        }

        System.out.println("最少加油次数为：" + numRefuel);
    }
}
