public class QuickSort {
	public static void main(String[] args) {
		QuickSort object = new QuickSort();

		int[] test = new int[]{7,1,4,5,8,2,6,3};
		System.out.println("The original array is: ");
		for(int t:test){
			System.out.print(t + " ");
		}

		System.out.println();

		object.sort(test);
		System.out.println("Afer sorting with QuickSort:");
		for(int t:test){
			System.out.print(t + " ");
		}
	}

	private void sort(int[] nums){
		quickSortInternally(nums, 0, nums.length-1);
	}

	private void quickSortInternally(int[] nums, int left, int right){
		if(left>=right) return;
		int pivot = partition(nums, left, right);

		quickSortInternally(nums, left, pivot-1);
		quickSortInternally(nums, pivot+1, right);
	}

	private int partition(int[] nums, int left, int right){
		int tmpNum = (int)(Math.random()*(right-left+1)) + left;
		swap(nums, tmpNum, right);
		int pivot = nums[right];
		int i=left;

		for(int j=left;j<right;j++){
			if(nums[j] < pivot){
				swap(nums, i, j);
				i++;
			}
		}

		swap(nums, i, right);
		return i;
	}

	private void swap(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
