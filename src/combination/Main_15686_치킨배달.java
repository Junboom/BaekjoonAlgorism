package combination;

import java.io.*;
import java.util.*;

public class Main_15686_치킨배달 {
	
	public static int ans, N, M;
	public static List<int[]> homes, bbqs;
	public static boolean[] v;
	
	public static void comb(int cnt, int idx) {
		if (cnt == M) {
			int distAll = 0;
			
			for (int i = 0; i < homes.size(); ++i) {
				int[] home = homes.get(i);
				int min = 987654321;
				
				for (int j = 0; j < bbqs.size(); ++j) {
					if (!v[j]) continue;
					int[] bbq = bbqs.get(j);
					
					int dist = Math.abs(home[0] - bbq[0]) + Math.abs(home[1] - bbq[1]);
					min = min > dist ? dist : min;
				}
				
				distAll += min;
			}
			
			ans = ans > distAll ? distAll : ans;
			return;
		}
		
		for (int i = idx; i < bbqs.size(); ++i) {
			if (v[i]) continue;
			
			v[i] = true;
			comb(cnt + 1, i + 1);
			v[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		ans = 987654321;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		homes = new ArrayList<>();
		bbqs = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int loc = Integer.parseInt(st.nextToken());
					 if (loc == 1) homes.add(new int[] {i, j});
				else if (loc == 2) bbqs.add(new int[] {i, j});
			}
		}
		
		v = new boolean[bbqs.size()];
		comb(0, 0);
		
		System.out.println(ans);
	}

}
