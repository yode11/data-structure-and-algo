package concurrency.practice.multiplethreadsprint;

/**
 * 两个线程交替打印，一个打印奇数，一个打印偶数，范围从0~100
 * synchronized关键字实现版本
 *
 * 此版本和wait/notify版本的区别是，两个线程固定一个只能打印奇数，一个只能打印偶数
 */
public class PrintOddEvenSynchronized {
	private static int count = 0;
	private static final Object OBJECT = new Object();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (count <= 100) {
					synchronized (OBJECT) {
						if(count <= 100 && (count & 1) == 0){
							System.out.println("线程名：" + Thread.currentThread().getName() + "，打印数字：" + count++);
						}
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(count <= 100){
					synchronized (OBJECT){
						if(count <= 100 && (count & 1) == 1){
							System.out.println("线程名：" + Thread.currentThread().getName() + "，打印数字：" + count++);
						}
					}
				}
			}
		}).start();
	}
}
