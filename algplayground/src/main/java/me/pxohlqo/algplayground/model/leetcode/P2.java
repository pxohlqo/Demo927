package me.pxohlqo.algplayground.model.leetcode;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;

@SolutionInfo(title = "2. Add Two Numbers", description = "You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.\n" +
        "\n" +
        "You may assume the two numbers do not contain any leading zero, except the number 0 itself.\n" +
        "\n" +
        "Example:\n" +
        "\n" +
        "Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)\n" +
        "Output: 7 -> 0 -> 8\n" +
        "Explanation: 342 + 465 = 807.")
public class P2 extends BaseSolution {
    public class ListNode {
        int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
        }

        @NonNull
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public ListNode newListNode(int x) {
        return new ListNode(x);
    }

    public String nodeToString(ListNode startNode) {
        if (startNode == null) return "NULL";
        ListNode mNode = startNode;
        StringBuilder builder = new StringBuilder();
        while (mNode != null) {
            builder.append(mNode.val);
            if (mNode.next != null) builder.append(" -> ");
            mNode = mNode.next;
        }
        return builder.toString();
    }

    public ListNode myApproach(ListNode l1, ListNode l2) {
        class Util {

            int listNodeToIntR(ListNode node) {
                int result = 0;
                int multiple = 1;
                ListNode mNode = node;
                while (mNode != null) {
                    result += mNode.val * multiple;
                    mNode = mNode.next;
                    multiple *= 10;
                }
                return result;
            }

            ListNode intToListNodeR(int x) {
                int m = x;
                ListNode startNode = null;
                ListNode cursor = null;
                while (m != 0) {
                    ListNode n = newListNode(m % 10);
                    if (startNode == null) {
                        startNode = n;
                        cursor = startNode;
                    } else {
                        cursor.next = n;
                        cursor = cursor.next;

                    }
                    m = m / 10;
                }

                return startNode;
            }
        }

        Util u = new Util();
        int i1 = u.listNodeToIntR(l1);
        int i2 = u.listNodeToIntR(l2);
        int sum = i1 + i2;
        jPrintln(sum);

        return u.intToListNodeR(sum);
    }


    /**
     * Approach 1: Elementary Math
     * <p>
     * [Intuition]
     * <p>
     * Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of list, which contains the least-significant digit.
     * <p>
     * Illustration of Adding two numbers
     *
     * <img src="https://leetcode.com/problems/add-two-numbers/Figures/2_add_two_numbers.svg"/>
     * <p>
     * Figure 1. Visualization of the addition of two numbers: 342 + 465 = 807.
     * Each node contains a single digit and the digits are stored in reverse order.
     * <p>
     * [Algorithm]
     * <p>
     * Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1l1 and l2l2. Since each digit is in the range of 0 \ldots 90…9, summing two digits may "overflow". For example 5 + 7 = 125+7=12. In this case, we set the current digit to 22 and bring over the carry = 1carry=1 to the next iteration. carrycarry must be either 00 or 11 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 199+9+1=19.
     * <p>
     * The pseudocode is as following:
     * <p>
     * - Initialize current node to dummy head of the returning list.
     * - Initialize carry to 0.
     * - Initialize pp and qq to head of l1 and l2 respectively.
     * - Loop through lists l1 and l2 until you reach both ends.
     * >    - Set x to node p's value. If pp has reached the end of l1, set to 0.
     * >    - Set y to node q's value. If qq has reached the end of l2, set to 0.
     * >    - Set sum = x + y + carry.
     * >    - Update carry = sum / 10carry=sum/10.
     * >    - Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
     * >    - Advance both pp and qq.
     * - Check if carry = 1, if so append a new node with digit 11 to the returning list.
     * - Return dummy head's next node.
     * <p>
     * Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.
     */
    public ListNode approach1(ListNode l1, ListNode l2) {
        ListNode dummyHead = newListNode(0);
        int carry = 0;
        ListNode p = l1;
        ListNode q = l2;
        ListNode curr = dummyHead;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = newListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) curr.next = newListNode(carry);
        return dummyHead;
    }


    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return null;
    }

    @NotNull
    @Override
    protected Object inputPreprocess(@NotNull String... input) {
        return super.inputPreprocess(input);
    }

    @NotNull
    @Override
    protected String outputPostprocess(@NotNull Object... output) {
        return super.outputPostprocess(output);
    }

}
