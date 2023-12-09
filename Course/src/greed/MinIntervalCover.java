package greed;

import java.util.Arrays;

/**
 * 设 x1, x2, ..., xn是实直线上的 n个点。用固定长度的闭区间覆盖这 n个点，
 * 至少需要多少个这样的固定长度闭区间 ?对于给定的实直线上的 n个点和闭区间的长度 k，
 * 设计解此问题的有效算法，计算覆盖点集的最少区间数。
 */

public class MinIntervalCover {

    public static int minIntervalCover(int[] points, int k) {
        // 将点按照坐标升序排序
        Arrays.sort(points);

        int n = points.length;
        int count = 0;
        int i = 0;

        while (i < n) {
            // 当前区间的右端点
            int right = points[i] + k;

            // 扩展区间，直到不能再扩展为止
            while (i < n && points[i] <= right) {
                i++;
            }

            count++;

            // 下一个区间的左端点
            int nextLeft = points[i - 1] + k;

            // 跳过所有与下一个区间相交的点
            while (i < n && points[i] <= nextLeft) {
                i++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] points = {1, 3, 7, 9, 12};
        int k = 2;

        int result = minIntervalCover(points, k);

        System.out.println("最少的区间个数: " + result);
    }
}
