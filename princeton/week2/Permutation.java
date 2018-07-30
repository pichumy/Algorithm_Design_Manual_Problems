import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args){
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    while(!StdIn.isEmpty()){
      // Put elements from file into queue
      queue.enqueue(StdIn.readString());
    }
    // get size from command line
    int k = Integer.parseInt(args[0]);
    // print out that number of random elements
    for (int i = 0; i < k; i++) {
      StdOut.println(queue.dequeue());
    }
  }
}
