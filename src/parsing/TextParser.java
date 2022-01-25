package parsing;

import java.util.StringTokenizer;

public class TextParser {
	
	private static int level;
	
	public static void main(String args[]) {
		
		String str = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";
		check(str);
	}
	
	private static void check(String str) {
		//System.out.println(str.indexOf("("));
		//System.out.println(str.lastIndexOf(")"));
		System.out.println("check: " + str);
		if(str.contains("(")) {
			if (str.startsWith("(")) {
				tokens(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")));
			}
		} 
	}
	
	private static void tokens(String str) {
		System.out.println("tokens: " + str);
		if(str.contains(",")) {
			StringTokenizer st = new StringTokenizer(str, ",");
			while (st.hasMoreTokens()) {
				String innerStr = st.nextToken();
				if (innerStr.contains("(")) {
					level++;
					check(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")));
				} else {
					print(innerStr, level);
				}
			}
		} else {
			System.exit(0);
		}
	}
	
	private static void print(String token, int level) {
		for(int i=0; i<level; i++) {
			System.out.print("-");
		}
		System.out.println(token);
	}

}
