package 笔试;

import java.util.*;

public class Main360 {
	public static void main(String[] args) {
		// 题目使用
		// class的名字必须为Main
		// 不要带任何package名字
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt(); // N个宝库
			int M = sc.nextInt(); // 每次最多飞M千米
			int K = sc.nextInt(); // 最多启动k次，即加上第一个宝库，最多一共拿K+1个宝库的钱

			int[] distance = new int[N];
			int[] coins = new int[N];

			for(int i=0;i<N;i++){
				distance[i] = sc.nextInt();
				coins[i] = sc.nextInt();
			}

			System.out.println(helper(distance, coins, N, M, K));
		}
	}

	static int helper(int[] distance, int[] coins, int N, int M, int K){
		int[][] dp = new int[K+1][N];
		for(int i=0;i<N;i++){
			dp[0][i] = coins[i];
		}

		for(int i=1;i<=K;i++){
			for(int j=N-1;j>=0;j--){
				int max = 0;
				int m = j+1;
				while(m < N && distance[m] - distance[j] <= M){
					max = Math.max(max, dp[i-1][m]);
					m++;
				}

				dp[i][j] = coins[j] + max;
			}
		}

		return dp[K][0];
	}

//	static public long helper(int[] arr, int n){
//		long sum = 0;
//		for(int i=0;i<n;i++){
//			sum += arr[i];
//			for(int j=i+1;j<n;j++){
//				sum += (arr[i] | arr[j]);
//			}
//		}
//		return sum;
//	}
}
