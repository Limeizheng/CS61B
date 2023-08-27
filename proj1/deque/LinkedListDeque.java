package deque;

import java.util.Iterator;
import java.lang.Iterable;

public class LinkedListDeque<T> implements Deque<T>{

    private class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /**Creates an empty linked list deque.*/
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    /**Adds an item of type T to the front of the deque.
     * must not involve any looping or recursion. A single such operation must take “constant time”*/
    @Override
    public void addFirst(T item){
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /** Adds an item of type T to the back of the deque.
     * must not involve any looping or recursion. A single such operation must take “constant time”*/
    @Override
    public void addLast(T item){
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    /**Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque(){
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * must not involve any looping or recursion. A single such operation must take “constant time”*/
    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    /**Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * must not involve any looping or recursion. A single such operation must take “constant time”*/
    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        Node lastNode = sentinel.prev;
        T item = lastNode.item;
        lastNode.prev.next = sentinel;
        sentinel.prev = lastNode.prev;
        size -= 1;
        return item;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * must use iteration, not recursion.*/
    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        Node p = sentinel;
        while(index >= 0){
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /**Same as get, but uses recursion.*/
     public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int index){
        if(index == 0){
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    /**The deques objects we’ll make are iterable
     * so we must provide this method to return an iterator.
     * We have a collection of elements in a Deque,
     * so it makes sense that we might want to iterate over this collection
     *  implement the Iterable interface.*/
    public class LLDIterator implements Iterable<T>{
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private Node p = sentinel.next;
                public boolean hasNext() {
                    return p != sentinel;
                }
                public T next() {
                    T item = p.item;
                    p = p.next;
                    return item;
                }
            };
        }
    }

    /**Returns whether or not the parameter o is equal to the deque.
     *o is considered equal if it is a deque and if it contains the same contents
     * (as determined by the generic T’s equals method)
     * in the same order. You’ll need to use the instance of keywords for this.*/
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof LinkedListDeque)){ //The instanceof keyword in Java is used to test whether an object
                                             // (o in this case) is an instance of a specific class or an implementation of an interface.
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if(size != other.size){
            return false;
        }
        Node p = sentinel.next;
        Node q = other.sentinel.next;
        while(p != sentinel){
            if(!p.item.equals(q.item)){
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }
}

