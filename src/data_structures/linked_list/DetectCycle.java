package data_structures.linked_list;

import data_structures.linked_list.util.Node;

//Given head, the head of a linked list, determine if the linked list has a cycle in it.
public class DetectCycle {

    // Floyd's Cycle detection Algo
    private static boolean hasCycle(Node head) {
        if (head == null)
            return false;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // Create a linked list with a cycle
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // Cycle here

        System.out.println("Has Cycle: " + hasCycle(head)); // Output: true

        // Create a linked list without a cycle
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);

        System.out.println("Has Cycle: " + hasCycle(head2)); // Output: false
    }
}
