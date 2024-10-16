package util;

import clinic.Technician;

public class Node {
    private Technician data;
    private Node next;

    public Node(Technician technician) {
        this.data = technician;
        this.next = null;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return next;
    }
    public Technician getData(){
        return data;
    }
}