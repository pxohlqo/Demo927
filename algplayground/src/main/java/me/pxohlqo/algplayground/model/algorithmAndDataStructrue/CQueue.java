package me.pxohlqo.algplayground.model.algorithmAndDataStructrue;

import androidx.annotation.NonNull;

import java.util.Iterator;

public class CQueue<T> implements Iterable<T> {

    private CDoublyLinkedList<T> linkedList = new CDoublyLinkedList<>();

    public CQueue() {}

    public CQueue(T firstElem) {
        offer(firstElem);
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return linkedList.peekLast();
    }

    public T poll() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return linkedList.removeLast();
    }

    public void offer(T elem) {
        linkedList.addLast(elem);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
