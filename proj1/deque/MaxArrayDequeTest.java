package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;



public class MaxArrayDequeTest {

    public class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    Comparator<Integer> TestComp = new IntegerComparator();
    MaxArrayDeque<Integer> TestMax = new MaxArrayDeque<>(TestComp);



    @Test
    public void Test()
    {
        TestMax.addLast(0);
        TestMax.addLast(10);
        TestMax.addLast(100);
        TestMax.addLast(20);
        assertEquals(100, (int) TestMax.max());
    }


}