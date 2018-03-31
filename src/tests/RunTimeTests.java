/* ------------------------------------------------------------- */
/* PQueueTest.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl (based on Code from E.P.)             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap tests                                             */
/* ------------------------------------------------------------- */

package tests;

import queues.BinaryHeap;
import queues.QHeapQueue;
import tests.TestBinaryHeap;
import utils.CsvFile;
import java.util.Random;

public class RunTimeTests {

    private int[] cntElements = { 25, 100, 1000, 10000 };   // 100000
    Integer max = 100000;
    int iterations = 20; 
    int[] dValues = { 4, 8, 12, 16, 20, 24, 28, 32 };

    public RunTimeTests() {

    }

    public void testInsertBinaryHeap(CsvFile f) throws java.io.IOException {
        System.out.println("--------------------------------------");
        System.out.println("testInsertBinaryHeap()");
        System.out.println("--------------------------------------");

        long[] durationInsertBinaryHeap = new long[cntElements.length];
        String[] title = new String[cntElements.length];

        Integer x;
        long dur = 0, start, end; 

        Random r = new Random();

        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h = getDummyBinaryHeap(cntElements[i]);
            dur = 0;
            title[i] = Integer.toString(cntElements[i]);

            for (int j = 0; j < iterations; j++) {
                x = r.nextInt(max / 2);
                start = System.nanoTime();
                h.insert(x);
                end = System.nanoTime();
                dur += (end - start);
            }
            durationInsertBinaryHeap[i] = dur / iterations;
        }

        // f.writeStringArray(title);
        // f.writeLongArray(durationInsertBinaryHeap);
        // System.out.print("title ");
        // for (int i = 0; i < title.length; i++) {
        //     System.out.print(title[i] + " / ");
        // }
        // System.out.println();

        // System.out.println("timings ");
        // for (int i = 0; i < durationInsertBinaryHeap.length; i++) {
        //     System.out.print(durationInsertBinaryHeap[i] + "  /  ");
        // }
        // System.out.println();
        // System.out.println();



        // f.writeStringArray(title);
        // f.writeLongArray(durationInsertBinaryHeap);
        System.out.println("title: ");
        StringBuilder sb = new StringBuilder();
        sb.append("type; operation; d; "); 
        for (int i = 0; i < title.length; i++) {
            sb.append("n = " + title[i] + " ; ");
        }
        sb.append("\n");
        f.writeString(sb.toString());
        System.out.println(sb.toString());
        System.out.println();

        sb = new StringBuilder();
        System.out.println("timings ");
        sb.append(" BinaryHeap; insert() ;  na ;");
        for (int i = 0; i < durationInsertBinaryHeap.length; i++) {
            // System.out.print("d = " + dValues[i] + "    ");
            sb.append( Long.toString(durationInsertBinaryHeap[i]) + " ; ");
            // sb.append("\n");
            // System.out.println();   
        }
        sb.append("\n");
        // System.out.println();   
        // System.out.println();   
        System.out.println(sb.toString());  

        f.writeString(sb.toString());
    }

    public void testInsertQHeap(CsvFile f) throws java.io.IOException {
        System.out.println("--------------------------------------");
        System.out.println("testInsertQHeap()");
        System.out.println("--------------------------------------");
        // int[] cntElements = { 10, 100, 1000, 10000 };
        
        long[][] durationInsertQHeap = new long[dValues.length][cntElements.length];
        String[] title = new String[cntElements.length];

        Integer x;
        long dur = 0, start, end; 

        Random r = new Random();

        for (int i = 0; i < cntElements.length; i++) {
            title[i] = "n = " + Integer.toString(cntElements[i]);
        }

        QHeapQueue<Integer> h = new QHeapQueue<Integer> (1);

        for (int k = 0; k < dValues.length; k++) {
            for (int i = 0; i < cntElements.length; i++) {
            
                h = getDummyQHeap(cntElements[i], dValues[k]);        

                dur = 0;

                for (int j = 0; j < iterations; j++) {
                    x = r.nextInt(max / 2);
                    start = System.nanoTime();
                    h.insert(x);
                    end = System.nanoTime();
                    dur += (end - start);
                }
                // if (k == 2) {
                //     System.out.println("d = " + dValues[k] +  "     iterations = " + iterations + "  dur =  " +dur);
                // }
                durationInsertQHeap[k][i] = dur / iterations;
            }
        }

        // f.writeStringArray(title);
        // f.writeLongArray(durationInsertBinaryHeap);
        System.out.println("title: ");
        StringBuilder sb = new StringBuilder();
        // sb.append("type; operation;"); 
        // for (int i = 0; i < title.length; i++) {
        //     sb.append(title[i] + " ; ");
        // }
        // sb.append("\n");
        // f.writeString(sb.toString());
        // System.out.println(sb.toString());
        // System.out.println();

        sb = new StringBuilder();
        System.out.println("timings ");
        for (int i = 0; i < durationInsertQHeap.length; i++) {
            // System.out.print("d = " + dValues[i] + "    ");
            sb.append(" QHeap; insert();    " + Integer.toString(dValues[i] ) + " ; ");
            for (int j = 0; j < durationInsertQHeap[i].length; j++) {
                // System.out.print(durationInsertQHeap[i][j] + "  /  ");
                sb.append(Long.toString(durationInsertQHeap[i][j]) + " ; ");
            }
            sb.append("\n");
            // System.out.println();   
        }
        // System.out.println();   
        // System.out.println();   
        System.out.println(sb.toString());  

        f.writeString(sb.toString());
    }

    private BinaryHeap<Integer> getDummyBinaryHeap(int n) {
        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            h.insert(r.nextInt(max));
        }
        return h;
    }

    private QHeapQueue<Integer> getDummyQHeap(int n, int d) {
        QHeapQueue<Integer> h = new QHeapQueue<Integer>(d);
        
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            h.insert(r.nextInt(max));
        }
        return h;
    }
}
