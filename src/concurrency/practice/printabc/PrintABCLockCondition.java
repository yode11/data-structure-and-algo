package concurrency.practice.printabc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，
 * 但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC……的顺序打印。
 *
 * ReentrantLock加Condition实现版本
 */
public class PrintABCLockCondition {
	public static void main(String[] args) {

		// 锁对象
		Lock lock = new ReentrantLock();

		// 三个condition对象，分别对应三个线程的锁
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();

		new Thread(() -> {
			lock.lock();

			try {
				for (int i = 0; i < 10; i++) {
					// 打印
					System.out.print(Thread.currentThread().getName());
					// 唤醒下一顺次的线程
					conditionB.signal();
					// 本线程阻塞于对应本线程的condition对象，等待上一顺次的线程在下一轮打印时唤醒自己
					conditionA.await();
				}
				// 最后一轮打印结束后，所有线程都陷入阻塞，所以需要按顺序唤醒下一顺次线程，使每个线程都结束
				conditionB.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "A").start();

		new Thread(() -> {
			lock.lock();

			try {
				for (int i = 0; i < 10; i++) {
					System.out.print(Thread.currentThread().getName());
					conditionC.signal();
					conditionB.await();
				}

				conditionC.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}, "B").start();

		new Thread(() -> {
			lock.lock();

			try {
				for (int i = 0; i < 10; i++) {
					System.out.print(Thread.currentThread().getName());
					conditionA.signal();
					conditionC.await();
				}

				conditionA.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}, "C").start();
	}

}
