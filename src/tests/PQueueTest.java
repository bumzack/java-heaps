/* ------------------------------------------------------------- */
/* PQueueTest.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;


import utils.CsvFile;

// import tests.TestBinaryHeap;
import queues.QHeapQueue;

public class PQueueTest {

    public static void main(String[] args) {
        TestBinaryHeap tBH = new TestBinaryHeap();
        tBH.simpleTests();
        tBH.testInsert();
        tBH.testMax();
        tBH.testRemoveMax();
        // tBH.testNLargest();
        tBH.testRemoveNLargest();
        tBH.testMerge();

        TestQHeapQueue qH = new TestQHeapQueue();
        for (int i = 2; i < 10; i++) {
            System.out.println("--------------------------------------------------"); 
            System.out.println("--------------------------------------------------"); 
            System.out.println("testing QHeapQueue()  with d = " + i); 
            System.out.println("--------------------------------------------------"); 
            System.out.println("--------------------------------------------------"); 

            qH.testInsert(i);
            qH.testMax(i);
            qH.testRemoveMax(i);
            // qH.testNLargest(i);
            qH.testRemoveNLargest(i);
            qH.testMerge(i);
        }

        // runtime tests
        // RunTimeTests RT = new RunTimeTests();
        // CsvFile f = new CsvFile("timings_insert.csv");
        // RT.testBinaryHeap(f);
        // RT.testQHeap(f);
        // f.closeFile();        
    }
}
