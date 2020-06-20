package test;

/**
 * @author yzze
 * @create 2020-04-14 11:50
 */
public class test1 {

    public void quickSort(int[] nums, int left, int right) {
        int pivot = nums[(left + right) / 2];
        int i = left, j = right;
        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                if (i != j) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(nums, left, j);
        }
        if (right > i) {
            quickSort(nums, i, right);
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{8,1,4,6,2,3,5,7};
        test1 test = new test1();
        test.quickSort(nums, 0, nums.length-1);
        System.out.println("排序后：");
        System.out.print("[");
        for (int num : nums) {
            System.out.print(num);
            System.out.print(",");
        }
        System.out.println("]");
    }
}
