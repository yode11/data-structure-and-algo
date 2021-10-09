package projectpractice;

import java.time.LocalDateTime;

public class LocalDateTimeTrial {

	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.now();
		String str = localDateTime.toString();
		System.out.println("LocalDateTime格式：" + localDateTime);
		System.out.println("字符串格式：" + str);

		System.out.println(beforeAndAfterTrial());
	}


	/**
	 * 比较两个LocalDateTime对象的时间前后
	 *
	 * @return
	 */
	public static boolean beforeAndAfterTrial(){
		final LocalDateTime now = LocalDateTime.now();
		final LocalDateTime before = LocalDateTime.now();
		final LocalDateTime after = before.plusHours(2L);

		boolean isBefore =  before.isBefore(after);
		//boolean isAfter = before.isAfter(after);
		//boolean isEqual = before.isEqual(now);

		return isBefore;
	}

}
