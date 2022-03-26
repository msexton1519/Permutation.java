import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
    A client for RandomizedQueue that takes, as input from a command-line argument,
    a number less than or equal to the number of elements read in through
    standard input and returns a permutation on the subset of the input.
 */
public class Permutation {

    public static void main(String[] args) {
        int iterations = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        iterations = Math.min(rq.size(), iterations);

        for (int i = 0; i < iterations; i++) {
            StdOut.println(rq.dequeue());
        }

    }

}
