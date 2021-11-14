package com.myapp.base;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class TestLong {
	
	public static void main(String[] args) throws Exception {
		String V = "";
	    Scanner scan = new Scanner(System.in);
	    int N = 0;
	    
	    V = scan.nextLine();
	    System.out.println(V);
	    N = scan.nextInt();
	    System.out.println(V);
	    String persons[] = new String[N];
	     
		
	}
	
	public int check(String s1, String s2, int inx1, int inx2) {
		if (s1.length() == inx1) {
			return 0;
		}
		if (s2.length() == inx2) {
			return 0;
		}
		 
		if (s1.charAt(inx1)  == s2.charAt(inx2)) {
			return 1 + check(s1, s2, inx1 +1, inx2 +1);
		}
		
		int t1 = check(s1, s2, inx1+1, inx2);
		int t2 = check(s1, s2, inx1, inx2 +1);
		if (t1 > t2) {
			return t1;
		} else {
			return t2;
		}
	}

}
