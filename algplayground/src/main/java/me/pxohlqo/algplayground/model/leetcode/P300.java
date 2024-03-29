package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

/**
 * 300. Longest Increasing Subsequence
 * <p>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
@SolutionInfo(
        title = "300. Longest Increasing Subsequence",
        description = "Given an unsorted array of integers, find the length of longest increasing subsequence.\n" +
                " \n" +
                "  Example:\n" +
                " \n" +
                "  Input: [10,9,2,5,3,7,101,18]\n" +
                "  Output: 4\n" +
                "  Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.")
public class P300 extends BaseSolution {


    private int approach1(int[] nums) {

        return 0;
    }

    private int testApproach(int[] nums) {
        return 4;
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        int[] realInput = new int[input.length];
        return testApproach(realInput);
    }
}


