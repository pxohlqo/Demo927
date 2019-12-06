package me.pxohlqo.algplayground.model.algorithmAndDataStructrue;

import androidx.annotation.NonNull;

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

    public boolean contains(T elem) {
        for (int i = 0; i < heapSize; i++) {
            if (heap.get(i).equals(elem)) return true;
        }
        return false;
    }

    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException();

        if (heapSize < heapCapity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapity++;
        }

        swim(heapSize);
        heapSize++;
    }

    // Tests if the value of node i <= node j
    // This method assumes i & j are valid indices, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // Perform bottom up node swim, O(log(n))
    private void swim(int k) {
        int parent = (k - 1) / 2;
        while (k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;

            parent = (k - 1) / 2;
        }
    }

    // Top down node sink, O(log(n))
    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;
            if (right < heapSize && less(right, left)) smallest = right;

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if (left >= heapSize || less(k, smallest)) break;

            // Move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    public boolean remove(T element) {
        if (element == null) return false;
        for (int i = 0; i < heapSize; i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;

        heapSize--;
        T removed_data = heap.get(i);
        swap(i, heapSize);
        heap.set(heapSize, null);
        if (i == heapSize) return removed_data;
        T elem = heap.get(i);
        sink(i);
        if (heap.get(i).equals(elem)) swim(i);
        return removed_data;
    }

    private boolean isMinHeap(int k) {
        if (k >= heapSize) return true;
        int left = k * 2 + 1;
        int right = k * 2 + 2;

        // Make sure that the current node k is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    @NonNull
    @Override
    public String toString() {
        return heap.toString();
    }
}
