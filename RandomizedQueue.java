import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
    This class creates a randomized queue. Essentially, adds elements to
    the Queue and removes elements such that
    the removal is uniformly random.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == arr.length) grow();
        arr[size++] = item;
    }

    // Helper function to grow the array. With this approach,
    // insertion is an amortized O(1) operation. Doubles when array is full.
    private void grow() {
        Item[] newArr = (Item[]) new Object[2 * arr.length];
        int counter = 0;
        for (Item it : arr) newArr[counter++] = it;
        arr = newArr;
    }

    public Item dequeue() {
        if (size == 0) noSuchElem();

        // Checks whether number of elements falls beneath a quarter of array size.
        if (size * 4 == arr.length && size > 0) shrink();
        int rand = StdRandom.uniform(size);
        Item temp = arr[rand];
        arr[rand] = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return temp;
    }

    // Helper function to shrink array when necessary. Cuts array in half.
    private void shrink() {
        Item[] newArr = (Item[]) new Object[(int) (1 / 2.0 * arr.length)];
        for (int counter = 0; counter < size; counter++) {
            newArr[counter] = arr[counter];
        }
        arr = newArr;
    }

    public Item sample() {
        if (size == 0) noSuchElem();
        return arr[StdRandom.uniform(size)];
    }

    // Helper function for when no element is available.
    private void noSuchElem() {
        throw new NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    /*
        Iterator class that iterates over the queue in a uniformly
        random order.
     */
    private class RandIterator implements Iterator<Item> {
        RandomizedQueue<Item> tempQ;

        public RandIterator() {
            tempQ = new RandomizedQueue<>();
            for (int i = 0; i < size; i++) {
                tempQ.enqueue(arr[i]);
            }
        }

        public boolean hasNext() {
            return tempQ.size() != 0;
        }

        public Item next() {
            if (tempQ.size() == 0) noSuchElem();
            return tempQ.dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        StdOut.println(q.size() + " " + q.size);

        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }

        int check = q.sample();
        while (check != 9) {
            check = q.sample();
            StdOut.println(check + " check");
        }
        
        StdOut.println(q.sample() + " sample");
        StdOut.println(q.size() + " size");
        for (Integer i : q) {
            StdOut.println(i + " itr test");
        }

        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

        RandomizedQueue<Integer> q2 = new RandomizedQueue<>();
        q2.enqueue(5);
        StdOut.println(q2.dequeue());
        q2.enqueue(5);
    }
}
