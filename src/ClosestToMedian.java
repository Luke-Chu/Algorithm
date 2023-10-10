/*
* 给定一个由n个互不相同的数组成的集合S，及一个正整数k≤n，
* 试设计一个O(n)时间算法找出S中最接近S 的中位数的k个数。
* */

import java.util.Arrays;

public class ClosestToMedian {

    public static void main(String[] args) {
        int[] S = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 5;

        int[] result = findClosestToMedian(S, k);

        System.out.println("Closest " + k + " elements to median: " + Arrays.toString(result));
    }

    public static int[] findClosestToMedian(int[] S, int k) {
        if (S == null || S.length < k) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = S.length;
        int medianIndex = n / 2;

        // 使用快速选择找到中位数的索引
        int median = quickSelect(S, 0, n - 1, medianIndex);
        System.out.println(medianIndex);
        System.out.println(median);

        // 找到最接近中位数的 k 个元素
        int[] result = new int[k];
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int leftDistance = Math.abs(S[left] - S[medianIndex]);
            int rightDistance = Math.abs(S[right] - S[medianIndex]);

            if (leftDistance <= rightDistance) {
                result[leftDistance] = S[left];
                left++;
            } else {
                result[rightDistance] = S[right];
                right--;
            }
        }

        return result;
    }

    // 快速选择算法
    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low <= high) {
            int partitionIndex = partition(arr, low, high);

            if (partitionIndex == k) {
                return arr[partitionIndex];
            } else if (partitionIndex < k) {
                return quickSelect(arr, partitionIndex + 1, high, k);
            } else {
                return quickSelect(arr, low, partitionIndex - 1, k);
            }
        }

        return -1; // 出错情况，实际中可以根据具体需求返回适当的值
    }

    // 分区函数，用于快速选择
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 交换数组中两个元素的位置
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
