import java.util.concurrent.Callable;

public class MergeSort implements Runnable {
  /**
   * @var arr The array of integers to test the algorithm with.
   */
  private int[] arr;
  /**
   * @var old The old array before sorting is complete.
   */
  private int[] old;
  /**
   * @var arrayOption The name of the type of input array.
   */
  private String arrayOption;

  /**
   * Prepare the sorting algorithm for timed testing.
   *
   * @param  arr  An array of integers to test the algorithm with.
   */
  public MergeSort(String arrayOption, int[] arr, int[] old) {
    // Assign the internal array reference to the provided parameter
    this.arr = arr;
    this.old = old;
    this.arrayOption = arrayOption;
  }

  /**
   * Runs the sorting algorithm via `SortingTimer` to determine the amount of
   * time required to run the algorithm.
   *
   * Displays a window with the results of the timed sort.
   */
  public void run() {
    try { System.out.println("Begin sorting ...");
      // Calculate the time required to perform the sorting algorithm
      double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
        public Void call() { mergeSort(); return null; }
          // Display the timing results of the sorting algorithm
      }); new DisplayResultsPage(timeTaken, arr, old, "Merge Sort (" +
        this.arrayOption + ")");
    } catch (Exception e) {
      // Print a stack trace if an exception occurs
      e.printStackTrace();
    }
  }

  /**
   * Perform the sorting algorithm with the given instance attributes.
   */
  public void mergeSort() {
    // Sort the entire subarray of the input array
    mergeSort(0, arr.length - 1);
  }

  /**
   * Performs merge sort on the provided subarray.
   *
   * @param  s  The starting index of the subarray.
   * @param  e  The ending index of the subarray.
   */
  private void mergeSort(int s, int e) {
    if (s < e) {
      int m = (s + e) / 2;
      // Split the input array into two subarrays
      mergeSort(s, m);
      mergeSort(m + 1, e);
      // Merge the two sorted subarrays
      merge(s, m, e);
    }
  }

  /**
   * Merges two subarrays into the original input array with temporary arrays.
   *
   * @param  s  The starting index of the first subarray.
   * @param  m  The midpoint separating the two subarrays.
   * @param  e  The ending index of the second subarray.
   */
  private void merge(int s, int m, int e) {
    // Determine the length of both subarrays
    int l1 = m - s + 1;
    int l2 = e - m;

    // Create two temporary arrays to hold the subarrays
    int t1[] = new int[l1];
    int t2[] = new int[l2];

    // Copy the subarrays into their respective temporary arrays
    for (int i = 0; i < l1; i++)
      t1[i] = arr[s + i];
    for (int i = 0; i < l2; i++)
      t2[i] = arr[m + 1 + i];

    int i = 0;
    int j = 0;
    int k = s;
    // Walk through the two subarrays and place the appropriate elements into
    // the original array in sorted order until there is a size mismatch
    while (i < l1 && j < l2) {
      // If the first temporary array contains the smaller element ...
      if (t1[i] <= t2[j]) {
        // Place it into the original array and increment the temporary's index
        arr[k] = t1[i]; i++;
      } else {
        // Place it into the original array and increment the temporary's index
        arr[k] = t2[j]; j++;
      } // Increment the original array's index
      k++;
    }

    // Place the remaining elements in the first temporary array into the
    // original array (size mismatch accommodation)
    while (i < l1) { arr[k] = t1[i]; i++; k++; }

    // Place the remaining elements in the first temporary array into the
    // original array (size mismatch accommodation)
    while (j < l2) { arr[k] = t2[j]; j++; k++; }
  }
}
