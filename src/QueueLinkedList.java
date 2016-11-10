import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Queue of generic dataitems based on a first-in-first-out (FIFO) and Linked List.
 *  It supports the usual enqueue and dequeue and some other operations.
 */

public class QueueLinkedList<Data> implements Iterable<Data> {
    private Node<Data> head;    
    private Node<Data> tail;     
    private int n;

    // helper linked list class
    private static class Node<Data> {
        private Data dataitem;
        private Node<Data> next;
    }

    /**
     * Constructor 
     */
    public QueueLinkedList() {
        head = null;
        tail  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty otherwise it returns false. 
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * Adds the data to this queue. parameter is i
     */

    public void enqueue(Data dataitem) {
        Node<Data> oldtail = tail;
        tail = new Node<Data>();
        tail.dataitem = dataitem;
        tail.next = null;
        if (isEmpty()) head = tail;
        else           oldtail.next = tail;
        n++;
    }

    /**
     * Remoce the dataitem recently added, from the Queue and returns it.
     */

    public Data dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Data dataitem = head.dataitem;
        head = head.next;
        n--;
        if (isEmpty()) tail = null;   // to avoid loitering
        return dataitem;
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
