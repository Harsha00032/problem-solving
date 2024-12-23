package data_structures.linked_list;

import data_structures.linked_list.util.LinkedList;
import data_structures.linked_list.util.Node;

import java.util.Stack;

/*
Given the head of a singly linked list, return true if it is a
palindrome or false otherwise.- Easy
Example 1:
Input: head = [1,2,2,1]
Output: true

 */
public class PalindromeCheck {

    private static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;
        Stack<Integer> nodeStack = new Stack<>();

        Node curr = head;

        while (curr != null) {
            nodeStack.push(curr.data);
            curr = curr.next;
        }

        curr = head;

        while (curr != null && nodeStack.pop() == curr.data) {
            curr = curr.next;
        }

        return curr == null;

    }

    public static void main(String[] args) {
        Node head = LinkedList.buildListFromArray(new int[]{1,2,3,2,1});
        System.out.println("The given linke list =>");
        LinkedList.printList(head);
        System.out.println("is Palindrome ?: "+ isPalindrome(head));
    }
}
