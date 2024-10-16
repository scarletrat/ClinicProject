package util;

import clinic.Technician;

public class Node {
    Technician data;
    Node next;

    public Node(Technician technician) {
        this.data = technician;
        this.next = null;
    }
}