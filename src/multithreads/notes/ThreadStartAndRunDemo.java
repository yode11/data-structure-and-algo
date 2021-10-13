package multithreads.notes;

/**
 * Thread类中.start()方法和.run()方法的区别
 */
public class ThreadStartAndRunDemo {
	/**
	 * block A和block B相互替换，使用不同的block得到的结果不同。
	 * 调用run方法，仍然使用主线程在执行任务，仍然是同步的，所以会先执行run方法，打印pong,再打印main方法中的ping
	 * 调用start方法，是真正的多线程操作，生成一个新线程，主线程返回，继续执行主线程的任务，由新线程去执行run()里的任务，
	 * 所以主线程先执行打印ping,然后新线程执行t1对象的run方法，打印pong
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("pong");
				System.out.println(Thread.currentThread().getName());
			}
		});

//		// block A —— 不建议调用run
//		t1.run();
//		System.out.println(Thread.currentThread().getName());
//		System.out.println("ping");

//		 block B —— 建议调用start
		t1.start();
		System.out.println(Thread.currentThread().getName());
		System.out.println("ping");
	}
}
