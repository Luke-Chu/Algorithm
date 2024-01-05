package dc;

import static java.lang.Math.abs;
/**
 * 给定一个由n个互不相同的数组成的集合S，及一个正整数k≤n，
 * 试设计一个O(n)时间算法找出S中最接近S中位数的k个数。
 * 1.通过该算法求数组的中位数；
 * 2.计算数组中每个元素与中位数之差的绝对值并存放在绝对值数组；
 * 3.通过该算法求绝对值数组中第k kk小的数；
 * 4.将原数组中与中位数之差的绝对值小于等于第k kk小的绝对值的元素输出即为答案。
 * 未解决
 * */
public class ClosestToMedian {

    public static void main(String[] args) {
        int[] S = {9, 6, 4, 2, 8, 15, 1, 5, 7, 3, 12};
        int k = 4;
        int n = S.length;
        //找到第k小的元素
        int kMin = Select(S, 0, n - 1, k);
        System.out.println("第" + k + "小的元素是：" + kMin);
        //计算整个数组的中位数
        int mid = Select(S, 0, n - 1, (n + 1) / 2);
        //计算数组中每个数与中位数之差的绝对值
        int[] dis = new int[n];
        for (int i = 0; i < n; ++i)
        {
            dis[i] = abs(S[i] - mid);
        }
        int res = Select(dis, 0, n - 1, k);
        //用k作计数器，保证结果不会多输出
        for (int j : S) {
            //与中位数之差的绝对值小于第k小的绝对值的数都符合要求(这里要求绝对值不重复)
            if (abs(j - mid) < res) {
                System.out.println(j + " ");
                k--;
            }
        }
        for (int i = 0; i < n; ++i)
        {
            //与中位数之差的绝对值等于第k小的绝对值的数都符合要求(这里要求绝对值不重复)
            if (abs(S[i] - mid) == res)
            {
                System.out.println(i + " ");
                k--;
            }
        }
    }

    //线性选择算法
    private static int Select(int[] arr, int f, int l, int k){
        if (l - f < 5){
            selectSort(arr, f, l); //整个数据不够分组，直接排序
            return arr[f + k - 1]; //返回第k小的数
        }
        //将数分成n/5组，每组5个数，找到每组的中位数并将其放到各组首位
        for (int i = 0; i <= (l - f - 4) / 5; i++) {
            int first = f + 5 * i;
            int last = f + 5 * i + 4;
            selectSort(arr, first, last);
            int mid = (first + last + 1) / 2; //找中位数
            swap(arr, f + i, mid); //将中位数放在首位置
        }
        //找到各组中位数的中位数
        int x = Select(arr, f, f + (l - f - 4) / 5, (l - f - 4) / 10 + 1);
        //按照中位数划分
        int i = partition(arr, f, l, x);
        //求较小数组的长度
        int len = i - f + 1;
        //若较小数组长度小于等于k，说明第k小的元素在这个数组内，递归调用
        if (k <= len) return Select(arr, f, i, k);
        //否则调用右边区间
        else return Select(arr, i + 1, l, k - len);
    }
    //选择排序，每次选择最小的
    private static void selectSort(int[] arr, int first, int last){
        for (int i = first; i < last; i++) {
            int minIndex = i;
            //找i后面最小的元素
            for (int j = i + 1; j <= last; j++) {
                if (arr[minIndex] > arr[j])
                    minIndex = j;
            }
            //找到最小元素后，与i位置交换
            if (i != minIndex) {
                swap(arr, i, minIndex);
            }
        }
    }
    // 该函数用于划分，基准x，左边小于等于x，右边大于x，最后返回左边最后一个index
    private static int partition(int[] arr, int low, int high, int x) {
        int i = low - 1;
        for (int j = low; j <= high; j++) {
            if (arr[j] <= x) {
                i++;
                swap(arr, i, j);
            }
        }
        return i;
    }

    // 交换数组中两个元素的位置
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
