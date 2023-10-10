import java.util.HashMap;
import java.util.Map;

public class MajorityElementFinder {

    public static int findMajorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // 遍历数组，统计每个元素的出现次数
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 找到出现次数最多的元素
        int majorityElement = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                majorityElement = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return majorityElement;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 2, 4, 2, 5, 2};
        int majorityElement = findMajorityElement(nums);
        System.out.println("The majority element is: " + majorityElement);
    }
}

/*
 * 算法计算复杂性分析：
 * 该算法的思路是使用哈希表来存储每个元素及其出现次数，然后遍历哈希表以找到出现次数最多的元素。
 * 这个算法的时间复杂性为O(n)，其中n是数组的长度，因为我们需要遍历整个数组。
 * 空间复杂性也是O(n)，因为在最坏的情况下，哈希表可能需要存储所有不同的元素。
 */