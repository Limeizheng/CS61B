package deque;

import org.junit.Test;

import static org.junit.Assert.*;

/* Performs some basic array deque tests. */
public class ArrayDequeTest {



    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertTrue("A newly initialized aDeque should be empty", ad.isEmpty());
        ad.addFirst(0);

        assertFalse("ad1 should now contain 1 item", ad.isEmpty());

    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> cd = new ArrayDeque<>();
        cd.addFirst(1);
        cd.addFirst(2);
        cd.removeFirst();
        assertEquals(1, (int) cd.get(0));
        cd.addLast(3);
        cd.addLast(4);
        cd.addLast(5);
        cd.removeLast();
        assertEquals(4, (int) cd.get(2));
        cd.addLast(6);
        cd.addLast(7);
        cd.addLast(8);
        cd.addLast(9);
        cd.addLast(10);
        cd.addLast(11);
        cd.addLast(12);
        cd.addLast(13);
        assertEquals(11, (int) cd.get(8));
        cd.printDeque();
    }


    @Test
    public void multipleParamsTest() {
        ArrayDeque<String> str_deque = new ArrayDeque<>();
        assertTrue(str_deque.isEmpty());
        str_deque.addFirst("Hello!");
        assertFalse(str_deque.isEmpty());
        str_deque.addLast("Hi!");
        str_deque.addLast("How are you?");
        str_deque.printDeque();
    }

    @Test
    public void emptyNullReturn() {
        ArrayDeque<Integer> ef = new ArrayDeque<>();
        assertNull(ef.removeFirst());
        assertNull(ef.removeLast());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> gh = new ArrayDeque<>();
        gh = new ArrayDeque<>();
        gh.addLast(1);
        gh.addLast(2);
        gh.addLast(3);
        gh.addLast(4);
        gh.addLast(5);
        gh.addLast(6);
        gh.addLast(7);
        gh.addLast(8);
        gh.addLast(9);
        gh.addLast(10);
        assertEquals(10, (int) gh.get(9));
        assertEquals(10 , gh.size());
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> mn = new ArrayDeque<>();
        for(int i=0; i<20; ++i){
            mn.addFirst(i);
            mn.printDeque();
        }
        for(int i=0; i<20; ++i){
            mn.removeFirst();
            mn.printDeque();
        }
        assertEquals(0, mn.size());
    }


}