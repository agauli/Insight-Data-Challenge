import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 *  a bag (or multiset) of generic dataitems..  
 */

public class Bag<Data> implements Iterable<Data> {
    private Node<Data> head;    //head of the Linkedlist
    private int size;               //number of items

    // Linked List class
    private static class Node<Data> {
        private Data dataitem;
        private Node<Data> next;
    }

    /* Constructor
     */
    public Bag() {
        head = null;
        size = 0;
    }

    
    // Returns the number of dataitems in this bag.
    public int size() {
        return size;
    }

    // return true if bag is empty 
    public boolean isEmpty() {
        return head == null;
    }

    // Adds the dataitem to this bag.
    public void add(Data dataitem) {
        Node<Data> oldhead = head;
        head = new Node<Data>();
        head.dataitem = dataitem;
        head.next = oldhead;
        size++;
    }

    public Iterator<Data> iterator()  {
        return new ListIterator<Data>(head);  
    }

    private class ListIterator<Data> implements Iterator<Data> {
        private Node<Data> current;
        public ListIterator(Node<Data> head) {
            current = head;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Data next() {
            if (!hasNext()) throw new NoSuchElementException();
            Data dataitem = current.dataitem;
            current = current.next; 
            return dataitem;
        }
    }
}
