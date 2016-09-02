import java.util.concurrent.Callable;


public class MergeSort implements Runnable{
	int[] arr;
	
	public MergeSort(int[] arr) {
		this.arr = arr;
	}
	
	public void mergeSort() {
		mergeSort(0, arr.length - 1);
	}
	
	private void mergeSort(int s, int e) {
		if (s < e) {
			int m = (s + e) / 2;
			mergeSort(s, m);
			mergeSort(m + 1, e);
			merge(s, m, e);
		}
	}
	
	private void merge(int s, int m, int e) {		
		int l1 = m - s + 1;
		int l2 = e - m;
		
		int t1[] = new int[l1];
		int t2[] = new int[l2];
		
		for (int i = 0; i < l1; i++) 
			t1[i] = arr[s + i];
		for (int i = 0; i < l2; i++)
			t2[i] = arr[m + 1 + i];
		
		int i = 0;
		int j = 0;
		int k = s;
		
		while (i < l1 && j < l2) {
			if (t1[i] <= t2[j]) {
				arr[k] = t1[i];
				i++;
			} else {
				arr[k] = t2[j];
				j++;
			}
			k++;
		}
		
		while (i < l1) {
			arr[k] = t1[i];
			i++;
			k++;
		}
		
		while (j < l2) {
			arr[k] = t2[j];
			j++;
			k++;
		}
		
	}

	public void run() {
		//code needed to time bubble sort
		try {
			double timeTaken = SortingTimer.getTimeToRun(arr, new Callable<Void>() {
				public Void call() {
					mergeSort();
					return null;
				}
			});
			new DisplayResultsPage(timeTaken, arr.length, "Merge Sort");
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
