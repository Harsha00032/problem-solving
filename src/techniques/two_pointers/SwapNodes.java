package techniques.two_pointers;


import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
You are given the head of a linked list, and an integer k.
Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
 */
public class SwapNodes {

    private static Node swapNodes(Node head, int k) {
        if (head == null || head.next == null)
            return head;
        Node slow = head;
        Node fast = head;

        while (k > 1) {
            fast = fast.next;
            k--;
        }

        Node first = fast;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        Node second = slow;

        int temp = first.data;
        first.data = second.data;
        second.data = temp;

        return head;
    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{7,9,6,6,7,8,3,0,9,5});
        int k =5;
        System.out.println("The given =>");
        LinkedList.printList(head);
        head = swapNodes(head, k);
        LinkedList.printList(head);
    }

}
