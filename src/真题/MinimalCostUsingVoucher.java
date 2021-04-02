package 真题;

import java.util.*;

/**
 * 美团真题，华为类似题
 * 链接：https://www.nowcoder.com/questionTerminal/95329d9a55b94e3fb2da475d3d052164?toCommentId=3377639
 * 	来源：牛客网
 *
 * 	你打开了美了么外卖，选择了一家店，你手里有一张满X元减10元的券，店里总共有n种菜，
 * 	第i种菜一份需要A_i元，因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，
 * 	现在问你最少需要选择多少元的商品才能使用这张券。
 *
 * 	输入描述:
 * 	第一行两个正整数n和X，分别表示菜品数量和券的最低使用价格。（1≤n≤100, 1≤X≤10000）
 * 	接下来一行n个整数，第i个整数表示第i种菜品的价格。（1≤A_i≤100）
 * 	输出描述:
 * 	一个数，表示最少需要选择多少元的菜才能使用这张满X元减10元的券，保证有解。
 * 	示例1
 * 	输入
 * 	5 20
 * 	18 19 17 6 7
 *
 * 	输出
 * 	23
 */
public class MinimalCostUsingVoucher {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int dishNum = in.nextInt();
		int voucherThreshold = in.nextInt();
		int[] prices = new int[dishNum];

		for (int i = 0; i < dishNum; i++) {
			prices[i] = in.nextInt();
		}

		// 最普通做法，时间空间复杂度都是最大，但是符合正向思维，笔试可以用
		System.out.println("非空间压缩的求满足阈值以上的最小值求法的答案是：");
		System.out.println(helper1(prices, dishNum, voucherThreshold));

		// 时间和空间都是最优，面试时候用
		System.out.println("使用滚动数组进行空间压缩以后，且把问题转换成01背包问题求最大值的DP解法的答案是：");
		System.out.println(helper2(prices, dishNum, voucherThreshold));

		// 只是普通做法和最优做法的一种中间形态
		System.out.println("非空间压缩，但是把第一种解法改成了01背包问题求最大值解法的答案是：");
		System.out.println(helper3(prices, dishNum, voucherThreshold));

	}

	/*
	非空间压缩的经典DP解法，根据题目的思路考虑，在编号为[0,i]范围的菜的任意组合，能凑起至少j元，花费的最少的钱数是多少
	 */
	private static int helper1(int[] prices, int dishNum, int voucherThreshold) {
//		因为本题中没有说明如果所有的价格加起来都不能达到减免优惠的最小金额的话该怎么办，
//		虽然在没有判断这一步的情况下，牛客上的所有tests仍然能通过，但是这个解答不完整。
//		所以这里自己定义，如果出现这种情况，则直接返回-1，表示肯定凑不到最小减免金额。
//      #############################
		int sum = 0;

		for (int p : prices) {
			sum += p;
		}

		if (sum < voucherThreshold) {
			return -1;
		}
//		#############################

		// 选择编号范围在[0,i]的菜，要凑起价格至少为j元钱，最少花多少钱
		int[][] dp = new int[dishNum][voucherThreshold + 1];

		for (int i = 1; i <= voucherThreshold; i++) {
			// 举例：0号菜价格为5元。意思是如果要只能选0号菜，你的选择只有买0号菜和不买0号菜，
			// 要凑起价格至少为0或1或2或3或4或5元的菜，都至少花费5元，即0号菜必须买
			if (i <= prices[0]) {
				dp[0][i] = prices[0];
			}

			// 如果要花费至少在5元以上，如6元，7元，……，是不可能的，因为所有菜全买（即买0号菜），也只能凑出5元，
			// 所以答案是不存在这种情况
			else {
				// 因为本题的转移方程用的是两种可能性取最小值的方式，所以对于这种不可能存在的情况，索性设置它为
				// 最大值Integer.MAX_VALUE，这样就肯定取不到它。
				// 但是后面需要注意的点是Integer.MAX_VALUE的超边界问题，因为Integer.MAX_VALUE = 2^31 - 1,
				// 且Integer.MAX_VALUE + 1 = Integer.MIN_VALUE。
				dp[0][i] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i < dishNum; i++) {
			for (int j = 1; j <= voucherThreshold; j++) {
				if (j < prices[i]) {
					// 如果要凑起的钱数都小于当前这种菜的价格，要不然是前面[0,i-1]的菜已经把至少凑起j元的任务
					// 完成了，所以i号菜不用买了；要不然就是只买i号菜，前面的[0,i-1]编号的菜都不买，因为只买i号菜
					// 都已经足够满足花费至少j元的条件了
					dp[i][j] = Math.min(prices[i], dp[i - 1][j]);
				} else {
					// 这里就是之前提到的Integer.MAX_VALUE的超边界问题。如果dp[i-1][j-arr[i]]是Integer.MAX_VALUE,
					// arr[i] + dp[i-1][j-arr[i]]肯定是一个负数，因此肯定在Math.min中被取到， 且这个值非常不合理

					dp[i][j] = dp[i - 1][j - prices[i]] == Integer.MAX_VALUE ? dp[i - 1][j] : Math.min(dp[i - 1][j], dp[i - 1][j - prices[i]] + prices[i]);
				}
			}
		}

		return dp[dishNum - 1][voucherThreshold];
	}

	/*
	原本是考虑菜品的组合使得他们的价格之和大于优惠券的最低使用值，并且还得大的最少。
	现在改变思路，从之前的选择元素使得最优结果，改成删掉元素使得最优结果。
	如果所有菜品价格之和为T元，比优惠券最低使用价格多X元，我们找菜品组合价格小于等于X元，且最接近X元的组合价格。
	这就是01背包问题：不超过背包限重的前提下，最多可以装价值最大的物品是多少。
	不过这里不设计物品的重量和价格两个维度，只考虑一个维度 —— 价格。

	使用滚动数组来优化DP问题的空间复杂度，使得二维空间的DP表可以变成一维空间。
	因为使用的是一维表，原本j的正序遍历会使得dp的更新会覆盖掉后面dp值计算时候需要的值，导致计算不正确
	所以这里的j的遍历应该使用逆序遍历，且遍历到j-price[i]。
	 */
	private static int helper2(int[] prices, int dishNum, int voucherThreshold) {
		int sum = 0;
		for (int p : prices) {
			sum += p;
		}

		if (sum < voucherThreshold) return -1;

		int maxValue = sum - voucherThreshold;
		int[] dp = new int[maxValue + 1];

		for (int i = 0; i <= maxValue; i++) {
			// 不超过price[0]的前提下，最多可以组成多少钱
			if (i >= prices[0]) {
				dp[i] = prices[0];
			}
		}

		// 148行 - 152行和154行到161行在本题是一样的效果，但是换成如MonsterProblem问题，用后者还是更稳妥。
		// 对j的分情况讨论最好还是放到循环体中用if else来判断，不容易乱。
//		for (int i = 1; i < dishNum; i++) {
//			for (int j = maxValue; j >= prices[i]; j--) {
//				dp[j] = Math.max(dp[j], dp[j - prices[i]] + prices[i]);
//			}
//		}

		for (int i = 1; i < dishNum; i++) {
			for (int j = maxValue; j >= 0; j--) {
				if (j >= prices[i]) {
					dp[j] = Math.max(dp[j], dp[j - prices[i]] + prices[i]);

				}
			}
		}

		// 所有菜的价格总和减去最大的超阈值值，答案就是满足阈值前提下花费的最少钱数
		return sum - dp[maxValue];
	}

	/*
	没有使用滚动数组思想，但是使用了求删掉菜品价值在小于T-X元的前提下的最大值的思想
	总的来说，helper1是常规的空间复杂度，加上求最小值问题，helper3是常规的空间复杂度，加上求最大值的01背包问题
	helper2是两者结合的最优解，既优化了空间，又把原来求最小值的问题改成了求最大值的01背包问题
	 */
	private static int helper3(int[] prices, int dishNum, int voucherThreshold) {
		int sum = 0;
		for (int p : prices) {
			sum += p;
		}

		if (sum < voucherThreshold) return -1;
		int maxValue = sum - voucherThreshold;
		int[][] dp = new int[dishNum][maxValue + 1];

		for (int i = 1; i <= maxValue; i++) {
			if (prices[0] <= i) {
				dp[0][i] = prices[0];
			}
		}

		for (int i = 1; i < dishNum; i++) {
			for (int j = 1; j <= maxValue; j++) {
				dp[i][j] = dp[i - 1][j];

				if (j >= prices[i]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i]] + prices[i]);
				}
			}
		}

		return sum - dp[dishNum - 1][maxValue];

	}
}
