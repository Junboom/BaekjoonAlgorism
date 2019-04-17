package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class Main16236_아기상어 {
	
	public static int ans, n, s;
	public static int[][] aqua;
	public static int front, rear;
	public static int[] queue;
	public static boolean[][] v;
	public static List<int[]> list;
	public static int[][] dir = { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };
	
	public static void bfs() {
		while (front != rear) {
			int ran = rear - front;
			for (int q = 0; q < ran; q++) {
				int tmp = queue[++front];
				int c = tmp / (n*n*100);
				int k = tmp % (n*n*100) / (n*n);
				int i = tmp % (n*n*100) % (n*n) / n;
				int j = tmp % (n*n*100) % n;
				
				for (int d = 0; d < dir.length; d++) {
					int ii = i + dir[d][0];
					int jj = j + dir[d][1];
					if (0 <= ii && ii < n && 0 <= jj && jj < n && !v[ii][jj]) {
						if (aqua[ii][jj] <= s) {
							if (aqua[ii][jj] != 0 && aqua[ii][jj] != s)
								list.add(new int[] {c, k, ii, jj});
							queue[++rear] = (c+1)*(n*n*100) + k*(n*n) + ii*n + jj;
							v[ii][jj] = true;
						}
					}
				}
			}
			
			if (!list.isEmpty()) {
				int c = 0;
				int k = 0;
				int minI = n;
				int minJ = n;
				for (int l = 0; l < list.size(); l++) {
					int cc = list.get(l)[0];
					int kk = list.get(l)[1];
					int ii = list.get(l)[2];
					int jj = list.get(l)[3];
					if (ii < minI) {
						c = cc;
						k = kk;
						minI = ii;
						minJ = jj;
					}
					else if (minI == ii && jj < minJ) {
						c = cc;
						k = kk;
						minI = ii;
						minJ = jj;
					}
				}
				
				front = rear = -1;
				v = new boolean[n][n];
				k = (--k == 0) ? ++s : k;
				queue[++rear] = (c+1)*(n*n*100) + k*(n*n) + minI*n + minJ;
				v[minI][minJ] = true;
				aqua[minI][minJ] = 0;
				list = new ArrayList<>();
				ans = c + 1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ans = 0;
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		s = 2;
		aqua = new int[n][n];
		front = rear = -1;
		queue = new int[n*n];
		v = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				aqua[i][j] = Integer.parseInt(st.nextToken());
				if (aqua[i][j] == 9) {
					queue[++rear] = ans*(n*n*100) + s*(n*n) + i*n + j;
					v[i][j] = true;
					aqua[i][j] = 0;
				}
			}
		}
		list = new ArrayList<>();
		bfs();
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}
	
}
