package debug;

public class xorDebug {
	public static void main(String[] args) {
		int[] arr = new int[]{3, 4};
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		swap(arr, 0, 1);
		System.out.println(arr[0]);
		System.out.println(arr[1]);
	}

	public static void swap(int[] arr, int i, int j){
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
}
