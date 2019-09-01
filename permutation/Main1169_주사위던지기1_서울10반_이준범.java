package com.ssafy.algo;

import java.util.Scanner;

public class Main1169_주사위던지기1_서울10반_이준범 {
	public static int n, r, caseCount, data[];
	
	public static void permutation(int before, int count) {
		if (count == n) {
			StringBuilder sb = new StringBuilder();
			sb.append(data[0]);
			for (int i = 1; i < n; i++)	{
				sb.append(" ");
				sb.append(data[i]);
			}
			System.out.println(sb.toString());
			return;
		}
		loop:for (int i = before; i <= 6; i++) {
			if (r == 3) {
				for (int j = 0; j < count; j++) {
					if (data[j] == i)
						continue loop;
				}
			}
			data[count] = i;
			if (r == 2)	permutation(i, count + 1);
			else		permutation(1, count + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		r = sc.nextInt();
		data = new int[n];
		
		switch(r) {
		case 1:	permutation(1, 0);		break;
		case 2:	permutation(1, 0);		break;
		case 3:	permutation(1, 0);		break;
		default:						break;
		}
		
		sc.close();
	}
}
