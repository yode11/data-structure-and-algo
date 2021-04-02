package 真题;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 以下所内容均转载自他人博文，仅用于个人学习和参考，版权归原作者所有，原链接如下：
 * https://www.xiaoshuaila.top/archives/%E8%A7%A3%E5%86%B3%E9%9B%A8%E6%B0%B4%E9%97%AE%E9%A2%98
 *
 * 京东 2021年实习生春招
 * LeetCode有接雨水I和接雨水II，京东的这道题是在接雨水II的基础上做了改进。
 */
public class TrappingRainWaterPlus {

	/**
	 * 接雨水II的思路：
	 * 和接雨水一类似,但是这次的边界不是两端,而是一个边界,要找出矮边界,这次使用优先队列,
	 * 保存每个边界柱子的x,y,h坐标的数组,重写他的比较器,使其按h(高度)从小到大排序,
	 * 每次访问都要访问最矮柱子前后左右四个柱子,同时,用一个boolean数组保存是否访问过,防止重复。
	 */
	public static int trapRainWaterII(int[][] heightMap) {
		int[][]guide=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

		int n=heightMap.length;
		int m=heightMap[0].length;
		//保存被访问过的地板
		boolean[][]visited=new boolean[n][m];
		//优先队列,重写比较器,使高度最小的永远在这个队列最前面
		PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		//将四周的地板先添加进队列
		for(int i=0;i<n;i++)
		{
			queue.add(new int[]{i,0, heightMap[i][0]});
			queue.add(new int[]{i,m-1, heightMap[i][m-1]});
			visited[i][0]=true;
			visited[i][m-1]=true;
		}
		for(int i=0;i<m;i++)
		{
			queue.add(new int[]{0,i, heightMap[0][i]});
			queue.add(new int[]{n-1,i, heightMap[n-1][i]});
			visited[0][i]=true;
			visited[n-1][i]=true;
		}
		//如果还有地板没被访问,一直循环
		int num=0;
		while(!queue.isEmpty())
		{
			int[]tem=queue.poll();
			//遍历当前地板(周围一圈最小地板)周围四个地板
			for(int i=0;i<4;i++)
			{
				int tx=tem[0]+guide[i][0];
				int ty=tem[1]+guide[i][1];
				if(tx>=0&&ty>=0&&tx<n&&ty<m&&!visited[tx][ty])
				{
					//如果比这个最小地板还小,那它肯定能注水
					if (tem[2]>heightMap[tx][ty])
					{
						num+=(tem[2]- heightMap[tx][ty]);
					}
					//访问过设为true
					visited[tx][ty]=true;
					//添加这块新地板,地板注水了则改为注水后的高度
					queue.add(new int[]{tx,ty,Math.max(tem[2],
							heightMap[tx][ty])});
				}
			}
		}
		return num;
	}


	/**
	 * 京东笔试题,力扣上没有原题,但是和接雨水2类似,只是最后求的不是总雨水的数量,
	 * 而是最后区域内留了多少雨水积水区,如接雨水2中的题目,答案就是两块积水区。
	 *
	 * 思路：
	 * 在接雨水2的基础上,不用使用sum求接雨水的数量了,再用一个boolean数组保存能注水的地板,
	 * 当遇到新地板比最小边界还小的地板时,将这块地板设为能注水(true)即可,
	 * 最后就是求这个这个boolean数组中出现的连在一起的"true块"的数量。
	 */
	public static int trapRainWater(int[][] heightMap) {
		int[][]guide=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

		int n=heightMap.length;
		int m=heightMap[0].length;
		//保存被访问过的地板
		boolean[][]visited=new boolean[n][m];
		//保存能被注水的地板
		boolean[][]isok=new boolean[n][m];
		//优先队列,重写比较器,使高度最小的永远在这个队列最前面
		PriorityQueue<int[]>queue=new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		//将四周的地板先添加进队列
		for(int i=0;i<n;i++)
		{
			queue.add(new int[]{i,0, heightMap[i][0]});
			queue.add(new int[]{i,m-1, heightMap[i][m-1]});
			visited[i][0]=true;
			visited[i][m-1]=true;
		}
		for(int i=0;i<m;i++)
		{
			queue.add(new int[]{0,i, heightMap[0][i]});
			queue.add(new int[]{n-1,i, heightMap[n-1][i]});
			visited[0][i]=true;
			visited[n-1][i]=true;
		}
		//如果还有地板没被访问,一直循环
		while(!queue.isEmpty())
		{
			int[]tem=queue.poll();
			//遍历当前地板(周围一圈最小地板)周围四个地板
			for(int i=0;i<4;i++)
			{
				int tx=tem[0]+guide[i][0];
				int ty=tem[1]+guide[i][1];
				if(tx>=0&&ty>=0&&tx<n&&ty<m&&!visited[tx][ty])
				{
					//如果比这个最小地板还小,那它肯定能注水
					if (tem[2]>heightMap[tx][ty])
					{
						isok[tx][ty]=true;
					}
					//访问过设为true
					visited[tx][ty]=true;
					//添加这块新地板,地板注水了则改为注水后的高度
					queue.add(new int[]{tx,ty,Math.max(tem[2],
							heightMap[tx][ty])});
				}
			}
		}
		//保存出现true的次数
		int sum=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				//每当出现一次true,就将其连着的true全设为false
				if(isok[i][j]){
					dfs(i,j,isok, m, n, guide);
					sum++;
				}
			}
		return sum;
	}

	private static void dfs(int a,int b,boolean[][]isok, int m, int n, int[][] guide){
		for(int i=0;i<4;i++)
		{
			int temi=a+guide[i][0];
			int temj=a+guide[i][1];
			if(temi>=0&&temi<n&&temj>=0&&temj<m&&isok[temi][temj])
			{
				isok[temi][temj]=false;
				dfs(temi,temj,isok, m, n, guide);
			}
		}
	}
}
