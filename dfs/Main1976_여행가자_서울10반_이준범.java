package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class Main1976_여행가자_서울10반_이준범 {
	
	public static int n;
	public static int[][] graph;
	public static boolean[][] v;
	public static Set<Integer>[] go;
	
	public static void dfs(int i, int j) {
		if (1 < go[j].size()) {
			go[i].addAll(go[j]);
			return;
		}
		
		go[i].add(j);
		for (int jj = 1; jj <= n; jj++) {
			if (graph[j][jj] == 1 && !v[j][jj]) {
				v[j][jj] = true;
				dfs(i, jj);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		String ans = "YES";
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		graph = new int[n+1][n+1];
		go = new HashSet[n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
			go[i] = new HashSet<>();
			go[i].add(i);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == 1) {
					v = new boolean[n+1][n+1];
					v[i][j] = true;
					dfs(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int[] route = new int[m];
		for (int i = 0; i < m; i++)
			route[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m-1; i++) {
			int s = route[i];
			int e = route[i+1];
			if (!go[s].contains(e)) {
				ans = "NO";
				break;
			}
		}
		bw.write(ans);
		bw.flush();
		bw.close();
		br.close();
	}
	
}
