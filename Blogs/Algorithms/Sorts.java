public class Sorts {

	/** 
	 *  冒泡排序
	 */
	public static void BubbleSortFakeCode(int[] list) {
		if (list.length == 0) {
			return;
		}
		boolean needNextPass = true;
		for (int k = 1; k < list.length && needNextPass; k++) {
			needNextPass = false;
			for (int i; i < list.length - k; i++) {
				if (list[i] > list[i + 1]) {
					swap list[i] with list[i + 1];
					int temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = list[i];
					needNextPass = true;
				}
			}
		}
	}

	/**
	  * 归并排序
	  * 使用到了System.arraycopy这个方法
	  * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
	  */
	public static void mergeSort(int[] list) {
		// if (list.length > 1) {
		// 	mergeSort(list[0... list.length / 2]);
		// 	mergeSort(list[list.length / 2 + 1 ... list.length]);
		// 	merge list[0... list.length / 2] with list[list.length / 2 + 1 ... list.length];
		// }

		if (list.length > 1) {
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);

			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);

			merge(firstHalf, secondHalf, list);
		}
	}

	public static void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; // current index in list1
		int current2 = 0; // current index in list2
		int current3 = 0; // current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
			} else {
				temp[current3++] = list2[current2++];
 			}

		}

		while (current1 < list1.length) {
			temp[current3++] = list1[current1++];
		}

		while (current2 < list2.length) {
			temp[current3++] = list2[current2++];
 		}
	}



	/**
	 * 快速排序
	 * 首先快排先将一个数组通过一个pivot将其划分为二
	 * 然后递归的对其左序列和右序列进行排序，因此quickSort方法应该有三
	 * 注意快排和归并的时间复杂度为O(nlogn)
	 */
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	public static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	public static int partition(int[] list, int first, int last) {
		int pivot = list[first]; // choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] < pivot) {
				low++;
			}

			while (low <= high && list[high] >= pivot) {
				high--;
			}

			if (high > low) {
				int temp = list[low];
				list[low] = list[high];
				list[high] = temp;
			}
		}

		while (high > first && list[high] >= pivot) {
			high--;
		}

		if (pivot < list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}

	}
}