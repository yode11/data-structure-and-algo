package concurrency.practice.producerconsumermodel;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * 用wait/notify来实现生产者消费者模型
 */
public class ProduConsuModelWaitNotify {
	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

	// 队列最大容量
	static int maxCapacity = 10;
	// 队列
	static LinkedList<LocalDateTime> queue = new LinkedList<>();
	// 生产者消费者线程争夺对象锁
	static final Object OBJECT = new Object();

	static class Producer implements Runnable{
		@Override
		public void run(){
			for (int i = 0; i < 100; i++) {
				synchronized (OBJECT){
					while(queue.size() == maxCapacity){
						try {
							System.out.println("当前队列满，" + Thread.currentThread().getName() + " 生产者阻塞");
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					queue.offer(LocalDateTime.now());
					System.out.println(Thread.currentThread().getName() + " 生产者生产完成，当前队列里容量 = " + queue.size());

					OBJECT.notifyAll();
				}
			}
		}
	}

	static class Consumer implements Runnable{
		@Override
		public void run(){
			for (int i = 0; i < 100; i++) {
				synchronized (OBJECT){
					while(queue.size() == 0){
						try {
							System.out.println("当前队列空，" + Thread.currentThread().getName() + " 消费者阻塞");
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					queue.poll();
					System.out.println(Thread.currentThread().getName() + " 消费者消费完成，当前队列里容量 = " + queue.size());

					OBJECT.notifyAll();
				}
			}
		}
	}
}