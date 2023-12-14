package application;

public class LinkedList<T extends Comparable<T>> {

	private Node<T> head;

	public Node<T> getHead() {
		return head;
	}

	public void display() {
		Node<T> curr = head;
		System.out.print("Head -> ");
		while (curr != null) {
			System.out.print(curr.getData() + " -> ");
			curr = curr.getNext();
		}
		System.out.println("Null");

	}

//	public void insertAtHead(T data) {
//		Node<T> newNode = new Node<T>(data);
//		newNode.setNext(head);
//		head = newNode;
//	}

	public void printAll() {
		System.out.print("Head->  ");
		Node<T> current = this.head;
		while (current != null) {
			System.out.print(current.toString() + " -> ");
			current = current.getNext();
		}
		System.out.println();
	}

//	public void inserAtEnd(T data) {
//		Node<T> newNode = new Node<T>(data);
//
//		if (head == null)
//			head = newNode;
//		else {
//			Node<T> current = this.head;
//			while (current.getNext() != null)
//				current = current.getNext();
//			current.setNext(newNode);
//		}
//	}

	public int getLength() {
		int c = 0;
		Node current = this.head;
		while (current != null) {
			c++;
			current = current.getNext();
		}
		return c;
	}

//	public void insertSorted(T data) {
//		Node<T> newNode = new Node<T>(data);
//
//		if (head == null || head.getData().compareTo(data) > 0) {
//			
//			newNode.setNext(head);
//			head = newNode;
//			return;
//		}
//
//		Node<T> current = head;
//		while (current.getNext() != null && current.getNext().getData().compareTo(data) < 0) {
//			
//			current = current.getNext();
//		}
//		newNode.setNext(current.getNext());
//		current.setNext(newNode);
//	}

	public void insertSorted(T data)

	{
		Node<T> newNode = new Node<T>(data);
		Node<T> current = head;
		Node<T> previous = null;

		while (current != null && current.getData().compareTo(data) < 0) {
			previous = current;
			current = current.getNext();
		}

		if (previous == null) {
			head = newNode;
		} else {
			previous.setNext(newNode);
		}

		newNode.setNext(current);
	}

	public void deleteSorted(T data) {
		if (head == null) {
			return;
		}

		if (head.getData().equals(data)) {
			head = head.getNext();
			return;
		}

		Node<T> current = head;
		while (current.getNext() != null) {
			if (current.getNext().getData().equals(data)) {

				current.setNext(current.getNext().getNext());
				return;

			} else if (current.getNext().getData().compareTo(data) > 0) {
				return;
			}
			current = current.getNext();
		}
	}

	public T searchSorted(T data) {
		Node<T> current = head;
		while (current != null) {

			if (current.getData().equals(data)) {
				return current.getData();

			} else if (current.getData().compareTo(data) > 0) {
				return null;
			}
			current = current.getNext();
		}
		return null;
	}

}