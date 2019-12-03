package me.pxohlqo.algplayground.model.algorithmAndDataStructrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A min priority queue implementation using a binary heap.
 *
 * @see "https://github.com/williamfiset/Algorithms/blob/master/com/williamfiset/algorithms/datastructures/priorityqueue/BinaryHeap.java"
 */
public class CPriorityQueue<T extends Comparable<T>> {

    private int heapSize = 0;
    private int heapCapity = 0;
    private List<T> heap = null;

    public CPriorityQueue() {
        this(1);
    }

    public CPriorityQueue(int size) {
        heap = new ArrayList<>(size);
    }

    public CPriorityQueue(T[] elems) {
        heapSize = heapCapity = elems.length;
        heap = new ArrayList<>(heapCapity);

        for (int i = 0; i < elems.length; i++) heap.add(elems[i]);
        // heap.addAll(Arrays.asList(elems));

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    public CPriorityQueue(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) heap.add(elem);
        //  heap.addAll(elems);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapSize; i++) heap.set(i, null);
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    // Returns the value of the element with the lowest
    // priority in this priority queue. If the priority
    // queue is empty null is returned.
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // Removes the root of the heap, O(log(n))
    public T poll() {
        return removeAt(0);
    }
}
