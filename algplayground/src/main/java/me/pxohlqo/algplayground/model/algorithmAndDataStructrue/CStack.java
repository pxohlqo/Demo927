package me.pxohlqo.algplayground.model.algorithmAndDataStructrue;

import androidx.annotation.NonNull;

import java.util.EmptyStackException;
import java.util.Iterator;

public class CStack<T> implements Iterable<T> {

    private CDoublyLinkedList<T> linkedList = new CDoublyLinkedList<>();

    public CStack() {}

    public CStack(T firstElem) {
        push(firstElem);
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(T elem) {
        linkedList.addLast(elem);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return linkedList.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return linkedList.peekLast();
    }


    @NonNull
    @Override
    public Iterator iterator() {
        return linkedList.iterator();
    }
}
