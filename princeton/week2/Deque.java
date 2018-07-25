import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int length = 0;

    private class Node {
      Item item;
      Node next;
      Node prev;
    }

    // constructs an empty deque
    public Deque() {

    }

    // checks if deque is empty
    public boolean isEmpty() {
      return length == 0;
    }
    // returns the size of the deque
    public int size() {
      return length;
    }
    // add item to the front
    public void addFirst(Item item) {
      if (item == null) {
        throw new IllegalArgumentException("Null is not a valid item");
      }
      Node oldfirst = first;
      Node newfirst = new Node();
      newfirst.item = item;
      newfirst.next = oldfirst;
      newfirst.prev = null;
      oldfirst.prev = newfirst;
      first = newfirst;
      length++;
    }
    // Add item to the end
    public void addLast(Item item) {
      if (item == null) {
        throw new IllegalArgumentException("Null is not a valid item");
      }
      Node oldlast = last;
      Node newlast = new Node();
      newlast.item = item;
      oldlast.next = newlast;
      newlast.next = null;
      newlast.prev = oldlast;
      last = newlast;
      length++;
    }
    // remove from front and return item
    public Item removeFirst() {
      if(isEmpty()){
        throw new NoSuchElementException();
      }
      Node oldfirst = first;
      Node newfirst = first.next;
      oldfirst.next = null;
      newfirst.prev = null;
      first = newfirst;
      length--;
      return oldfirst.item;
    }
    // remove from end and return item
    public Item removeLast() {
      if(isEmpty()){
        throw new NoSuchElementException();
      }
      Node oldlast = last;
      Node newlast = last.prev;
      newlast.next = null;
      last = newlast;
      oldlast.prev = null;
      length--;
      return oldlast.item;
    }

    // return an interator over items in order from front to end
    public Iterator<Item> iterator() {
      return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

      private Node current = first;

      public boolean hasNext() {
        return current != null;
      }

      public Item next() {
        if (!hasNext()) {
          throw new NoSuchElementException("You have reached the end of the list!");
        }
        Item item = current.item;
        current = current.next;
        return item;
      }

      public void remove() {
        throw new UnsupportedOperationException("Remove() is not supported by this iterator!");
      }

    }

}
