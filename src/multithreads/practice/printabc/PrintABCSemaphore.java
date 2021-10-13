package multithreads.practice.printabc;

import java.util.concurrent.Semaphore;

/**
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，
 * 但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC……的顺序打印。
 * <p>
 * Semaphore实现版本
 */
public class PrintABCSemaphore {
	public static void main(String[] args) {
		Semaphore semaphoreA = new Semaphore(1);
		Semaphore semaphoreB = new Semaphore(0);
		Semaphore semaphoreC = new Semaphore(0);

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					// 获取一个permit，此时semaphoreA的permits数量为0了，
					// 下一次循环时，如果没有其他线程对它的permits增加（调.release()），
					// 线程就会阻塞在这里
					semaphoreA.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.print(Thread.currentThread().getName());

				// 给下一顺次的线程对应的semaphore对象的permits加1，让其不用阻塞
				semaphoreB.release();
			}
		}, "A").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					semaphoreB.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.print(Thread.currentThread().getName());

				semaphoreC.release();
			}
		}, "B").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					semaphoreC.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.print(Thread.currentThread().getName());

				semaphoreA.release();
			}
		}, "C").start();
	}
}
