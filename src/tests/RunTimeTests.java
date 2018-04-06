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

    private int[] cntElements = {   100  };            //25, 100,   1000,  10000, 100000
    Integer max = 100000;                                       
    int iterations = 2;                                         // 2
    int[] dValues = { 8,  };

    int nLargest = 8;

    public RunTimeTests() {
        // nothing to   
    }

    public void testBinaryHeap(CsvFile f)   {    
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

        System.out.println("--------------------------------------");
        System.out.println("test insert()");
        System.out.println("--------------------------------------");

        Random r = new Random();

        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h = getDummyBinaryHeap(cntElements[i]);
            dur = 0;
            title[i] = Integer.toString(cntElements[i]);

            for (int j = 0; j < iterations; j++) {
                x = r.nextInt(max);
                start = System.nanoTime();
                h.insert(x);
                end = System.nanoTime();
                dur += (end - start);
            }
            durationInsert[i] = dur / iterations;
        }

        System.out.println("--------------------------------------");
        System.out.println("test max()");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h = getDummyBinaryHeap(cntElements[i]);
            dur = 0;
            // title[i] = Integer.toString(cntElements[i]);
            for (int j = 0; j < iterations; j++) {

                start = System.nanoTime();
                x = h.max();
                end = System.nanoTime();
                dur += (end - start);
            }
            durationMax[i] = dur / iterations;
        }

        System.out.println("--------------------------------------");
        System.out.println("test removeMax()");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h;
            dur = 0;
            // title[i] = Integer.toString(cntElements[i]);
            for (int j = 0; j < iterations; j++) {
                h = getDummyBinaryHeap(cntElements[i]);

                start = System.nanoTime();
                x = h.removeMax();
                end = System.nanoTime();
                dur += (end - start);
            }
            durationRemoveMax[i] = dur / iterations;
        }

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
        
        System.out.println("--------------------------------------");
        System.out.println("test removeNLargest()");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < cntElements.length; i++) {
            BinaryHeap<Integer> h;
            dur = 0;
            ArrayList<Integer> res; 
            // title[i] = Integer.toString(cntElements[i]);
            for (int j = 0; j < iterations; j++) {
                h = getDummyBinaryHeap(cntElements[i]);

                start = System.nanoTime();
                res = h.removeNLargest(nLargest);
                end = System.nanoTime();

                System.out.println("test remmoveNLargest() BEFORE assert isHeap()");
                assert h.isHeap();
                System.out.println("test remmoveNLargest() AFTER assert isHeap()");

                dur += (end - start);
            }
            durationRemoveNLargest[i] = dur / iterations;
        }

        // System.out.println("--------------------------------------");
        // System.out.println("test heapify()");
        // System.out.println("--------------------------------------");
        
        // for (int i = 0; i < cntElements.length; i++) {
        //     BinaryHeap<Integer> h;
        //     dur = 0;
        //     ArrayList<Integer> res; 
        //     // title[i] = Integer.toString(cntElements[i]);
        //     for (int j = 0; j < iterations; j++) {
        //         h = getDummyBinaryHeapUnsorted(cntElements[i]);
                
        //         start = System.nanoTime();
        //         h.heapify(0);
        //         end = System.nanoTime();

        //         assert h.isHeap();
        //         dur += (end - start);
        //     }
        //     durationHeapify[i] = dur / iterations;
        // }

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
        
        long[][] durationInsert = new long[dValues.length][cntElements.length];
        long[][] durationMax = new long[dValues.length][cntElements.length];
        long[][] durationRemoveMax = new long[dValues.length][cntElements.length];
        long[][] durationNLargest = new long[dValues.length][cntElements.length];
        long[][] durationRemoveNLargest = new long[dValues.length][cntElements.length];
        long[][] durationHeapify = new long[dValues.length][cntElements.length];


        // String[] title = new String[cntElements.length];

        Integer x;
        long dur = 0, start, end; 

        Random r = new Random();

        QHeapQueue<Integer> h = new QHeapQueue<Integer> (1);
        ArrayList<Integer> res; 

        System.out.println("--------------------------------------");
        System.out.println("test insert()");
        System.out.println("--------------------------------------");

        
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
                durationInsert[k][i] = dur / iterations;
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("test max()");
        System.out.println("--------------------------------------");

        for (int k = 0; k < dValues.length; k++) {
            for (int i = 0; i < cntElements.length; i++) {
            
                h = getDummyQHeap(cntElements[i], dValues[k]);        

                dur = 0;

                for (int j = 0; j < iterations; j++) {
                    x = r.nextInt(max);
                    start = System.nanoTime();
                    x = h.max();
                    end = System.nanoTime();
                    dur += (end - start);
                }
                durationMax[k][i] = dur / iterations;
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("test removeMax()");
        System.out.println("--------------------------------------");

        for (int k = 0; k < dValues.length; k++) {
            for (int i = 0; i < cntElements.length; i++) {
                dur = 0;

                for (int j = 0; j < iterations; j++) {

                    h = getDummyQHeap(cntElements[i], dValues[k]);   
                    start = System.nanoTime();
                    x = h.removeMax();
                    end = System.nanoTime();
                    dur += (end - start);
                }
                durationMax[k][i] = dur / iterations;
            }
        }
        
        // System.out.println("--------------------------------------");
        // System.out.println("test nLargest()");
        // System.out.println("--------------------------------------");

        // 
        // for (int k = 0; k < dValues.length; k++) {
        //     for (int i = 0; i < cntElements.length; i++) {
        //         dur = 0;

        //         for (int j = 0; j < iterations; j++) {

        //             h = getDummyQHeap(cntElements[i], dValues[k]);   
                    
        //             start = System.nanoTime();
        //             res = h.nLargest(nLargest);
        //             end = System.nanoTime();
        //             dur += (end - start);
        //         }
        //         durationNLargest[k][i] = dur / iterations;
        //     }
        // }

        // System.out.println("--------------------------------------");
        // System.out.println("test removeNLargest()");
        // System.out.println("--------------------------------------");

        // for (int k = 0; k < dValues.length; k++) {
        //     for (int i = 0; i < cntElements.length; i++) {
        //         dur = 0;

        //         for (int j = 0; j < iterations; j++) {

        //             h = getDummyQHeap(cntElements[i], dValues[k]);   

        //             System.out.println("current heap before removing 20 elements ---------------");
        //             System.out.println(h);
                    
        //             start = System.nanoTime();
        //             res = h.removeNLargest(nLargest);
        //             end = System.nanoTime();
                    
        //             System.out.println("current heap AFTER removing 20 elements ---------------");
        //             System.out.println(h);
                    
        //             if (!h.isHeap()) {
        //                 System.out.println("XXXXXXXXXXXXXXXXXXX");
        //                 System.exit(99);
        //             }
                    
        //             dur += (end - start);
        //         }
        //         durationRemoveNLargest[k][i] = dur / iterations;
        //     }
        // }
        // System.out.println("AFTER  test removeNLargest()");

        System.out.println("--------------------------------------");
        System.out.println("test heapify()");
        System.out.println("--------------------------------------");

        for (int k = 0; k < dValues.length; k++) {
            for (int i = 0; i < cntElements.length; i++) {
                dur = 0;

                for (int j = 0; j < iterations; j++) {

                    h = getDummyQHeapUnordered(cntElements[i], dValues[k]);   
                    
                    start = System.nanoTime();
                    h.heapify();
                    end = System.nanoTime();

                    assert h.isHeap();
                    dur += (end - start);
                }
                durationRemoveNLargest[k][i] = dur / iterations;
            }
        }

        
        

        // StringBuilder sb = new StringBuilder();
        // System.out.println("timings ");
        // for (int i = 0; i < durationInsert.length; i++) {
        //     // System.out.print("d = " + dValues[i] + "    ");
        //     sb.append(" QHeap; insert();    " + Integer.toString(dValues[i] ) + " ; ");
        //     for (int j = 0; j < durationInsert[i].length; j++) {
        //         // System.out.print(durationInsert[i][j] + "  /  ");
        //         sb.append(Long.toString(durationInsert[i][j]) + " ; ");
        //     }
        //     sb.append("\n");
        //     // System.out.println();   
        // }
        // // System.out.println();   
        // // System.out.println();   
        // System.out.println(sb.toString());  

        // f.writeString(sb.toString());

        StringBuilder sbInsert = new StringBuilder();
        StringBuilder sbMax = new StringBuilder();
        StringBuilder sbRemoveMax = new StringBuilder();
        StringBuilder sbNLargest = new StringBuilder();
        StringBuilder sbRemoveNLargest = new StringBuilder();
        StringBuilder sbHeapify = new StringBuilder();

        System.out.println("timings ");
        // sbInsert.append(" QHeap; insert() ;  na ;");
        // sbMax.append(" QHeap; max() ;  na ;");
        // sbRemoveMax.append(" QHeap; removeMax() ;  na ;");
        // sbNLargest.append(" QHeap; nLargest() ;  na ;");
        // sbRemoveNLargest.append(" QHeap; removeNLargest() ;  na ;");
        // sbHeapify.append(" QHeap; heapify() ;  na ;");
        for (int i = 0; i < durationInsert.length; i++) {
            // System.out.print("d = " + dValues[i] + "    ");
            sbInsert.append(" QHeap; insert();    " + Integer.toString(dValues[i] ) + " ; ");
            sbMax.append(" QHeap; max();    " + Integer.toString(dValues[i] ) + " ; ");
            sbRemoveMax.append(" QHeap; removeMax();    " + Integer.toString(dValues[i] ) + " ; ");
            sbNLargest.append(" QHeap; nLargest();    " + Integer.toString(dValues[i] ) + " ; ");
            sbRemoveNLargest.append(" QHeap; removeNLargest();    " + Integer.toString(dValues[i] ) + " ; ");
            sbHeapify.append(" QHeap; heapify();    " + Integer.toString(dValues[i] ) + " ; ");
            for (int j = 0; j < durationInsert[i].length; j++) {
                // System.out.print(durationInsert[i][j] + "  /  ");
                sbInsert.append(Long.toString(durationInsert[i][j]) + " ; ");
                sbMax.append(Long.toString(durationMax[i][j]) + " ; ");
                sbRemoveMax.append(Long.toString(durationRemoveMax[i][j]) + " ; ");
                sbNLargest.append(Long.toString(durationNLargest[i][j]) + " ; ");
                sbRemoveNLargest.append(Long.toString(durationRemoveNLargest[i][j]) + " ; ");
                sbHeapify.append(Long.toString(durationHeapify[i][j]) + " ; ");
            }
            sbInsert.append("\n");
            sbMax.append("\n");
            sbRemoveMax.append("\n");
            sbNLargest.append("\n");
            sbRemoveNLargest.append("\n");
            sbHeapify.append("\n");
        }
             
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

    private QHeapQueue<Integer> getDummyQHeapUnordered(int n, int d) {
        QHeapQueue<Integer> h = new QHeapQueue<Integer>(d);
        
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            h.insertUnordered(r.nextInt(max));
        }
        return h;
    }
}
