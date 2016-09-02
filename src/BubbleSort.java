import java.util.concurrent.Callable;


public class BubbleSort implements Runnable {
	int[] arr;
	
	public BubbleSort(int[] arr) {
		this.arr = arr;
	}
	
	public void bubbleSort() {
		boolean done = false;
		
		for (int i = 0; i < this.arr.length && !done; i++) {
			done = true;
			for (int j = i + 1; j < this.arr.length; j++) {
				if (this.arr[i] > this.arr[j]) {
					int temp = this.arr[i];
					this.arr[i] = this.arr[j];
					this.arr[j] = temp;
					done = false;
				} 
			}
		}
	}

	public void run() {
		//code needed to time bubble sort
		try {
			double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
				public Void call() {
					bubbleSort();
					return null;
				}
			});
			new DisplayResultsPage(timeTaken, arr.length, "Bubble Sort");
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
