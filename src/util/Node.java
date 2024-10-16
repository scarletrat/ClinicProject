package util;

import clinic.Technician;

public class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E e) {
        this.data = e;
        this.next = null;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return next;
    }
    public E getData(){
        return data;
    }
}