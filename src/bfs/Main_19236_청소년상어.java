package bfs;

import java.io.*;
import java.util.*;

public class Main_19236_청소년상어 {

	public static int answer, cnt;
	public static Map<Integer, int[][]> sMap;
	public static Map<Integer, Map<Integer, int[]>> fMove;
	public static Queue<int[]> q;
	public static int[][] dir = {
			{0,  0}, {-1,  0}, {-1, -1},
			{0, -1}, { 1, -1}, { 1,  0},
			{1,  1}, { 0,  1}, {-1,  1}
	};
	
	public static void bfs() {
		while (!q.isEmpty()) {
			int[] s = q.poll();
			int[][] map = sMap.get(s[4]);
			Map<Integer, int[]> f = fMove.get(s[4]);
			int sum = s[3] + map[s[0]][s[1]];
			answer = answer < sum ? sum : answer;
			
			map[s[0]][s[1]] = -1;
			move(map, f);
			map[s[0]][s[1]] = 0;
			
			int y = s[0] + dir[s[2]][0];
			int x = s[1] + dir[s[2]][1];
			
			while (0 <= y && y < 4 && 0 <= x && x < 4) {
				if (map[y][x] != 0) {
					int[][] cMap = new int[4][4];
					Map<Integer, int[]> cFish = new HashMap<>();
					
					for (int i = 0; i < 4; ++i) {
						for (int j = 0; j < 4; ++j) {
							cMap[i][j] = map[i][j];
						}
					}
					
					cFish.putAll(f);
					
					sMap.put(cnt, cMap);
					fMove.put(cnt, cFish);
					q.offer(new int[] {y, x, f.get(map[y][x])[2], sum, cnt++});
				}
				
				y += dir[s[2]][0];
				x += dir[s[2]][1];
			}
		}
	}
	
	public static boolean dead(int[][] map, int num) {
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (map[i][j] == num)
					return false;
			}
		}
		return true;
	}
	
	public static void move(int[][] map, Map<Integer, int[]> f) {
		for (int i = 1; i <= 16; ++i) {
			if (dead(map, i))
				continue;
			
			int[] l = new int[] {f.get(i)[0], f.get(i)[1]};
			int d = f.get(i)[2];
			int y = l[0] + dir[d][0];
			int x = l[1] + dir[d][1];
			
			while (y < 0 || 4 <= y || x < 0 || 4 <= x || map[y][x] == -1) {
				d = (d + 1) % 9;
				if (d == 0) d = 1;
				
				y = l[0] + dir[d][0];
				x = l[1] + dir[d][1];
			}
			
			if (map[y][x] == 0) {
				map[l[0]][l[1]] = 0;
			}
			else {
				f.put(map[y][x], new int[] {l[0], l[1], f.get(map[y][x])[2]});
				map[l[0]][l[1]] = map[y][x];
			}
			
			f.put(i, new int[] {y, x, d});
			map[y][x] = i;
		}
	}
	
	public static void main(String[] args) throws IOException {
		answer = 0;
		cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4];
		Map<Integer, int[]> f = new HashMap<>();
		sMap = new HashMap<>();
		fMove = new HashMap<>();
		q = new LinkedList<>();
		
		for (int i = 0; i < 4; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			for (int j = 0; j < 4; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				f.put(map[i][j], new int[] {i, j, Integer.parseInt(st.nextToken())});
			}
		}
		
		sMap.put(cnt, map);
		fMove.put(cnt, f);
		q.offer(new int[] {0, 0, f.get(map[0][0])[2], 0, cnt++});
		bfs();
		
		System.out.println(answer);
	}

}
