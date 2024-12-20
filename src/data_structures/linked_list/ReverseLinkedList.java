package data_structures.linked_list;

public class ReverseLinkedList {

    private static Node reverseLinkedList(Node head) {

        if (head == null || head.next == null)
            return head;

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next; // stores the next node
            curr.next = prev; // reverses the current node link
            prev = curr; // move prev to curr node
            curr = next; // move curr to next node
        }

        return prev;
    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new  int[] {6,5,4,3,2,1});
        System.out.println("The given linked list: ");
        LinkedList.printList(head); // Output: 1 -> 2 -> 3 -> 4 -> NULL

        head = reverseLinkedList(head);

        System.out.println("Reversed Linked List:");
        LinkedList.printList(head); // Output: 4 -> 3 -> 2 -> 1 -> NULL
    }


}
