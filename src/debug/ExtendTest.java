package debug;

public class ExtendTest {
	static class Father{
		String name;
		int age;
		Father(String name, int age){
			this.age = age;
			this.name = name;
		}

		int getAge(){
			return this.age;
		}

		void printInfo(){
			System.out.println("this is father class");
		}
	}

	static class Child extends Father{
		String name;
		int age;
		String another;

		Child(String name, int age){
			super(name, age);
			this.another = "aaa";
		}

//		@Override
		void printInfo(){
			System.out.println("this is child");
			super.printInfo();
		}
	}

	public static void main(String[] args) {
		Father father = new Child("father",20);
		Father father1 = new Father("father", 30);
		father.printInfo();
		father1.printInfo();
	}
}
