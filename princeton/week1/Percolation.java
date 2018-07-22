import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF unionStructure;
  // Explanation of backwash problem:
  // When you successfully connect the top node to the bottom node, you now have a complete union. This is fine.
  // However, any additional nodes connected to the bottom node will also start being counted as "full nodes", since they will be added to the union through the bottom node. This is not good, since they are not real paths
  // to reach the top. Hence, backwash. We avoid this issue with one unionUF object having backwash, while the other one which is used to call isFull does not have backwash.
  // One of them is used to tell the route, the other one is used to tell whether or not there is percolation. 
  private final WeightedQuickUnionUF backwash;
  private boolean[] openedSites;
  private final int size; // keeps track of n x n size
  private int openSites; // keeps track of how many sites are open
  // create n by n grid
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
      size = n;
      unionStructure = new WeightedQuickUnionUF(n * n + 2);
      backwash = new WeightedQuickUnionUF(n * n + 2);
      openedSites = new boolean[n * n];
  }
  // open site at row, col if not open already
  public void open(int row, int col) {
    outOfBounds(row, col);
    // if closed, open it
    if (!isOpen(row, col)) {
      openSites += 1;
      int position = position(row, col);
      openedSites[position - 1] = true;
      if (row == 1) {
        unionStructure.union(0, position);
        backwash.union(0, position);
      }
      if (row == size) {
        backwash.union(size * size + 1, position);
      }
      // check above
      if (row > 1 && isOpen(row - 1, col)) {
        unionStructure.union(position - size, position);
        backwash.union(position - size, position);
      }
      // check below
      if (row < size && isOpen(row + 1, col)) {
        unionStructure.union(position + size, position);
        backwash.union(position + size, position);
      }
      // check left
      if (col > 1 && isOpen(row, col - 1)) {
        unionStructure.union(position - 1, position);
        backwash.union(position - 1, position);
      }
      // check right
      if (col < size && isOpen(row, col + 1)) {
        unionStructure.union(position + 1, position);
        backwash.union(position + 1, position);
      }
    }

  }
  // checks if spot is open
  public boolean isOpen(int row, int col) {
    outOfBounds(row, col);
    return openedSites[position(row, col) - 1];
  }
  // checks if site is full
  public boolean isFull(int row, int col) {
    outOfBounds(row, col);
    return unionStructure.connected(0, position(row, col));
  }
  // returns how many sites are open
  public int numberOfOpenSites() {
      return openSites;
  }
  // does the system percolate?
  public boolean percolates() {
    return backwash.connected(0, size * size + 1);
  }

  private void outOfBounds(int row, int col) {
    if (row <= 0 || col <= 0 || row > size || col > size) {
      throw new IllegalArgumentException("Out of bounds");
    }
  }

  private int position(int row, int col) {
    return (row - 1) * size + (col - 1) + 1;
  }

}
