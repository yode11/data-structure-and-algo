package multithreads.notes;

/**
 * 开启一个线程，执行相应任务，普通写法和lambda表达式写法
 *
 */
public class ThreadExecution {

	public static void main(String[] args) {

		// 第一种
		// 参数为一个实现了Runnable接口的类的对象，明确定义了一个类作为任务，用于需要用多个线程执行相同任务的场景
		new Thread(new Task()).start();

		// 第二种
		// 匿名函数，直接在参数中实现Runnable接口的run()方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行线程的第二种形式");
			}
		}).start();

		// 第三种
		// 因为Runnable接口是Functional interface(只定义了一个方法)，所以直接可以用lambda表达式形式实现
		new Thread(() -> {
			System.out.println("执行线程的第三种形式");
		}).start();
	}

	static class Task implements Runnable{
		@Override
		public void run(){
			System.out.println("执行线程的第一种形式");
		}
	}
}
