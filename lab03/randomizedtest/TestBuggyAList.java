package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        A.addLast(4);
        A.addLast(5);
        A.addLast(6);

        B.addLast(4);
        B.addLast(5);
        B.addLast(6);

        assertEquals(A.size(), B.size());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
                assertEquals(A.size(), B.size());
            }else if(operationNumber == 1){
                if (A.size() > 0 && B.size() > 0) {
                    assertEquals(A.getLast(), B.getLast());
                }
            }else if (operationNumber == 2) {
                if (A.size() > 0 && B.size() >0) {
                    assertEquals(A.removeLast(), B.removeLast());
                }
            }
        }
    }

}
