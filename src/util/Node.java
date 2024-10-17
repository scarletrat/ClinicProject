package util;

import clinic.Technician;
/**
 * This class represents a node. The node has a pointer to the next node and Technician data
 * @author Gordon Lin, Christopher Lee modified Oct. 16, 2024
 */
public class Node {
    private Technician data;
    private Node next;

    /**
     * constructor sets instance variables to null
     */
    public Node(){
        this.data = null;
        this.next = null;
    }

    /**
     * Constructor sets instance variables to parameters
     * @param technician technician object
     */
    public Node(Technician technician) {
        this.data = technician;
        this.next = null;
    }

    /**
     * sets node pointer to parameter
     * @param next parameter to set node next pointer to
     */
    public void setNext(Node next){
        this.next = next;
    }

    /**
     * returns the node that this node is pointing to
     * @return next instance variable
     */
    public Node getNext(){
        return next;
    }

    /**
     * returns data in this node
     * @return data instance variable
     */
    public Technician getData(){
        return data;
    }
}