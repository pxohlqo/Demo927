package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "12. Integer to Roman",
        description = "Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.\n" +
                "\n" +
                "Symbol       Value\n" +
                "I             1\n" +
                "V             5\n" +
                "X             10\n" +
                "L             50\n" +
                "C             100\n" +
                "D             500\n" +
                "M             1000\n" +
                "For example, two is written as II in Roman numeral, just two one's added together. " +
                "Twelve is written as, XII, which is simply X + II. The number twenty seven is written " +
                "as XXVII, which is XX + V + II.\n" +
                "\n" +
                "Roman numerals are usually written largest to smallest from left to right. However, " +
                "the numeral for four is not IIII. Instead, the number four is written as IV. Because " +
                "the one is before the five we subtract it making four. The same principle applies to " +
                "the number nine, which is written as IX. There are six instances where subtraction " +
                "is used:\n" +
                "\n" +
                "I can be placed before V (5) and X (10) to make 4 and 9. \n" +
                "X can be placed before L (50) and C (100) to make 40 and 90. \n" +
                "C can be placed before D (500) and M (1000) to make 400 and 900.\n" +
                "Given an integer, convert it to a roman numeral. Input is guaranteed to be within " +
                "the range from 1 to 3999.\n" +
                "\n" +
                "Example 1:\n" +
                "\n" +
                "Input: 3\n" +
                "Output: \"III\"\n" +
                "Example 2:\n" +
                "\n" +
                "Input: 4\n" +
                "Output: \"IV\"\n" +
                "Example 3:\n" +
                "\n" +
                "Input: 9\n" +
                "Output: \"IX\"\n" +
                "Example 4:\n" +
                "\n" +
                "Input: 58\n" +
                "Output: \"LVIII\"\n" +
                "Explanation: L = 50, V = 5, III = 3.\n" +
                "Example 5:\n" +
                "\n" +
                "Input: 1994\n" +
                "Output: \"MCMXCIV\"\n" +
                "Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.")
public class P12 extends BaseSolution {

    public String myApproach(int num) {
        StringBuilder builder = new StringBuilder();
        int numK = num / 1000;
        for (int i = 0; i < numK; i++) {
            builder.append("M");
        }
        num = num % 1000;
        jPrintln(num);

        int numH = num / 100;
        if (numH < 5) {
            if (numH == 4) {
                builder.append("CD");
            } else for (int i = 0; i < numH; i++) builder.append("C");
        } else if (numH < 9) {
            builder.append("D"); // 1894 M DCCC XCIV
            for (int i = 0; i < (numH - 5); i++) builder.append("C");
        } else {
            builder.append("CM");
        }
        num = num % 100;
        jPrintln(num);

        int numT = num / 10;
        if (numT < 5) {
            if (numT == 4) {
                builder.append("XL");
            } else for (int i = 0; i < numT; i++) builder.append("X");
        } else if (numT < 9) {
            builder.append("L");
            for (int i = 0; i < (numT - 5); i++) builder.append("X");
        } else {
            builder.append("XC");
        }
        num = num % 10;
        jPrintln(num);

        int numO = num;
        if (numO < 5) {
            if (numO == 4) {
                builder.append("IV");
            } else for (int i = 0; i < numO; i++) builder.append("I");
        } else if (numO < 9) {
            builder.append("V");
            for (int i = 0; i < (numO - 5); i++) builder.append("I");
        } else {
            builder.append("IX");
        }

        return builder.toString();
    }

    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
