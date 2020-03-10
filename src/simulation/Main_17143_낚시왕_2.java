package simulation;

import java.io.*;
import java.util.*;

public class Main_17143_낚시왕_2 {
	
	public static int catchShark(int[][] map, int[][] sharks, int R, int j) {
		int ans = 0;
		
		for (int i = 1; i <= R; ++i) {
			if (map[i][j] == 0)
				continue;
			
			ans = sharks[map[i][j]][4];
			sharks[map[i][j]][4] = 0;
			map[i][j] = 0;
			break;
		}
		
		return ans;
	}
	
	public static void moveShark(int[][] dir, int[][] map, int[][] sharks, int R, int C, int M) {
		Map<Integer, Integer> sharkMap = new HashMap<>();
		
		for (int i = 1; i <= M; ++i) {
			if (sharks[i][4] == 0)	// 죽은 상어
				continue;

			map[sharks[i][0]][sharks[i][1]] = 0;
			int move = sharks[i][2];
			
			switch (sharks[i][3]) {
			case 1:
			case 2:
				move %= R * 2 - 2;
				while (move-- > 0) {
						 if (sharks[i][0] == 1) sharks[i][3] = 2;
					else if (sharks[i][0] == R) sharks[i][3] = 1;
					
					sharks[i][0] += dir[sharks[i][3]][0];
				}
				break;
			case 3:
			case 4:
				move %= C * 2 - 2;
				while (move-- > 0) {
						 if (sharks[i][1] == 1) sharks[i][3] = 3;
					else if (sharks[i][1] == C) sharks[i][3] = 4;
					
					sharks[i][1] += dir[sharks[i][3]][1];
				}
				break;
			}
			
			int loc = (sharks[i][0] - 1) * C + (sharks[i][1] - 1);
			
			if (sharkMap.containsKey(loc)) {	// 다른 상어가 있을 경우
				if (sharks[i][4] < sharks[sharkMap.get(loc)][4]) {
					sharks[i][4] = 0;
					continue;
				}
				sharks[sharkMap.get(loc)][4] = 0;
			}
			
			sharkMap.put(loc, i);
		}
		
		sharkMap.forEach((key, value) -> {
			int ii = key / C + 1;
			int jj = key % C + 1;
			
			map[ii][jj] = value;
		});
	}
	
	public static void main(String[] args) throws IOException {
		int[][] dir = { {}, {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R+1][C+1];
		int[][] sharks = new int[M+1][5];
		
		for (int i = 1; i <= M; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 5; ++j)
				sharks[i][j] = Integer.parseInt(st.nextToken());
			map[sharks[i][0]][sharks[i][1]] = i;
		}
		
		for (int j = 1; j <= C; ++j) {
			ans += catchShark(map, sharks, R, j);
			moveShark(dir, map, sharks, R, C, M);
		}
		
		System.out.println(ans);
	}

}
