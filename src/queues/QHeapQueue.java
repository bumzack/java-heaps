/* ------------------------------------------------------------- */
/* QHeapQueue.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl                                       */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* d-ary heapimplementation                                      */
/* ------------------------------------------------------------- */

package queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

public class QHeapQueue<T extends Comparable<T>>  {
    
    private ArrayList<T> values;
    private static int d; 

    protected static int parent(int i) { 
        return (i - 1) / d; 
    }
    
    protected static int kthChild(int i, int k) {
        return d * i + k;
    }


    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public QHeapQueue(int _d) {
        d = _d; 
        values = new ArrayList<T>();
    }

    public void insertUnordered(T elem) {
        values.add(elem);
    }

    public void insertUnordered(T[] elemArray) {
        List<T> newList = Arrays.asList(elemArray);
        values.addAll(newList);
    }
    
    public void insert(T elem) {
        assert isHeap();

        // print("before");

        insertUnordered(elem);
        // print("after insertUnordered()");
        
        heapify(values.size()-1);
        // print("after heapify()");

        assert isHeap();
    }

    public void heapify(int a) {
        T tmp = (T) values.get(a);
        // System.out.println("a = " + a + " ;   tmp = " + tmp + "        values.get(parent(a)) =   " + (T) values.get(parent(a)));
        // System.out.println("less(tmp, (T) values.get(parent(a) = " + less( (T) values.get(parent(a)), tmp));
        
        while ((a > 0) && (less((T) values.get(parent(a)), tmp))) {
            // System.out.println("while   ");
            values.set(a, values.get(parent(a)));
            a = parent(a);
        }
        values.set(a, tmp);
    }

    public T max() {
        if (values.isEmpty())
            throw new IllegalStateException("queue is empty!!   max()");
        return (T) values.get(0);
        // return null;
    }

    public T removeMax() {
        if (values.isEmpty())
            throw new IllegalStateException("queue is empty!!   removeMax()");
        return (T) dequeue();
        // return null;
    }

    private T dequeue() {
        if (values.isEmpty())
            throw new IllegalStateException("queue is empty!!   removeMax()");
        T ret = (T) values.get(0);
        values.set(0, values.get(values.size()-1));
        values.remove(values.size()-1);
        if (!values.isEmpty()) {
            downHeap(0);
        }
        return ret; 
    }

    private void downHeap(int i) {
        int child;
        T tmp = (T) values.get(i);
        while (kthChild(i, 1) < values.size()) {
            child = getMinChild(i);
            if (less(tmp, (T) values.get(child))) {
                values.set(i, values.get(child));
            } else {
                break;
            }
            i = child;
        }
        // heap[ind] = tmp;
        values.set(i, tmp);
    }

    private int getMinChild(int i) {
        int c = kthChild(i, 1);
        int k = 2;
        int pos = kthChild(i, k);
        while ((k <= d) && (pos < values.size())) {
            if (less((T) values.get(c), (T) values.get(pos)))
                c = pos;
            pos = kthChild(i, k++);
        }    
        return c;
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

        assert isHeap();
        ArrayList<T> elems = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            elems.add((T) values.get(i));
        }
        assert isHeap();
        return elems; 
    }

    public ArrayList<T> removeNLargest(int n) {
        assert isHeap();
        ArrayList<T> elems = nLargest(n);
        //  values.removeRange(0, n);       // is protected :-(        
        
        // TODO: faster?? 
        for (int i = 0; i < n; i++) {
            dequeue();
        }
        assert isHeap();
        return elems; 
    }

    public void merge(QHeapQueue<T> queue) {
        assert isHeap();

        System.out.println("queue.size = " + queue.size());

        System.out.println("BEFORE for loop   h =  " + values);
        System.out.println("BEFORE for loop   h.size() =  " + values.size());


        int cnt = queue.size();
        for (int i = 0; i < cnt; i++) {
            T val = (T) queue.removeMax();
            System.out.println("adding value " + val + ",   i = " + i + "   queue.size() = " + queue.size());
            insertUnordered(val);
        }
        System.out.println("AFTER   for loop   h =  " + values);
        System.out.println("AFTER for loop   h.size() =  " + values.size());

        heapify(values.size()-1);
        assert isHeap();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean isHeap() {
        return isHeap(1);
    }

    protected boolean isHeap(int i) {
        // System.out.println("\n\n\n isHeap()");
        while (i < values.size() && !less((T) values.get(parent(i)), (T) values.get(i))) {
            // System.out.println("parent:  values[" + parent(i) + "] = " + (T) values.get(parent(i)));
            // System.out.println("            > ");

            // System.out.println("child:  values[" + i + "] = " + (T) values.get(i));
            // System.out.println("i = " + i + "\n\n");
            i++;
        }
        // System.out.println("afer WHILE:   i = " + i  + "   size = " + values.size());
        return i >= values.size()-d;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QHeapQueue = [");
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(values.get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
