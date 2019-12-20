import java.io.*;
import java.util.*;

public class Main_17822_원판돌리기 {

	public static int N, M, T;
	
	public static void turn(int[][] circle, int x, int d, int k) {
		k %= M;
		
		if (d == 0) {
			for (int i = x; i <= N; i+=x) {
				for (int l = 0; l < k; ++l) {
					int temp = circle[i][M-1];
					for (int j = M-1; j > 0; --j) {
						circle[i][j] = circle[i][j-1];
					}
					circle[i][0] = temp;
				}
			}
		}
		else {
			for (int i = x; i <= N; i+=x) {
				for (int l = 0; l < k; ++l) {
					int temp = circle[i][0];
					for (int j = 0; j < M-1; ++j) {
						circle[i][j] = circle[i][j+1];
					}
					circle[i][M-1] = temp;
				}
			}
		}
	}
	
	public static void find(int[][] circle) {
		Set<int[]> set = new HashSet<>();
		double total = 0;
		int count = 0;
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j < M-1; ++j) {
				if (circle[i][j] == 0)
					continue;
				
				if (circle[i][j] == circle[i][j+1])
					input(set, i, i, j, j+1);
				if (i != N && circle[i][j] == circle[i+1][j])
					input(set, i, i+1, j, j);
				total += circle[i][j];
				++count;
			}
			if (circle[i][M-1] == 0)
				continue;
			
			if (circle[i][M-1] == circle[i][0])
				input(set, i, i, M-1, 0);
			if (i != N && circle[i][M-1] == circle[i+1][M-1])
				input(set, i, i+1, M-1, M-1);
			total += circle[i][M-1];
			++count;
		}
		
		if (set.size() == 0) {
			double avg = total / count;
			
			for (int i = 1; i <= N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (circle[i][j] == 0)
						continue;
					
					if (avg < circle[i][j])
						--circle[i][j];
					else if (avg > circle[i][j])
						++circle[i][j];
				}
			}
		}
		else {
			for (int[] axis : set) {
				circle[axis[0]][axis[1]] = 0;
			}
		}
	}
	
	public static void input(Set<int[]> set, int i1, int i2, int j1, int j2) {
		int[][] temp = new int[2][2];
		temp[0][0] = i1;
		temp[0][1] = j1;
		temp[1][0] = i2;
		temp[1][1] = j2;
		
		set.add(temp[0]);
		set.add(temp[1]);
	}
	
	public static void main(String[] args) throws IOException {
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int[][] circle = new int[N+1][M];
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			turn(circle, x, d, k);
			find(circle);
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j < M; ++j) {
				ans += circle[i][j];
			}
		}
		
		System.out.println(ans);
	}

}
