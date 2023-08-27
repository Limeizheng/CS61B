package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    /**creates a MaxArrayDeque with the given Comparator.*/
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comp = c;
    }

    /**returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null*/
    public T max(){
        return max(this.comp);
    }

    /**returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c){
        if(size == 0){
            return null;
        }
        T maxItem = items[head + 1];
        for(int i = head + 1; i < tail; i++){
            if(c.compare(maxItem, items[i]) < 0){
                maxItem = items[i];
            }
        }
        return maxItem;
    }
}
