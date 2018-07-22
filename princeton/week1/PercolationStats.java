import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
  private double[] results; // keep track of results of each trial
  // perform trials independent experiments on an n x n grid
  public PercolationStats(int n, int trials){
    if(n <= 0 || trials <= 0){
      throw IllegalArgumentException("n and trials must be greater than 0");
    }
    results = new double[trials];
  }

  private void runTrials(int size, int trials){
    for(int i = 0; i < trials; i++){
      results[i] = runTrial(size);
    }
  }

  private double runTrial(int size){
    Percolation board = new Percolation(size);
    double spacesUsed = 0;
    while(!board.percolates()){
      int row = StdRandom.uniform(1, size + 1);
      int col = StdRandom.unfirom(1, size + 1);
      if(!board.isOpen(row, col)){
        board.open(row, col);
        spacesUsed++;
      }
    }
    return spacesUsed / (size * size);
  }
  // sample mean of percolation threshold
  public double mean(){
    // takes an array of numbers and returns the mean
    return StdStats.mean(results);
  }
  // sample standard deviation of percolation threshold
  public double stddev(){
      // takes an array of numbers and returns the stddev
      return StdStats.stddev(results);
  }
  // lower bound
  public double confidenceLo(){
    // 1.96 is used for a 95% confidence interval
    return mean() - (1.96 * stddev() / Math.sqrt(results.length));
  }
  // upper bound
  public double confidenceHi(){
    return mean() + (1.96 * stddev() / Math.sqrt(results.length));
  }

  public static void main(String[] args){
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println("mean =                     " + stats.mean());
    System.out.println("stddev =                   " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo + ", " + stats.confidenceHi());
  }
}
