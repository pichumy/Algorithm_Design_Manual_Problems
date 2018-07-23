// NOTE: These files are more for notes rather than compilation. You would need to move the WeightedQuickUnion code to a seperate file in order to use it. 
// If the trees dont get too tall (such as a node being linked to another node in a line), then this
// works pretty well actually, but the worst case scenario (nodes linked in a line) gives you really bad runtime
public class QuickUnion{
  private int[] id;
  // O(n)
  public QuickUnion(int N){
    id = new int[N];
    for(int i = 0; i < N; i++) id[i] = i;
  }
  // O(n)
  private int root(int i){
    while (i != id[i]) i = id[i];
    return i;
  }
  // O(n)
  public boolean connected(int p, int q){
    return root(p) == root(q);
  }
  // O(n)
  public void union(int p, int q){
    int i = root[p];
    int j = root[q];
    id[i] = j;
  }
}
// This is much better. The bottleneck was caused by root, and this makes sure the root only grows at a pace of log(n). Overall complexity while working is O(n) for connecting N objects.
// (# objects + #unions * lg(star)#objects) => (#objects + #unions * 5) => linear
// *technically* not linear, but basically linear.
// Reasoning: Every union, the smaller tree at least *doubles* in size, since the other tree will be bigger or the same size. You can only double log(n) times before you reach N (which is the total number of nodes). Hence, the depth will always be at most log(n) or smaller.
public class WeightedQuickUnion {
  private int[] id;
  private int[] sz;
  // O(n)
  public QuickUnion(int N){
    id = new int[N];
    for(int i = 0; i < N; i++) { id[i] = i; sz[i] = 1; }
  }
  // O(logn)
  private int root(int i){
    while (i != id[i])
    {
      id[i] = id[id[i]]; // path compression for optimization (flattening)
      i = id[i];
      return i;
    }
  }
  // O(logn)
  public boolean connected(int p, int q){
    return root(p) == root(q);
  }
  // O(logn)
  public void union(int p, int q){
    int i = root[p];
    int j = root[q];
    if(i == j) return;
    if(sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i] }
    else              { id[j] = i; sz[i] += sz[j] }
  }
}
