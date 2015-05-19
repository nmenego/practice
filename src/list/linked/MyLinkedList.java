package list.linked;

import java.util.AbstractList;

/**
 * Simple linked list implementation.
 * 
 * @author nicomartin.enego
 *
 */
public class MyLinkedList<T> extends AbstractList<T> {

	private Node head;
	private int size;

	public MyLinkedList() {
		this.head = new Node();
		this.size = 0;
	}

	@Override
	public boolean add(T element) {
		Node nodeToAdd = new Node();
		nodeToAdd.setValue(element);
		nodeToAdd.setNext(null);

		Node currentNode = head;
		Node nextNode = currentNode.getNext();
		for (int x = 0; nextNode != null && x < this.size; x++) {
			currentNode = nextNode;
			nextNode = nextNode.getNext();
		}

		nextNode = new Node(nodeToAdd.getValue(), nodeToAdd.getNext());
		currentNode.setNext(nextNode);
		this.size++;

		return true;
	}

	@Override
	public void add(int index, T element) {
		Node nodeToAdd = new Node();
		nodeToAdd.setValue(element);
		nodeToAdd.setNext(null);

		Node currentNode = head;
		Node nextNode = currentNode.getNext();
		for (int x = 0; nextNode != null && x < index; x++) {
			currentNode = nextNode;
			nextNode = nextNode.getNext();
		}

		nodeToAdd.setNext(currentNode.getNext()); // point to next
		nextNode = new Node(nodeToAdd.getValue(), nodeToAdd.getNext());
		currentNode.setNext(nextNode); // point to newly added
		this.size++;
	}

	@Override
	public T get(int index) {
		Node currentNode = head.getNext();
		Node nextNode = currentNode.getNext();
		for (int x = 0; nextNode != null && x < index; x++) {
			currentNode = nextNode;
			nextNode = nextNode.getNext();
		}
		return currentNode.getValue();
	}

	@Override
	public T remove(int index) {
		Node prevNode = null;
		Node currentNode = head.getNext();
		Node nextNode = currentNode.getNext();
		for (int x = 0; nextNode != null && x < index; x++) {
			prevNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.getNext();
		}

		prevNode.setNext(currentNode.getNext()); // point to next
		currentNode.setNext(null);
		this.size--;
		return currentNode.getValue();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		Node currentNode = head.getNext();
		for (int x = 0; x < this.size; x++) {
			sb.append(currentNode.getValue() + ", ");
			currentNode = currentNode.getNext();
		}
		sb.append("}");

		return sb.toString();
	}

	/**
	 * This class refers to each node in the list.
	 *
	 */
	private class Node {

		private T value;
		private Node next;

		public Node() {
			this.value = null;
			this.next = null;
		}

		public Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}
}
