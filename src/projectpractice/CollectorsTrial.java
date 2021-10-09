package projectpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsTrial {

	public static class Node {
		private String serviceOrderCode;
		private String serviceItemCode;

		public Node(String serviceOrderCode, String serviceItemCode) {
			this.serviceOrderCode = serviceOrderCode;
			this.serviceItemCode = serviceItemCode;
		}

		public String getServiceOrderCode() {
			return this.serviceOrderCode;
		}

		public String getServiceItemCode() {
			return this.serviceItemCode;
		}
	}


	public static void main(String[] args) {
		// 每个Node对象有两个参数，准备用第一个参数作为key,另外一个参数作为value，按照key做classifier，生成map.
		// 以下是两种不同的分类方式:

		List<Node> list = new ArrayList<>();
		list.add(new Node("1", "2"));
		list.add(new Node("1", "3"));
		list.add(new Node("1", "4"));
		list.add(new Node("2", "2"));
		list.add(new Node("2", "3"));
		list.add(new Node("2", "4"));

		System.out.println("原数据：");
		for (Node node : list) {
			System.out.println(node.getServiceOrderCode() + "," + node.getServiceItemCode());
		}


		System.out.println("方法1结果输出：");
		// method1
		final Map<String, List<Node>> collect1 = list.stream()
				.collect(Collectors.groupingBy(Node::getServiceOrderCode));

		collect1.forEach((key, value) -> System.out.println(key + "," + value));
		// 结果：key : 参数1，value：List[Node对象]


		System.out.println("方法2结果输出：");
		// method2
		final Map<String, List<String>> collect2 = list.stream().
				collect(Collectors.groupingBy(Node::getServiceOrderCode, Collectors.mapping(Node::getServiceItemCode, Collectors.toList())));

		collect2.forEach((key, value) -> System.out.println(key + "," + value));
		// 结果：key：参数1，value：List[参数2]


		System.out.println("方法3结果输出：");
		// method3
		final Map<String, Node> collect3 = list.stream().collect(Collectors.toMap(Node::getServiceOrderCode, Function.identity()));
		collect3.forEach((k, v) -> System.out.println(k + "," + v));
		// 结果：报错，出现重复key，
		// 所以，如果想要设置的key无法保证唯一，就会出错，需要使用method2的方法，把同一个key的objs放到一个list里，作为value。
	}
}
