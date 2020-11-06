/* *****************************************************************************
 *  Name:    Kenar Vyas
 *  NetID:   kvyas
 *  Precept: P03A
 *
 *  Description: Applies burrows-wheeler transform and inverse transform
 *  using standard input stream. Uses principels from key-indexed sort
 * to make transform process more efficient.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // alphabet size
    private static final int R = 256;


    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {

        // reads in input
        String str = BinaryStdIn.readString();
        BinaryStdIn.close();

        // creates new csa for working with transform
        CircularSuffixArray csa = new CircularSuffixArray(str);

        // determines "first" row, or suffix with offset 0
        int first = -1;
        int n = csa.length();
        for (int i = 0; i < n; i++) {
            if (csa.index(i) == 0) {
                first = i;
                break;
            }
        }
        BinaryStdOut.write(first);

        // determines and prints array t[] for csa
        for (int i = 0; i < n; i++) {

            int offset = csa.index(i);
            if (offset == 0) {
                BinaryStdOut.write(str.charAt(str.length() - 1));
            }
            else {
                BinaryStdOut.write(str.charAt(offset - 1));
            }
        }
        BinaryStdOut.close();

    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        // read in
        int first = BinaryStdIn.readInt();
        String key = BinaryStdIn.readString();
        int n = key.length();

        // converting input string to array of characters
        char[] t = new char[n];
        for (int i = 0; i < n; i++) {
            t[i] = key.charAt(i);
        }

        // key-indexed counting algorithm to represent sorted t[]
        int[] count = new int[R + 1];

        // find frequencies
        for (int i = 0; i < n; i++) {
            count[t[i] + 1]++;
        }
        // compute cumulates
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        // computing next[] array
        int[] next = new int[n];

        // iterates through t and finds associated value in sorted t by
        // using method of key-indexed sort
        for (int i = 0; i < n; i++) {
            next[count[t[i]]++] = i;
        }

        // prints out text, following next pointers
        int pointer = next[first];
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(t[pointer]);
            pointer = next[pointer];
        }
        BinaryStdOut.close();

    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else {
            inverseTransform();
        }

    }
}
