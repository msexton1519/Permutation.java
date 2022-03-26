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

    public static void main(String args[]) {
        Deque<Integer> deq = new Deque<>();
        deq.addLast(5);
        deq.removeFirst();
        deq.addFirst(7);
        deq.removeLast();
        deq.addFirst(10);
        deq.removeFirst();
        deq.addLast(11);
        deq.removeLast();
        deq.addFirst(5);
        deq.addFirst(4);
        deq.addFirst(3);
        deq.addFirst(2);
        deq.addFirst(1);
        deq.addFirst(0);
        StdOut.println(deq.size());
        for (Integer i : deq) {
            StdOut.println(i);
        }
        for (Integer j : deq) {
            StdOut.println(j);
        }
        while (!deq.isEmpty()) {
            StdOut.println(deq.removeLast());
        }
        deq.addFirst(5);
        deq.removeFirst();
        deq.addFirst(5);
        deq.removeLast();
        try {
            deq.removeLast();
        } catch (NoSuchElementException ex) {
            StdOut.println(ex);
            StdOut.println("Spotbugs lies!?");
        }
        int n = 5;
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < n; i++)
            dq.addLast(i);
        for (int a : dq) {
            for (int b : dq)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
        Deque<Integer> deq2 = new Deque<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                deq2.addFirst(i);
            } else {
                deq2.addLast(i);
            }
        }
        for (Integer i : deq2) {
            StdOut.println(i + " ");
        }
        StdOut.println();
        StdOut.println(deq2.size());
        while (deq2.size() > 0) {
            StdOut.println(deq2.removeLast() + " " + deq2.size());
        }

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        Iterator<Integer> itr = deque.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next() + " first");
        }
        deque.removeLast();
        System.out.println(deque.size());
        itr = deque.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        StdOut.println("*********************************");
        deque = new Deque<>();
        deque.addFirst(1);
        for (Integer i : deque) {
            StdOut.println(i + " itr1");
        }
        deque.addFirst(2);
        for (Integer i : deque) {
            StdOut.println(i + " itr2");
        }
        deque.removeLast();
        deque.removeLast();
        Iterator<Integer> itr3 = deque.iterator();
        while (itr3.hasNext()) {
            StdOut.println(itr3.next() + " itr3");
        }
    }
}
