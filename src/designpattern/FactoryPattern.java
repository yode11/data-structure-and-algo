package designpattern;

/**
 * 工厂模式 Factory Pattern
 * https://xie.infoq.cn/article/88c926822394aa1c80847dd2a
 * https://juejin.cn/post/6844903474639929357
 *
 * 在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。
 */
public class FactoryPattern {

	/**
	 * 简单工厂
	 * 简单工厂其实不是一个标准的的设计模式。GOF 23 种设计模式中只有「工厂方法模式」与「抽象工厂模式」。
	 * 简单工厂模式可以看为工厂方法模式的一种特例，为了统一整理学习，就都归为工厂模式。
	 *
	 * 简单工厂模式其实并不算是一种设计模式，更多的时候是一种编程习惯。
	 * 定义一个工厂类，根据传入的参数不同返回不同的实例，被创建的实例具有共同的父类或接口。
	 * 屏蔽产品的具体实现，调用者只关心产品的接口。
	 */
	static class SimpleFactory {

		public interface ProductCreation {
			void createProduct();
		}

		static class Product1 implements ProductCreation {
			@Override
			public void createProduct() {
				System.out.println("创建产品1");
			}
		}

		static class Product2 implements ProductCreation {
			@Override
			public void createProduct() {
				System.out.println("创建产品2");
			}
		}

		static class Product3 implements ProductCreation {
			@Override
			public void createProduct() {
				System.out.println("创建产品3");
			}
		}

		/**
		 * 工厂创建方法
		 * @param productName
		 * @return
		 */
		public static ProductCreation createProduct(String productName) {
			ProductCreation productCreation;

			switch (productName) {
				case "product1":
					productCreation = new Product1();
					break;
				case "product2":
					productCreation = new Product2();
					break;
				case "product3":
					productCreation = new Product3();
					break;
				default:
					throw new UnsupportedOperationException("不支持该操作");
			}

			return productCreation;
		}

		public static void main(String[] args) {
			ProductCreation productCreation = SimpleFactory.createProduct("product1");
		}
	}


	/**
	 * 工厂方法
	 * 我们常说的工厂模式，就是指「工厂方法模式」，也叫「虚拟构造器模式」或「多态工厂模式」。
	 *
	 * 和简单工厂对比一下，最根本的区别在于，简单工厂只有一个统一的工厂类，
	 * 而工厂方法是针对每个要创建的对象都会提供一个工厂类，这些工厂类都实现了一个工厂基类或者实现一个共同的接口
	 *
	 * 工厂方法完全符合开闭原则。
	 * 客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，
	 * 具体的产品对象由具体工厂类创建，客户端需要知道创建具体产品的工厂类。
	 * 在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其子类来确定具体要创建的对象，利用面向对象的多态性和里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
	 */
	static class FactoryMethod {

		public interface ProductCreation {
			AbstractProduct createProduct();
		}

		static class AbstractProduct{};

		static class Product1 extends AbstractProduct{}
		static class Product1Factory implements ProductCreation {

			@Override
			public AbstractProduct createProduct() {
				return new Product1();
			}
		}

		static class Product2 extends AbstractProduct{}
		static class Product2Factory implements ProductCreation {

			@Override
			public AbstractProduct createProduct() {
				return new Product2();
			}
		}

		static class Product3 extends AbstractProduct{}
		static class Product3Factory implements ProductCreation {

			@Override
			public AbstractProduct createProduct() {
				return new Product3();
			}
		}

		public static void main(String[] args) {
			// 客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，
			final AbstractProduct product1 = new Product1Factory().createProduct();
			final AbstractProduct product2 = new Product2Factory().createProduct();
			final AbstractProduct product3 = new Product3Factory().createProduct();

		}

	}

	/**
	 * 抽象工厂
	 */
	static class AbstractFactory {

	}
}
