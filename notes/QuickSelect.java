import java.util.*;

class QuickSelect {

	public int select(int[] arr, int left, int right, int K) {
		if (left == right) return arr[left];
		int split = partition(arr, left, right);

		int length = split - left + 1;
		if (length == K) return arr[split];
		else if (K < length) return select(arr, left, split - 1, K);
		else return select(arr, split + 1, right, K - length);
	}

	private int partition(int[] arr, int left, int right) {
		//   l       r 
		//[3,5,4,6,1,2,9,8,7]
		//     l   r
		//[3,2,4,6,1,5,9,8,7]
        //     r l
		//[3,2,1,6,4,5,9,8,7]
		//left   r
		//  [1,2,3,6,4,5,9,8,7]
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

	private void swap(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp; 
	}


	public static void main(String[] args) {
		QuickSelect qs = new QuickSelect();
		int[] arr = new int[]{3,5,4,6,1,2,9,8,7};
		int ans = qs.select(arr, 0, arr.length - 1, 1);
		System.out.println(ans);

	}
}