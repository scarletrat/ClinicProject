package util;

import clinic.Technician;

public class CircularLinkedList {
    private Node last;  // Points to the last node in the list
    private int size;
    public CircularLinkedList() {
        this.last = null;
    }

    // Insert a node at the end of the list
    public void insert(Technician technician) {
        Node newNode = new Node(technician);

        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Node shiftByOne() {
        if (last == null) {
            return null;
        }

        Node current = last.next;
        current = current.next;

        return current;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return last == null;
    }
}
