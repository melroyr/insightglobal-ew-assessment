package parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextParser1 {

	private static int level;
	private static String finalStr;
	
	private static ArrayList<String> prunedStrs = new ArrayList<>();
	private static ArrayList<String> unPrunedStrs = new ArrayList<>();
	
	public static void main(String args[]) {
		
		String str = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";
		check(str);
		replace();
	}
	
	private static void check(String str) {
		//System.out.println(str.indexOf("("));
		//System.out.println(str.lastIndexOf(")"));
		//System.out.println("check: " + str);
		if(str.contains("(")) {
			String localStr = str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"));
			prunedStrs.add(localStr);
			unPrunedStrs.add(str.substring(str.indexOf("("), str.lastIndexOf(")") + 1));
			str = localStr;
			check(str);
		}
	}
	
	private static void replace() {
		
		int len = prunedStrs.size();
		
		for (int i=0; i<len-1; i++) {
			//String unPrunedStr =  prunedStrs.get(i);
			prunedStrs.add(i, prunedStrs.remove(i).replace(unPrunedStrs.get(i+1), ",$,"));
		}
		format();
	}
	
	private static void format() {
		int len = prunedStrs.size();
		
		List<StringBuilder> parseStrs = new ArrayList<>();
		for(int i=0; i<len; i++) {
			parseStrs.add(formatStr(prunedStrs.get(i), i));
		}
		
		int strs = parseStrs.size();
		
		finalStr = parseStrs.get(0).toString();
		for(int i=1; i<strs; i++) {
			//finalStr = finalStr + parseStrs.get(i).toString();
			if(finalStr.contains("$")) {
				finalStr = finalStr.replace("$", parseStrs.get(i).toString());
			}
		}
		
		System.out.print(finalStr);
	}
	
	private static StringBuilder formatStr(String str, int level) {
		
		StringBuilder strTokens = new StringBuilder();
		StringTokenizer st = new StringTokenizer(str, ",");
		StringBuilder sb;
		while (st.hasMoreTokens()) {
			sb = new StringBuilder();
			for(int i=0; i<level; i++) {
				sb.append("-");
			}
			String str1 = st.nextToken().trim();
			if(str1.equals("$")) {
				strTokens.append(str1);
			} else {
				strTokens.append(sb.toString() + str1 + "\n");
			}
		}
		return strTokens;
		
	}
	
	/*
	private static void printData(String str) {
		StringTokenizer st = new StringTokenizer(str, ",");
		st.
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (!token.equals("$")) {
				System.out.println(token);
			} else {
				for(int j=0; j<i; j++) {
					System.out.print("-");
				}
			}
		}
	}*/
}
