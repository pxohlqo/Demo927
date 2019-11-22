package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(
        title = "1. Two Sum",
        description = "Given an array of integers, return indices of the two numbers such that they add up to a specific target.\n" +
                "\n" +
                "You may assume that each input would have exactly one solution, and you may not use the same element twice.\n" +
                "\n" +
                "Example:\n" +
                "\n" +
                "Given nums = [2, 7, 11, 15], target = 9,\n" +
                "\n" +
                "Because nums[0] + nums[1] = 2 + 7 = 9,\n" +
                "return [0, 1].")
public class P1 extends BaseSolution {

    /**
     * Approach 1: Brute Force
     * The brute force approach is simple. Loop through each element xx and find if there is another
     * value that equals to target - x.
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : O(n^2). For each element, we try to find its complement by looping through
     * the rest of array which takes O(n)O(n) time. Therefore, the time complexity is O(n^2).
     * <p>
     * Space complexity : O(1).
     */
    int[] approach1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    print(nums[i]);
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1};
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        Object[] inputAry = (Object[]) input[0];
        int[] nums = (int[]) inputAry[0];
        int target = (int) inputAry[1];
        return approach1(nums, target);
    }

    @NotNull
    @Override
    protected Object inputPreprocess(@NotNull String... input) {
        Object[] processedInput = new Object[2];
        processedInput[0] = toIntArray(input[0]);
        processedInput[1] = Integer.valueOf(input[1]);
        return processedInput;
    }

    @NotNull
    @Override
    protected String outputPostprocess(@NotNull Object... output) {
        int[] resultAry = (int[]) output[0];
        return toString(resultAry);
    }

    void printClass(Object object) {
        System.out.println(object.getClass().getSimpleName());
        System.out.println();
    }

    void print(Object object) {
        System.out.println(object);
        System.out.println();
    }
}
