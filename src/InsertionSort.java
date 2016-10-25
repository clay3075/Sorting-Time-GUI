import java.util.concurrent.Callable;

public class InsertionSort implements Runnable {
  /**
   * @var arr The array of integers to test the algorithm with.
   */
  private int[] arr;

  /**
   * Prepare the sorting algorithm for timed testing.
   *
   * @param  arr  An array of integers to test the algorithm with.
   */
  public InsertionSort(int[] arr) {
    // Assign the internal array reference to the provided parameter
    this.arr = arr;
  }

  /**
   * Runs the sorting algorithm via `SortingTimer` to determine the amount of
   * time required to run the algorithm.
   *
   * Displays a window with the results of the timed sort.
   */
  public void run() {
    try {
      // Calculate the time required to perform the sorting algorithm
      double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
        public Void call() { insertionSort(); return null; }
          // Display the timing results of the sorting algorithm
      }); new DisplayResultsPage(timeTaken, arr.length, "Insertion Sort");
    } catch (Exception e) {
      // Print a stack trace if an exception occurs
      e.printStackTrace();
    }
  }

  /**
   * Perform the sorting algorithm with the given instance attributes.
   */
  public void insertionSort() {
    // Iterate over each element in the array from the second index
    for (int i = 1; i < arr.length; i++) {
      // Find the maximum element in the subarray [j,i] and swap the max to the
      // ending position
      for (int j = i; j > 0 && arr[j - 1] > arr[j]; --j) {
        int temp   = arr[j];
        arr[j]     = arr[j - 1];
        arr[j - 1] = temp;
      }
    }
  }
}
