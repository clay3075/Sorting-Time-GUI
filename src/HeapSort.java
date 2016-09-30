import java.util.concurrent.Callable;


public class HeapSort implements Runnable {
	
	private int[] arr;
	private int   heapSize;
	
	private void swap(int i1, int i2) {
		int temp = arr[i1];
		arr[i1]  = arr[i2];
		arr[i2]  = temp;
	}
	
	private int getParent(int index) {
		return index / 2;
	}
	
	private int getLeft(int index) {
		return index * 2 + 1;
	}
	
	private int getRight(int index) {
		return index * 2 + 2;
	}
	
	public HeapSort(int[] arr) {
		this.arr = arr;
		heapSize = arr.length;
	}
	
	private void makeHeap() {
		for (int i = heapSize / 2; i >= 0; i--) {
			heapify(i);
		}
	}
	
	private void heapify(int index) {
		int left    = getLeft(index);
		int right   = getRight(index);
		int greater = left;
		if (left < heapSize) {
			if (right < heapSize) {
				if (arr[right] > arr[greater]) 
					greater = right;
			}
			if (arr[greater] > arr[index]) {
				swap(greater, index);
				heapify(greater);
			}
		}
	}
	
	public void heapSort() {
		makeHeap();
		while (heapSize - 1 > 0) {
			swap(0, heapSize - 1);
			heapSize--;
			makeHeap();
		}
	}

	
	public void run() {
		//code needed to time heap sort
		try {
			double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
				public Void call() {
					heapSort();
					return null;
				}
			});
			new DisplayResultsPage(timeTaken, arr.length, "Heap Sort");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
