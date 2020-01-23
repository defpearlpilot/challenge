package leetcode.medium;

import java.util.List;

public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode currentL1 = l1;
        ListNode currrenL2 = l2;
        ListNode toRet = null;
        ListNode currentRet = null;

        int leftOver = 0;

        while (currentL1 != null || currrenL2 != null) {
            int sum = leftOver;
            if (currentL1 != null) {
                sum += currentL1.val;
                currentL1 = currentL1.next;
            }

            if (currrenL2 != null) {
                sum += currrenL2.val;
                currrenL2 = currrenL2.next;
            }

            ListNode toAppend = new ListNode(sum);
            if (sum == 10) {
                leftOver = 1;
                toAppend.val = 0;
            } else if (sum > 10) {
                leftOver = 1;
                toAppend.val = sum - 10;
            } else {
                leftOver = 0;
            }

            if (toRet == null) {
                toRet = toAppend;
            } else {
                currentRet.next = toAppend;
            }

            currentRet = toAppend;
        }

        if (leftOver > 0) {
            currentRet.next = new ListNode(leftOver);
        }

        return toRet;
    }

    private static void baseCase() {
        ListNode two = new ListNode(2);
        two.next = new ListNode(4);
        two.next.next = new ListNode(3);

        ListNode five = new ListNode(5);
        five.next = new ListNode(6);
        five.next.next = new ListNode(4);

        new AddTwoNumbers().addTwoNumbers(two, five);
    }

    public static void twoNines() {
        ListNode two = new ListNode(9);
        ListNode five = new ListNode(9);

        new AddTwoNumbers().addTwoNumbers(two, five);

    }

    public static void main(String[] args) {
        twoNines();
    }
}
