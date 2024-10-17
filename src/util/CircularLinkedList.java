package util;

import clinic.Technician;

public class CircularLinkedList<E> {
    private Node<E> last;  // Points to the last node in the list
    private int size;
    private Node<E> current;

    public CircularLinkedList() {
        this.last = null;
        this.current = null;
    }

    public void insert(E e) {
        Node<E> newNode = new Node<E>(e);

        if (last == null) {
            last = newNode;
            last.setNext(last);
        } else {
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
            current = last.getNext();
        }
        size++;
    }

    public void shiftByOne() {
        if (last == null) {
            last = null;
            current = null;
        }

        current = current.getNext();
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return last == null;
    }

    public int getSize(){
        return size;
    }
    public Node<E> getCurrent(){
        return current;
    }
}
