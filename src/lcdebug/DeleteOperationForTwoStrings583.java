package lcdebug;

public class DeleteOperationForTwoStrings583 {

	public static int minDistance(String word1, String word2) {
		if(word1 == null || word2==null) return 0;
		int n = word1.length(), m = word2.length();
		if(n==0 || m==0) return n==0? m: n;

		char[] arr1 = word1.toCharArray();
		char[] arr2 = word2.toCharArray();
		int[][] dp = new int[n][m];
		boolean flag = false;
		for(int i=0;i<n;i++){
			if(flag || arr1[i] == arr2[0]){
				if(!flag) flag = true;
				dp[i][0] = 1;
			}
		}
		flag = false;
		for(int i=0;i<m;i++){
			if(flag || arr1[0] == arr2[i]){
				if(!flag) flag = true;
				dp[0][i] = 1;
			}
		}

		for(int i=1;i<n;i++){
			for(int j=1;j<m;j++){
				if(arr1[i] == arr2[j]){
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		return m+n-2*dp[n-1][m-1];
	}

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		System.out.println(minDistance(a, b));

		String c = "eat";
		String d = "sea";
		System.out.println(minDistance(c, d));
	}
}
