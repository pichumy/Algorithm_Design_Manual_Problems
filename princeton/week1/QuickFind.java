// This becomes O(n^2) if you run union N times. (Link all the nodes together)
public class QuickFind{
  private int[] id;
  // O(n)
  public QuickFind(int N){
    id = new int[N];
    for(int i = 0; i < N; i++){
      id[i] = i;
    }
  }
  // O(1)
  public boolean connected(int p, int q){
    return id[p] == id[q];
  }
  // O(n)
  public void union(int p, int q){
    int pid = id[p];
    int qid = id[q];
    if(pid == qid) return;
    for(int i = 0; i < id.length; i++){
      if(id[i] == pid) id[i] = qid;
    }
  }
}
