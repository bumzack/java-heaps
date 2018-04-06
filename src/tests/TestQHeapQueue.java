/* ------------------------------------------------------------- */
/* TestQHeapQueue.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;

import java.util.ArrayList;
import java.util.Random;

import queues.QHeapQueue;

public class TestQHeapQueue {

    private static final int max = 100000;

    public TestQHeapQueue() {}

    public static QHeapQueue<Integer> getDummyDataInsert(int d) {
        QHeapQueue<Integer> h = new QHeapQueue<Integer>(d);
        h.insert(2);
        h.insert(20);      
        h.insert(200);
        h.insert(1);
        h.insert(10);
        h.insert(100);
        return h;
    }

    public static void testInsert(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing insert() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h = getDummyDataInsert(d);        
        System.out.println("heap contains the following elements: "); 
        System.out.println(h);

        assert h.size() == 6;
        System.out.println("insert 111");
        h.insert(111);
        assert h.size() == 7;
        System.out.println("insert done");

        System.out.println(h);
        System.out.println();
    }

    public static void testMax(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing max() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h = getDummyDataInsert(d);
        System.out.println(h);

        
        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;

        System.out.println(h);
        System.out.println();
    }

    public static void testRemoveMax(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testRemoveMax() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h = getDummyDataInsert(d);
        System.out.println("heap contains the following elements: "); 

        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;

        System.out.println(h);

        assert h.removeMax() == max;
        assert h.removeMax() == 200;
        assert h.removeMax() == 100;
        assert h.removeMax() == 20;
        assert h.removeMax() == 10;
        assert h.removeMax() == 2;
        assert h.removeMax() == 1;

        assert h.isEmpty();

        System.out.println(h);
        System.out.println();
        System.out.println(); 
    }

    public static<T> void assertArrayLists(ArrayList<T> arr, ArrayList<T> arr_expected) {
        assert arr.size() == arr_expected.size();
        for (int i = 0; i < arr.size(); i++) {
            assert arr.get(i).equals(arr_expected.get(i));
        }
    }

    public static void testNLargest(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testNLargest() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h = getDummyDataInsert(d);
        System.out.println("heap contains the following elements: "); 

        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;
        System.out.println(h);

        ArrayList<Integer> n3_expected = new ArrayList<Integer>(3);
        n3_expected.add(max);        
        n3_expected.add(200);
        n3_expected.add(100);

        ArrayList<Integer> n3 = h.nLargest(3);
        System.out.println("n3: " + n3);
        System.out.println("n3_expected: " + n3_expected);
        
        assertArrayLists(n3, n3_expected);

        // elements still in list!
        assert h.size() == 7;
        assert h.max() == max;

        System.out.println(h);
        System.out.println();
    }

    public static void testRemoveNLargest(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testRemoveNLargest() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h = getDummyDataInsert(d);
        System.out.println("heap contains the following elements: "); 
        
        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;
        System.out.println(h);

        ArrayList<Integer> n4_expected = new ArrayList<Integer>(4);
        n4_expected.add(max);        
        n4_expected.add(200);
        n4_expected.add(100);
        n4_expected.add(20);

        ArrayList<Integer> n4 = h.removeNLargest(4);
        System.out.println("n4: " + n4);
        System.out.println("n4_expected: " + n4_expected);
        assertArrayLists(n4, n4_expected);

        // elements have been removed
        assert h.size() == 3;
        assert h.max() == 10;

        System.out.println();
        System.out.println(); 
    }

    public static void testMerge(int d) {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testMerge() "); 
        System.out.println("--------------------------------------------------"); 

        QHeapQueue<Integer> h1 = getDummyDataInsert(d);

        QHeapQueue<Integer> h2 = new QHeapQueue<Integer>(d);
        h2.insert(4);
        h2.insert(5);            
        h2.insert(6);

        System.out.println("heap h2.size(): " + h2.size() ); 
        
        System.out.println("heap h1 contains the following elements: " + h1 ); 
        System.out.println("heap h2 contains the following elements: " + h2 ); 
        
        h1.merge(h2);

        assert h1.size() == 9;
        assert h1.max() == 200;

        System.out.println();
        System.out.println("merged queues contains the following elements: ");
        System.out.println(h1);
        System.out.println(); 
    }
}
