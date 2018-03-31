/* ------------------------------------------------------------- */
/* PQueueTest.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;

import java.util.Random;
import queues.BinaryHeap;

public class PQueueTest {

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
            System.out.println(h.dequeue());
        }
        System.out.println(h);
    }

    public static void testInsert() {

        System.out.print("--------------------------------------------------"); 
        System.out.print("testing insert() "); 
        System.out.print("--------------------------------------------------"); 

        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        System.out.println(h);
        
        h.enqueue(2);
        h.enqueue(20);            
        h.enqueue(200);
        h.enqueue(1);
        h.enqueue(10);
        h.enqueue(100);
        System.out.print("heap contains the following elements: "); 
        System.out.println(h);

        System.out.println("insert 111");
        h.insert(111);
        System.out.println("insert done");

        System.out.println(h);
        System.out.println();
        System.out.println(); 
    }


    public static void testInsertEnqueue() {

        System.out.print("--------------------------------------------------"); 
        System.out.print("testInsertEnqueue() "); 
        System.out.print("--------------------------------------------------"); 

        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        h.enqueue(2);
        h.enqueue(20);            
        h.enqueue(200);
        h.enqueue(1);
        h.enqueue(10);
        h.enqueue(100);
        h.enqueue(111);

        System.out.println(h);
        System.out.println();
        System.out.println(); 
    }

    public static void main(String[] args) {
        // simpleTests();
        testInsert();
        testInsertEnqueue();
    }
}
