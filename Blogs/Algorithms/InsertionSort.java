public class InsertionSort {
	public static void sort(Comparabale[] array) {
		int N = array.length();
		for (int i = 0; i < N ; i++) {
			for (int j = N; j > 0 && less(array[j], array[j - 1]); j-- ) {
				exchange(array, j, j - 1);
			}
		}
	}

	public static boolean less(int a, int b) {
		return (a - b < 0) ? true : false;
	}

	public static void exchange(Comparabale[] array, int i, j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}