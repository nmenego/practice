package sort.quicksort;

import java.util.Arrays;

/**
 * Simple quicksort algorithm to sort integer arrays.
 * 
 * @author nicomartin.enego
 *
 */
public class Quicksort {

	public static void main(String[] args) {
		Quicksort qs = new Quicksort();
		int[] arr = qs.sanitize(args);
		System.out.println("before:" + Arrays.toString(arr));
		qs.sort(arr);
		System.out.println("after:" + Arrays.toString(arr));
	}

	/**
	 * Parse input.
	 * 
	 * @param args
	 *            string array
	 * @return int array
	 */
	public int[] sanitize(String[] args) {
		int[] arr = new int[args.length];
		for (int x = 0; x < args.length; x++) {
			arr[x] = Integer.parseInt(args[x]);
		}

		return arr;
	}

	/**
	 * Main sort method.
	 * 
	 * @param arr
	 *            int array to sort
	 */
	public void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private void sort(int[] arr, int startIdx, int endIdx) {
		if (startIdx < endIdx) {
			int pvtIdx = partition(arr, startIdx, endIdx);
			sort(arr, startIdx, pvtIdx - 1);
			sort(arr, pvtIdx + 1, endIdx);
		}
	}

	private int partition(int[] arr, int startIdx, int endIdx) {
		int pvtIdx = getPivotIndex(startIdx, endIdx);
		int lIdx = startIdx;
		int rIdx = endIdx - 1; // last idx is pivot
		// put pivot in end of sub-array.
		swap(arr, pvtIdx, endIdx);
		pvtIdx = endIdx;
		int pvtVal = arr[pvtIdx];

		while (lIdx < rIdx) {

			while (arr[lIdx] <= pvtVal && lIdx < rIdx) {
				lIdx++;
			}

			while (arr[rIdx] > pvtVal && lIdx < rIdx) {
				rIdx--;
			}

			if (lIdx < rIdx) {
				swap(arr, lIdx, rIdx);
			}
		}

		swap(arr, rIdx, pvtIdx);
		return rIdx;
	}

	private int getPivotIndex(int a, int b) {
		int idx = (a + b) / 2;
		return idx;
	}

	private void swap(int[] arr, int idxA, int idxB) {
		System.out.println(String
				.format("swap: idxA: %s, idxB: %s", idxA, idxB));
		if (idxA != idxB) {
			arr[idxA] = arr[idxA] - arr[idxB];
			arr[idxB] = arr[idxA] + arr[idxB];
			arr[idxA] = arr[idxB] - arr[idxA];
		}
		System.out.println(" - " + Arrays.toString(arr));
	}
}
