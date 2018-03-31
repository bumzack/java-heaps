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

        System.out.println("heapify() "); 
        System.out.println("BEFORE contents of array 'values' = "); 
        for (int i = 0; i < values.size(); i++) {
            System.out.println(values.get(i) + ",");
        }

        System.out.println("heapify() "); 
        int i = a;
        System.out.println("i = " + i + "   a = " + a); 
        do {
            System.out.println("assert left(i) = " + left(i) + ", right(i) = " + right(i)); 
            assert(isHeap(left(i)) && isHeap(right(i)));
            int min = i; 
            if ((left(i) < values.size()-1) && (less((T) values.get(left(i)), (T) values.get(min)))) {
                min = left(i);
            } 
            if ((right(i) < values.size()-1) && (less((T) values.get(right(i)), (T) values.get(min)))) {
                min = right(i);
            }  
            // System.out.println("min = " + min + ",   i = " + i);
            if (min == i) {
                System.out.println("AFTER contents of array 'values' = "); 
                for (int j = 0; j < values.size(); j++) {
                    System.out.print(values.get(j) + ",");
                }   
                System.out.println("\n**********************************"); 
                
                break;
            }
            Collections.swap(values, i, min);
            i = min;
        } while (true);
    }

    public void insert(T elem) {
        super.isHeap();
        insertUnordered(elem);
        heapify(values.size());
        super.isHeap();
    }
}
