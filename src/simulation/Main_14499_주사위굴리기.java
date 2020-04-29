package simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {

	public static void main(String[] args) throws IOException {
		int[][] dir = { {}, {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dice = new int[6];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine().trim());
		
		for (int i = 0; i < K; ++i) {
			int k = Integer.parseInt(st.nextToken());
			int xx = x + dir[k][0];
			int yy = y + dir[k][1];
			if (xx < 0 || N <= xx || yy < 0 || M <= yy)
				continue;
			
			x = xx;
			y = yy;
			int temp = 0;
			
			switch (k) {
			case 1:
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[2];
				dice[2] = dice[5];
				dice[5] = temp;
				break;
			case 2:
				temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[2];
				dice[2] = dice[4];
				dice[4] = temp;
				break;
			case 3:
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[2];
				dice[2] = dice[1];
				dice[1] = temp;
				break;
			case 4:
				temp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[3];
				dice[3] = temp;
				break;
			}
			
			if (map[x][y] == 0)
				map[x][y] = dice[2];
			else {
				dice[2] = map[x][y];
				map[x][y] = 0;
			}
			
			System.out.println(dice[0]);
		}
	}

}
