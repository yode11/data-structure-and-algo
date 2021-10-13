package basics.javaspecific;

import java.io.IOException;

/**
 * 异常类和异常处理机制
 */
public class ExceptionDemo {
	public static void main(String[] args) {
//		try {
//			fun();
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//			System.out.println("捕捉到exception");
//			e.printStackTrace();
//		}

//		fun();
//		System.out.println("结束了fun");

//		int[] arr = new int[2];
//		arr[0] = 0;
//		arr[1] = 1;
//		final int i = arr[5];
//		System.out.println(i);

//		try {
//			System.out.println(trycatchTest());
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
	}

	public static String trycatchTest(){
		String str = "";
//		try {
//			str.charAt(3);
//		} catch (Exception e) {
////			e.printStackTrace();
////			System.out.println(e.getMessage());
//		}

		str.charAt(3);

		return str;
	}

	public static void fun() throws IOException {
//		throw new ArithmeticException("出错了");
		System.out.println("进入到fun方法");
//		throw new RuntimeException();
//		throw new NullPointerException();
//		throw new Exception();
//		throw new IOException();
	}
}
