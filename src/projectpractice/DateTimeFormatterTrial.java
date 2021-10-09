package projectpractice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTrial {

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");

	public static void main(String[] args) {
		final LocalDateTime now = LocalDateTime.now();

		System.out.println("LocalDateTime obj = " + now);

		String timeStr = now.format(DATE_TIME_FORMATTER);
		System.out.println("年月日形式：" + timeStr);

		final String format = now.format(HH_MM);
		System.out.println("小时分钟形式：" + format);
	}

}
