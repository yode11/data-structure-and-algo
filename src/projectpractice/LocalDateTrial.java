package projectpractice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTrial {

	public static void main(String[] args) {
		// 当天日期 年-月-日
		LocalDate localDate = LocalDate.now();
		System.out.println("当天日期" + localDate);

		// 当天日期 年-月-日 -> 转换成 -> 当天的开始时间 当天日期 年-月-日-时-分-秒
		LocalDateTime startTimeOfTheDay = LocalDateTime.
				ofInstant(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		System.out.println("当天的开始时间" + startTimeOfTheDay);

		// 当天日期 年-月-日 -> 转换成 -> 当天的结束日期前最后一秒 当天日期 年-月-日-时-分-秒
		final LocalDateTime endTimeOfTheDay = LocalDateTime.
				ofInstant(localDate.plusDays(1L).atStartOfDay(ZoneId.systemDefault()).minusSeconds(1L).toInstant(), ZoneId.systemDefault());
		System.out.println("当天的结束时间" + endTimeOfTheDay);

		// 当天日期 年-月-日 -> 转换成 -> 下一天的开始时间 下一天日期 年-月-日-时-分-秒
		final LocalDateTime startTimeOfNextDay = LocalDateTime.
				ofInstant(localDate.plusDays(1L).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		System.out.println("下一天的开始时间" + startTimeOfNextDay);
	}
}
