package designpattern;

/**
 * 单例模式 Singleton Pattern
 * https://www.cnblogs.com/codeshell/p/14177102.html
 * https://zhuanlan.zhihu.com/p/33102022
 */
public class SingletonPattern {

	/**
	 * 懒汉式，线程不安全版本
	 * 需要获取到实例时，再创建实例
	 *
	 */
	static class Singleton1 {

		// private修饰的实例对象，防止外部直接调用
		private static Singleton1 singleton;


		/**
		 * private修饰的构造器，防止外部对于本类的构造，造成多个实例对象的产生
		 */
		private Singleton1() {
		}

		/**
		 * 获取实例对象的唯一途径
		 *
		 * @return
		 */
		public static Singleton1 getInstance() {
			// 如果不存在任何实例对象，才去实例化生成一个对象，否则直接返回已经生成的那个实例化对象
			if (singleton == null) {
				singleton = new Singleton1();
			}

			return singleton;
		}
	}


	/**
	 * 饿汉式，线程安全
	 * 实例提前创建好，获取时直接拿
	 */
	static class Singleton2 {

		// private修饰的实例对象，防止外部直接调用
		private static Singleton2 singleton = new Singleton2();


		/**
		 * private修饰的构造器，防止外部对于本类的构造，造成多个实例对象的产生
		 */
		private Singleton2() {
		}

		/**
		 * 获取实例对象的唯一途径
		 *
		 * @return
		 */
		public static Singleton2 getInstance() {
			return singleton;
		}
	}


	/**
	 * 懒汉式，线程安全版本
	 * 导致每次获取对象时，都进行加锁操作，如果单例对象被频繁使用，加锁操作影响了并发度
	 */
	static class Singleton3 {

		// private修饰的实例对象，防止外部直接调用
		private static Singleton3 singleton;


		/**
		 * private修饰的构造器，防止外部对于本类的构造，造成多个实例对象的产生
		 */
		private Singleton3() {
		}

		/**
		 * 获取实例对象的唯一途径
		 * 获取时进行加锁处理，保证多线程访问下的线程安全，仍然只创建一个实例
		 *
		 * @return
		 */
		public static synchronized Singleton3 getInstance() {
			// 如果不存在任何实例对象，才去实例化生成一个对象，否则直接返回已经生成的那个实例化对象
			if (singleton == null) {
				singleton = new Singleton3();
			}

			return singleton;
		}
	}


	/**
	 * 懒汉式，线程安全版本，双重检验 + 禁止指令重排
	 * 除了第一次获取对象实例时的并发线程们需要进行加锁处理，
	 * 后期所有获取示例的线程都不需要进行加锁处理，提高获取效率
	 */
	static class Singleton4 {

		// private修饰的实例对象，防止外部直接调用
		// volatile修饰变量，禁止指令重排
		private static volatile Singleton4 singleton;


		/**
		 * private修饰的构造器，防止外部对于本类的构造，造成多个实例对象的产生
		 */
		private Singleton4() {
		}

		/**
		 * 获取实例对象的唯一途径
		 * 获取时进行加锁处理，保证多线程访问下的线程安全，仍然只创建一个实例
		 * 双重检验，只有第一批并发线程会在第一次singleton == null判断时，进入synchronize代码块，
		 * 其余后续的所有请求线程，会在第一次判断时，直接略过加锁操作，返回示例。
		 * 第二次检验时，针对的是第一批次线程，其中只有一个线程会创建示例，其余线程都会在二次校验时singleton == null
		 * 得到false的结果，从而避免了重复创建实例
		 *
		 * @return
		 */
		public static synchronized Singleton4 getInstance() {
			// 如果不存在任何实例对象，才去实例化生成一个对象，否则直接返回已经生成的那个实例化对象
			if (singleton == null) {
				synchronized(Singleton4.class) { // 注意这里是类级别的锁
					if (singleton == null) {       // 这里的检测避免多线程并发时多次创建对象
						singleton = new Singleton4();
					}
				}
			}
			return singleton;
		}
	}


	static class Singleton5 {
		/**
		 * 内部静态类，只有一个final类型的field，是已经实例化好的对象
		 */
		private static class SingletonInner {
			// private修饰的实例对象，防止外部直接调用
			private static final Singleton5 SINGLETON = new Singleton5();
		}

		/**
		 * private修饰的构造器，防止外部对于本类的构造，造成多个实例对象的产生
		 */
		private Singleton5 () {}


		/**
		 * 获取实例对象的唯一途径
		 * 从外部无法访问静态内部类，只有当调用Singleton.getInstance方法的时候，才能得到单例对象INSTANCE
		 * INSTANCE对象初始化的时机并不是在单例类Singleton被加载的时候，而是在调用getInstance方法的时候，
		 * 因此这种实现方式是利用classloader的加载机制来实现懒加载，并保证构建单例的线程安全
		 *
		 * @return
		 */
		public static Singleton5 getInstance() {
			return SingletonInner.SINGLETON;
		}
	}

}
