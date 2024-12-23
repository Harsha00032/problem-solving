package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

public class MiddleOfTheLinkedList {

    private static Node findMiddle(Node head) {
        if (head == null || head.next == null)
            return head;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{1,2,3,4,5,6,7});
        System.out.println("The given linked list: ");
        LinkedList.printList(head);
        Node middle = findMiddle(head);
        System.out.print("Middle of the linked is: "+ middle.data);

    }
}
