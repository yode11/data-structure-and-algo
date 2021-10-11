package concurrency.quiz;

/**
 * 多线程交替打印的多种实现版本
 */
public class PrintInTurnByMultipleThreads {
	public static final Object OBJECT = new Object();
	public static int count = 1;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		Task t1 = new Task();
		Task t2 = new Task();
		new Thread(t1).start();
		new Thread(t2).start();
	}

	public static class Task implements Runnable{
		@Override
		public void run(){
			while(count <= 100){
				synchronized(OBJECT){
					System.out.println(count++);
					OBJECT.notify();
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
