package concurrency.practice.printoddeven;

/**
 * 两个线程交替打印，一个打印奇数，一个打印偶数，范围从0~100
 * wait/notify实现版本
 *
 * reference from 悟空
 */
public class PrintOddEvenWaitNotify {
	private static int count = 0;
	private static final Object OBJECT = new Object();

	public static void main(String[] args) {
		new Thread(new Task()).start();
		new Thread(new Task()).start();
	}


	static class Task implements Runnable{

		@Override
		public void run() {
			while (count <= 100){
				synchronized (OBJECT){
					// 拿到锁就打印
					System.out.println("线程名：" + Thread.currentThread().getName() + "，打印数字：" + count++);

					// 唤醒另一个阻塞的线程
					OBJECT.notify();

					// 如果任务还没有结束，释放锁资源，自己进入休眠
					// 如果count == 100，说明打印任务已经完成，本线程就不需要阻塞了，否则任务无法结束
					if(count <= 100){
						try {
							OBJECT.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
