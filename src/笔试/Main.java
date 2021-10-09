package 笔试;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
//7
//1 1 A
//1 2 B
//1 5 A
//1 3 C
//1 4 D
//2 A B
//2 B C
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		in.nextLine();

		Node[] orderList = new Node[100];
		int groupIdx = 0;

		Map<String, List<String>> map = new HashMap<>();

		for(int i=0;i<n;i++){
			String[] arr = in.nextLine().split(" ");

			String ope = arr[0];
			String num = arr[1];
			String group = arr[2];

			if(ope.equals("1")){
				// 增加车厢
				if(!map.containsKey(group)){
					map.put(group, new ArrayList<>());
				}
				map.get(group).add(num);

				orderList[groupIdx] = new Node(group, groupIdx);
				groupIdx++;
			}
			else{
				// 交换两个group系列
				Node n1 = new Node("", 0);
				Node n2 = new Node("", 0);
				for(Node node : orderList){
					if(node.group.equals(num)){
						n1 = node;
					}
					if(node.group.equals(group)){
						n2 = node;
					}
				}

				int tmp = n1.order;
				n1.order = n2.order;
				n2.order = tmp;
			}
		}

		Arrays.sort(orderList, new MyComparator());
		for(Node node : orderList){
			if(node!=null){
				
			}
		}
	}

	static class Node{
		String group;
		int order;
		Node(String group, int order){
			this.group = group;
			this.order = order;
		}
	}

	static class MyComparator implements Comparator<Node>{
		@Override
		public int compare(Node n1, Node n2){
			return n1.order - n2.order;
		}
	}
}
