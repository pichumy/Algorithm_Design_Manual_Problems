import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
  private WeightedQuickUnionUF unionStructure;
  private int[][] store;
  private int size; // keeps track of n x n size
  private int openSites; // keeps track of how many sites are open
  // create n by n grid
  public Percolation(int n){
      size = n;
      unionStructure = new WeightedQuickUnionUF(n * n);
      store = int[n][n];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          store[i][j] = 0; // 0 means closed
        }
      }
  }
  //open site at row, col if not open already
  public void open(int row, int col){
    outOfBounds(row, col);
    // if closed, open it
    if(!isOpen(row, col)){
      store[row - 1][col - 1] = 1; // opens inside store
      // check above
      int position = (row - 1) * size + (col - 1);
      if(row != 1 && isOpen(row - 1, col)){
        unionStructure.union(position - size, position);
      }
      // check below
      if(row != size && isOpen(row + 1, col)){
        unionStructure.union(position + size, position);
      }
      // check left
      if(col != 0 && isOpen(row, col - 1)){
        unionStructure.union(position - 1, position);
      }
      // check right
      if( col != size && isOpen(row, col + 1)){
        unionStructure.union(position + 1, position);
      }
    }

  }
  // checks if spot is open
  public boolean isOpen(int row, int col){
    outOfBounds(row, col);
    return (store[row - 1][col - 1] != 0);
  }
  // checks if site is full
  public boolean isFull(int row, int col){
    outOfBounds(row, col);
  }
  // returns how many sites are open
  public int numberOfOpenSites(){
      return openSites;
  }
  // does the system percolate?
  public boolean percolates() {

  }

  private void outOfBounds(int row, int col){
    if(row <= 0 || col <= 0 || row > size || col > size){
      throw new IllegalArgumentException("Out of bounds")
    }
  }

  public static void main(string[] args) // test client if needed
}
