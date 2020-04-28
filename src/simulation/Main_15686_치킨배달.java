package simulation;

import java.io.*;
import java.util.*;
import java.util.Map.*;

public class Main_15686_치킨배달 {
	
	public static int ans, N, M;
	public static List<int[]> homes, bbqs;
	public static Map<String, Integer> dists;
	public static List<Entry<String, Integer>> list;
	public static boolean[] v;
	
	public static void comb(int cnt) {
		if (cnt == M) {
			int distAll = 0;
			
			for (int i = 0; i < homes.size(); ++i) {
				for(Entry<String, Integer> entry : list) {
					String dist = entry.getKey();
					StringTokenizer st = new StringTokenizer(dist);
					st.nextToken();
					int bbq = Integer.parseInt(st.nextToken());
					if (!v[bbq]) continue;
					
					distAll += entry.getValue();
					break;
				}
			}
			
			ans = ans > distAll ? distAll : ans;
			return;
		}
		
		for (int i = 0; i < bbqs.size(); ++i) {
			if (v[i]) continue;
			
			v[i] = true;
			comb(cnt + 1);
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
		dists = new HashMap<>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int loc = Integer.parseInt(st.nextToken());
					 if (loc == 1) homes.add(new int[] {i, j});
				else if (loc == 2) bbqs.add(new int[] {i, j});
			}
		}
		
		for (int i = 0; i < homes.size(); ++i) {
			int[] home = homes.get(i);
			
			for (int j = 0; j < bbqs.size(); ++j) {
				int[] bbq = bbqs.get(j);
				
				int dist = Math.abs(home[0] - bbq[0]) + Math.abs(home[1] - bbq[1]);
				dists.put(i + " " + j, dist);
			}
		}
		
		list = new ArrayList<>(dists.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		v = new boolean[bbqs.size()];
		comb(0);
		
		System.out.println(ans);
	}

}
