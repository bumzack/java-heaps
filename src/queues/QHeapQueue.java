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

import com.sun.javafx.binding.SelectBinding.AsString;

public class QHeapQueue<T extends Comparable<T>>  {
    
    private ArrayList<T> values;
    private static int d; 


    public QHeapQueue(int _d) {
        d = _d; 
        values = new ArrayList<T>();
    }

    public QHeapQueue(QHeapQueue<T> h) {
        values = h.getValues();
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
        values.add(elem);
        int idx = values.size() - 1;
        int parentIndex = parent(idx);
        while (idx != 0 &&  less((T) values.get(parentIndex), elem )) {
            values.set(idx, values.get(parentIndex));
            idx = parentIndex; 
            parentIndex = parent(idx);
        }
        values.set(idx, elem);
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
        T tmp = (T) values.get(0);
        values.set(0, values.get(values.size()-1));   
        values.remove(values.size()-1);
        if (!isEmpty())
           repairTop(0);
        return tmp;
    }

    private void repairTop(int topIndex) {
        T tmp = values.get(topIndex);
        int succ = findSuccessor(topIndex * d + 1, topIndex * d + d);
        while (succ < values.size() && less( tmp, (T) values.get(succ) )) {        
            values.set(topIndex, values.get(succ));    
            topIndex = succ;
            succ = findSuccessor(succ * d + 1, succ * d + d);
        }
        values.set(topIndex, tmp);
    }
    
    private int findSuccessor(int from, int to) {
        int succ = from;
        for (int i = from + 1; i <= to && i < values.size(); i++) {
            if (less( (T) values.get(succ), values.get(i) )) {
                succ = i;
            }
        }
        return succ;
    }

    public boolean isHeap() {
        for (int i = 1; i < values.size() ; i++) {
            // System.out.println(" i = " + i + "    parent(i) = " + parent(i));
            // System.out.println(" values.get(i) = " +  values.get(i)    + "    values.get(parent(i)  = " + values.get(parent(i)));

            if (!less((T) values.get(i),  (T) values.get(parent(i)))) {
                System.out.println(" values.get(i)   <   values.get(parent(i))    .....     " +   values.get(i) + " < "  + values.get(parent(i)));
                System.exit(33);
                return false;
            }
        }
        // System.out.println(" isHeap() .. TRUE     ");
        return true;

    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
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
        return a.compareTo(b) <= 0;
    }

    public void merge(QHeapQueue<T> h) {
        assert isHeap();
        
        values.addAll(h.getValues());
        heapify();
        
        assert isHeap();
    }

    private ArrayList<T> getValues() {
        return values; 
    }

    public void heapify() {
        
        for (int i = parent(values.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }

        assert isHeap();   
    }

    public void heapifyDown(int i) {
        int largest = i; 
        for (int k = 1; k <= d; k++) {
            int l = kthChild(i, k);
            if (l < values.size() && less( (T) values.get(largest), (T) values.get(l))) {
                largest = l;
            }
        }
        if (largest != i) {
            Collections.swap(values, i, largest);
            heapifyDown(largest);
        }
    }

    public ArrayList<T> nLargest(int n) {        
        if (values.size() < n)
            throw new IllegalArgumentException("not enough elements in queue!!   nLargest()");

        QHeapQueue<T> tmp = new QHeapQueue<T>(this);

        ArrayList<T> elems = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            elems.add(tmp.removeMax());
        }
        return elems; 
    }

    public ArrayList<T> removeNLargest(int n) {
        assert isHeap();

        ArrayList<T> elems = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            elems.add(removeMax());
        }
        return elems; 
    }
}
