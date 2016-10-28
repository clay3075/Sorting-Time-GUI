import java.util.concurrent.Callable;

public class BubbleSort implements Runnable {
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
  public BubbleSort(String arrayOption, int[] arr, int[] old) {
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
        public Void call() { bubbleSort(); return null; }
          // Display the timing results of the sorting algorithm
      }); new DisplayResultsPage(timeTaken, arr, old, "Bubble Sort (" +
        this.arrayOption + ")");
    } catch (Exception e) {
      // Print a stack trace if an exception occurs
      e.printStackTrace();
    }
  }

  /**
   * Perform the sorting algorithm with the given instance attributes.
   */
  public void bubbleSort() {
    // Create a variable to keep track of whether we're done sorting
    boolean done = false;
    // Loop over the length of the array unless done sorting
    for (int i = 0; i < this.arr.length && !done; i++) {
      // Assume that sorting is complete for this run
      done = true;
      // Iterate from the next index of `i` to the end of the array
      for (int j = i + 1; j < this.arr.length; j++)
        // Determine if we should swap the current element
        if (this.arr[i] > this.arr[j]) {
          // Perform the swap using a temporary variable
          int temp = this.arr[i];
          this.arr[i] = this.arr[j];
          this.arr[j] = temp;
          // Mark done as false since we swapped an item
          done = false;
        }
    }
  }
}
