import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] items;
  private int size;

  // constructs an empty randomized queue
  public RandomizedQueue() {
    items = (Item[]) new Object[1];
    size = 0;
  }
  // Is the queue empty?
  public boolean isEmpty() {
    return size == 0;
  }
  // return the number of items in the queue
  public int size() {
    return size;
  }
  // add item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null!");
    }
    if (items.length == size) {
      resize(items.length * 2);
      items[size] = item;
      size++;
    } else {
      items[size] = item;
      size++;
    }
  }
  private int randomIndex() {
    return StdRandom.uniform(size());
  }
  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot dequeue from an empty Queue");
    }
    int index = randomIndex();
    Item returnItem = items[index];
    // if we are removing the last item... yay!
    if(size - 1 == index){
      items[index] = null;
    } else {
      // replace an index with the last value in the array, and then reduce the array size by 1.
      items[index] = items[size - 1];
      items[size - 1] = null;
    }
    if (size == items.length / 4) {
      resize(items.length / 2);
    }
    size--;
    return returnItem;

  }
  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty!");
    }
    int index = randomIndex();
    return items[index];

  }
  // resize the array. Basically make a bigger array and copy all the old values into the new array.
  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for(int i = 0; i < size; i++) {
      copy[i] = items[i];
    }
    items = copy;
  }
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomQueueIterator();
  }

  private class RandomQueueIterator implements Iterator<Item> {
    // use this 1 to iterate through
    private int i = 0;
    // this will hold the indice to access. These indices will be randomly shuffled.
    private int[] indices;

    public RandomQueueIterator() {
      indices = new int[size];
      // initialize indices
      for (int j = 0; j < indices.length; j++) {
        indices[j] = j;
      }
      // shuffle them
      StdRandom.shuffle(indices);
    }

    public boolean hasNext() {
      return i < size;
    }

    public Item next() {
      if(!hasNext()) {
        throw new NoSuchElementException("End of queue");
      }
      return items[indices[i++]];
    }

    public void remove() {
      throw new UnsupportedOperationException("remove() is not supported");
    }
  }

}
