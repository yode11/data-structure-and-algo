package concurrency.practice.producerconsumermodel;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用ReentrantLock类和Condition接口来实现生产者消费者模型
 */
public class ProduConsuModelLockCondition {
	static int maxCapacity = 10;
	static LinkedList<LocalDateTime> queue = new LinkedList<>();

	static Lock lock = new ReentrantLock();
	// 队列非满时唤醒阻塞于此的线程，生产者阻塞于此
	static final Condition NOTFULL = lock.newCondition();
	// 队列非空时唤醒阻塞于此的线程，消费者阻塞于此
	static final Condition NOTEMPTY = lock.newCondition();

	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

	static class Producer implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				lock.lock();

				try {
					while(queue.size() == maxCapacity){
						try {
							System.out.println("当前队列满，" + Thread.currentThread().getName() + " 生产者阻塞");
							NOTFULL.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					queue.offer(LocalDateTime.now());
					System.out.println(Thread.currentThread().getName() + " 生产者生产完成，当前队列里容量 = " + queue.size());

					NOTEMPTY.signalAll();
				}
				finally {
					lock.unlock();
				}
			}
		}
	}

	static class Consumer implements Runnable{

		@Override
		public void run() {
			for (int i=0;i<100;i++){
				lock.lock();

				try{
					while(queue.size() == 0){
						try{
							System.out.println("当前队列满，" + Thread.currentThread().getName() + " 消费者阻塞");
							NOTEMPTY.await();
						}catch (InterruptedException e){
							e.printStackTrace();
						}
					}

					queue.poll();
					System.out.println(Thread.currentThread().getName() + " 消费者消费完成，当前队列里容量 = " + queue.size());

					NOTFULL.signalAll();
				}finally {
					lock.unlock();
				}
			}
		}
	}
}
