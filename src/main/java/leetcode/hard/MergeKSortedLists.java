package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static Map<Integer, List<ListNode>> merge(Map<Integer, List<ListNode>> m1, Map<Integer, List<ListNode>> m2) {
        return Stream.of(m1, m2)
                .flatMap(m -> m.entrySet().stream())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (v1, v2) -> Stream.of(v1, v2).flatMap(Collection::stream).collect(Collectors.toList())
                        ));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        Map<Integer, List<ListNode>> merged = Arrays.stream(lists).reduce(new HashMap<>(), (acc, node) -> {
            if (node != null) {
                acc.computeIfAbsent(node.val, (i) -> new LinkedList<>()).add(node);
            }

            return acc;
        }, MergeKSortedLists::merge);

        ListNode head = null;
        ListNode current = null;

        List<Integer> list = new LinkedList<>(merged.keySet());
        if (list.isEmpty()) {
            return null;
        }

        Collections.sort(list);

        int key = ((LinkedList<Integer>) list).getFirst();

        while (!merged.isEmpty()) {
            List<ListNode> nodes = merged.get(key);
            if (nodes == null) {
                key++;
                continue;
            }

            for (ListNode n : nodes) {
                if (current == null) {
                    head = n;
                    current = n;
                } else {
                    current.next = n;
                    current = n;
                }

                while (current.next != null) {
                    if (current.next.val == key) {
                        current = current.next;
                    } else {
                        merged.computeIfAbsent(current.next.val, (i) -> new LinkedList<>()).add(current.next);
                        current.next = null;
                    }
                }
            }

            merged.remove(key);
            key++;
        }

        return head;
    }

    private static void test4() {
        //[[2],[], [-1]]
        ListNode listOne = new ListNode(2);
        ListNode listTwo = new ListNode(-1);

        ListNode[] lists = {listOne, null, listTwo};

        mergeKLists(lists);
    }


    private static void test3() {
        //[[1,2,2],[1,1,2]]
        ListNode listOne = new ListNode(1);
        listOne.next = new ListNode(2);
        listOne.next.next = new ListNode(2);

        ListNode listTwo = new ListNode(1);
        listTwo.next = new ListNode(1);
        listTwo.next.next = new ListNode(2);

        ListNode[] lists = {listOne, listTwo};

        mergeKLists(lists);
    }

    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
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

    private static void test2() {
        ListNode[] list2 = {null};

        mergeKLists(list2);
    }


}
