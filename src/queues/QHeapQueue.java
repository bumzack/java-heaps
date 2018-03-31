/* ------------------------------------------------------------- */
/* QHeapQueue.java                                               */
/* ------------------------------------------------------------- */
/* author: Georg Schinnerl                                       */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* d-ary heap implementation                                     */
/* ------------------------------------------------------------- */

package queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

public class QHeapQueue<T extends Comparable<T>>  {
    
    private ArrayList<T> values;
    private static int d; 


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
        // return null;
    }

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
        
        // TODO: faster?? 
        for (int i = 0; i < n; i++) {
            dequeue();
        }
        assert isHeap();
        return elems; 
    }

    public void heapify(int a) {
        T tmp = (T) values.get(a);
        while ((a > 0) && (less((T) values.get(parent(a)), tmp))) {
            values.set(a, values.get(parent(a)));
            a = parent(a);
        }
        values.set(a, tmp);
    }

    public boolean isHeap() {
        return isHeap(1);
    }

    protected boolean isHeap(int i) {
        while (i < values.size() && !less((T) values.get(parent(i)), (T) values.get(i))) {
            i++;
        }
        return i >= values.size()-d;
    }

    
    public int size() {
        return values.size();
    }

    public void merge(QHeapQueue<T> queue) {
        assert isHeap();
        int cnt = queue.size();
        for (int i = 0; i < cnt; i++) {
            T val = (T) queue.removeMax();
            insertUnordered(val);
        }
        heapify(values.size()-1);
        assert isHeap();
    }

    public boolean isEmpty() {
        return values.isEmpty();
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

    private static int parent(int i) { 
        return (i - 1) / d; 
    }
    
    private static int kthChild(int i, int k) {
        return d * i + k;
    }

    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
