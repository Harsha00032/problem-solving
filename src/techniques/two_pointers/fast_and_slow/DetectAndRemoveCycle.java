package techniques.two_pointers.fast_and_slow;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

// Remove cyclic node in the linked list
public class DetectAndRemoveCycle {

    //Applying floyd's algorithm to detect cycle and remove after
    private static Node removeCycle(Node head) {

        if (head == null)
            return head;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println("cycle is detected"); // guarantee the cycle but didn't confirm the start of the cycle
                // The distance from the head to cycle start is same as distance from fast(after detection) to start of the cycle
                slow = head;
                Node prev = null; // To keep track of the node that comes before start of the loop
                while (slow != fast){
                    prev = fast; // track the prev node before cycle
                    slow = slow.next;
                    fast = fast.next;
                }
                System.out.println("cycle is detected at "+ slow.data);

                prev.next = null;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        // Create a linked list with a cycle
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // Cycle here

        System.out.println("Applying cycle detection and removal, the linked list now is =>");// Output: true
        head = removeCycle(head);
        LinkedList.printList(head);

    }
}
