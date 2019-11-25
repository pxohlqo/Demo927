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
        jPrintln(s);
        int sLen = s.length();
        int oIdx = 0;
        int len = 0;
        for (int i = 0; i < sLen; i++) {
            int sIdx = i, eIdx = i + ((sLen + 1) % 2);
//            jPrintln("s: " + sIdx + " e: " + eIdx);
            while (sIdx > 0 && eIdx < sLen) {
                jPrintln("s: " + sIdx + " e: " + eIdx);
                jPrintln(s.substring(sIdx, eIdx));
                if (s.charAt(sIdx) == s.charAt(eIdx-1)) {
                    sIdx--;
                    eIdx++;
                } else {
                    if ((eIdx - sIdx) != len) {
                        len = Math.max(len, eIdx - sIdx);
                        jPrintln("len: "+len);
                        oIdx = i;
                    }
                    break;
                }
            }
        }

        return s.substring(oIdx - len / 2, oIdx + len / 2);
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }
}
