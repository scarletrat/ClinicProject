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
            last.setNext(last);
        } else {
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public Node shiftByOne() {
        if (last == null) {
            return null;
        }

        Node current = last.getNext();
        current = current.getNext();

        return current;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return last == null;
    }

    public int getSize(){
        return size;
    }
}
