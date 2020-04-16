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
public class Main_14890_경사로 {

	public static int N, L;
	public static int[][] map, map_v;
	
	public static boolean check(int[] line) {
		boolean[] v = new boolean[N];
		
		for (int i = 1; i < N; ++i) {
			if (Math.abs(line[i-1] - line[i]) > 1)
				return false;
			
			int cnt = 1;
			if (line[i-1] - 1 == line[i]) {
				int[] axis = new int[L];
				axis[0] = i;
				if (v[axis[0]])
					return false;
				
				while (cnt < L) {	
					int idx = i + cnt;
					
					if (idx == N && cnt == L)
						return true;
					if (idx >= N || line[i] != line[idx] || v[idx])
						return false;
					
					axis[cnt] = idx;
					++cnt;
				}
				
				for (int j = 0; j < L; ++j)
					v[axis[j]] = true;
			}
			if (line[i-1] == line[i] - 1) {
				int[] axis = new int[L];
				axis[0] = i - 1;
				if (v[axis[0]])
					return false;
				
				while (cnt < L) {
					int idx = i - 1 - cnt;
					
					if (idx == -1 && cnt == L)
						return true;
					if (idx < 0 || line[i-1] != line[idx] || v[idx])
						return false;
					
					axis[cnt] = idx;
					++cnt;
				}
				
				for (int j = 0; j < L; ++j)
					v[axis[j]] = true;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		map_v = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map_v[j][i] = map[i][j];
			}
		}
		
		for (int i = 0; i < N; ++i) {
			if (check(map[i])) ++ans;
			if (check(map_v[i])) ++ans;
		}
		
		System.out.println(ans);
	}

}
