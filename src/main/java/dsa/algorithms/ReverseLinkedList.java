package dsa.algorithms;

import dsa.structures.Node;

import java.util.List;

public class ReverseLinkedList {

    /* 5 -> 4 -> 3 -> 2 -> null

    n = 5
    r = 4 (n.next)
    t = 3 (r.next)

    r.next = h
    h.next = null

    h = 5
    r = 4 -> 5
    t = 3

    t != null
      h = r
      r = t

      h = 4 -> 5
      r = 3 -> 2 -> null
      t = 2 (r.next)
    t == null

    */

    public static Node<Integer> reverseList(Node<Integer> node) {
        Node<Integer> first = node;

        Node<Integer> head = node;
        Node<Integer> remaining = head.getNext();
        Node<Integer> remainingNext = null;

        while (remaining != null) {
            remainingNext = remaining.getNext();

            remaining.setNext(head);
            head = remaining;

            if (remainingNext == null) {
                remaining = null;
            } else {
                remaining = remainingNext;
            }
        }

        first.setNext(null);

        return head;
    }

    public static void main(String[] args) {
        Node<Integer> a = new Node<>(5);
        Node<Integer> b = a.setNext(new Node<>(4));
        Node<Integer> c = b.setNext(new Node<>(3));
        c.setNext(new Node<>(2));
        Node<Integer> reversed = reverseList(a);
        System.out.println("HELLO");
    }
}
