package simulation;

import java.io.*;
import java.util.*;

/**
 * 
 * @author JBoom
 *
 * @Clear 2020.04.16
 *
 */
public class Main_14502_연구소 {

	public static int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	public static int ans, N, M;
	public static int[][] map;
	public static int[][] virus;
	public static int rear;
	public static boolean[][] v;

	public static int count(int[][] cMap) {
		int cnt = 0;
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <M; ++j) {
				if (cMap[i][j] == 0)
					++cnt;
			}
		}
		
		return cnt;
	}
	
	public static void spread(int[][] cMap) {
		int top = 0;
		int cRear = rear;
		
		while (top != cRear) {
			int i = virus[top][0];
			int j = virus[top++][1];
			
			for (int d = 0; d < 4; ++d) {
				int ii = i + dir[d][0];
				int jj = j + dir[d][1];
				
				if (0 <= ii && ii < N && 0 <= jj && jj < M && cMap[ii][jj] == 0) {
					cMap[ii][jj] = 2;
					virus[cRear][0] = ii;
					virus[cRear++][1] = jj;
				}
			}
		}
		
		int cnt = count(cMap);
		ans = ans < cnt ? cnt : ans;
	}
	
	public static void copy(int[][] wall) {
		int[][] cMap = new int[N][M];
		for (int i = 0; i < N; ++i)
			cMap[i] = map[i].clone();
		for (int i = 0; i < 3; ++i)
			cMap[wall[i][0]][wall[i][1]] = 1;
		
		spread(cMap);
	}
	
	public static void comb(int[][] wall, int cnt) {
		if (cnt == 3) {
			copy(wall);
			return;
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] != 0 || v[i][j])
					continue;
				
				v[i][j] = true;
				wall[cnt][0] = i;
				wall[cnt][1] = j;
				comb(wall, cnt + 1);
				v[i][j] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		virus = new int[N*M][2];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus[rear][0] = i;
					virus[rear++][1] = j;
				}
			}
		}
		
		v = new boolean[N][M];
		comb(new int[3][2], 0);
		System.out.println(ans);
	}

}
