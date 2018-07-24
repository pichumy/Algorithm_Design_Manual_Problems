import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item> {
  private Node first = null;

  private class Node {
    Item item;
    Node next;
  }

  public Iterator<Item> iterator() { return new ListIterator(); }

  private class ListIterator implements Iterator<Item> {

    private Node current = first;

    public boolean hasNext() { return current != null; }
    public Item next() {
      Item item = current.item;
      current = current.next;
      return item;
    }

  }

  public boolean isEmpty(){
    return first == null;
  }
  // make sure to change the type it expects here as well
  public void push(Item item){
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
  }
  // and here
  public Item pop(){
      String item = first.item;
      first = first.next;
      return item;
  }
}

// array implementation just for reference

public class ArrayStack<Item> implements Iterable<Item> {
  private Item[] s;
  private int N = 0;
  // requiring capacity is cheating
  public FixedCapacityStackOfStrings(int capacity) {
    s = (Item[]) new Object[capacity];
  }

  public Iterator<item> iterator() { return new ReverseArrayIterator(); }

  private class ReverseArrayIterator implements Iterator<Item> {
    private int i = N;

    public boolean hasNext() { return i > 0; }
    public Item next() { return s[--i]; }
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void push(Item item) {
    // use and then increment
    s[N++] = item;
  }

  public Item pop() {
    Item item = s[--N];
    s[N] = null;
    return item;
  }

}
