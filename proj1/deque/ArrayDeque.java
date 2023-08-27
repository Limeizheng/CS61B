package deque;

import java.util.Iterator;
import java.lang.Iterable;
public class ArrayDeque<T> implements Deque<T>{
    public T[] items;
    public int size;
    public int head;
    public int tail;

    /** head is the index of the first item in the deque
     * tail is the index one past the last item in the deque
     * size is the number of items in the deque
     * */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        head = 0;/*head should point to the beginning of the array*/
        tail = 1;/*tail should point to the next available index after the last element*/
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldSize = size;
        int start = (head + 1) % items.length;

        for (int i = 0; i < oldSize; i++) {
            a[i] = items[start];
            start = (start + 1) % items.length;
        }

        items = a;
        head = capacity - 1;//head should point to the beginning of the array
        tail = oldSize;//tail should point to the next available index after the last element
    }

    @Override
    public void addFirst(T item){
        items[head] = item;
        head = (head - 1 + items.length) % items.length;
        size++;
        if(size == items.length){
            resize(size * 2);
        }
    }

    @Override
    public void addLast(T item){
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
        if(size == items.length){
            resize(size * 2);
        }
    }

    @Override
    public int size(){
        return size;
    }

    /**Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque(){
        if(size == 0){
            return;
        }
        /*if head < tail, print from head + 1 to tail - 1
         * if head > tail, print from head + 1 to the end of the array, then print from the beginning of the array to tail - 1
         * */
        if(head < tail){
            for(int i = head + 1; i < tail; i++){
                System.out.print(items[i] + " ");
            }
        }else{
            for(int i = head + 1; i < items.length; i++){
                System.out.print(items[i] + " ");
            }
            for(int i = 0; i < tail; i++){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T item = items[(head + 1) % items.length];//head + 1 is the index of the first item
        items[(head + 1) % items.length] = null;
        head = (head + 1) % items.length;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T item = items[(tail - 1 + items.length) % items.length];//tail - 1 is the index of the last item
        items[(tail - 1 + items.length) % items.length] = null;
        tail = (tail - 1 + items.length) % items.length;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[(head + 1 + index) % items.length];
    }

    /**The deques objects we’ll make are iterable
     * so we must provide this method to return an iterator.
     * We have a collection of elements in a Deque,
     * so it makes sense that we might want to iterate over this collection
     *  implement the Iterable interface.*/
    public class ADIterator implements Iterable<T>{
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int p = head + 1;
                public boolean hasNext() {
                    return p != tail;
                }
                public T next() {
                    T item = items[p];
                    p = (p + 1) % items.length;
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
        if(!(o instanceof ArrayDeque)){
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if(size != other.size){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(!items[(head + 1 + i) % items.length].equals(other.items[(other.head + 1 + i) % other.items.length])){
                return false;
            }
        }
        return true;
    }
}



