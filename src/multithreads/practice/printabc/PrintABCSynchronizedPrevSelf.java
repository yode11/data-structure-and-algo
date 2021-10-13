package multithreads.practice.printabc;

/**
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，
 * 但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC……的顺序打印。
 *
 * synchronized关键字实现版本，第二版，每个线程必须持同时有两个对象锁，才能打印
 *
 * 	https://blog.csdn.net/xiaokang123456kao/article/details/77331878
 */
public class PrintABCSynchronizedPrevSelf {

	/**
	 * 思路：
	 * 使用同步块和wait、notify的方法控制三个线程的执行次序。
	 * 从大的方向上来讲，该问题为三线程间的同步唤醒操作，主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。
	 * 为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁，才能进行打印操作。
	 * 一个对象锁是prev，就是前一个线程所对应的对象锁，其主要作用是保证当前线程一定是在前一个线程操作完成后（即前一个线程释放了其对应的对象锁）才开始执行。
	 * 还有一个锁就是自身对象锁
	 * 为了控制执行的顺序，必须要先持有prev锁（也就前一个线程要释放其自身对象锁），然后当前线程再申请自己对象锁，两者兼备时打印。
	 * 之后首先调用self.notify()唤醒下一个等待线程（注意notify不会立即释放对象锁，只有等到同步块代码执行完毕后才会释放），
	 * 再调用prev.wait()立即释放prev对象锁，当前线程进入休眠，等待其他线程的notify操作再次唤醒。
	 */

	public static void main(String[] args) throws InterruptedException {
		// 三个对象锁，分别对应三个线程
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();

		// 线程之间用sleep隔一段时间，保证以A,B,C的顺序打印，否则容易出现ACB的顺序
		new Thread(new Task(c, a), "A").start();
		Thread.sleep(100);
		new Thread(new Task(a, b), "B").start();
		Thread.sleep(100);
		new Thread(new Task(b, c), "C").start();
	}


	static class Task implements Runnable{
		private Object prev; // 前一个线程对应的对象锁
		private Object self; // 本线程对应的对象锁
		int count; // 打印次数

		public Task(Object prev, Object self){
			this.prev = prev;
			this.self = self;
			this.count = 10;
		}

		@Override
		public void run(){
			while(count > 0){
				synchronized (prev){
					synchronized (self){
						System.out.printf(Thread.currentThread().getName());
						count--;

						// 唤醒下一顺次的线程，使其准备可以拿锁
						self.notifyAll();
					}

					// 如果是count = 0,表示是打印的最后一轮，线程就不需要阻塞了，否则线程无法结束
					if (count > 0){
						try {
							// 线程阻塞在prev锁上，目的是等待上一顺次的线程打印完毕后唤醒它
							prev.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
