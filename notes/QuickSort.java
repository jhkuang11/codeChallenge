import java.util.*;

class QuickSort {

	public static void sort(int[] arr, int left, int right) {
		if (left < right) {
			int split = partition(arr, left, right);
			sort(arr, left, split - 1);
			sort(arr, split + 1, right);
		}
	}

	private static int partition(int[] arr, int left, int right) {
		int pivot = arr[left];
		int l = left + 1;
		int r = right;

		while (true) {
			while (l < right && arr[l] < pivot) ++l;
			while (r > left && arr[r] > pivot) --r;

			if (l >= r) break;
			swap(arr, l, r);
		}
		swap(arr, left, r);
		return r;
	}

	private static void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
	}

	public static void main(String[] args) {
		//Quicksort qs = new QuickSort();
		int[] arr = new int[]{5,6,3,2,1,4,9,8,7};
		System.out.println(Arrays.toString(arr));
		QuickSort.sort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}