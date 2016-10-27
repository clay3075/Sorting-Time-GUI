import java.util.concurrent.Callable;

public class QuickSort implements Runnable {
  /**
   * @var arr The array of integers to test the algorithm with.
   */
  private int[] arr;
  /**
   * @var old The old array before sorting is complete.
   */
  private int[] old;

  /**
   * Prepare the sorting algorithm for timed testing.
   *
   * @param  arr  An array of integers to test the algorithm with.
   */
  public QuickSort(int[] arr, int[] old) {
    // Assign the internal array reference to the provided parameter
    this.arr = arr;
    this.old = old;
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
        public Void call() { quickSort(); return null; }
          // Display the timing results of the sorting algorithm
      }); new DisplayResultsPage(timeTaken, arr, old, "Quick Sort");
    } catch (Exception e) {
      // Print a stack trace if an exception occurs
      e.printStackTrace();
    }
  }

  /**
   * Perform the sorting algorithm with the given instance attributes.
   */
  public void quickSort() {
    // Call quick sort on the entire array
    quickSort(0, arr.length - 1);
  }

  /**
   * Performs a quick sort on the provided subrange.
   *
   * @param   start  The starting item's index.
   * @param   end    The ending item's index.
   */
  private void quickSort(int start, int end) {
    // Check that the starting index is larger than the ending index
    if (start < end) {
      // Partition the input array on the given range
      int index = partition(start, end);
      // Perform quick sort on the two partitions
      quickSort(start, index - 1);
      quickSort(index + 1, end);
    }
  }

  /**
   * Swaps the values at the provided indicies in the instance's array.
   *
   * @param  i1  The index of the first item.
   * @param  i2  The index of the second item.
   */
  private void swap(int i1, int i2) {
    // Swap the items at the provided indicies using a temporary variable
    int temp = arr[i1];
    arr[i1]  = arr[i2];
    arr[i2]  = temp;
  }

  /**
   * Partitions an array on the given range by selecting the middle index of the
   * provided range and using its value for the pivot value.
   *
   * All values on the provided range less than or equal to the pivot value are
   * moved left of the pivot and right for all items otherwise.
   *
   * @param   start  The starting item's index.
   * @param   end    The ending item's index.
   *
   * @return  int    The index of the resulting pivot.
   */
  private int partition(int start, int end) {
    // Select the middle index of the provided range as the pivot
    int pivotIndex = (start + end) / 2;
    int pivotValue = arr[pivotIndex];
    // Swap the pivot value with the range's end value
    swap(pivotIndex, end);
    // Set the pivot index to the start of the range
    pivotIndex = start;
    // Iterate over the provided range
    for (int i = start; i < end; i++)
      // If the current value is less than the pivot value, move it to the left
      // of the pivot index
      if (arr[i] < pivotValue) swap(i, pivotIndex++);
    // Move the original pivot value to the resulting pivot index
    swap(pivotIndex, end);
    // Return the resulting pivot index
    return pivotIndex;
  }
}
