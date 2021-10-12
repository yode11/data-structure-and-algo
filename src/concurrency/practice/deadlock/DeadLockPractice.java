package concurrency.practice.deadlock;

/**
 * 实现一个死锁的例子，顺便讲一下什么是死锁
 */
public class DeadLockPractice {
	static final Object objA = new Object();
	static final Object objB = new Object();

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "准备获取objA资源");
				synchronized (objA){
					System.out.println(Thread.currentThread().getName() + "现在获取到了objA资源");

					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println(Thread.currentThread().getName() + "准备获取objB资源");
					synchronized (objB){
						System.out.println(Thread.currentThread().getName() + "现在获取到了objB资源");
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "准备获取objB资源");
				synchronized (objB){
					System.out.println(Thread.currentThread().getName() + "现在获取到了objB资源");

					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println(Thread.currentThread().getName() + "准备获取objA资源");
					synchronized (objA){
						System.out.println(Thread.currentThread().getName() + "现在获取到了objA资源");
					}
				}
			}
		});

		t1.start();
		t2.start();
	}
}
