package LeetCode;

/**
 * @author yzze
 * @create 2020-05-24 15:46
 */
public class LeetCode9 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int[] array = new int[10];

        int next = x;
        int index = 0;

        while (next != 0) {
            array[index] = next % 10;
            next = next / 10;
            index++;
        }

        index--;

        int compare = 0;

        for (int i = 0; i < array.length; i++) {
            compare += array[i] * ((int)Math.pow(10, index));
            index--;
            if (index < 0) {
                break;
            }
        }

        if (x == compare) {
            return true;
        } else {
            return false;
        }

    }
    public static void main(String[] args) {
        LeetCode9 leetCode9 = new LeetCode9();
        System.out.println(leetCode9.isPalindrome(121));
    }
}
