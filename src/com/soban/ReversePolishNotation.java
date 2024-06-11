package com.soban;

/**
 * @author Soban
 * date 30/04/2024
 * Reverse Polish Notation (RPN) calculator using stacks and linked lists.
 * Supports basic arithmetic operations: addition (+), subtraction (-), multiplication (*), and division (/).
 */

import java.util.Scanner;

public class ReversePolishNotation {
	/**
	 * Main method
	 * @param args Command line arguments
	 * @throws Exception if an error occurs during calculation or input handling
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a reverse polish notation expression: ");
		String rpn = input.nextLine();

		ReversePolishNotation rpnCalculator = new ReversePolishNotation();
		System.out.println("Result: " + rpnCalculator.RPN(rpn));

	}

	/**
	 * Evaluates a Reverse Polish Notation expression.
	 * @param rpn The input RPN expression as a String
	 * @return The result of the RPN calculation
	 * @throws Exception if there are errors in the RPN expression or during calculation
	 */
	public static String RPN(String rpn) throws Exception {
		MyStack stack = new MyStack();

		for (int i = 0; i < rpn.length(); i++) {
			char c = rpn.charAt(i);
			try {
				if (Character.isDigit(c)) {
					stack.push(String.valueOf(c));
				} else if (c == '+') {
					int a = Integer.parseInt(stack.pop());
					int b = Integer.parseInt(stack.pop());
					stack.push(String.valueOf(a + b));// Perform addition
				} else if (c == '-') {
					int a = Integer.parseInt(stack.pop());
					int b = Integer.parseInt(stack.pop());
					stack.push(String.valueOf(b - a)); // Perform subtraction
				} else if (c == '*') {
					int a = Integer.parseInt(stack.pop());
					int b = Integer.parseInt(stack.pop());
					stack.push(String.valueOf(b * a)); // Perform multiplication
				} else if (c == '/') {
					int a = Integer.parseInt(stack.pop());
					int b = Integer.parseInt(stack.pop());
					stack.push(String.valueOf(b / a)); // Perform division
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return stack.pop();

	}

	/**
	 * Represents a stack data structure using linkedlist
	 */
	static class MyStack {
		private MyLinkedList list;
		
		/**
		 * Constructs a new stack with an empty linked list
		 */
		public MyStack() {
			this.list = new MyLinkedList();
		}
		
		/**
		 * Pushes a new element onto the top of the stack.
		 * @param value The value to push onto the stack
		 */
		public void push(String value) {
			list.addToFront(value); // Add value to the front of the list
		}
		
		/**
		 * Pops and returns the top element from the stack.
		 * @return The value "popped" from the stack
		 * @throws Exception if the stack is empty and cannot pop
		 */
		public String pop() throws Exception {
			String front = list.lookAtFront();
			list.removeFromFront(); // Remove and return value from the front of the list
			return front;

		}
		
		/**
		 * Returns the top element of the stack without removing it.
		 * @return The value at the top of the stack
		 * @throws Exception if the stack is empty and cannot peek
		 */
		public String peek() throws Exception {
			return list.lookAtFront(); // Return value from the front of the list without removing

		}
		
		/**
		 * Returns a string representation of this object.
		 */
		public String toString() {
			return list.toString();
		}
	}
	
	/**
	 * Represents a linked list data structure for the stack implementation.
	 */
	static class MyLinkedList {
		public Node head;
		private int size;
		
		/**
		 * Constructs an empty linked list.
		 */
		public MyLinkedList() {
			this.head = null;
			this.size = 0;
		}
		
		/**
		 * Checks whether the linked list is empty.
		 * @return true if the linked list is empty, false otherwise. 
		 */
		public boolean isEmpty() {
			return this.head == null;
		}
		
		/**
		 *  Adds a new node with the specified value to the front of the linked list.
		 * @param value The value to add to the front of the list
		 */
		public void addToFront(String value) {
			Node node = new Node(value);
			this.size ++;

			if (this.isEmpty()) {
				this.head = node;
				return;
			}
			
			Node oldHead = head;
			node.setNext(oldHead);
			this.head = node;

		}
		
		/**
		 * Returns the value at the front of the linked list without removing it.
		 * @return The value at the front of the list
		 * @throws Exception if the list is empty and cannot look at the front  
		 */
		public String lookAtFront() throws Exception {
			if (isEmpty()) {
				throw new Exception("Cannot look at the front of an empty list.");
			}

			return this.head.value;
		}
		
		/**
		 * Removes and returns the value at the front of the linked list.
		 */
		public void removeFromFront() {
			if (!isEmpty()) {
				this.size -= 1;
				this.head = this.head.next;
			}
		}
		
		/**
		 * Adds the value to the back of the linked list slowly
		 * @param value The value to add the back of the list
		 */
		public void addToBackSlow(String value) {
			
			// Create our new node
			Node node = new Node(value);
			this.size += 1;

			// Handle empty case
			if (isEmpty()) {
				this.head = node;
				return;
			}

			// We need to find the back of the list
			// DONT MESS UP OUR LIST
			Node curr = this.head;
			while (curr.next != null) {
				curr = curr.next;
			}

			// Add our new node to the backs next
			curr.next = node;
		}
		
		/**
		 * Removes and returns the value at the back of the linked list
		 */
		public void removeFromBackSlow() {
			if (!isEmpty()) {
				this.size -= 1;

				// Check if there is only on element in the list
				if (this.head.next == null) {
					// Empty the list
					this.head = null;
					return;
				}

				// DONT MESS UP THE LIST
				Node curr = head;
				while (curr.next.next != null) {
					curr = curr.next;
				}
				curr.next = null;
			}
		}
		
		/**
		 * Looks at the value at the back of the linked list and returns it
		 * @return The current value of the list
		 * @throws Exception if the list is empty and cannot look at the back.
		 */
		public String lookAtBack() throws Exception {
			if (isEmpty()) {
				throw new Exception("Cannot look at the back of an empty list.");
			}

			// Go to the back of the list
			// DONT MESS UP THE LIST
			Node curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			return curr.value;

		}
		
		/**
		 * Returns the size of the linked list
		 * @return The number of elements in the linked list
		 */
		public int size() {
			return size;
		}
		
		/**
		 * Returns a string representation of this object.
		 */
		@Override
		public String toString() {
			if (this.isEmpty()) {
				return "[]";
			}

			String listRep = "[";

			// "Loop" over every element in the list
			// DON'T MESS UP OUR LIST
			Node curr = head;
			while (curr.next != null) {
				
				// Add the current value to the String
				listRep += curr + ", ";
				
				// Move up the list
				curr = curr.next;
			}
			
			// Right now, curr is at the LAST value of the list
			listRep += curr;

			listRep += "]";
			return listRep;

		}
		
		/**
		 * Represents a node in the linked list. 
		 */
		
		private class Node {
			String value;
			Node next;
			
			/**
			 * Constructs a new node with the specified value.
			 * @param value The value of the node
			 */
			public Node(String value) {
				this.value = value;
				this.next = null;
			}
			
			/**
			 * Sets the next node in the linked list.
			 * @param next The next node to set
			 */
			public void setNext(Node next) {
				this.next = next;
			}
			
			/**
			 * Returns a string representation of this object.
			 */
			@Override
			public String toString() {
				return String.valueOf(value);
				// return value + "";
			}
		}
	}
}
