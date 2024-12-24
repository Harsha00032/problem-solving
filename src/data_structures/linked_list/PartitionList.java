package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {

    private static Node partitionList(Node head, int x) {

        Node lessValueNode = new Node(0);
        Node lessValueNodeHead = lessValueNode;
        Node highValueNode = new Node(0);
        Node highValueNodeHead = highValueNode;

        while (head != null) {
            if (head.data < x) {
                lessValueNode.next = head;
                lessValueNode = lessValueNode.next;
            }

            if (head.data >= x) {
                highValueNode.next = head;
                highValueNode = highValueNode.next;
            }

            head = head.next;
        }

        highValueNode.next = null;
        lessValueNode.next = highValueNodeHead.next;

        return lessValueNodeHead.next;

    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{1, 4, 3, 2, 5, 2});
        int x = 3;
        System.out.println("The given linked list =>");
        LinkedList.printList(head);
        head = partitionList(head, x);
        System.out.println("After applying partition function: ");
        LinkedList.printList(head);


    }
}
