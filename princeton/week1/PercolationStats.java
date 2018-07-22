import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private double[] results; // keep track of results of each trial
  // perform trials independent experiments on an n x n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("n and trials must be greater than 0");
    }
    results = new double[trials];
    runTrials(n, trials);
  }

  private void runTrials(int size, int trials) {
    for (int i = 0; i < trials; i++) {
      results[i] = runTrial(size);
    }
  }

  private double runTrial(int size) {
    Percolation board = new Percolation(size);
    double spacesUsed = 0;
    while (!board.percolates()) {
      openRandomBlock(size, board);
      spacesUsed += 1;
    }
    return spacesUsed / (size * size);
  }

  private void openRandomBlock(int size, Percolation board) {
    boolean isOpen = true;
    int row = 0;
    int col = 0;
    while(isOpen) {
      row = random(size);
      col = random(size);
      isOpen = board.isOpen(row, col);
    }
    board.open(row, col);
  }

  private int random(int size){
    return StdRandom.uniform(1, size + 1);
  }
  // sample mean of percolation threshold
  public double mean() {
    // takes an array of numbers and returns the mean
    return StdStats.mean(results);
  }
  // sample standard deviation of percolation threshold
  public double stddev() {
      // takes an array of numbers and returns the stddev
      return StdStats.stddev(results);
  }
  // lower bound
  public double confidenceLo() {
    return mean() - confidenceMiddle();
  }
  // upper bound
  public double confidenceHi() {
    return mean() + confidenceMiddle();
  }

  private double confidenceMiddle() {
    // 1.96 is used for a 95% confidence interval
    return (1.96 * stddev() / Math.sqrt(results.length));
  }

  public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println("mean =                     " + stats.mean());
    System.out.println("stddev =                   " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi());
  }
}
