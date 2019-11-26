package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "@SolutionInfo(title = \"\")\n",
        description = "The string \"PAYPALISHIRING\" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)\n" +
                "\n" +
                "P   A   H   N\n" +
                "A P L S I I G\n" +
                "Y   I   R\n" +
                "And then read line by line: \"PAHNAPLSIIGYIR\"\n" +
                "\n" +
                "Write the code that will take a string and make this conversion given a number of rows:\n" +
                "\n" +
                "string convert(string s, int numRows);\n" +
                "Example 1:\n" +
                "\n" +
                "Input: s = \"PAYPALISHIRING\", numRows = 3\n" +
                "Output: \"PAHNAPLSIIGYIR\"\n" +
                "Example 2:\n" +
                "\n" +
                "Input: s = \"PAYPALISHIRING\", numRows = 4\n" +
                "Output: \"PINALSIGYAHRPI\"\n" +
                "Explanation:\n" +
                "\n" +
                "P     I    N\n" +
                "A   L S  I G\n" +
                "Y A   H R\n" +
                "P     I")
public class P6 extends BaseSolution {


    /**
     * Approach 1: Sort by Row
     * <p>
     * Intuition
     * <p>
     * By iterating through the string from left to right, we can easily determine which row in the
     * Zig-Zag pattern that a character belongs to.
     * <p>
     * Algorithm
     * <p>
     * We can use min(numRows,len(s)) lists to represent the non-empty rows of the Zig-Zag Pattern.
     * <p>
     * Iterate through ss from left to right, appending each character to the appropriate row. The
     * appropriate row can be tracked using two variables: the current row and the current direction.
     * <p>
     * The current direction changes only when we moved up to the topmost row or moved down to the
     * bottommost row.
     * <p>
     * Complexity Analysis
     * <p>
     * Time Complexity: O(n), where n==len(s)
     * Space Complexity: O(n)
     */
    public String approach1(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
