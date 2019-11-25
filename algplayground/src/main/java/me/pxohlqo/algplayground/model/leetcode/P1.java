package me.pxohlqo.algplayground.model.leetcode;

import android.content.IntentFilter;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Approach 2: Two-pass Hash Table
     * <p>
     * To improve our run time complexity, we need a more efficient way to check if the complement
     * exists in the array. If the complement exists, we need to look up its index. What is the best
     * way to maintain a mapping of each element in the array to its index? A hash table.
     * <p>
     * We reduce the look up time from O(n) to O(1) by trading space for speed. A hash table
     * is built exactly for this purpose, it supports fast look up in near constant time. I say "near"
     * because if a collision occurred, a look up could degenerate to O(n) time. But look up in hash
     * table should be amortized O(1) time as long as the hash function was chosen carefully.
     * <p>
     * A simple implementation uses two iterations. In the first iteration, we add each element's
     * value and its index to the table. Then, in the second iteration we check if each element's
     * complement (target - nums[i]) exists in the table. Beware that the complement
     * must not be nums[i] itself!
     * <p>
     * Complexity Analysis:
     * - Time complexity : O(n). We traverse the list containing nn elements exactly twice. Since
     * the hash table reduces the look up time to O(1), the time complexity is O(n).
     * - Space complexity : O(n). The extra space required depends on the number of items stored in
     * the hash table, which stores exactly n elements.
     */
    int[] approach2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complment = target - nums[i];
            if (map.containsKey(complment) && map.get(complment) != -i) {
                return new int[]{i, map.get(complment)};
            }
        }
        return new int[]{-1};
    }

    /**
     * Approach 3: One-pass Hash Table
     * It turns out we can do it in one-pass. While we iterate and inserting elements into the table,
     * we also look back to check if current element's complement already exists in the table. If it
     * exists, we have found a solution and return immediately.
     *
     * Complexity Analysis:
     *
     * - Time complexity : O(n)O(n). We traverse the list containing nn elements only once. Each look
     * up in the table costs only O(1)O(1) time.
     * - Space complexity : O(n)O(n). The extra space required depends on the number of items stored
     * in the hash table, which stores at most nn elements.
     * */
    int[] approach3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complment = target - nums[i];
            if (map.containsKey(complment)) return new int[] {map.get(complment), i};
            map.put(nums[i], i);
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
