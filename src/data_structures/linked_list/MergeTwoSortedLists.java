package data_structures.linked_list;

public class MergeTwoSortedLists {

    private static Node mergeTwoSortedLists(Node l1, Node l2) {

        Node current = new Node(0);

        while (l1 != null && l2 != null){

            if (l1.data <= l2.data){
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null){
            current.next = l1;
        } else {
            current.next = l2;
        }

        return current.next;
    }

    public static void main(String[] args) {
        Node l1 = LinkedList.buildListFromArray(new int[]{7, 12});
        Node l2 = LinkedList.buildListFromArray(new int[]{7, 13});

        System.out.println("The given linked list 1: ");
        LinkedList.printList(l1); // Output: 1 -> 2 -> 4 -> NULL

        System.out.println("The given linked list 2: ");
        LinkedList.printList(l2); // Output: 1 -> 3 -> 4 -> NULL

       Node mergedHead = mergeTwoSortedLists(l1, l2);

        System.out.println("After merging the given Linked List:");
        LinkedList.printList(l1); // Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> NULL
    }

}
