/* *****************************************************************************
 *  Name: Krishan Duhan
 *  Date: Jan 6, 2019
 *  Description: Permutation.java
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> ob = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            ob.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(ob.dequeue());
        }
    }
}
