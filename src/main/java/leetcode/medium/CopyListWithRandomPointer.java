package leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CopyListWithRandomPointer {
    /*
    A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

    Return a deep copy of the list.

    The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

    val: an integer representing Node.val
    random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
     */

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + (next != null ? next.val + "" : "null" )+
                    ", random=" + (random != null ? random.val + "": "null") +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val &&
                    Objects.equals(next, node.next) &&
                    Objects.equals(random, node.random);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    public static void testOne() {
        /*
        Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
         */

        Node seven = new Node(7);
        Node thirteen = new Node(13);
        Node eleven = new Node(11);
        Node ten = new Node(10);
        Node one = new Node(1);

        seven.next = thirteen;
        seven.random = null;

        thirteen.next = eleven;
        thirteen.random = seven;

        eleven.next = ten;
        eleven.random = one;

        ten.next = one;
        ten.random = seven;

        one.random = seven;
        Node copy = copyRandomListMap(seven);
        printNodes(copy);
    }

    public static void copyNode(Node node, Map<Node, Node> copyMapping) {
        if (!copyMapping.containsKey(node)) {
            copyMapping.put(node, new Node(node.val));
        }

        Node copyHead = copyMapping.get(node);

        if (node.next != null && !copyMapping.containsKey(node.next)) {
            Node copyNext = new Node(node.next.val);
            copyMapping.put(node.next, copyNext);
        }

        copyHead.next = copyMapping.get(node.next);

        if (node.random != null && !copyMapping.containsKey(node.random)) {
            Node copyRandom = new Node(node.random.val);
            copyMapping.put(node.random, copyRandom);
        }

        copyHead.random = copyMapping.get(node.random);
    }

    public static void printNodes(Node head) {
        Node current = head;

        while (current != null) {
            System.out.println(current + "");
            current = current.next;
        }
    }

    public static Node copyRandomListMap(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> copyMapping = new HashMap<>();

        Node current = head;


        while (current != null) {
            copyNode(current, copyMapping);

            System.out.println(current + "");
            current = current.next;
        }

        return copyMapping.get(head);
    }

    public static void main(String[] args) {
        testOne();
    }
}
