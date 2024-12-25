package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
 */
public class RotateLinkedList {

    private static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null)
            return head;

        int len = 0;
        Node curr = head;

        while (curr != null) {
            len++;
            curr = curr.next;
        }
        k = k % len;
        if (k == 0)
            return head;

        int lastNodePos = len - k;
        curr = head;
        while (lastNodePos > 1) {
            curr = curr.next;
            lastNodePos--;
        }

        Node newHead = curr.next;
        curr.next = null;
        curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;

        return newHead;

    }

    public static void main(String[] args) {

        Node head = LinkedList.buildListFromArray(new int[]{1, 3, 5, 7, 9, 11});
        System.out.println("The given linked list is =>");
        LinkedList.printList(head);
        int k =5;
        head = rotateRight(head, k);
        System.out.println("After rotation by "+ k);
        LinkedList.printList(head);
    }
}
