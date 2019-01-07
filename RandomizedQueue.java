/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] it;
    private int sz = 0;

    public RandomizedQueue() {               // construct an empty randomized queue
        it = (Item[]) new Object[1];
    }

    public boolean isEmpty() {               // is the randomized queue empty?
        return (sz == 0);
    }

    public int size() {                      // return the number of items on the randomized queue
        return sz;
    }

    private void checkNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument passed!");
        }
    }

    public void enqueue(Item item) {          // add the item
        checkNull(item);
        if (sz == it.length) {
            resize(2 * it.length);
        }
        it[sz++] = item;
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < sz; i++) {
            copy[i] = it[i];
        }
        it = copy;
    }

    public Item dequeue() {                   // remove and return a random item
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int index = StdRandom.uniform(sz);
            Item tmp = it[index];
            it[index] = it[--sz];
            it[sz] = null;
            if (sz <= it.length / 4) {
                resize(it.length / 2);
            }
            return tmp;
        }
    }

    public Item sample() {                    // return a random item (but do not remove it)
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int index = StdRandom.uniform(sz);
            return it[index];
        }
    }

    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {
        private int index = sz;
        private Item[] shuf_it;

        public RandIterator() {
            shuf_it = (Item[]) new Object[sz];
            System.arraycopy(it, 0, shuf_it, 0, sz);
            StdRandom.shuffle(shuf_it);
        }

        public boolean hasNext() {
            return (index > 0);
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return shuf_it[--index];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {   // unit testing (optional)

    }
}
