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
import queues.QHeapQueue;
import tests.TestBinaryHeap;
import utils.CsvFile;

public class RunTimeTests {

    private int[] cntElements = { 25, 100, 1000, 10000, 100000 };   // 100000
    Integer max = 100000;
    int iterations = 20; 
    int[] dValues = { 4, 8, 12, 16, 20, 24, 28, 32 };

    int nLargest = 20;

    public RunTimeTests() {
        // nothing to   
    }

    public void testBinaryHeap(CsvFile f)   {       // throws IllegalStateException
        System.out.println("--------------------------------------");
        System.out.println("testBinaryHeap()");
        System.out.println("--------------------------------------");

        long[] durationInsert = new long[cntElements.length];
        long[] durationMax = new long[cntElements.length];
        long[] durationRemoveMax = new long[cntElements.length];
        long[] durationNLargest = new long[cntElements.length];
        long[] durationRemoveNLargest = new long[cntElements.length];
        long[] durationHeapify = new long[cntElements.length];

        String[] title = new String[cntElements.length];

        Integer x;
        long dur = 0, start, end; 

        // System.out.println("--------------------------------------");
        // System.out.println("test insert()");
        // System.out.println("--------------------------------------");

        // Random r = new Random();

        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h = getDummyBinaryHeap(cntElements[i]);
        //     dur = 0;
        //     title[i] = Integer.toString(cntElements[i]);

        //     for (int j = 0; j < iterations; j++) {
        //         x = r.nextInt(max);
        //         start = System.nanoTime();
        //         h.insert(x);
        //         end = System.nanoTime();
        //         dur += (end - start);
        //     }
        //     durationInsert[i] = dur / iterations;
        // }

        // System.out.println("--------------------------------------");
        // System.out.println("test max()");
        // System.out.println("--------------------------------------");
        
        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h = getDummyBinaryHeap(cntElements[i]);
        //     dur = 0;
        //     // title[i] = Integer.toString(cntElements[i]);
        //     for (int j = 0; j < iterations; j++) {

        //         start = System.nanoTime();
        //         x = h.max();
        //         end = System.nanoTime();
        //         dur += (end - start);
        //     }
        //     durationMax[i] = dur / iterations;
        // }

        // System.out.println("--------------------------------------");
        // System.out.println("test removeMax()");
        // System.out.println("--------------------------------------");
        
        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h;
        //     dur = 0;
        //     // title[i] = Integer.toString(cntElements[i]);
        //     for (int j = 0; j < iterations; j++) {
        //         h = getDummyBinaryHeap(cntElements[i]);

        //         start = System.nanoTime();
        //         x = h.removeMax();
        //         end = System.nanoTime();
        //         dur += (end - start);
        //     }
        //     durationRemoveMax[i] = dur / iterations;
        // }

        // System.out.println("--------------------------------------");
        // System.out.println("test nLargest()");
        // System.out.println("--------------------------------------");
        
        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h;
        //     dur = 0;
        //     ArrayList<Integer> res; 
        //     // title[i] = Integer.toString(cntElements[i]);
        //     for (int j = 0; j < iterations; j++) {
        //         h = getDummyBinaryHeap(cntElements[i]);

        //         start = System.nanoTime();
        //         res = h.nLargest(nLargest);
        //         end = System.nanoTime();
        //         dur += (end - start);
        //     }
        //     durationNLargest[i] = dur / iterations;
        // }
        
        // System.out.println("--------------------------------------");
        // System.out.println("test removeNLargest()");
        // System.out.println("--------------------------------------");
        
        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h;
        //     dur = 0;
        //     ArrayList<Integer> res; 
        //     // title[i] = Integer.toString(cntElements[i]);
        //     for (int j = 0; j < iterations; j++) {
        //         h = getDummyBinaryHeap(cntElements[i]);

        //         start = System.nanoTime();
        //         res = h.removeNLargest(nLargest);
        //         end = System.nanoTime();
        //         dur += (end - start);
        //     }
        //     durationRemoveNLargest[i] = dur / iterations;
        // }

        System.out.println("--------------------------------------");
        System.out.println("test heapify()");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h;
            dur = 0;
            ArrayList<Integer> res; 
            // title[i] = Integer.toString(cntElements[i]);
            for (int j = 0; j < iterations; j++) {
                h = getDummyBinaryHeapUnsorted(cntElements[i]);
                
                start = System.nanoTime();
                h.heapify(0);
                end = System.nanoTime();

                assert h.isHeap();
                dur += (end - start);
            }
            durationHeapify[i] = dur / iterations;
        }

        // write data into CSV file
        // System.out.println("title: ");
        StringBuilder sb = new StringBuilder();
        sb.append("type; operation; d; "); 
        for (int i = 0; i < title.length; i++) {
            sb.append("n = " + title[i] + " ; ");
        }
        sb.append("\n");
        f.writeString(sb.toString());
        // System.out.println(sb.toString());
        // System.out.println();

        StringBuilder sbInsert = new StringBuilder();
        StringBuilder sbMax = new StringBuilder();
        StringBuilder sbRemoveMax = new StringBuilder();
        StringBuilder sbNLargest = new StringBuilder();
        StringBuilder sbRemoveNLargest = new StringBuilder();
        StringBuilder sbHeapify = new StringBuilder();

        System.out.println("timings ");
        sbInsert.append(" BinaryHeap; insert() ;  na ;");
        sbMax.append(" BinaryHeap; max() ;  na ;");
        sbRemoveMax.append(" BinaryHeap; removeMax() ;  na ;");
        sbNLargest.append(" BinaryHeap; nLargest() ;  na ;");
        sbRemoveNLargest.append(" BinaryHeap; removeNLargest() ;  na ;");
        sbHeapify.append(" BinaryHeap; heapify() ;  na ;");
        for (int i = 0; i < durationInsert.length; i++) {
            sbInsert.append( Long.toString(durationInsert[i]) + " ; ");
            sbMax.append( Long.toString(durationMax[i]) + " ; ");
            sbRemoveMax.append( Long.toString(durationRemoveMax[i]) + " ; ");
            sbNLargest.append( Long.toString(durationNLargest[i]) + " ; ");
            sbRemoveNLargest.append( Long.toString(durationRemoveNLargest[i]) + " ; ");
            sbHeapify.append( Long.toString(durationHeapify[i]) + " ; ");
        }
        sbInsert.append("\n");
        sbMax.append("\n");
        sbRemoveMax.append("\n");
        sbNLargest.append("\n");
        sbRemoveNLargest.append("\n");
        sbHeapify.append("\n");
             
        System.out.println(sbInsert.toString());  
        System.out.println(sbMax.toString());  
        System.out.println(sbRemoveMax.toString());  
        System.out.println(sbNLargest.toString());  
        System.out.println(sbRemoveNLargest.toString());  
        System.out.println(sbHeapify.toString());  

        f.writeString(sbInsert.toString());  
        f.writeString(sbMax.toString());  
        f.writeString(sbRemoveMax.toString());  
        f.writeString(sbNLargest.toString());  
        f.writeString(sbRemoveNLargest.toString());  
        f.writeString(sbHeapify.toString());  
    }

    public void testQHeap(CsvFile f)  {
        System.out.println("--------------------------------------");
        System.out.println("testQHeap()");
        System.out.println("--------------------------------------");
        
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
                    x = r.nextInt(max);
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
        // f.writeLongArray(durationInsert);
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

    private BinaryHeap<Integer> getDummyBinaryHeapUnsorted(int n) {
        BinaryHeap<Integer> h = new BinaryHeap<Integer>();
        
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            h.insertUnordered(r.nextInt(max));
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
