public class SelectionSort {
	public static void sort(Comparable[] array) {
		int N = array.length();
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			// 把选择出来的当前最小的元素放到最左边
			if (min != i) {
				int temp = array[i];
				array[i] = array[min];
				array[min] = temp;	
			}
		}
	}
}