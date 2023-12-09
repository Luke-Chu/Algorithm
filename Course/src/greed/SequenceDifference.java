package greed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 【数列极差】问题描述：在黑板上写了 N个正整数 组成 的一个数列，进行如下操作：
 * 每一次擦去其中的两个数 a和 b，然后在数列中加入一个数 a*b+1，如此下去直至黑板上剩下一个数，
 * 在所有按这种操作方式得到的 所有 数中，最大的 max，最小的为 min，
 * 则该数列的极差定义为 M = max -min。试设计一个算法，计算 M。
 * 分析：可以取几个代数分析下，先擦除最大得到min，先擦除最小得到max.
 */
public class SequenceDifference {

    public static int calculateDifference(List<Integer> sequence) {
        int n = sequence.size();
        //对数列排序
        Collections.sort(sequence);
        List<Integer> maxSeq = new ArrayList<>(sequence);
        //求最小值时按照降序排序
        List<Integer> minSeq = new ArrayList<>(sequence);
        minSeq.sort(Collections.reverseOrder());

        // 计算最大值
        int max = 0;
        for (int i = 1; i < n; i++) { //总共会执行n-1次循环
            max = maxSeq.get(1) * maxSeq.get(0) + 1;
            maxSeq.set(1, max);     //每次擦除后就将新结果插入
            maxSeq.remove(0);   //然后移除一个元素
            Collections.sort(maxSeq);   //最后再次排序进行下一轮计算
        }

        //计算最小值
        int min = 0;
        for (int i = 1; i < n; i++) {
            min = minSeq.get(1) * minSeq.get(0) + 1;
            minSeq.set(1, min);
            minSeq.remove(0);
            minSeq.sort(Collections.reverseOrder());
        }

        System.out.println("max = " + max + ", min = " + min);
        // 计算极差
        return max - min;
    }

    public static void main(String[] args) {
        List<Integer> sequence = new LinkedList<>();
        int[] data = {2, 1, 4, 3};
        for (int d: data) {
            sequence.add(d);
        }

        int result = calculateDifference(sequence);

        System.out.println("数列极差: " + result);
    }
}
