package 笔试;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

	private static int helper(int target, Map<Integer, List<Integer>> map) {


//		4
//		1,2
//		3,4,5,6
//		2,3
//		6,4,2
//		8,9
//		10
		Set<Integer> set = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		q.offer(target);
		set.add(target);

		int res = 0;

		while(!q.isEmpty()){
			int cur = q.poll();
			res += cur;

			for(Integer i : map.get(cur)){
				if(!set.contains(i)){
					set.add(i);
					q.offer(i);
				}
			}
		}

		if(res == target){
			return -1;
		}

		return res - target;
	}


	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		String line = in.nextLine();
		Scanner in2 = new Scanner(line);

//		int target = in2.nextInt();
//		System.out.println("target" + target);

		boolean targethave = false;
		int target = 0;

		Map<Integer, List<Integer>> map = new HashMap<>();

		while(in2.hasNext()){
			if(!targethave){
				target = in2.nextInt();
				targethave = true;
				continue;
			}

			String[] tmp = in2.nextLine().split(",");

			if(tmp.length == 1){
				continue;
			}

			int relied = Integer.parseInt(tmp[0]);

			for(int i=1;i<tmp.length;i++){
				int num = Integer.parseInt(tmp[i]);

				if(!map.containsKey(num)){
					map.put(num, new ArrayList<>());
				}
				map.get(num).add(relied);
			}

		}

		System.out.println(helper(target, map));

	}
}
