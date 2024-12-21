package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {

    private static Node addTwoNumbers(Node l1, Node l2) {

        Node result = new Node(0);
        Node head = result;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int val = (l1 != null ? l1.data : 0) + (l2 != null ? l2.data : 0) + carry;
            carry = val / 10;
            val = val % 10;

            result.next = new Node(val);
            result = result.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return head.next;

    }

    public static void main(String[] args) {
        Node l1 = LinkedList.buildListFromArray(new int[]{1, 2, 3, 5, 9, 9, 9});
        Node l2 = LinkedList.buildListFromArray(new int[]{2, 3, 4, 5, 0, 1});

        System.out.println("The given linked list 1: ");
        LinkedList.printList(l1); // Output: 1 -> 2 -> 4 -> NULL

        System.out.println("The given linked list 2: ");
        LinkedList.printList(l2); // Output: 1 -> 3 -> 4 -> NULL

        Node newHead = addTwoNumbers(l1, l2);

        System.out.println("After merging the given Linked List:");
        LinkedList.printList(newHead); // Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> NULL
    }
}
