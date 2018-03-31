/* ------------------------------------------------------------- */
/* BinaryHeap.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl                                       */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* binary heap implementation                                    */
/* ------------------------------------------------------------- */

package queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

public class BinaryHeap<T extends Comparable<T>> extends Heap {
    
    // M.W.
    public BinaryHeap() {
        super(); 
        // System.out.println("BinaryHeap constructor - END");
    }

    public void insertUnordered(T elem) {
        // System.out.println("insertUnordered BEGIN");
        // System.out.println("values  " + values);
        // System.out.println("values.size()  " + values.size());
        // if (values.add(elem)) {
        values.add(elem);
        // System.out.println("insertUnordered success!");
        // }
        // System.out.println("values.size()  " + values.size());
        // System.out.println("insertUnordered END");
    }

    public void insertUnordered(T[] elemArray) {
        List<T> newList = Arrays.asList(elemArray);
        values.addAll(newList);
    }

    private void heapify(int a) {
        // System.out.println("heapify() "); 
        // System.out.println("BEFORE contents of array 'values' = "); 
        // for (int i = 0; i < values.size(); i++) {
        //     System.out.println(values.get(i) + ",");
        // }
        // int i = a;
        // System.out.println("i = " + i + "   a = " + a); 
        
        // https://en.wikipedia.org/wiki/Binary_heap
 
        // int left = 2*i + 1;
        // int right = 2*i + 2;
        // int largest = i;
        // int len = values.size()-1;
        // if (left <= len && less((T) values.get(largest), (T) values.get(left))) {            
        //     largest = left;
        // }   
        // if (right <= len && less((T) values.get(largest), (T) values.get(right))) {
        //     largest = right;
        // }       
        // if (largest != i) {
        //     Collections.swap(values, i, largest);
        //     heapify(largest);
        // }         
        int cnt = values.size() - 1;

        for (int i = cnt / 2; i >= 0; i--)
            heapifyHelper(i, cnt);

        for (int i = cnt; i > 0; i--) {
            // swap(0, i);
            Collections.swap(values, 0, i);
            cnt--;
            heapifyHelper(0, cnt);
        }
    }

    private void heapifyHelper(int i, int cnt) {
        int left = left(i);
        int right = right(i);
        int greatest = i;

        if (left <= cnt && (Integer) values.get(left) < ((Integer) values.get(greatest)))
        greatest = left;
        if (right <= cnt && (Integer) values.get(right) < ((Integer) values.get(greatest)))
        greatest = right;
        if (greatest != i) {
            // swap(i, greatest);
            Collections.swap(values, i, greatest);
            heapifyHelper(greatest, cnt);
        }
    }


    public void insert(T elem) {
        super.isHeap();
        insertUnordered(elem);
        heapify(values.size()-1);

        // for (int i = 0; i < values.size(); i++) {
        //     System.out.println(values.get(i) + ",");
        // }

        if (super.isHeap()) {
            System.out.println("It is a Heap !!!");
        } else {
            throw new IllegalStateException("insert()  OHHHH NOOOO  It is NOT a Heap !!!");
        }
    }

    public void printAsTree() {
        //TODO
    }
}
