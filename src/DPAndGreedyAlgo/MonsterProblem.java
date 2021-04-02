package DPAndGreedyAlgo;

import java.util.*;

public class MonsterProblem {

	/*

	题目：
	int[] d，d[i]：i号怪兽的能力
	int[] p，p[i]：i号怪兽要求的钱

	开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。

	如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
	如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，
	然后怪兽就会加入你，他的能力直接累加到你的能力上。

	返回通过所有的怪兽，需要花的最小钱数

	 */

	public static void main(String[] args) {
		int len = 10;
		int value = 20;
		int testTimes = 1000000;

		System.out.println("开始进行对数器测试...");

		for (int i = 0; i < testTimes; i++) {
			int[][] arrs = generateTwoRandomArray(len, value);
			int[] d = arrs[0]; // 怪兽的能力值数组
			int[] p = arrs[1]; // 怪兽的贿赂金数组

			long ansRecursion = funcRecursion(d, p);
			int ansHelper1 = helper1(d, p);
			int ansHelper2 = helper2(d, p);
			int ansHelper1Compressed = helper1WithCompressedSpace(d, p);
			int ansHelper2Compressed = helper2WithCompressedSpace(d, p);

			if (ansRecursion != ansHelper1) {
				System.out.println("你写的helper1方法得到的答案和对数器的答案不一样啊");
				System.out.println("能力值数组d为：");
				for (int a : d) {
					System.out.print(a);
				}
				System.out.println("贿赂金额数组p为：");
				for (int a : p) {
					System.out.print(a);
				}
				System.out.println("正确答案是： " + ansRecursion + ", 而你的答案是：" + ansHelper1);
			}

			if (ansRecursion != ansHelper2) {
				System.out.println("你写的helper2方法得到的答案和对数器的答案不一样啊");
				System.out.println("能力值数组d为：");
				for (int a : d) {
					System.out.print(a);
				}
				System.out.println("贿赂金额数组p为：");
				for (int a : p) {
					System.out.print(a);
				}
				System.out.println("正确答案是： " + ansRecursion + ", 而你的答案是：" + ansHelper2);
			}

			if (ansRecursion != ansHelper1Compressed) {
				System.out.println("你写的空间最优的helper1方法得到的答案和对数器的答案不一样啊");
				System.out.println("能力值数组d为：");
				for (int a : d) {
					System.out.print(a);
				}
				System.out.println("贿赂金额数组p为：");
				for (int a : p) {
					System.out.print(a);
				}
				System.out.println("正确答案是： " + ansRecursion + ", 而你的答案是：" + ansHelper1Compressed);
			}

			if (ansRecursion != ansHelper2Compressed) {
				System.out.println("你写的空间最优的helper2方法得到的答案和对数器的答案不一样啊");
				System.out.println("能力值数组d为：");
				for (int a : d) {
					System.out.print(a);
				}
				System.out.println("贿赂金额数组p为：");
				for (int a : p) {
					System.out.print(a);
				}
				System.out.println("正确答案是： " + ansRecursion + ", 而你的答案是：" + ansHelper2Compressed);
			}
		}

		System.out.println("对数器测试结束，如果没有错误信息打印，说明你的方法写对了。");

	}


	private static int[][] generateTwoRandomArray(int len, int value) {
		int size = (int) (Math.random() * len) + 1;
		int[][] arrs = new int[2][size];
		for (int i = 0; i < size; i++) {
			arrs[0][i] = (int) (Math.random() * value) + 1;
			arrs[1][i] = (int) (Math.random() * value) + 1;
		}
		return arrs;
	}


	private static long funcRecursion(int[] d, int[] p) {
		return process(d, p, 0, 0);
	}


	private static long process(int[] d, int[] p, int hp, int index) {
		if (index == d.length) {
			return 0;
		}
		if (hp < d[index]) {
			return p[index] + process(d, p, hp + d[index], index + 1);
		} else { // 可以贿赂，也可以不贿赂
			return Math.min(
					p[index] + process(d, p, hp + d[index], index + 1),
					process(d, p, hp, index + 1));
		}
	}


	// d为能力值，p为贿赂金额
	// column为能力值，dp值表示的是某个怪兽范围内，达到能力值为某个值，所花费的最少的贿赂金是多少
	private static int helper1(int[] d, int[] p) {
		int n = d.length; // 一共有多少个怪兽

		int sum = 0; // 最多拥有的能力值（每个怪兽都贿赂，每个怪兽的能力值都加到你身上），作为column
		for (int ability : d) {
			sum += ability;
		}

		// 想要通过[0,i]范围内的怪兽，达到的能力值为j，至少花费的钱数是多少
		int[][] dp = new int[n][sum + 1];

		// DP前的初始化。对于只对付0号怪兽来说，只有在贿赂它以后，才会获得合理的能力值d[0]，
		// 花费的钱是p[0]；不可能在经过0号怪兽以后获得其他数值的能力值，所以其他位置的dp值都是Integer.MAX_VALUE（方便
		// 后面做Math.min() 比较的时候去掉这种不存在的情况）。
		for (int i = 0; i <= sum; i++) {
			dp[0][i] = Integer.MAX_VALUE;
		}
		dp[0][d[0]] = p[0];

		// 对于[0, i]范围的怪兽来说，走过他们之后，你的能力值是0，这是不可能的，因为走过一个怪兽之后，如果能力值
		// 是小于这个怪兽的能力值，这是不可能的，所以dp值都是Integer.MAX_VALUE。
		for (int i = 0; i < n; i++) {
			dp[i][0] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= sum; j++) {
				// 虽然我们很明确是两种可能性，做比较取最小值，但是需要额外判断的是有没有任何一种可能性是不可能发生的
				// 即对于某一个可能性的值为Integer.MAX_VALUE的判断，这个非常重要。
				if (j >= d[i]) {
					dp[i][j] = dp[i - 1][j - d[i]] == Integer.MAX_VALUE ? dp[i - 1][j] : Math.min(dp[i - 1][j - d[i]] + p[i], dp[i - 1][j]);

				}

				// 如果走过这个怪兽之后，你的能力值竟然比这个怪兽的能力值小，这是不可能的。面对一个怪兽，处理方式有两种：
				// 1）你的能力值比怪兽高，可以不贿赂，或者贿赂，但是不论如何，做出操作之后你的能力值一定比这个怪兽的
				// 能力值高；2）你的能力值比怪兽低，所以你必须花钱贿赂它，做出操作以后，它的能力值会加在你身上，所以你的
				// 能力值是高于怪兽能力值的。
				// 综上所述，如果对一个怪兽进行操作之后，你的能力值竟然小于这个怪兽的能力值，这是不可能的，dp值为Integer.MAX_VALUE。
				else {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		// res返回的是最后一行，所有怪兽的范围中，所有最终达成的能力值中，选花费大于0的最小值（最小的正数）
		int res = Integer.MAX_VALUE;

		for (int j = 0; j <= sum; j++) {
			res = Math.min(res, dp[n - 1][j]);
		}

		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private static int helper1WithCompressedSpace(int[] d, int[] p) {
		int n = d.length;

		int sum = 0;
		for (int a : d) {
			sum += a;
		}

		// 建立空间复杂度最优的DP数组，进行初始化设置
		int[] dp = new int[sum + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[d[0]] = p[0];

		for (int i = 1; i < n; i++) {
			for (int j = sum; j >= 0; j--) {
				if (j >= d[i]) {
					dp[j] = dp[j - d[i]] == Integer.MAX_VALUE ? dp[j] : Math.min(dp[j], dp[j - d[i]] + p[i]);
				} else {
					dp[j] = Integer.MAX_VALUE;
				}
			}
		}

		int res = Integer.MAX_VALUE;
		for (int i = 0; i <= sum; i++) {
			res = Math.min(res, dp[i]);
		}

		return res == Integer.MAX_VALUE ? -1 : res;
	}


	private static int helper2(int[] d, int[] p) {
		int n = d.length; // 怪兽的个数

		int sum = 0; // 最多花的钱数（每个怪兽都贿赂），是所有怪兽的贿赂金的和
		for (int pri : p) {
			sum += pri;
		}

		// 想要通过[0,i]范围的怪兽，花费正好j元钱，可以获得的最大的能力值是多少
		int[][] dp = new int[n][sum + 1];

		// DP前的初始化。
		// 对于0号怪兽，只有正好花费p[0]元时，可以通过它，花费其他的金额的情况都是不存在的
		for (int i = 1; i <= sum; i++) {
			dp[0][i] = i == p[0] ? d[0] : -1;
		}

		// 已经通过了[0, i]范围的所有怪兽，花费的金额竟然是0元，不可能的，因为无论如何，你的初始
		// 能力值是0，所以第一个怪兽的贿赂金是一定要给的，否则无法继续下去。
		for (int i = 0; i < n; i++) {
			dp[i][0] = -1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= sum; j++) {
				// 如果存在通过了[0,i-1]的怪兽，且最后花了j元钱，而且得到的能力值是大于等于i号怪兽能力值的，
				// 则说明通过i号怪兽可以不花钱，否则的话，不满足这两个条件，想通过i号怪兽而不花钱是不可能的
				int p1 = -1;
				if (dp[i - 1][j] != -1 && dp[i - 1][j] >= d[i]) {
					p1 = dp[i - 1][j];
				}

				// 如果选择贿赂怪兽，且贿赂之后花的钱数为j，需要在给i号怪兽花钱之前，通过完[0,i-1]怪兽之后花
				// 费的钱数为j-p[i]，想让这个值有意义，就必须j>=p[i]。而且画这个钱的情况是存在的，也就是说不能为-1。
				int p2 = -1;
				if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
					p2 = dp[i - 1][j - p[i]] + d[i];
				}

				// 两种通过i号怪兽的方式，取可以获得的最大能力值
				dp[i][j] = Math.max(p1, p2);
			}
		}

		// res返回最后一行，第一个能力值大于0的花费钱数，也就是所有合理的通过所有怪兽的方式中，选择花费最少的。
		int res = -1;

		for (int i = 0; i <= sum; i++) {
			if (dp[n - 1][i] > 0) {
				res = i;
				break;
			}
		}

		return res;
	}


	private static int helper2WithCompressedSpace(int[] d, int[] p) {
		int n = d.length;

		int sum = 0;
		for (int a : p) {
			sum += a;
		}

		// 建立空间复杂度最优的DP数组，进行初始化设置
		int[] dp = new int[sum + 1];
		Arrays.fill(dp, -1);
		dp[p[0]] = d[0];

		for (int i = 1; i < n; i++) {
			for (int j = sum; j >= 0; j--) {
				int p1 = dp[j] >= d[i] ? dp[j] : -1;

				int p2 = (j >= p[i] && dp[j - p[i]] != -1) ? dp[j - p[i]] + d[i] : -1;

				dp[j] = Math.max(p1, p2);
			}
		}

		int res = -1;
		for (int i = 0; i <= sum; i++) {
			if (dp[i] > 0) {
				res = i;
				break;
			}
		}

		return res;
	}

}
