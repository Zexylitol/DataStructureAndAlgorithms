package Array;

/**
 * 二分查找
 * Although the basic idea of binary search is comparatively straightforward,
 * the details can be surprisingly tricky...
 * 思路很简单，细节是魔鬼
 *
 * @author yzz
 */
public class BinarySearch {

    /**
     * 寻找一个数
     *
     * @param nums
     * @param target
     * @return
     */
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意  在[left, right]的范围里寻找target

        while (left <= right) { // 注意  当left==right时,区间[left,right]依然有效
            int mid = (right + left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意  target在[mid+1,right]中
            else if (nums[mid] > target)
                right = mid - 1; // 注意  target在[left, mid-1]中
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    /**
     * 寻找右侧边界
     *
     * @param nums
     * @param target
     * @return
     */
    int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        BinarySearch bs = new BinarySearch();

        int[] test = new int[]{0, 1, 2, 2, 2, 3, 4};

        int result1 = bs.left_bound(test, 2);
        int result2 = bs.binarySearch(test, 2);
        int result3 = bs.right_bound(test, 2);
        System.out.println(result1 + " " + result2 + " " + result3);
    }

}
