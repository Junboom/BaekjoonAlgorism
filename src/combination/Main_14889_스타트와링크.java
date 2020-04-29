package combination;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {

	public static int ans, N;
	public static int[][] score;
	public static boolean[] v;
	
	public static void comb(int cnt, int idx) {
		if (cnt == N/2) {
			int start = 0;
			int link = 0;
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (v[i] && v[j])
						start += score[i][j] + score[j][i];
					else if (!v[i] && !v[j])
						link += score[i][j] + score[i][j];
				}
			}
			
			int diff = Math.abs(start - link);
			ans = ans > diff ? diff : ans;
			
			return;
		}
		
		for (int i = idx; i < N; ++i) {
			if (v[i]) continue;
			
			v[i] = true;
			if (!v[0]) return;
			comb(cnt + 1, i + 1);
			v[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		ans = 987654321;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		score = new int[N][N];
		v = new boolean[N];
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j)
				score[i][j] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0);
		System.out.println(ans / 2);
	}

}
