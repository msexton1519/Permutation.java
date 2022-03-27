import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
    This class creates a double-ended queue or deque for short.
    It uses a linked list style implementation and adds
    elements to either end, left/right, and can remove
    elements from either end as well.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node leftEnd, rightEnd;
    private int size;

    // To make the implementation simpler, uses private inner class.
    private class Node {
        Item data;
        Node prev, next;
    }

    // Constructor to create an empty deque
    public Deque() {
        leftEnd = null;
        rightEnd = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) illegalArg();
        Node temp = new Node();
        temp.data = item;
        if (size == 0) {
            rightEnd = temp;
        } else {
            temp.next = leftEnd;
            leftEnd.prev = temp;
        }
        leftEnd = temp;
        size++;
    }

    public Item removeFirst() {
        if (size == 0) noSuchElem();
        Item temp = leftEnd.data;
        leftEnd = leftEnd.next;
        if (size == 1) rightEnd = null;
        else leftEnd.prev = null;
        size--;
        return temp;
    }

    public void addLast(Item item) {
        if (item == null) illegalArg();
        Node temp = new Node();
        temp.data = item;
        if (size == 0) {
            leftEnd = temp;
        } else {
            temp.prev = rightEnd;
            rightEnd.next = temp;
        }
        rightEnd = temp;
        size++;
    }

    public Item removeLast() {
        if (size == 0) noSuchElem();
        Item temp = rightEnd.data;
        rightEnd = rightEnd.prev;
        if (size == 1) leftEnd = null;
        else rightEnd.next = null;
        size--;
        return temp;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Helper function for when an illegal argument is passed into a method.
    private void illegalArg() {
        throw new IllegalArgumentException();
    }

    // Helper function for when no such element is available.
    private void noSuchElem() {
        throw new NoSuchElementException();
    }

    /*
        Iterator class to iterate through the elements of the deque.
        By default, traverses from left to right.
     */
    private class DequeIterator implements Iterator<Item> {
        Node curr = leftEnd;

        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Don't Use!");
        }

        public Item next() {
            if (curr == null) noSuchElem();
            Item item = curr.data;
            curr = curr.next;
            return item;
        }
    }
}
