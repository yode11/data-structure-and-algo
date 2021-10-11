package concurrency.practice.multiplethreadsprint;

/**
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC的顺序打印。
 * synchronized关键字实现版本，第一版
 *
 * https://segmentfault.com/a/1190000021433079
 */
public class PrintABCSynchronized1 {
	// 使用布尔变量对打印顺序进行控制，true表示轮到当前线程打印
	// 使用Object类的notify()，notifyAll()和wait()方法，无法针对指定线程，所以需要boolean值对线程执行顺序做控制
	private static boolean startA = true;
	private static boolean startB = false;
	private static boolean startC = false;

	public static void main(String[] args) {
		// 作为锁对象
		final Object OBJECT = new Object();

		// A线程
		new Thread(() -> {
			synchronized (OBJECT) {
				for (int i = 0; i < 10; ) {
					if (startA) {
						// 代表轮到当前线程打印
						System.out.print(Thread.currentThread().getName());
						// 下一个轮到B打印，所以把startB置为true，其它为false
						startA = false;
						startB = true;
						startC = false;
						// 唤醒其他线程
						OBJECT.notifyAll();
						// 在这里对i进行增加操作
						i++;
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
			synchronized (OBJECT) {
				for (int i = 0; i < 10; ) {
					if (startB) {
						System.out.print(Thread.currentThread().getName());
						startA = false;
						startB = false;
						startC = true;
						OBJECT.notifyAll();
						i++;
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
			synchronized (OBJECT) {
				for (int i = 0; i < 10; ) {
					if (startC) {
						System.out.print(Thread.currentThread().getName());
						startA = true;
						startB = false;
						startC = false;
						OBJECT.notifyAll();
						i++;
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
