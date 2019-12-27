package leetcode.hard;

import java.util.*;

public class MergeKSortedLists2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class ListNodeIter implements Iterator<ListNode> {
        private ListNode current;

        ListNodeIter(ListNode node) {
            this.current = node;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public ListNode next() {
            ListNode toReturn = this.current;
            this.current = toReturn.next;
            return toReturn;
        }
    }

    public static class ListNodeIterator implements Iterator<ListNode>{
        private List<ListNodeIter> queue;

        ListNodeIterator(ListNode[] lists) {
            queue = new LinkedList<>();

            for (ListNode l: lists) {
                if (l != null) {
                    queue.add(new ListNodeIter(l));
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public ListNode next() {
            ListNode toReturn = null;
            do {
                ListNodeIter iter = queue.remove(0);
                if (iter.hasNext()) {
                    toReturn = iter.next();
                    if (iter.hasNext()) {
                        queue.add(iter);
                    }
                }
            } while (toReturn == null);

            return toReturn;
        }
    }


    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        ListNode returnList = new ListNode(0);
        ListNode current = returnList;

        ListNodeIterator listQueue = new ListNodeIterator(lists);

        int k = 0;
        while (listQueue.hasNext()) {
            queue.add(listQueue.next().val);
            k++;

            if (k > lists.length) {
                current.next = new ListNode(queue.remove());
                current = current.next;
            }
        }

        while (!queue.isEmpty()) {
            current.next = new ListNode(queue.remove());
            current = current.next;
        }

        return returnList;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void testOriginal() {
        ListNode listOne = new ListNode(1);
        listOne.next = new ListNode(4);
        listOne.next.next = new ListNode(5);

        ListNode listTwo = new ListNode(1);
        listTwo.next = new ListNode(3);
        listTwo.next.next = new ListNode(4);

        ListNode listThree = new ListNode(2);
        listThree.next = new ListNode(6);

        ListNode[] lists = {listOne, listTwo, listThree};

        mergeKLists(lists);
    }

    /*
    [
        1->2->3->4->5
        1->3->4
        2->6
    ]

     */
    private static void test1() {
        ListNode listOne = new ListNode(1);
        listOne.next = new ListNode(2);
        listOne.next.next = new ListNode(3);
        listOne.next.next.next  = new ListNode(4);
        listOne.next.next.next.next  = new ListNode(5);

        ListNode listTwo = new ListNode(1);
        listTwo.next = new ListNode(3);
        listTwo.next.next = new ListNode(4);

        ListNode listThree = new ListNode(2);
        listThree.next = new ListNode(6);

        ListNode[] lists = {listOne, listTwo, listThree};

        mergeKLists(lists);
    }

}
