package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "11. Container With Most Water",
        description = "Given n non-negative integers a1, a2, ..., an , where each represents a point at " +
                "coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at " +
                "(i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that " +
                "the container contains the most water.\n" +
                "\n" +
                "Note: You may not slant the container and n is at least 2." +
                "<img src=\"https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg\"/>" +
                "The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max " +
                "area of water (blue section) the container can contain is 49.\n" +
                "\n" +
                "Example:\n" +
                "\n" +
                "Input: [1,8,6,2,5,4,8,3,7]\n" +
                "Output: 49")
public class P11 extends BaseSolution {


    /**
     * 2019-11-26 Tu 15:36
     * Runtime: 212 ms, faster than 11.71% of Java online submissions for Container With Most Water.
     * Memory Usage: 40.3 MB, less than 91.03% of Java online submissions for Container With Most Water.
     */
    public int myApproach(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(h, height[j]) * (j - i));
            }
        }
        return maxArea;
    }

    /**
     * Approach 2: Two Pointer Approach
     * <p>
     * Algorithm
     * <p>
     * The intuition behind this approach is that the area formed between the lines will always be
     * limited by the height of the shorter line. Further, the farther the lines, the more will be
     * the area obtained.
     * <p>
     * We take two pointers, one at the beginning and one at the end of the array constituting the
     * length of the lines. Further, we maintain a variable maxarea to store the maximum
     * area obtained till now. At every step, we find out the area formed between them, update maxarea
     * and move the pointer pointing to the shorter line towards the other end by one step.
     * <p>
     * How this approach works?
     * <p>
     * Initially we consider the area constituting the exterior most lines. Now, to maximize the area,
     * we need to consider the area between the lines of larger lengths. If we try to move the pointer
     * at the longer line inwards, we won't gain any increase in area, since it is limited by the
     * shorter line. But moving the shorter line's pointer could turn out to be beneficial, as per
     * the same argument, despite the reduction in the width. This is done since a relatively longer
     * line obtained by moving the shorter line's pointer might overcome the reduction in area caused
     * by the width reduction.
     * <p>
     * For further clarification {@see https://leetcode.com/problems/container-with-most-water/discuss/6099/yet-another-way-to-see-what-happens-in-the-on-algorithm}
     * and for the proof {@see https://leetcode.com/problems/container-with-most-water/discuss/6089/Anyone-who-has-a-O(N)-algorithm/7268}.
     */
    public int approach2(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
