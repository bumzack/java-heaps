/* ------------------------------------------------------------- */
/* Heap.java                                                     */
/* ------------------------------------------------------------- */
/* author: Martin Winkler                          )             */
/* date: 2018-03-31                                              */
/* ------------------------------------------------------------- */
/* heap implementation                                           */
/* ------------------------------------------------------------- */

package queues;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    
    ArrayList<T> values;

    // M.W.
    public Heap() {
        // System.out.println("Heap constructor BEGIN");
        values = new ArrayList<T>();
        // System.out.println("Heap constructor END");
    }

    // M.W.
    // G.S. changed to protected
    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    // M.W.
    public boolean isEmpty() {
        return values.isEmpty();
    }

    // M.W.
    public void enqueue(T x) {
        assert isHeap();
        values.add(x); // not insert
        upHeap();
        assert isHeap();
    }

    // M.W.
    public T peek() {
        return values.isEmpty() ? null : values.get(0);
    }

    // M.W.
    public T dequeue() {
        assert isHeap();
        if (values.isEmpty())
            throw new IllegalStateException("cannot pop from empty queue");
        T top = values.get(0);
        values.set(0, values.get(values.size()-1));
        values.remove(values.size()-1);
        if (!values.isEmpty())
            downHeap();
        assert isHeap();
        return top;
    }

    // M.W.
    // shift index (more computation, less space)
    protected static int parent(int i) { return (i - 1) / 2; }
    protected static int left (int i) { return i * 2 + 1; }
    protected static int right (int i) { return i * 2 + 2; }
    
    private void upHeap() {
        int i = values.size() - 1;
        T x = values.get(i);
        while (i != 0 && less(values.get(parent(i)), x)) { // ~ a[i/2] < e
            values.set(i, values.get(parent(i))); // ~ a[i] = a[i/2]
            i = parent(i);
        }
        values.set(i, x);
    }

    // M.W.
    private void downHeap() {
        assert !values.isEmpty(); // enable with "java -ea"
        int i = 0;
        T x = values.get(0);
        while (left(i) < values.size()) {
            int j = left(i); // j = larger child index
            if (right(i) < values.size() && 
                less(values.get(left(i)), values.get(right(i)))) {
                j = right(i);
            }
            if (!less(x, values.get(j))) break;
            values.set(i, values.get(j));
            i = j;
        }
        values.set(i, x);
    }

    // M.W.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("heap = [");
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(values.get(i));
        }
        sb.append("]");
        return sb.toString();
    }

    // M.W.
    // G.S: changed from private to public
    public boolean isHeap() {
        return isHeap(1);
    }

    public boolean isHeap(int i) {
        while (i < values.size() && !less(values.get(parent(i)), values.get(i))) {
            i++;
        }
        return i >= values.size();
    }
}
