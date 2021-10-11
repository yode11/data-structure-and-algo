package languagebasics;

/**
 * 类的继承，多态的应用
 */
public class PolymorphismDemo {

	static class Father {
		String name;
		int age;

		Father(String name, int age) {
			this.age = age;
			this.name = name;
		}

		/**
		 * 本次实验中，子类将重写父类的该方法，用以证明子类可以重写父类的方法
		 */
		void toBeOverriddenMethod() {
			System.out.println("this is father class");
		}

		/**
		 *
		 * @return
		 */
		public String fatherSpecificMethod(){
			return "this is father specific";
		}

	}

	static class Child extends Father {

		String childSpecificField;

		Child(String name, int age, String childSpecificField) {
			super(name, age);
			this.childSpecificField = childSpecificField;
		}

		//加上@Override可以让编译器帮助检查是否进行了正确的覆写。希望进行覆写，但是不小心写错了方法签名，编译器会报错
		@Override
		void toBeOverriddenMethod() {
			System.out.println("this is child class");
		}


		public String childSpecificMethod(){
			return "这是子类特有的方法";
		}
	}


	public static void main(String[] args) {
		Father child = new Child("father", 20, "actuallyChild"); // 多态
		Father father = new Father("father", 30);

		father.toBeOverriddenMethod(); // 父类的原有方法
		child.toBeOverriddenMethod(); // 子类中，对于父类原有方法的改写

		// 子类可以调用父类的所有方法，哪怕子类中并没有改写
		// 所以二者返回同样的结果
		System.out.println(father.fatherSpecificMethod());
		System.out.println(child.fatherSpecificMethod());

		final Child child1 = new Child("child", 10, "sdfs");
		System.out.println(child1.childSpecificMethod());
//		child.childSpecificMethod(); // 标红，甚至IDE都没有该对象的该方法的提示，
//		说明多态模式下，由于引用类型是父类，所以父类找不到子类特有的方法，所以无法编译
		// 但是在转变类型后，就可以使用子类特有的方法了
		Child child2 = (Child)child;
		System.out.println(child2.childSpecificMethod());

	}

}
