package languagebasics;

/**
 * 多态的优先级
 * this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)
 *
 * 原则：
 * 超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，
 * 但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法，但是它仍然要根据继承链中方法调用的优先级来确认方法
 *
 */
public class PolymorphismQuiz2 {

	static class A {
		public String show(D obj) {
			return ("A and D");
		}

		public String show(A obj) {
			return ("A and A");
		}
	}

	static class B extends A {
		public String show(B obj) {
			return ("B and B");
		}

		public String show(A obj) {
			return ("B and A");
		}
	}

	static class C extends B {
	}

	static class D extends B {
	}

	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();

		// 以下为非多态，用继承的知识去做就可以
		System.out.println("1-- " + a1.show(b));
		System.out.println("2-- " + a1.show(c));
		System.out.println("3-- " + a1.show(d));

		// a2对象引用类型是A，但是指向堆空间中的对象类型是B，所以调用方法的对象是B类型的对象
		// B类中存在入参类型为B的相应成员方法，但是由于多态的存在，B类对应的超类A中并没有相应的成员方法，所以不能调用B类中该方法
		// 去到第三优先级，找入参b对象的类型B的超类，找到为A
		// B类中正好有入参为A类型的相应方法，所以调用B类中的该方法，返回 B and A
		System.out.println("4-- " + a2.show(b));

		// a2对象引用类型是A，但是指向堆空间中的对象类型是B，所以调用方法的对象是B类型的对象
		// B类中不存在入参类型为C的相应成员方法
		// 去到第三优先级，找入参c对象的类型C的超类，找到为A和B
		// 由于B类中，入参为B类型的方法是B类型独有，而不是重写的其超类A的方法，所以不能调用
		// 调用入参类型为A的方法，返回 B and A
		System.out.println("5-- " + a2.show(c));

		// a2对象引用类型是A，但是指向堆空间中的对象类型是B，所以调用方法的对象是B类型的对象
		// B类中存在入参类型为B的相应成员方法，但是由于多态的存在，B类对应的超类A中并没有相应的成员方法，所以不能调用B类中该方法
		// 向下优先级找，找到第二优先级，找到B类的超类A中，有入参类型为D的相应方法，调用该方法，返回 A and D
		System.out.println(a2.show(d));

		// 以下为非多态，用继承的知识去做就可以
		System.out.println("7-- " + b.show(b));
		System.out.println("8-- " + b.show(c));
		System.out.println("9-- " + b.show(d));



//		答案：
//		1-- A and A
//		2-- A and A
//		3-- A and D
//		4-- B and A
//		5-- B and A
//		6-- A and D
//		7-- B and B
//		8-- B and B
//		9-- A and D

	}
}
