package data_structures.linked_list;

public class SwapInPairs {

    private static Node swapPairs(Node head) {

        if (head == null || head.next == null)
            return head;

        Node prev = null;
        Node first = head;
        Node second = head.next;
        head = second;

        while (second != null) {

            if (prev != null)
                prev.next = second;
            first.next = second.next;
            second.next = first;

            prev = first;
            first = first.next;
            if (first != null)
                second = first.next;
            else
                second = null;

        }

        return head;
    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{1, 2, 3, 12, 13, 14});

        System.out.println("The given linked list 1: ");
        LinkedList.printList(head);

        head = swapPairs(head);

        System.out.println("The linked list after swapping in pairs => ");
        LinkedList.printList(head);


    }
}
