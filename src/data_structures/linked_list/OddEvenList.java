package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

public class OddEvenList {

    private static Node oddEvenList(Node head) {
        if (head == null || head.next == null)
            return head;

        Node oddNodePointer = new Node(0);
        Node oddNodeHead = oddNodePointer;

        Node evenNodePointer = new Node(0);
        Node evenNodeHead = evenNodePointer;
        int count = 1;

        Node curr = head;

        while (curr != null) {

            if(count % 2 == 1) {
                oddNodePointer.next = curr;
                oddNodePointer = oddNodePointer.next;
            } else {
                evenNodePointer.next = curr;
                evenNodePointer = evenNodePointer.next;
            }

            count++;
            curr = curr.next;
        }

        evenNodePointer.next = null;
        oddNodePointer.next = evenNodeHead.next;

        return oddNodeHead.next;

    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{2,1,3,5,6,4,7});
        System.out.println("The given linked list => ");
        LinkedList.printList(head);
        System.out.println("After : ");
        head = oddEvenList(head);
       LinkedList.printList(head);
    }
}
