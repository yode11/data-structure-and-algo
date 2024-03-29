package multithreads.practice.printabc;

/**
 * 建立三个线程A、B、C。A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，
 * 但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC……的顺序打印。
 * <p>
 * synchronized关键字实现版本，第一版，用三个布尔值变量帮助获取到时间片的线程判断自己是否应该打印
 * <p>
 * https://segmentfault.com/a/1190000021433079
 */
public class PrintABCSynchronizedBoolean {

	// 使用布尔变量对打印顺序进行控制，true表示轮到当前线程打印
	// 由打印A的线程先开始打印
	static boolean startA = true;
	static boolean startB = false;
	static boolean startC = false;

	public static void main(String[] args) {

		// 作为锁对象
		final Object OBJECT = new Object();

		// A线程
		new Thread(() -> {
			int count = 10;

			synchronized (OBJECT) {
				while (count > 0) {
					if (startA) {
						// 代表轮到当前线程打印
						System.out.print(Thread.currentThread().getName());
						// 下一个轮到B打印，所以把startB置为true，其它为false
						startA = false;
						startB = true;
						startC = false;
						// 唤醒其他线程
						OBJECT.notifyAll();

						count--;
					} else {
						// 说明没有轮到当前线程打印，继续wait
						try {
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "A").start();

		// B线程
		new Thread(() -> {
			int count = 10;

			synchronized (OBJECT) {
				while (count > 0) {
					if (startB) {
						System.out.print(Thread.currentThread().getName());

						startA = false;
						startB = false;
						startC = true;

						OBJECT.notifyAll();

						count--;
					} else {
						try {
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "B").start();

		// C线程
		new Thread(() -> {
			int count = 10;

			synchronized (OBJECT) {
				while (count > 0) {
					if (startC) {
						System.out.print(Thread.currentThread().getName());

						startA = true;
						startB = false;
						startC = false;

						OBJECT.notifyAll();

						count--;
					} else {
						try {
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "C").start();
	}
}
