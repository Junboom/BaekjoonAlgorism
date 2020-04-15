package simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {
	
	public static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	public static int N, M;
	public static int[][] map;
	public static boolean[][] clear;
	public static int top, rear;
	
	public static int dfs(int r, int c, int d, int ans) {
		clear[r][c] = true;
		
		for (int i = 0; i < 4; ++i) {
			int dd = (d + 3 - i) % 4;
			int rr = r + dir[dd][0];
			int cc = c + dir[dd][1];
			
			if (0 < rr && rr < N-1 && 0 < cc && cc < M-1 && map[rr][cc] == 0 && !clear[rr][cc])
				return dfs(rr, cc, dd, ans+1);
		}
		
		int rr = r - dir[d][0];
		int cc = c - dir[d][1];
		if (map[rr][cc] == 1) return ans;
		
		return dfs(rr, cc, d, ans);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] robo = new int[3];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < 3; ++i)
			robo[i] = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		clear = new boolean[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(dfs(robo[0], robo[1], robo[2], 1));
	}

}
