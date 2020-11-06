/* *****************************************************************************
 *  Name:    Kenar Vyas
 *  NetID:   kvyas
 *  Precept: P03A
 *
 *  Description:  Creates CircularSuffixArray object that is a sorted array
 * of all possible circular-suffixes of a given String. Uses private class
 * Suffix to implement more efficiently.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    // length of string s
    private final int n;
    // array that holds all suffixes
    private final Suffix[] suffixes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("null arg");
        }
        n = s.length();
        suffixes = new Suffix[n];

        // creates all suffixes
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(s, i);
        }

        // sorts suffixes using built in Java sort
        Arrays.sort(suffixes);

    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i >= length() || i < 0) {
            throw new IllegalArgumentException("bad arg");
        }
        return suffixes[i].offset;
    }

    // private class Suffix that helps meet memory requirements over substring
    private class Suffix implements Comparable<Suffix> {
        // text value of string
        private final String text;
        // offset of suffix
        private final int offset;

        // constructs suffix object
        public Suffix(String text, int offset) {
            this.text = text;
            this.offset = offset;
        }

        // returns char at index i of suffix
        public char charAt(int i) {
            return text.charAt(i);
        }

        // compareTo implementation for Suffix that compares suffixes
        // lexicographically
        public int compareTo(Suffix that) {
            int len = text.length();
            int i = this.offset;
            int j = that.offset;
            int counter = 0;
            while (counter < text.length()) {
                if (this.charAt(i % len) > that.charAt(j % len)) {
                    return 1;
                }
                else if (this.charAt(i % len) < that.charAt(j % len)) {
                    return -1;
                }
                i++;
                j++;
                counter++;
            }
            return 0;
        }

    }


    // unit testing (required)
    public static void main(String[] args) {
        String test = "*************";
        CircularSuffixArray csa = new CircularSuffixArray(test);
        for (int i = 0; i < test.length(); i++) {
            StdOut.println(csa.index(i));
        }
        StdOut.println("Length: " + csa.length());

    }
}
