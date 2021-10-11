package concurrency.practice.multiplethreadsprint;

/**
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC的顺序打印。
 * synchronized关键字实现版本，第二版
 *
 * 	https://blog.csdn.net/xiaokang123456kao/article/details/77331878
 */
public class PrintABCSynchronized2 {

	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();

		new Thread(new Task("A", c, a)).start();
		Thread.sleep(10);
		new Thread(new Task("B", a, b)).start();
		Thread.sleep(10);
		new Thread(new Task("C", b, c)).start();
	}


	static class Task implements Runnable{
		private String name;
		private Object prev;
		private Object self;

		public Task(String name, Object prev, Object self){
			this.name = name;
			this.prev = prev;
			this.self = self;
		}

		@Override
		public void run(){
			int count = 10;

			while(count > 0){
				synchronized (prev){
					synchronized (self){
						System.out.print(this.name);
						count--;

						self.notifyAll();
					}

					try{
						if(count <= 0){
							prev.notifyAll();
						}
						else{
							prev.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
