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
    }

    public void insertUnordered(T elem) {
        values.add(elem);
    }

    public void insertUnordered(T[] elemArray) {
        List<T> newList = Arrays.asList(elemArray);
        values.addAll(newList);
        }

    public void heapify(int a) {
        int cnt = values.size() - 1;
        
        for (int i = cnt / 2; i >= 0; i--)
            heapifyHelper(i, cnt);

        for (int i = cnt; i > 0; i--) {
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
            Collections.swap(values, i, greatest);
            heapifyHelper(greatest, cnt);
        }
    }

    public void insert(T elem) {
        assert isHeap();
        insertUnordered(elem);
        heapify(values.size()-1);
        assert isHeap();
    }

    public T max() {
        if (values.isEmpty())
            throw new IllegalStateException("queue is empty!!   max()");
        return (T) values.get(0);
    }

    public T removeMax() {
        if (values.isEmpty())
            throw new IllegalStateException("queue is empty!!   removeMax()");
        return (T) dequeue();
    }

    public int size() {
        return values.size();
    }

     // https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
     // this does not work at runtime: 
    //   T[] elems = (T[]) new Comparable[n];
    //      [java] Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Comparable; cannot be cast to [Ljava.lang.Integer;
    //      [java] 	at tests.PQueueTest.testNLargest(PQueueTest.java:162)
    //      [java] 	at tests.PQueueTest.main(PQueueTest.java:179)

    public ArrayList<T> nLargest(int n) {
        if (values.size() < n)
            throw new IllegalArgumentException("not enough elements in queue!!   nLargest()");

        ArrayList<T> elems = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            elems.add((T) values.get(i));
        }
        return elems; 
    }

    public ArrayList<T> removeNLargest(int n) {
        
        ArrayList<T> elems = nLargest(n);
        // values.removeRange(0, n); is protected :-(        
        values.subList(0, n).clear();
        assert isHeap();
        return elems; 
    }

    public void merge(BinaryHeap<T> queue) {
        assert isHeap();

        System.out.println("queue.size = " + queue.size());
        int cnt = queue.size();
        for (int i = 0; i < cnt; i++) {
            T val = (T) queue.removeMax();
            // System.out.println("adding value " + val + ",   i = " + i + "   queue.size() = " + queue.size());
            insertUnordered(val);
        }
        // System.out.println("after for loop  ");
        heapify(values.size()-1);
        assert isHeap();
    }
}
