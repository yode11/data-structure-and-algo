package concurrency.notes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock对象，进行加锁lock时，必须用try-catch-finally结构包裹，上锁在try结构之前且紧邻try，finally中第一行就是unlock操作。
 *
 * https://blog.csdn.net/u013568373/article/details/98480603
 */
public class LockTryFinally {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();

		// .lock()在try外面
		lock.lock();
		try{
			// access the resource protected by this lock
			System.out.println("print");
		}finally{
			lock.unlock();
		}
	}
}
