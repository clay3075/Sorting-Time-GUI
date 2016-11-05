import java.util.concurrent.Callable;

public class HeapSort implements Runnable {
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
   * @var comparisons The number of comparisons required for the sort.
   */
  private int    comparisons = 0;
  /**
   * @var heapSize The size of the array to be sorted.
   */
  private int    heapSize;

  /**
   * Prepare the sorting algorithm for timed testing.
   *
   * @param  arr  An array of integers to test the algorithm with.
   */
  public HeapSort(String arrayOption, int[] arr, int[] old) {
    // Assign the internal array reference to the provided parameter
    this.arr = arr;
    this.old = old;
    this.arrayOption = arrayOption;
    // Store the original size of the input array
    heapSize = arr.length;
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
        public Void call() { heapSort(); return null; }
          // Display the timing results of the sorting algorithm
      }); new DisplayResultsPage(timeTaken, comparisons, arr, old, 
          "Heap Sort (" + this.arrayOption + ")");
    } catch (Exception e) {
      // Print a stack trace if an exception occurs
      e.printStackTrace();
    }
  }

  /**
   * Perform the sorting algorithm with the given instance attributes.
   */
  public void heapSort() {
    // Make a heap out of the provided array
    makeHeap();
    // For each item in the array ...
    while (heapSize - 1 > 0) { ++comparisons;
      // Swap the first and last elements of the heap
      swap(0, heapSize - 1);
      // Reduce the heap size
      --heapSize;
      // Re-heapify to fix the new root
      heapify(0);
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
   * Calculates the left child index of the provided index in the context of a
   * heap array.
   *
   * @param   index  The index of the parent.
   *
   * @return         The index of the left child.
   */
  private int getLeft(int index) {
    // Calculate the index of the left child
    return index * 2 + 1;
  }

  /**
   * Calculates the right child index of the provided index in the context of a
   * heap array.
   *
   * @param   index  The index of the parent.
   *
   * @return         The index of the right child.
   */
  private int getRight(int index) {
    // Calculate the index of the right child
    return index * 2 + 2;
  }

  /**
   * Recursively calls `heapify()` for the minimum number of nodes in the heap
   * in order to make the provided input array into a heap.
   */
  private void makeHeap() {
    // Heapify from the middle array item to the root of the tree
    for (int i = heapSize / 2; i >= 0; --i) { ++comparisons; heapify(i); }
  }

  /**
   * Makes the subtree at the given index a heap by recursively calling
   * `heapify()` until heap conditions are satisfied for the given subtree.
   *
   * @param  index  The index for the root of the subtree.
   */
  private void heapify(int index) {
    // Get the index for the left and right child of the provided index
    int left    = getLeft (index);
    int right   = getRight(index);
    // Assume that the left index is greatest
    int greater = left;
    // Check if the left index is less than the heap size
    if (left < heapSize) { ++comparisons;
      // Check if the left index is less than the heap size
      if (right < heapSize) { ++comparisons;
        // Check if the right value is greater than the left value
        if (arr[right] > arr[greater]) { ++comparisons;
          // Assign the right index to the greater index placeholder
          greater = right; }
      } // Determine if the max child is larger than its parent
      if (arr[greater] > arr[index]) { ++comparisons;
        // Swap the parent and max child
        swap(greater, index);
        // Re-heapify at the index of the max child's old position
        heapify(greater);
      }
    }
  }
}
