/* ------------------------------------------------------------- */
/* PQueueTest.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;

import java.util.ArrayList;
import java.util.Random;

import queues.BinaryHeap;

public class TestBinaryHeap {

    private static final int max = 100000;

    public TestBinaryHeap() {}

    public static BinaryHeap<Integer> getDummyDataEnqueue() {
        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        h.enqueue(2);
        h.enqueue(20);            
        h.enqueue(200);
        h.enqueue(1);
        h.enqueue(10);
        h.enqueue(100);
        return h;
    }

    public static BinaryHeap<Integer> getDummyDataInsert() {
        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        h.insert(2);
        h.insert(20);      
        h.insert(200);
        h.insert(1);
        h.insert(10);
        h.insert(100);
        return h;
    }

    // E.P.
    public static void simpleTests() {
        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        System.out.println(h);
        
        h.enqueue(1);
        System.out.println(h);
        
        h.enqueue(2);
        System.out.println(h);

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            h.enqueue(r.nextInt(10));
        }
        System.out.println(h);

        while (!h.isEmpty()) {
            System.out.print(h.dequeue() + "  ");
        }
        System.out.println();
        System.out.println(h);
        System.out.println();
    }

    public static void testInsert() {

        System.out.println("--------------------------------------------------"); 
        System.out.println("testing insert() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h = getDummyDataEnqueue();        
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

    public static void testMax() {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing max() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h = getDummyDataInsert();
        System.out.println(h);

        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;

        System.out.println(h);
        System.out.println(); 
    }

    public static void testRemoveMax() {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testRemoveMax() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h = getDummyDataInsert();
        System.out.println("heap contains the following elements: "); 
        System.out.println(h);

        h.insert(max);
        assert h.size() == 7;
        assert h.max() == max;

        assert h.removeMax() == max;
        assert h.removeMax() == 200;
        assert h.removeMax() == 100;
        assert h.removeMax() == 20;
        assert h.removeMax() == 10;
        assert h.removeMax() == 2;
        assert h.removeMax() == 1;

        System.out.println(h);
        System.out.println();
    }

    public static<T> void assertArrayLists(ArrayList<T> arr, ArrayList<T> arr_expected) {
        assert arr.size() == arr_expected.size();
        for (int i = 0; i < arr.size(); i++) {
            assert (arr.get(i).equals( (T) arr_expected.get(i) ));
        }
    }

    public static void testNLargest() {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testNLargest() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h = getDummyDataInsert();
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

        System.out.println("after assert assertArrayLists");
        System.out.println("h..size() = " + h.size());
        System.out.println("h..size() = " + h.max());
        System.out.println("h() = " + h);

        // elements still in list!
        assert h.size() == 7;
        System.out.println("hsize() ok ");
        assert h.max() == max;
        System.out.println("h.max() ok ");

        System.out.println(h);
        System.out.println();
    }

    public static void testRemoveNLargest() {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testRemoveNLargest() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h = getDummyDataInsert();
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

        System.out.println(h);
        System.out.println();
    }

    public static void testMerge() {
        System.out.println("--------------------------------------------------"); 
        System.out.println("testing testMerge() "); 
        System.out.println("--------------------------------------------------"); 

        BinaryHeap<Integer> h1 = getDummyDataInsert();

        BinaryHeap<Integer> h2 = new BinaryHeap<Integer>();
        h2.enqueue(4);
        h2.enqueue(5);            
        h2.enqueue(6);

        System.out.println("heap h2.size(): " + h2.size() ); 
        System.out.println("heap h1 contains the following elements: " + h1 ); 
        System.out.println("heap h2 contains the following elements: " + h2 ); 
        
        h1.merge(h2);

        assert h1.size() == 9;
        assert h1.max() == 200;

        System.out.println();
        System.out.println("merged queues: ");
        System.out.println(); 
        System.out.println(h1);
    }
}
