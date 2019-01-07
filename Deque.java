/* *****************************************************************************
 *  Name: Krishan Duhan
 *  Date: Jan 6,2018
 *  Description: Deque implementation
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int sz;                         //Size of deque
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {                         // construct an empty deque
        first = null;
        last = null;
        sz = 0;
    }

    private void checkNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument passed!");
        }
    }

    private void checkEmpty() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }
    }

    public boolean isEmpty() {               // is the deque empty?
        return (sz == 0);
    }

    public int size() {                      // return the number of items on the deque
        return sz;
    }

    public void addFirst(Item item) {        // add the item to the front
        checkNull(item);
        if (first == null) {
            Node oldfirst = new Node();
            oldfirst.item = item;
            first = oldfirst;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        if (last == null) {
            last = first;
        }
        sz++;
    }

    public void addLast(Item item) {         // add the item to the end
        checkNull(item);
        if (last == null) {
            Node oldlast = new Node();
            oldlast.item = item;
            last = oldlast;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.prev = oldlast;
            oldlast.next = last;
        }
        if (first == null) {
            first = last;
        }
        sz++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        checkEmpty();
        Item res = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        else {
            last = null;
        }
        sz--;
        return res;
    }

    public Item removeLast() {                // remove and return the item from the end
        checkEmpty();
        Item res = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        sz--;
        return res;
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new DeqIterator();
    }

    private class DeqIterator implements Iterator<Item> {
        private Node current;

        public DeqIterator() {
            current = first;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item res = current.item;
            current = current.next;
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {      // unit testing (optional)
    }
}
