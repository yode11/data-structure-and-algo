package debug;

import java.util.LinkedList;

public class LargestRectangleInHistogram84 {
	private static int largestRectangleArea(int[] heights) {
		if(heights==null || heights.length == 0) return 0;

		LinkedList<Integer> stack = new LinkedList<>();

		int[] newHeights = new int[heights.length+2];

		for(int i=0;i<heights.length;i++){
			newHeights[i+1] = heights[i];
		}

		int res = 0;

		for(int i = 0; i < newHeights.length; i++){
			while(!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]){
				int cur = stack.pop();
				res = Math.max(res, (i-stack.peek()-1) * newHeights[cur]);
			}

			stack.push(i);
		}

		return res;
	}

	public static void main(String[] args) {
		int[] testArray = new int[]{2,1,5,6,2,3};
		System.out.println(largestRectangleArea(testArray));
	}
}
