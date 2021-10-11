package basics.javaspecific;

/**
 * 有关多态、继承的小练习，判断a,b,ab分别调用.show()方法，会输出什么
 */
public class PolymorphismQuiz1 {

	public static void main(String[] args) {
		// 输出1
		A a = new A();
		a.show();

		// 输出2
		// 理论同输出3
		B b = new B();
		b.show();

		// 输出3
		// 利用多态，运行时看得是实现类B，B中没有.show()方法，所以走向父类的.show()方法，
		// 父类.show()方法中，走向.show2()方法，由于B中有.show2()方法，所以走向自己的.show2()方法
		A ab = new B();
		ab.show();
	}

	static class A {
		public void show() {
			show2();
		}

		public void show2() {
			System.out.println("A");
		}
	}

	static class B extends A {
		public void show2() {
			System.out.println("B");
		}
	}
}
