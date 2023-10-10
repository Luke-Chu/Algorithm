import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static List<List<Integer>> generatePermutations(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPermutation = new ArrayList<>();
        boolean[] used = new boolean[n];

        generatePermutationsHelper(n, currentPermutation, used, result);

        return result;
    }

    private static void generatePermutationsHelper(int n, List<Integer> currentPermutation, boolean[] used, List<List<Integer>> result) {
        if (currentPermutation.size() == n) {
            // 当当前排列的大小达到n时，将其添加到结果列表中
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                // 如果数字i尚未在当前排列中使用
                used[i] = true;
                currentPermutation.add(i + 1); // 添加1到n的数字
                generatePermutationsHelper(n, currentPermutation, used, result);
                currentPermutation.remove(currentPermutation.size() - 1); // 回溯
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int n = 3; // 更改n的值以获得不同n的排列
        List<List<Integer>> permutations = generatePermutations(n);

        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }
}
