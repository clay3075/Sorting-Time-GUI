import java.util.concurrent.Callable;


public class InsertionSort implements Runnable {
	
	private int[] arr;
	
	public InsertionSort(int[] arr) {
		this.arr = arr;
	}
		
	public void insertionSort() {
		for (int i = 1; i < arr.length; i++) {
			int anchor = i;
			while (anchor > 0 && arr[anchor] < arr[anchor - 1]) {
				int temp        = arr[anchor];
				arr[anchor]     = arr[anchor - 1];
				arr[anchor - 1] = temp;
				anchor--;
			}
		}
	}
	
	public void run() {
		//code needed to time insertion sort
		try {
			double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
				public Void call() {
					insertionSort();
					return null;
				}
			});
			new DisplayResultsPage(timeTaken, arr.length, "Insertion Sort");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
