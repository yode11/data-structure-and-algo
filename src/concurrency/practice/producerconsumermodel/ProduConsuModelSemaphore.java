package concurrency.practice.producerconsumermodel;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 用Semaphore来实现生产者消费者模型
 */
public class ProduConsuModelSemaphore {

	// 队列最大容量
	static int maxCapacity = 10;

	static LinkedList<LocalDateTime> queue = new LinkedList<>();

	// 消费者在此获取许可证或者被阻塞
	static Semaphore notEmpty = new Semaphore(0);

	// 生产者在此获取去可证或者被阻塞
	// 初始，在不消费的情况下，生产者生产的数量不能超过队列最大容量
	static Semaphore notFull = new Semaphore(maxCapacity);

	// 保证同时只有一个生产者或者一个消费者在操作队列
	static Semaphore mutex = new Semaphore(1);


	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

	static class Producer implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					notFull.acquire();
					mutex.acquire();

					queue.offer(LocalDateTime.now());
					System.out.println(Thread.currentThread().getName() + " 生产者生产完成，当前队列里容量 = " + queue.size());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					notEmpty.release();
					mutex.release();
				}

			}
		}
	}

	static class Consumer implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					notEmpty.acquire();
					mutex.acquire();

					queue.poll();
					System.out.println(Thread.currentThread().getName() + " 消费者消费完成，当前队列里容量 = " + queue.size());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					notFull.release();
					mutex.release();
				}

			}
		}
	}
}
