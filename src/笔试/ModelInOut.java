//package 笔试;
//

//import java.util.*;
//
//public class ModelInOut {
// 输入空行回车，结束输入
//	public static void main(String[] args) {
//			Scanner in = new Scanner(System.in);
//			while(in.hasNextLine()){//第一处
//			String s = in.nextLine();
//			if(s.length() == 0)//第二处
//			break;
//			System.out.println(s);
//			}
//			System.out.println("Over Input");
//			}



//	/**
//	 * 1.数组求和 一直输入
//	 * 1 5
//	 * 10 20
//	 * <p>
//	 * 6
//	 * 30
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			int a = sc.nextInt();
//			// long a=sc.nextLong();
//
//			int b = sc.nextInt();
//			// long b=sc.nextLong();
//
//			System.out.println(a + b);
//		}
//	}
//
//	/** 2.数组求和 有组数
//	 *
//	 2（组数）
//	 1 5
//	 10 20
//	 *
//	 6
//	 30
//	 * */
////	public static void main(String[] args) {
////		Scanner sc = new Scanner(System.in);
////		int n = sc.nextInt();
////		while (n > 0) {
////			int a = sc.nextInt();
////			int b = sc.nextInt();
////			int sum = a + b;
////			System.out.println(sum);
////			n--;
////		}
////	}
//
//	/**
//	 * 3.数组 0 0 为结束
//	 * <p>
//	 * 1 5
//	 * 10 20
//	 * 0 0
//	 * <p>
//	 * 6
//	 * 30
//	 */
//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		while (input.hasNext()) {
//			int a = input.nextInt();
//			int b = input.nextInt();
//			//
//			if (a == 0 && b == 0) {
//				break;
//			}
//			System.out.println(a + b);
//		}
//	}
//
//	/**
//	 * 4. 数组求和   0为结束
//	 * <p>
//	 * 4 1 2 3 4（4个数 和为1+2+3+4 ）
//	 * 5 1 2 3 4 5
//	 * 0
//	 * <p>
//	 * 10
//	 * 15
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			int n = sc.nextInt(); // 当前数组的长度
//			if (n == 0) { // 表示所有数组已经输入完毕，可以停止接收了
//				return;
//			}
//			int sum = 0;
//			//while (n-- > 0){
//			//    sum += input.nextInt();
//			//}
//			for (int i = 0; i < n; i++) {
//				sum += sc.nextInt();
//			}
//			System.out.println(sum);
//		}
//	}
//
//	/**
//	 * 5. 数组求和   有组数
//	 * <p>
//	 * 2（组数）
//	 * 4 1 2 3 4  （4个数 和为1+2+3+4 ）
//	 * 5 1 2 3 4 5
//	 * <p>
//	 * <p>
//	 * 10
//	 * 15
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int t = sc.nextInt(); // 即将输入几个数组
//		for (int i = 0; i < t; i++) {
//			int num = sc.nextInt(); // 当前数组的长度
//			int sum = 0;
//			for (int j = 0; j < num; j++) {
//				sum = sum + sc.nextInt();
//			}
//			System.out.println(sum);
//		}
//	}
//
//	/**
//	 * 6. 数组求和   一直输入
//	 * 4 1 2 3 4 （4个数 和为1+2+3+4 ）
//	 * 5 1 2 3 4 5
//	 * <p>
//	 * <p>
//	 * 10
//	 * 15
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			int n = sc.nextInt(); // 当前数组的长度
//			int sum = 0;
//			for (int i = 0; i < n; i++) {
//				sum += sc.nextInt();
//			}
//			System.out.println(sum);
//		}
//	}
//
//	/**
//	 * 7，数组求和 直接求  一直输入
//	 * <p>
//	 * 1 2 3
//	 * 4 5
//	 * 0 0 0 0 0
//	 * <p>
//	 * <p>
//	 * 6
//	 * 9
//	 * 0
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			int sum = 0;
//			String[] str = sc.nextLine().split(" ");
//			for (int i = 0; i < str.length; i++) {
//				sum += Integer.parseInt(str[i]);
//			}
//			//for(String str:int1){
//			//    i+=Integer.valueOf(str);
//			//}
//			System.out.println(sum);
//		}
//	}
//
//	/**
//	 * 字符串1 有个数
//	 * <p>
//	 * 5（个数）
//	 * c d a bb e
//	 * <p>
//	 * a bb c d e
//	 */
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			int num = sc.nextInt();
//			String[] s = new String[num];
//			for (int i = 0; i < num; i++) {
//				s[i] = sc.next();
//			}
//			Arrays.sort(s);
//			System.out.println(String.join(" ", s));
//		}
//	}
//
//	/**
//	 * 字符串2 一直输入
//	 * <p>
//	 * a c bb
//	 * f dddd
//	 * nowcoder
//	 * <p>
//	 * <p>
//	 * a bb c
//	 * dddd f
//	 * nowcoder
//	 */
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String[] strs = in.nextLine().split(" ");
//			Arrays.sort(strs);
//			// System.out.println(String.join(" ",s));
//			String res = "";
//			for (String s : strs)
//				res += s + " ";
//			System.out.println(res.trim());
//		}
//	}
//
//	/**
//	 * 字符串3 一直输入 ，号间隔
//	 * a,c,bb
//	 * f,dddd
//	 * nowcoder
//	 * <p>
//	 * a,bb,c
//	 * dddd,f
//	 * nowcoder
//	 */
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String[] strs = in.nextLine().split(",");
//			Arrays.sort(strs);
//			// System.out.println(String.join(",",s));
//			String res = "";
//			for (String s : strs)
//				res += s + ",";
//			System.out.println(res.substring(0, res.length() - 1));
//		}
//	}
//}