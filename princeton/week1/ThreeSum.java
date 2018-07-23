// NOTE: This file is mostly for taking notes.
// brute force
public class ThreeSum {
  public static int count(int[] a) {
    int N = a.length;
    int count = 0;
    for(int i = 0; i < N; i++){
      for (int j = i + 1; j < N; j++){
        for(int k = j + 1; k < N; k++){
          if (a[i] + a[j] + a[k] == 0){
            count++;
          }
        }
      }
    }
    return count;
  }
  public static void main(String[] args){
    int[] a = In.readInts(args[0]);
    Stopwatch stopwatch = new Stopwatch();
    StdOut.println(ThreeSum.count(a));
    double time = stopwatch.elapsedTime();
  }
}

// binary search version

public class BinarySearchThreeSum {
  public static int count(int[] a){
    int N = a.length;
    int count = 0;
    a = a.sort(); // need a way to filter out duplicates still
    for(int i = 0; i < N; i++){
      for(int j = i + 1; j < N; j++){
        int sum = a[i] + a[j];
        if(binary_search(a, sum)){
            count++;
        }
      }
    }
  }
}

// two sum version of three sum? note: incomplete, only idea portion written out

public class ThreeSumTwoSum {
  public static int count(int[] a){
    int N = a.length;
    int count = 0;
    for(int i = 0; i < N; i++){
      int target = a[i] * -1;
      if(twoSum(a, target)){
        count++;
      }
    }
  }
  private boolean twoSum(array, target){
    int N = array.length;
    HashTable hash = new HashTable();
    for(int i = 0; i < N; i++){
      int complement = target - array[i];
      if(hash.containsKey(complement)){
        return true
      }else{
        hash.puts(complement, "true");
      }
    }
    return false;
  }
}
// implemented with a hash
