package util;


import java.util.Iterator;

/**
 * Manages array of appointments by sorting, adding elements, removing elements,
 * and adjusting the size of the array.
 * @author Christopher Lee, Gordon Lin modified Sept. 30, 2024.
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;
    private static final int NOT_FOUND = -1;

    public List() {
        objects = (E[]) new Object[4];
        size=0;
    }

    private int find(E e){
        for(int i = 0; i<size; i++){
            if(objects[i].equals(e)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow(){
        E[] newArray = (E[]) new Object[size+4];
        for(int i = 0; i<size; i++){
            newArray[i] = objects[i];
        }
        objects = newArray;
    }

    public boolean contains(E e){
        return find(e) != NOT_FOUND;
    }

    public void add(E e){
        if(size == objects.length){
            grow();
        }
        objects[size] = e;
        size++;
    }

    public void remove(E e){
        int index = indexOf(e);
        if(index != NOT_FOUND){
            for(int i= index; i<size-1 ; i++){
                objects[i] = objects[i+1];
            }
            //set last element to null
            objects[size-1] = null;
            size = size -1;
        }
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>();
    }

    //return the object at the index
    public E get(int index){
        if(index <size) {
            return objects[index];
        }
        return null;
    }

    //put object e on the index
    public void set(int index, E e){
        if (index >= 0 && index < size) {
            objects[index] = e; // Set the element at the specified index
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public int indexOf(E e){
        return find(e);
    }

    private class ListIterator<E> implements Iterator<E>{
        private int currentIndex = 0;
        //return false if its empty or end of list
        public boolean hasNext(){
            return currentIndex < size;
        }
        //return next object in the list
        public E next(){
            if (!hasNext()){
                throw new java.util.NoSuchElementException("No more elements");
            }
            return (E) objects[currentIndex++];
        }
    }
}