package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "5. Longest Palindromic Substring", description = "Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.\n" +
        "\n" +
        "Example 1:\n" +
        "\n" +
        "Input: \"babad\"\n" +
        "Output: \"bab\"\n" +
        "Note: \"aba\" is also a valid answer.\n" +
        "Example 2:\n" +
        "\n" +
        "Input: \"cbbd\"\n" +
        "Output: \"bb\"")
public class P5 extends BaseSolution {

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/discuss/2928/Very-simple-clean-java-solution
     */
    public String approachVSCJS(String s) {
        int max = 0, idx = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = extend(s, i, i), len2 = extend(s, i, i + 1);
            if (max < Math.max(len1, len2)) {
                idx = (len1 > len2) ? (i - len1 / 2) : (i - len2 / 2 + 1);
                max = Math.max(len1, len2);
            }
        }
        return s.substring(idx, idx + max);
    }

    /**
     * used in approachVSCJS {@link P5#approachVSCJS(String)}
     *
     * @return length of palindromic substring
     */
    private int extend(String s, int i, int j) {
        for (; i >= 0 && j < s.length(); i--, j++) {
            if (s.charAt(i) != s.charAt(j)) break;
        }
        return j - i - 2 + 1;
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/discuss/2921/Share-my-Java-solution-using-dynamic-programming
     * <p>
     * commented ver.
     */
    public String approachDP(String s) {
        int n = s.length(), startsAt = 0, maxLen = 0;
        boolean[][] dp = new boolean[n][n]; // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                jPrint("i=" + i + " j=" + j);
                jPrint(s.substring(i, j + 1));
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);
                jPrint(dp[i][j]);
                if (dp[i][j] && (j - i + 1 > maxLen)) {
                    startsAt = i;
                    maxLen = j - i + 1;
                }
                jPrintln("s=" + startsAt + " L=" + maxLen);
            }
        }
        return s.substring(startsAt, startsAt + maxLen);
    }

    /**
     * Approach 4: Expand Around Center
     * <p>
     * In fact, we could solve it in O(n^2) time using only constant space.
     * <p>
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded
     * from its center, and there are only 2n - 1 such centers.
     * <p>
     * You might be asking why there are 2n - 1 but not nn centers? The reason is the center of a
     * palindrome can be in between two letters. Such palindromes have even number of letters (such
     * as "abba") and its center are between the two 'b's.
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : O(n^2). Since expanding a palindrome around its center could take O(n) time,
     * the overall complexity is O(n^2).
     * <p>
     * Space complexity : O(1).
     */
    public String approach4(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lenEven = expendAroundCenter(s, i, i);
            int lenOdd = expendAroundCenter(s, i, i + 1);
            int len = Math.max(lenEven, lenOdd);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end+1);
    }

    /**
     * used in approach4 {@link P5#approach4(String)}
     */
    private int expendAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
