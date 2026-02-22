package Learning.InterviewPreparation;

import java.util.*;

/**
 * Data Structure Problems for Interview Preparation
 */

public class DataStructures {
    
    // ==================== LinkedList ====================
    
    static class LinkedListNode {
        int data;
        LinkedListNode next;
        
        LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    static class LinkedList {
        LinkedListNode head;
        
        public void insert(int data) {
            LinkedListNode newNode = new LinkedListNode(data);
            if (head == null) {
                head = newNode;
            } else {
                LinkedListNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
        
        public void display() {
            LinkedListNode current = head;
            System.out.print("LinkedList: ");
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
        
        public void reverse() {
            LinkedListNode prev = null;
            LinkedListNode current = head;
            
            while (current != null) {
                LinkedListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }
        
        public boolean hasCycle() {
            LinkedListNode slow = head;
            LinkedListNode fast = head;
            
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }
        
        public int length() {
            int count = 0;
            LinkedListNode current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }
    }
    
    // ==================== Stack ====================
    
    static class Stack {
        private List<Integer> items;
        
        Stack() {
            items = new ArrayList<>();
        }
        
        public void push(int value) {
            items.add(value);
        }
        
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack underflow");
            }
            return items.remove(items.size() - 1);
        }
        
        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return items.get(items.size() - 1);
        }
        
        public boolean isEmpty() {
            return items.size() == 0;
        }
        
        public int size() {
            return items.size();
        }
        
        public void display() {
            System.out.println("Stack: " + items);
        }
    }
    
    // ==================== Queue ====================
    
    static class Queue {
        private List<Integer> items;
        
        Queue() {
            items = new ArrayList<>();
        }
        
        public void enqueue(int value) {
            items.add(value);
        }
        
        public int dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("Queue underflow");
            }
            return items.remove(0);
        }
        
        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            return items.get(0);
        }
        
        public boolean isEmpty() {
            return items.size() == 0;
        }
        
        public int size() {
            return items.size();
        }
        
        public void display() {
            System.out.println("Queue: " + items);
        }
    }
    
    // ==================== Binary Search Tree ====================
    
    static class TreeNode {
        int value;
        TreeNode left, right;
        
        TreeNode(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }
    
    static class BinarySearchTree {
        TreeNode root;
        
        public void insert(int value) {
            root = insertRec(root, value);
        }
        
        private TreeNode insertRec(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value);
            }
            
            if (value < node.value) {
                node.left = insertRec(node.left, value);
            } else if (value > node.value) {
                node.right = insertRec(node.right, value);
            }
            
            return node;
        }
        
        public void inorder() {
            System.out.print("InOrder: ");
            inorderRec(root);
            System.out.println();
        }
        
        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }
        
        public TreeNode search(int value) {
            return searchRec(root, value);
        }
        
        private TreeNode searchRec(TreeNode node, int value) {
            if (node == null) {
                return null;
            }
            
            if (value == node.value) {
                return node;
            } else if (value < node.value) {
                return searchRec(node.left, value);
            } else {
                return searchRec(node.right, value);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Data Structure Interview Problems ===\n");
        
        // ==================== LinkedList Demo ====================
        System.out.println("--- LinkedList ---");
        LinkedList linkedList = new LinkedList();
        linkedList.insert(10);
        linkedList.insert(20);
        linkedList.insert(30);
        linkedList.insert(40);
        linkedList.insert(50);
        linkedList.display();
        System.out.println("Length: " + linkedList.length());
        
        System.out.print("Reversed: ");
        linkedList.reverse();
        linkedList.display();
        
        System.out.println("Has Cycle: " + linkedList.hasCycle());
        
        // ==================== Stack Demo ====================
        System.out.println("\n--- Stack ---");
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.display();
        
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.display();
        System.out.println("Size: " + stack.size());
        
        // ==================== Queue Demo ====================
        System.out.println("\n--- Queue ---");
        Queue queue = new Queue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.display();
        
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.display();
        System.out.println("Size: " + queue.size());
        
        // ==================== Binary Search Tree Demo ====================
        System.out.println("\n--- Binary Search Tree ---");
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.inorder();
        
        System.out.println("Search 40: " + (bst.search(40) != null ? "Found" : "Not Found"));
        System.out.println("Search 100: " + (bst.search(100) != null ? "Found" : "Not Found"));
    }
}

