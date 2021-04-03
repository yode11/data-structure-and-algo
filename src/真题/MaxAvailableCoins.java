package 真题;

import java.util.*;

/**
 * 360 2021年3月春招
 * <p>
 * 题目：
 * 现在有两个等长数组，长度为N。第一个数组coins，表示每个index位置是一个宝库，值是这个宝库里有的金币数量；
 * 第二个数组distance，每个index位置的值表示当前位置距离第一个位置（index=0）的距离。
 * 如果它要想拿去一个宝库的金币，就需要启动飞船，飞到这个宝库的位置，拿金币，
 * 如果他想出发去下一个宝库，必须再次启动。
 * 一个人乘坐飞船的启动次数是有限的，是K次。
 * 且每次飞行的距离有限制，最多飞M千米。
 * 一个人会从下标为0的位置出发，问它在给定K，M，distance，coins的情况下，最多能拿到多少金币？
 */
public class MaxAvailableCoins {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt(); // N个宝库
			int M = sc.nextInt(); // 每次最多飞M千米
			int K = sc.nextInt(); // 最多启动k次，即加上第一个宝库，最多一共拿K+1个宝库的钱

			int[] distance = new int[N];
			int[] coins = new int[N];

			for (int i = 0; i < N; i++) {
				distance[i] = sc.nextInt();
				coins[i] = sc.nextInt();
			}

			System.out.println(helper(distance, coins, N, M, K));
		}
	}

	static private int helper(int[] distance, int[] coins, int N, int M, int K) {
		int[][] dp = new int[K + 1][N];
		for (int i = 0; i < N; i++) {
			dp[0][i] = coins[i];
		}

		for (int i = 1; i <= K; i++) {
			for (int j = N - 1; j >= 0; j--) {
				int max = 0;
				int m = j + 1;
				while (m < N && distance[m] - distance[j] <= M) {
					max = Math.max(max, dp[i - 1][m]);
					m++;
				}

				dp[i][j] = coins[j] + max;
			}
		}

		return dp[K][0];
	}
}
