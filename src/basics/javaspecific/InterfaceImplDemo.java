package basics.javaspecific;

/**
 * 一个普通类，实现了某个接口，就必须实现这个接口的所有方法
 */
public class InterfaceImplDemo {

	public static void main(String[] args) {
		Person p = new Person();
		p.doPrint();
		System.out.println(p.getAndReturn("sss"));
	}

	/**
	 * 除非定义这个类为抽象类，加关键字abstract，
	 * 否则，必须实现Behavior接口的所有方法
	 */
	static class Person implements Behavior{

		Person(){

		}

		@Override
		public void doPrint() {
			System.out.println("实现打印方法");
		}

		@Override
		public String getAndReturn(String str) {
			return str;
		}
	}

	public interface Behavior{
		void doPrint();

		String getAndReturn(String str);
	}
}
