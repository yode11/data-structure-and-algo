package javabasics;

/**
 * 抽象类和抽象方法
 */
public class AbstractClassTutorial {

	/**
	 * 抽象类无法实例化，但是可以有成员变量，成员方法，构造器
	 */
	static abstract class AbstractParent {

		private int field1;
		private String field2;

		AbstractParent(int field1, String field2){
			this.field1 = field1;
			this.field2 = field2;
		}

		/**
		 * 抽象类中完全可以含有非抽象的成员方法，成员字段，构造器
		 * @return
		 */
		public String nonAbstractMethod(){
			return "这是父类中的非抽象成员方法";
		}


		/**
		 * 抽象类的静态方法可以直接调用
		 * @return
		 */
		public static String staticMethodTest() {
			return "抽象类的静态方法，可以直接调用";
		}

		/**
		 * 抽象类中的抽象方法，留待非抽象的子类继承抽象父类后去具体实现
		 * @return
		 */
		public abstract String abstractMethod();

		public abstract String anotherAbstractMethod();

	}


	/**
	 * 如果继承抽象父类的子类，自身没有声明abstract（即自身不是抽象类），则其必须重写（Override）抽象父类中的所有抽象方法
	 */
	static class Child extends AbstractParent{

		/**
		 * 子类中必须有相应的构造器，匹配抽象父类的构造器
		 * @param field1
		 * @param field2
		 */
		Child(int field1, String field2) {
			super(field1, field2);
		}

		@Override
		public String abstractMethod() {
			return "非抽象子类中重写抽象父类的抽象方法";
		}

		@Override
		public String anotherAbstractMethod() {
			return "必须重写抽象父类的所有抽象方法";
		}

	}

	public static void main(String[] args) {
		System.out.println(AbstractParent.staticMethodTest());

		final AbstractParent child = new Child(1, "child"); // 多态
		System.out.println(child.abstractMethod());
		System.out.println(child.anotherAbstractMethod());
		System.out.println(child.nonAbstractMethod());
	}
}
