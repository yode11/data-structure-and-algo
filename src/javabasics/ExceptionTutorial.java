package javabasics;

public class ExceptionTutorial {
	public static void main(String[] args) {
//		try {
//			fun();
//		}catch (Exception e){
//			System.out.println(e);
//			System.out.println("捕捉到exception");
//			e.printStackTrace();
//		}

		fun();
		System.out.println("结束了fun");
	}

	public static void fun() {
//		throw new ArithmeticException("出错了");
		System.out.println("进入到fun方法");
		throw new RuntimeException();
//		throw new Exception();
	}
}
