package com.myapp.base;

import java.io.*;

import java.util.*;
public class CandidateCode {
    public static void main(String args[] ) throws Exception {

   String V = "";
	    Scanner scan = new Scanner(System.in);
	    int N = 0;
	    
	    V = scan.nextLine();
	   // System.out.println(V);
	    N = scan.nextInt();
	   // System.out.println(N);
	    String persons[] = new String[N];
	    if (N > 0 || N <= 10)
	    for (int i = 0; i < N; i++) {
	        persons[i] = scan.nextLine();           
            if (persons[i].length() < 1) {
                persons[i] = scan.nextLine();
            }
            int c1 = check(V, persons[i], 0, 0);
            if (c1 == persons[i].length() && c1 > 0) {
                System.out.println("POSITIVE");
            } else {
                 System.out.println("NEGATIVE");
            }
	        
	    }
	    
	    scan.close();

   }

   	public static int check(String s1, String s2, int inx1, int inx2) {
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