package projectpractice;

import java.math.BigDecimal;
import java.text.NumberFormat;

public abstract class BigDecimalTrial {
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal("3.0");
		final BigDecimal bigDecimal1 = new BigDecimal("3.00");
		final BigDecimal bigDecimal2 = new BigDecimal("4");
		System.out.println(bigDecimal.equals(bigDecimal1));
		System.out.println(bigDecimal.equals(bigDecimal2));
		System.out.println(bigDecimal.compareTo(bigDecimal2));
		final BigDecimal max = bigDecimal.max(bigDecimal2);
		System.out.println(max);

		NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
		NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
		percent.setMaximumFractionDigits(3); //百分比小数点最多3位

		BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
		BigDecimal interestRate = new BigDecimal("0.008"); //利率
		BigDecimal interest = loanAmount.multiply(interestRate); //相乘

		System.out.println(currency.format(loanAmount));
		System.out.println("贷款金额:\t" + currency.format(loanAmount));
		System.out.println("利率:\t" + percent.format(interestRate));
		System.out.println("利息:\t" + currency.format(interest));

	}

}
