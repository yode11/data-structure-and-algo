package DPAndGreedyAlgo;

import java.util.*;

public class MinimalCostUsingVoucher {
	/*
	链接：https://www.nowcoder.com/questionTerminal/95329d9a55b94e3fb2da475d3d052164?toCommentId=3377639
来源：牛客网

	你打开了美了么外卖，选择了一家店，你手里有一张满X元减10元的券，店里总共有n种菜，
	第i种菜一份需要A_i元，因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，
	现在问你最少需要选择多少元的商品才能使用这张券。

	输入描述:
	第一行两个正整数n和X，分别表示菜品数量和券的最低使用价格。（1≤n≤100, 1≤X≤10000）
	接下来一行n个整数，第i个整数表示第i种菜品的价格。（1≤A_i≤100）

	输出描述:
	一个数，表示最少需要选择多少元的菜才能使用这张满X元减10元的券，保证有解。

	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int len = in.nextInt();
		int money = in.nextInt();
		int[] arr = new int[len];

		for(int i=0;i<len;i++) {
			arr[i] = in.nextInt();
		}

		System.out.println(helper(arr, len, money));

	}

	private static int helper(int[] arr, int len, int money){

		// 选择编号范围在[0,i]的菜，要凑起价格至少为j元钱，最少花多少钱
		int[][] dp = new int[len][money+1];

		for(int i=0;i<=money;i++){
			// 举例：0号菜价格为5元。意思是如果要只能选0号菜，你的选择只有买0号菜和不买0号菜，
			// 要凑起价格至少为0或1或2或3或4或5元的菜，都至少花费5元，即0号菜必须买
			if(i<=arr[0]){
				dp[0][i] = arr[0];
			}

			// 如果要花费至少在5元以上，如6元，7元……，是不可能的，因为所有菜全买（即买0号菜），也只能凑出5元，
			// 所以答案是不存在这种情况
			else{
				// 这里一开始用的是Integer.MAX_VALUE,不行，会超边界，改掉就通过了
				dp[0][i] = 10001;
			}
		}

		for(int i=1;i<len;i++){
			for(int j=1;j<=money;j++){
				if(j<arr[i]){
					dp[i][j] = Math.min(arr[i], dp[i-1][j]);
				}
				else{
					dp[i][j] = Math.min(dp[i-1][j], arr[i] + dp[i-1][j-arr[i]]);
				}
			}
		}

		return dp[len-1][money];
	}
}
