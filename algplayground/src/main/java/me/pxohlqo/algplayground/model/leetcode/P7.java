package me.pxohlqo.algplayground.model.leetcode;

import org.jetbrains.annotations.NotNull;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "7. Reverse Integer", description = "Given a 32-bit signed integer, reverse digits of an integer.\n" +
        "\n" +
        "Example 1:\n" +
        "\n" +
        "Input: 123\n" +
        "Output: 321\n" +
        "Example 2:\n" +
        "\n" +
        "Input: -123\n" +
        "Output: -321\n" +
        "Example 3:\n" +
        "\n" +
        "Input: 120\n" +
        "Output: 21\n" +
        "Note:\n" +
        "Assume we are dealing with an environment which could only store integers within the 32-bit " +
        "signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your " +
        "function returns 0 when the reversed integer overflows.")
public class P7 extends BaseSolution {

    public int myApproach(int x) {
        if (x == 0 || x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) return 0;
        int a = Math.abs(x), b = 0, sign = x / a;
        while (a > 0) {
            jPrintln("a=" + a + " b=" + b);
            b += a % 10;
            jPrint("b=" + b);
            a /= 10;
            if (a == 0) break;
            if (b > Integer.MAX_VALUE / 10 || (b == Integer.MAX_VALUE / 10 && a > 7)) {
//            if (b >= Integer.MAX_VALUE/10 && a >7) {
                return 0;
            }
            b *= 10;
            jPrint(" b=" + b);
            jPrint("a=" + a);
            jPrintln("");
        }
        return b * sign;
    }

    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
