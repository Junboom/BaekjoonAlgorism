package dfs;

import java.io.*;
import java.util.*;

public class Main_3190_뱀 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int ans = 0;
		int n = Integer.parseInt(br.readLine());
		int[][][] map = new int[n][n][2];
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1][1] = 1;
		}
		int l = Integer.parseInt(br.readLine());
		int[][] curve = new int[10001][2];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			curve[idx][0] = 1;
			curve[idx][1] = (st.nextToken().charAt(0) == 'L') ? 0 : 1;
		}
		int[][] dir = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };		// 오른쪽, 아래, 왼쪽, 위 (시계방향)
		
		int i = 0;
		int j = 0;
		int d = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		for (int c = 1; ; c++) {
			int ii = i + dir[d][0];
			int jj = j + dir[d][1];
			if (ii < 0 || jj < 0 || n <= ii || n <= jj || map[ii][jj][0] == 1) {
				ans = c;
				break;
			}
			
			if (map[ii][jj][1] == 1)
				map[ii][jj][1] = 0;
			else {
				int[] tmp = q.poll();
				map[tmp[0]][tmp[1]][0] = 0;
			}
			map[ii][jj][0] = 1;
			q.add(new int[] {ii, jj});
			if (curve[c][0] == 1) {
				switch (d) {
				case 0:		d = (curve[c][1] == 0) ? 3 : 1;		break;
				case 1:		d = (curve[c][1] == 0) ? 0 : 2;		break;
				case 2:		d = (curve[c][1] == 0) ? 1 : 3;		break;
				case 3:		d = (curve[c][1] == 0) ? 2 : 0;		break;
				}
			}
			
			i = ii;
			j = jj;
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}
	
}
