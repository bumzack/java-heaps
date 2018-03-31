/* ------------------------------------------------------------- */
/* PQueueTest.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;

import tests.TestBinaryHeap;

public class PQueueTest {

   
    public static void main(String[] args) {
        TestBinaryHeap tBH = new TestBinaryHeap();
        tBH.simpleTests();
        tBH.testInsert();
        tBH.testMax();
        tBH.testRemoveMax();
        tBH.testNLargest();
        tBH.testRemoveNLargest();
        tBH.testMerge();

        // TestQHeapQueue qH = new TestQHeapQueue();
        // qH.testInsert(2);
        // qH.testMax(2);
        // qH.testRemoveMax(2);
        // qH.testNLargest(2);
        // qH.testRemoveNLargest(2);
        // qH.testMerge(2);
    }
}
