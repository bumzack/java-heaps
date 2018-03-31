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
    
    ArrayList<T> values;

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

    private void heapify(int a) {
//         void heapify(Array H, int a)
//   int i = a
//   do {
//     assert(isheap(H, left(i)) && isheap(H, right(i))
//     int min = i
//     if (left(i) < H.size && H.key(left(i)) < H.key(min))
//       min = left(i)
//     if (right(i) < H.size && H.key(right(i)) < H.key(min))
//       min = right(i)
//     if (min == i)
//       break
//     H.swap(i, min)
//     i = min
//   } while(true)
        System.out.print("heapify() "); 
        int i = a;
        do {
            assert(isHeap(left(i)) && isHeap(right(i)));
            int min = i; 
            if ((left(i) < values.size()) && (less(values.get(left(i)), values.get(min)))) {
                min = left(i);
            } 
            if ((right(i) < values.size()) && (less(values.get(right(i)), values.get(min)))) {
                min = right(i);
            }  
            if (min == i) break;
            Collections.swap(values, i, min);
        } while (true);

    }

    public void insert(T elem) {
        insertUnordered(elem);
        heapify(values.size()-1); 
        super.isHeap();
    }
}