package lcdebug;

public class MaximumSubarray53 {
	private static int maxSubArray(int[] nums) {
		// int[] dp = new int[nums.length];
		int pre = nums[0];
		int res = Integer.MIN_VALUE;
		// int res = nums[0];
		// dp[0] = nums[0];

		// for(int i=1;i<nums.length;++i){
		//     dp[i] = Math.max(nums[i] + dp[i-1], nums[i]);
		//     res = Math.max(dp[i], res);
		// }

		for (int i = 1; i < nums.length; i++) {
			if(pre < 0){
				pre = nums[i];
			}
			else{
				pre += nums[i];
			}
			
			res = Math.max(res, pre);
		}

		return res;
	}

	public static void main(String[] args) {
		int[] testArray = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

		System.out.println(maxSubArray(testArray));
	}
}
