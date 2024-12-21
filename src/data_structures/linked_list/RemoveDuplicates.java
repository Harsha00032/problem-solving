package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
Given a sorted linked list, remove all the duplicate elements to leave only distinct numbers.
The linked list should remain sorted, and the modified list should be returned.

Examples
Example 1:
Input: 1 -> 1 -> 2
Output: 1 -> 2
 */
public class RemoveDuplicates {

    private static Node removeDuplicates(Node head) {
        if (head == null || head.next == null)
            return head;

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            Node next = curr.next; // stores the next node
            if (curr.data == prev.data) // check if curr node is a duplicate
                prev.next = curr.next; // update link to remove curr node
            else
                prev = curr; // move prev to curr
            curr = next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{6, 6, 5, 4, 4, 3, 2, 2, 1, 1});
        System.out.println("The given linked list: ");
        LinkedList.printList(head); // Output: 1 -> 2 -> 3 -> 4 -> NULL

        head = removeDuplicates(head);

        System.out.println("After removing duplicates the given Linked List:");
        LinkedList.printList(head); // Output: 4 -> 3 -> 2 -> 1 -> NULL
    }
}
