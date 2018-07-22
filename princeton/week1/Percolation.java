import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF unionStructure;
  private boolean[] openedSites;
  private int size; // keeps track of n x n size
  private int openSites; // keeps track of how many sites are open
  // create n by n grid
  public Percolation(int n) {
      size = n;
      unionStructure = new WeightedQuickUnionUF(n * n + 2);
      openedSites = new boolean[n * n];
  }
  // open site at row, col if not open already
  public void open(int row, int col) {
    outOfBounds(row, col);
    // if closed, open it
    if(!isOpen(row, col)){
      openSites += 1;
      int position = position();
      openedSites[position - 1] = true;
      if(row == 1){
        unionStructure.union(0, position);
      }
      if(row == size){
        unionStructure.union(size * size + 2, position);
      }
      // check above
      if(row != 1 && isOpen(row - 1, col)) {
        unionStructure.union(position - size, position);
      }
      // check below
      if(row != size && isOpen(row + 1, col)) {
        unionStructure.union(position + size, position);
      }
      // check left
      if(col != 0 && isOpen(row, col - 1)) {
        unionStructure.union(position - 1, position);
      }
      // check right
      if( col != size && isOpen(row, col + 1)) {
        unionStructure.union(position + 1, position);
      }
    }

  }
  // checks if spot is open
  public boolean isOpen(int row, int col) {
    outOfBounds(row, col);
    return openedSites[position() - 1];
  }
  // checks if site is full
  public boolean isFull(int row, int col) {
    return unionStructure.connected(0, position());
  }
  // returns how many sites are open
  public int numberOfOpenSites() {
      return openSites;
  }
  // does the system percolate?
  public boolean percolates() {
    return unionStructure.connected(0, size * size + 1);
  }

  private void outOfBounds(int row, int col) {
    if(row <= 0 || col <= 0 || row > size || col > size){
      throw new IllegalArgumentException("Out of bounds");
    }
  }

  private position(int row, int col){
    return (row - 1) * size + (col - 1) + 1;
  }

}
