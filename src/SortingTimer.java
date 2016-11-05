import java.util.concurrent.Callable;

public class SortingTimer {
  public static double getTimeToRun(int[] arr, Callable<Void> sortingFunc) throws Exception {

    //get how long it took to run said function
    double startTime = System.nanoTime() / 1000000.0;
    double endTime   = 0.0;
    double time      = 0.0;
    //run function
    sortingFunc.call();
    //end time
    endTime = System.nanoTime() / 1000000.0;
    //get total time
    time = endTime - startTime;

    return time;
  }
}
