public class MergeSort {
	public static void main(String[] args) {
		MergeSort object = new MergeSort();
		int[] test = new int[]{7,1,4,5,8,2,6,3};

		System.out.println("The original array is: ");
		for(int t:test){
			System.out.print(t + " ");
		}

		System.out.println();

		object.sort(test);
		System.out.println("Afer sorting with MergeSort:");
		for(int t:test){
			System.out.print(t + " ");
		}
	}

	private void sort(int[] nums){
		mergeSortInternally(nums, 0, nums.length-1);
	}

	private void mergeSortInternally(int[] nums, int left, int right){
		if(left>=right) return;

		int mid = left + (right-left)/2;

		mergeSortInternally(nums, left, mid);
		mergeSortInternally(nums, mid+1, right);
		merge(nums, left, mid, right);
	}

	private void merge(int[] nums, int left, int mid, int right){
		int i = left;
		int j = mid+1;
		int idx = 0;
		int[] tmpArray = new int[right-left+1];

		while(i<=mid && j<=right){
			if(nums[i]<=nums[j]) tmpArray[idx++] = nums[i++];
			else tmpArray[idx++] = nums[j++];
		}

		while(i<=mid) tmpArray[idx++] = nums[i++];
		while(j<=right) tmpArray[idx++] = nums[j++];

		for(int k=0;k<right-left+1;k++){
			nums[k+left] = tmpArray[k];
		}
	}
}
