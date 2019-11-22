package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
     * */
    public int myApproach(String str) {
        char[] cs = str.toCharArray();
        List<Character> subCharAry = new ArrayList<>();
        int maxLength = Math.min(cs.length, 1);
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            for (int j = 0; j < cs.length - i; j++) {
                char r = cs[i+j];
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


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
