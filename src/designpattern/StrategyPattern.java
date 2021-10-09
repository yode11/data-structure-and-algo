package designpattern;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 策略模式 Strategy Pattern
 * https://www.jianshu.com/p/d0c1b312d563
 */
public class StrategyPattern {

	public enum StrategyEnum {
		ADD {
			@Override
			public int calculate(int a, int b) {
				return a + b;
			}
		},

		SUBTRACT {
			@Override
			public int calculate(int a, int b) {
				return a - b;
			}
		};

		// 枚举方法，让每一个枚举实例都以不同的方式实现同一个方法，可以在enum类里声明抽象方法，也可以实现接口然后各自去实现接口的方法
		public abstract int calculate(int a, int b);

	}


	public static void main(String[] args) {
		// 3
		int addResult = StrategyEnum.ADD.calculate(1, 2);
		System.out.println(addResult);

		// -1
		int subResult = StrategyEnum.SUBTRACT.calculate(1, 2);
		System.out.println(subResult);
	}
}
