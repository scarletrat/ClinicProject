package util;

import clinic.Profile;
import clinic.Technician;

public class CircularLinkedList {
    private Node last;  // Points to the last node in the list
    private int size;

    public CircularLinkedList() {
        this.last = null;
    }

    public void insert(Node e) {

        if (last == null) {
            last = e;
            last.setNext(last);
        } else {
            e.setNext(last.getNext());
            last.setNext(e);
            last = e;
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

    public int getSize() {
        return size;
    }

    public Node getLast() {
        return last;
    }
    public void setLast(Node e){
        this.last = e;
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
}