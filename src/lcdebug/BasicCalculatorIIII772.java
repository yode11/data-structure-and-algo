package lcdebug;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculatorIIII772 {
	public int calculate(String s) {
		int[] ans = helper(s, 0);
		return ans[0];
	}

	private int[] helper(String s, int i){
		Deque<Integer> stack = new LinkedList<>();
		int sign = 1;
		char operator = ' ';

		if(s.charAt(i) == '-'){
			stack.push(0); // 防止一开始就是一个‘-’符号的情况，如-3
		}

		for(;i<s.length();i++){
			char ch = s.charAt(i);

			if(Character.isDigit(ch)){
				int num = ch - '0';
				while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))){
					num = num * 10 - '0' + s.charAt(++i);
				}

				if(operator == ' '){
					stack.push(sign * num);
				}
				else if(operator == '*'){
					stack.push(stack.pop() * num);
					operator = ' ';
				}
				else if(operator == '/'){
					stack.push(stack.pop() / num);
					operator = ' ';
				}
			}
			else if(ch == '+'){
				sign = 1;
			}
			else if(ch == '-'){
				sign = -1;
			}
			else if(ch == '*'){
				operator = '*';
				sign = 1;
			}
			else if(ch == '/'){
				operator = '/';
				sign = 1;
			}

			else if(ch== '('){
				// 递归调用函数，计算并返回该括号内的结果
				int[] subRes = helper(s, i+1);

				i = subRes[1];

				if(operator == '*'){
					stack.push(stack.pop() * subRes[0]);
					operator = ' ';
				}
				else if(operator == '/'){
					stack.push(stack.pop() / subRes[0]);
					operator = ' ';
				}
				else{
					stack.push(sign * subRes[0]);
				}
			}
			else if(ch == ')'){
				break;
			}
		}

		return new int[]{calInsideBracket(stack), i};
	}

	private int calInsideBracket(Deque<Integer> stack){
		int res = 0;

		while(!stack.isEmpty()){
			res += stack.pop();
		}

		return res;
	}

	public static void main(String[] args) {
		BasicCalculatorIIII772 basicCalculatorIIII772 = new BasicCalculatorIIII772();

		String string = "2*(5+5*2)/3+(6/2+8)";

		int res = basicCalculatorIIII772.calculate(string);
		System.out.println(res);
	}
}
