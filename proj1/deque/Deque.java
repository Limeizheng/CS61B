package deque;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public default boolean isEmpty() {return size() == 0;}
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    /**Returns whether or not the parameter o is equal to the deque.
     *o is considered equal if it is a deque and if it contains the same contents
     * (as determined by the generic T’s equals method)
     * in the same order. You’ll need to use the instance of keywords for this.*/
    public boolean equals(Object o);
}
