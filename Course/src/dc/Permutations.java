package dc;

import java.util.ArrayList;
import java.util.List;

/**
 * 求1-n的全排列
 * O(n!)
 */
public class Permutations {
    public static final int N = 4;  //全排列大小N
    public static final List<List<Integer>> res = new ArrayList<>();    //存储结果

    public static void main(String[] args) {
        List<Integer> curPer = new ArrayList<>();
        //定义变量判断在当前排列中该数字是否使用过
        boolean[] used = new boolean[N];
        //调用产生某个排列的函数
        backTracking(curPer, used);
        //输出排列结果
        for (List<Integer> permutation : res) {
            System.out.println(permutation);
        }
    }
    private static void backTracking(List<Integer> curPer, boolean[] used) {
        if (curPer.size() == N) {
            // 当当前排列的大小达到n时，将其添加到结果列表中
            res.add(new ArrayList<>(curPer));
            return;
        }
        //1-n数字都要使用
        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                // 如果数字i尚未在当前排列中使用
                used[i] = true;
                curPer.add(i + 1); // 添加1到n的数字
                backTracking(curPer, used);     //递归调用
                curPer.remove(curPer.size() - 1); // 回溯
                used[i] = false;    //回溯
            }
        }
    }
}
