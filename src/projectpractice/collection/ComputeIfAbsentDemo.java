package projectpractice.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap类中的computeIfAbsent()方法和putIfAbsent()方法的使用和对比
 */
public class ComputeIfAbsentDemo {

	static class Compute{
		public static String compute(String s) {
			return "val4" + s;
		}
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("key1", "val0");
		map.put("key2", "val2");

		// put(key, value)
		// 如果key已经存在在map中，覆盖添加新的键值对，并返回key对应的旧value值，
		// 如果key不存在map中，此次添加为真正的添加，添加键值对后，返回null
		String val = map.put("key1", "val1");
		String put = map.put("key3", "val3");

		System.out.println(val + " " + put);
		//


		// computeIfAbsent(key, mappingFunction)
		// 如果key已经存在于map中，直接返回key在map中对应的value值，不进行MappingFunction的计算
		// 如果key不存在于map中，通过mappingFunction，计算得到的值作为value，放入map中，并返回这个value
		System.out.println(map.computeIfAbsent("key3", Compute::compute));
		System.out.println(map.computeIfAbsent("key4", Compute::compute));

		System.out.println(map);
		//

		// putIfAbsent(key, value)
		// 如果key已经存在于map中，直接返回key在map中对应的value值
		// 如果key不存在于map中，把value值作为新值，新键值对放入map中，并返回null
		// 和computeIfAbsent()的区别就是value是否需要计算，如果计算value的值比较复杂，使用computeIfAbsent可以
		// 避免在无法put的情况下计算value值，节省了资源
		System.out.println(map.putIfAbsent("key1", "val111"));
		System.out.println(map.putIfAbsent("key5","val5"));

	}
}
