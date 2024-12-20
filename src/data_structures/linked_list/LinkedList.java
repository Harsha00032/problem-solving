package data_structures.linked_list;

public class LinkedList {

    private static Node head;

    public static void printList(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Method to construct a linked list from an array
    public static Node buildListFromArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null; // Return null for an empty array
        }

        head = new Node(arr[0]); // Create the head node
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]); // Create a new node and link it
            current = current.next; // Move to the next node
        }

        return head; // Return the head of the linked list
    }
}
