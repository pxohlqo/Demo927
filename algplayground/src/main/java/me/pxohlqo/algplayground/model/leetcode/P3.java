package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "3. Longest Substring Without Repeating Characters", description = "Given a string, find the length of the longest substring without repeating characters.\n" +
        "\n" +
        "Example 1:\n" +
        "\n" +
        "Input: \"abcabcbb\"\n" +
        "Output: 3 \n" +
        "Explanation: The answer is \"abc\", with the length of 3. \n" +
        "Example 2:\n" +
        "\n" +
        "Input: \"bbbbb\"\n" +
        "Output: 1\n" +
        "Explanation: The answer is \"b\", with the length of 1.\n" +
        "Example 3:\n" +
        "\n" +
        "Input: \"pwwkew\"\n" +
        "Output: 3\n" +
        "Explanation: The answer is \"wke\", with the length of 3. \n" +
        "             Note that the answer must be a substring, \"pwke\" is a subsequence and not a substring.")
public class P3 extends BaseSolution {

    /**
     * 2019-11-22 17:50
     * Runtime: 111 ms, faster than 7.54% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.5 MB, less than 99.76% of Java online submissions for Longest Substring Without Repeating Characters.
     */
    public int myApproach(String str) {
        char[] cs = str.toCharArray();
        List<Character> subCharAry = new ArrayList<>();
        int maxLength = Math.min(cs.length, 1);
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            for (int j = 0; j < cs.length - i; j++) {
                char r = cs[i + j];
                if (subCharAry.indexOf(r) == -1) {
                    subCharAry.add(r);
                } else {
                    maxLength = Math.max(maxLength, subCharAry.size());
                    subCharAry.clear();
                    break;
                }
            }
        }
        return maxLength;
    }

    /**
     * Approach 2: Sliding Window
     * <p>
     * Algorithm
     * <p>
     * The naive approach is very straightforward. But it is too slow. So how can we optimize it?
     * <p>
     * In the naive approaches, we repeatedly check a substring to see if it has duplicate character.
     * But it is unnecessary. If a substring s_{ij} from index i to j - 1 is already checked to have
     * no duplicate characters. We only need to check if s[j] is already in the substring s_{ij}.
     * <p>
     * To check if a character is already in the substring, we can scan the substring, which leads
     * to an O(n^2) algorithm. But we can do better.
     * <p>
     * By using HashSet as a sliding window, checking if a character in the current can be done in O(1).
     * <p>
     * A sliding window is an abstract concept commonly used in array/string problems. A window is a
     * range of elements in the array/string which usually defined by the start and end indices,
     * i.e. [i, j) (left-closed, right-open). A sliding window is a window "slides" its two
     * boundaries to the certain direction. For example, if we slide [i, j) to the right by 1
     * element, then it becomes [i+1, j+1)(left-closed, right-open).
     * <p>
     * Back to our problem. We use HashSet to store the characters in current window [i, j)(j = i initially).
     * Then we slide the index j to the right. If it is not in the HashSet, we slide j further.
     * Doing so until s[j] is already in the HashSet. At this point, we found the maximum size of
     * substrings without duplicate characters start with index i. If we do this for all i, we get
     * our answer.
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : O(2n) = O(n). In the worst case each character will be visited twice by i and j.
     * <p>
     * Space complexity : O(min(m, n)). Same as the previous approach. We need O(k) space for the
     * sliding window, where k is the size of the Set. The size of the Set is upper bounded by the
     * size of the string n and the size of the charset/alphabet m
     * .
     */
    public int approach2(String str) {
        int n = str.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(str.charAt(j))) {
                set.add(str.charAt(j++));
                ans = Math.max(ans, j-i);
            } else {
                set.remove(str.charAt(i++));
            }
        }
        return ans;
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
