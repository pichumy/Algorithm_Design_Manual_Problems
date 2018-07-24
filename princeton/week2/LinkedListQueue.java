public class LinkedListQueue {

  private Node first = null;
  private Node last = null;

  private class Node {
    String item;
    Node next;
  }

  public void enqueue(String item) {
      Node oldlast = last;
      Node last = new Node();
      last.item = item;
      last.next = null;
      oldlast.next = last;
  }

  public string dequeue() {
    String val = first.item; 
    first = first.next;
    return val;
  }

}
