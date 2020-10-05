package Algorithms.String;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自己实现字符串常用方法
 * @author yzz
 * @create 2020-10-04 11:15
 */
public class MyString {
    /**
     * 删除字符串中的多余空格
     * "  Bob    Loves Alice  " -> "Bob Loves Alice"
     * @param s
     * @return
     */
    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length()-1;
        // 去掉字符串开头的空白字符 left<=right 避免越界
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') right--;

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
//        while (left <= right) {
//            char c = s.charAt(left);
//            if (c != ' ') {
//                sb.append(c);
//            } else if (sb.charAt(sb.length() - 1) != ' ') {
//                sb.append(c);                     // 空格
//            }
//            left++;
//        }
        // 另一种去除字符间多余的空白字符方式
        while (left <= right) {
            char c = s.charAt(left++);
            if (c != ' ') {
                sb.append(c);
            } else {
                sb.append(' ');
                while (left <= right && s.charAt(left) == ' ') left++;
            }
        }
        return sb;
    }

    /**
     *  将区间[left, right]之间的字符串进行翻转
     *  "Bob Loves Alice" -> "ecilA sevoL boB"
     * @param sb
     * @param left
     * @param right
     */
    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    /**
     * 翻转字符串中的每个单词
     * "ecilA sevoL boB" -> "Alice Loves Bob"
     * @param sb
     */
    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 循环至单词的末尾 空格结尾
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            // 翻转单词
            reverse(sb, start, end-1);
            // 更新start, 去找下一个单词
            start = end + 1;
            end++;
        }
    }

    /**
     * 最长回文串
     * @param s
     * @return
     */
    String longestPalindrome(String s) {
        int len = 0;
        String ans = new String();
        for (int k = 0; k < s.length(); k++) {
            // 回文串长度为偶数
            int i = k, j = k + 1;
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            if (j - i - 1 > len) {
                len = j - i - 1;
                ans = s.substring(i + 1, i + 1 + len);
            }
            // 回文串长度为奇数
            i = k - 1;
            j = k + 1;
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            if (j - i - 1 > len) {
                len = j - i - 1;
                ans = s.substring(i + 1, i + 1 + len);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 翻转字符串里的单词
        String s = "  Bob    Loves Alice  ";
        // 内置API
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        System.out.println(String.join(" ", wordList));

        // 自行编写对应函数
        MyString myString = new MyString();
        StringBuilder sb = myString.trimSpaces(s);

        // 翻转字符串
        myString.reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        myString.reverseEachWord(sb);
        System.out.println(sb.toString());

        System.out.println(myString.longestPalindrome("cbbd"));
    }
}
