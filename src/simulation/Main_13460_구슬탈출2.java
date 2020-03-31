package simulation;

import java.io.*;
import java.util.*;

/**
 * 
 * @author JBoom
 *
 * @value . 빈 칸
 * @value # 벽
 * @value O 구멍 위치
 * @value R 빨간 구슬
 * @value B 파란 구슬
 * 
 * @date 2020.03.31 Clear
 * 
 */
public class Main_13460_구슬탈출2 {

	public static int[][] dir = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
	public static int ans, N, M;
	public static char[][] map;
	public static Queue<int[]> q;
	
	public static boolean rFir(int ry, int rx, int by, int bx, int d) {
		boolean[] goal = new boolean[2];
		
		map[ry][rx] = 'R';
		
		while (0 < ry+dir[d][0] && ry+dir[d][0] < N-1 &&
				0 < rx+dir[d][1] && rx+dir[d][1] < M-1) {
			
			if (map[ry+dir[d][0]][rx+dir[d][1]] == 'O') {
				map[ry][rx] = '.';
				goal[0] = true;
				break;
			}
			else if (map[ry+dir[d][0]][rx+dir[d][1]] == '.') {
				map[ry][rx] = '.';
				ry += dir[d][0];
				rx += dir[d][1];
				map[ry][rx] = 'R';
			}
			else break;
		}
		
		while (0 < by+dir[d][0] && by+dir[d][0] < N-1 &&
				0 < bx+dir[d][1] && bx+dir[d][1] < M-1) {
			
			if (map[by+dir[d][0]][bx+dir[d][1]] == 'O') {
				goal[1] = true;
				break;
			}
			else if (map[by+dir[d][0]][bx+dir[d][1]] == '.') {
				by += dir[d][0];
				bx += dir[d][1];
			}
			else break;
		}
		
		map[ry][rx] = '.';
		
		if (!goal[1]) {
			if (goal[0]) return true;
			
			q.offer(new int[] {ry, rx, by, bx, ans+1});
		}
		
		return false;
	}
	
	public static boolean bFir(int ry, int rx, int by, int bx, int d) {
		boolean[] goal = new boolean[2];
		
		map[by][bx] = 'B';
		
		while (0 < by+dir[d][0] && by+dir[d][0] < N-1 &&
				0 < bx+dir[d][1] && bx+dir[d][1] < M-1) {
			
			if (map[by+dir[d][0]][bx+dir[d][1]] == 'O') {
				map[by][bx] = '.';
				goal[1] = true;
				break;
			}
			else if (map[by+dir[d][0]][bx+dir[d][1]] == '.') {
				map[by][bx] = '.';
				by += dir[d][0];
				bx += dir[d][1];
				map[by][bx] = 'B';
			}
			else break;
		}
		
		while (0 < ry+dir[d][0] && ry+dir[d][0] < N-1 &&
				0 < rx+dir[d][1] && rx+dir[d][1] < M-1) {
			
			if (map[ry+dir[d][0]][rx+dir[d][1]] == 'O') {
				goal[0] = true;
				break;
			}
			else if (map[ry+dir[d][0]][rx+dir[d][1]] == '.') {
				ry += dir[d][0];
				rx += dir[d][1];
			}
			else break;
		}

		map[by][bx] = '.';
		
		if (!goal[1]) {
			if (goal[0]) return true;
			
			q.offer(new int[] {ry, rx, by, bx, ans+1});
		}
		
		return false;
	}
	
	public static boolean bfs() {
		while (true) {
			int[] l = q.peek();
			if (ans != l[4]) return false;
			
			l = q.poll();
			
			for (int d = 0; d < 4; ++d) {
				boolean goal = false;
				
				switch (d) {
				case 0:
					if (l[1] < l[3])	goal = rFir(l[0], l[1], l[2], l[3], d);
					else				goal = bFir(l[0], l[1], l[2], l[3], d);
					break;
				case 1:
					if (l[3] < l[1])	goal = rFir(l[0], l[1], l[2], l[3], d);
					else				goal = bFir(l[0], l[1], l[2], l[3], d);
					break;
				case 2:
					if (l[0] < l[2])	goal = rFir(l[0], l[1], l[2], l[3], d);
					else				goal = bFir(l[0], l[1], l[2], l[3], d);
					break;
				case 3:
					if (l[2] < l[0])	goal = rFir(l[0], l[1], l[2], l[3], d);
					else				goal = bFir(l[0], l[1], l[2], l[3], d);
					break;
				}
				
				if (goal) return true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		q = new LinkedList<>();
		int[] r = null;
		int[] b = null;
		
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 'R') {
					r = new int[] {i, j};
					map[i][j] = '.';
				}
				if (map[i][j] == 'B') {
					b = new int[] {i, j};
					map[i][j] = '.';
				}
			}
		}
		
		q.offer(new int[] {r[0], r[1], b[0], b[1], 1});

		while (++ans <= 10) {
			boolean suc = bfs();
			
			if (suc) {
				System.out.println(ans);
				break;
			}
		}
		
		if (ans > 10) System.out.println(-1);
	}

}
