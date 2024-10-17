package util;

import clinic.Profile;
import clinic.Technician;

public class CircularLinkedList<E> {
    private Node<E> last;  // Points to the last node in the list
    private int size;

    public CircularLinkedList() {
        this.last = null;
    }

    public void insert(E e) {
        Node<E> newNode = new Node<E>(e);

        if (last == null) {
            last = newNode;
            last.setNext(last);
        }
        else{
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public void shiftByOne() {
        if (last != null) {
            last = last.getNext();
        }
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return last == null;
    }

    public int getSize(){
        return size;
    }
    public Node<E> getCurrent(){
        return last.getNext();
    }

    public void display() {
        if (last == null) {
            System.out.println("The list is empty.");
            return;
        }

        Node ptr = last.getNext(); // Start from the first node
        do {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        } while (ptr != last.getNext()); // Go around the circular list until back to start
        System.out.println();
    }
    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        // Inserting some elements
        cll.insert(2);
        cll.insert(3);
        cll.insert(4);

        // Display original list
        System.out.println("Original list:");
        cll.display(); // Expected Output: 1 2 3 4

        // Shift the list by one
        cll.shiftByOne();

        // Display list after shifting by one
        System.out.println("After shifting by one:");
        cll.display(); // Expected Output: 2 3 4 1

        // Shift the list by one again
        cll.shiftByOne();

        // Display list after another shift
        System.out.println("After shifting by one again:");
        cll.display(); // Expected Output: 3 4 1 2
    }
}
