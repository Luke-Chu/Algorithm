package dc;

/**
 * 在一个由元素组成的表中，出现的次数最多的元素称为众数。试写一个寻找众数的算法，并分析其计算复杂性。
 */
public class MajorityElementFinder {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        //大小为1的数组中唯一的元素是众数
        if (lo == hi) {
            return nums[lo];
        }

        // 对该数组的左右半部分进行递归。
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // 如果左右部分众数相同，则返回其中一个众数即可。
        if (left == right) {
            return left;
        }

        // 否则，对每个元素进行计数并返回“优胜者”。
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 2, 4, 2, 5, 2, 6, 5, 4};
        var obj = new MajorityElementFinder();
        int majorityElement = obj.majorityElementRec(nums, 0, nums.length - 1);
        System.out.println("众数是: " + majorityElement);
    }
}