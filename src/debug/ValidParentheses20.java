package debug;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ValidParentheses20 {
	public static boolean isValid(String s) {
		Deque<Character> stack = new LinkedList<>();

		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		char[] arr = s.toCharArray();

		for(int i = 0;i<arr.length;i++){
			if(map.containsKey(arr[i])) {
				if (stack.isEmpty() || stack.pop() != map.get(arr[i])) {
					return false;
				}
			}
			else{
				stack.push(arr[i]);
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		String s = "()";
		System.out.println(isValid(s));
	}
}
