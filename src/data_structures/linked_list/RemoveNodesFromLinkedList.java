package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
2487. Remove Nodes From Linked List - Medium
You are given the head of a linked list.
Remove every node which has a node with a greater value anywhere to the right side of it.
Return the head of the modified linked list.
Example 1:
Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
 */
public class RemoveNodesFromLinkedList {

    private static Node removeNodes(Node head){

        if (head ==  null || head.next == null)
            return head;

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node newHead = prev;
        curr = newHead.next;

        while (curr != null) {
            if (curr.data < prev.data) {
                prev.next = curr.next;
            } else
                prev = curr;
            curr = curr.next;
        }

        prev = null;
        curr = newHead;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{15,2,13,3,8, 18});
        System.out.println("The given linked list: ");
        LinkedList.printList(head);
        Node newHead = removeNodes(head);
        System.out.println("The linked list after : ");
        LinkedList.printList(newHead);
    }

}
