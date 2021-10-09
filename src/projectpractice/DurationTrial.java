package projectpractice;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 计算LocalDateTime对象之间的时间差，用Duration类的.between()方法
 *
 */
public class DurationTrial {

	public static void main(String[] args) {
		final LocalDateTime now = LocalDateTime.now();
		final LocalDateTime after = now.plusHours(1L);

		final Duration between = Duration.between(now, after);
		final long l = between.toDays();
		final long l1 = between.toMinutes();

		System.out.println(between);
		System.out.println(l);
		System.out.println(l1);
	}
}
