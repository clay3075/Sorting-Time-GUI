import java.util.concurrent.Callable;


public class QuickSort implements Runnable{
	int[] arr;
	
	public QuickSort(int[] arr) {
		this.arr = arr;
	}
	
	public void QuickSort() {
		QuickSort(0, arr.length - 1);
	}
	
	private void QuickSort(int start, int end) {
		//do actual sort
		if (start < end) {
			int index = partition(start, end);
			QuickSort(start, index - 1);
			QuickSort(index + 1, end);
		}
	}
	
	private void swap(int i1, int i2) {
		int temp = arr[i1];
		arr[i1]  = arr[i2];
		arr[i2]  = temp;
	}
	
	private int partition(int start, int end) {
		int pivotIndex = (start + end) / 2; //find pivot index
	    int pivotValue = arr[pivotIndex];


	    swap(pivotIndex, end); //move pivot value to end of list
	    pivotIndex = start; //move pivot to start of list

	 	//go through the list
	    for (int i = start; i < end; i++)
	    {
	    	//if an element is smaller than the pivot value 
	    	//make neccessary swaps
	        if (arr[i] < pivotValue)
	        {
	            swap(i, pivotIndex);
	            pivotIndex++;
	        }
		}
		swap(pivotIndex, end); //move pivot back in place
	    return pivotIndex;
	}
	

	public void run() {
		//code needed to time quick sort
		try {
			double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
				public Void call() {
					QuickSort();
					return null;
				}
			});
			new DisplayResultsPage(timeTaken, arr.length, "Quick Sort");
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
