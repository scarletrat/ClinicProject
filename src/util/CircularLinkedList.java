package util;

import clinic.Profile;
import clinic.Technician;
/**
 * This class represents a circular linked list. uses nodes and pointers from the Node class
 * @author Gordon Lin, Christopher Lee modified Oct. 16, 2024
 */
public class CircularLinkedList {
    private Node last;  // Points to the last node in the list
    private int size;

    /**
     * Constructor sets last to null
     */
    public CircularLinkedList() {
        this.last = null;
    }

    /**
     * inserts node last spot of the list
      * @param e node to be inserted
     */
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

    /**
     * shifts the pointer to the next node in the list
     */
    public void shiftByOne() {
        if (last != null) {
            last = last.getNext();
        }
    }

    /**
     * checks if the linked list has no nodes
      * @return true if last is null, false otherwise
     */
    public boolean isEmpty() {
        return last == null;
    }

    /**
     * returns size of linked list
     * @return size instance variable
     */
    public int getSize() {
        return size;
    }

    /**
     * returns the last node in the linked list
     * @return last instance variable
     */
    public Node getLast() {
        return last;
    }

    /**
     * sets the last node in the list to parameter
     * @param e parameter to be set to last node
     */
    public void setLast(Node e){
        this.last = e;
    }
}