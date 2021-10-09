package lcdebug;

import java.util.HashMap;

public class WordPattern290 {

	public static boolean wordPattern(String pattern, String s) {
		if(s==null || pattern == null) return false;

		String[] arr = s.split(" ");
		if(arr.length != pattern.length()) return false;

		HashMap<Character, Integer> charMap = new HashMap<>();
		HashMap<String, Integer> strMap = new HashMap<>();

		for(Integer i=0;i<pattern.length();i++){
			if(charMap.put(pattern.charAt(i), i) != strMap.put(arr[i], i))
				return false;
		}
        return true;
}

	public static void main(String[] args) {
		System.out.println("hello");
		String pattern = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd";
		String str = "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";
		boolean res = wordPattern(pattern, str);
		System.out.println(res);
	}
}
