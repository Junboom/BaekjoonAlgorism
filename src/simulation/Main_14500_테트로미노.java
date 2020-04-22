package simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {

	public static void main(String[] args) throws IOException {
		int[][][] tet = {
				{ {0, 1}, {0, 2}, {0, 3} },
				
				{ {1, 0}, {2, 0}, {3, 0} },
				
				{ {0, 1}, {1, 0}, {1, 1} },
				
				{ {1, 0}, {2, 0}, {2, 1} },
				{ {0, 1}, {0, 2}, {1, 0} },
				{ {0, 1}, {1, 1}, {2, 1} },
				{ {0, 1}, {0, 2}, {-1, 2} },
				
				{ {1, 0}, {2, 0}, {2, -1} },
				{ {1, 0}, {1, 1}, {1, 2} },
				{ {0, 1}, {1, 0}, {2, 0} },
				{ {0, 1}, {0, 2}, {1, 2} },
				
				{ {1, 0}, {1, 1}, {2, 1} },
				{ {0, 1}, {1, 0}, {1, -1} },
				
				{ {1, 0}, {0, 1}, {-1, 1} },
				{ {0, 1}, {1, 1}, {1, 2} },
				
				{ {0, 1}, {0, 2}, {1, 1} },
				{ {0, 1}, {1, 1}, {-1, 1} },
				{ {0, 1}, {0, 2}, {-1, 1} },
				{ {1, 0}, {2, 0}, {1, 1} }
		};
		
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				loop:for (int t = 0; t < 19; ++t) {
					int sum = map[i][j];
					
					for (int d = 0; d < 3; ++d) {
						int ii = i + tet[t][d][0];
						int jj = j + tet[t][d][1];
						if (ii < 0 || N <= ii || jj < 0 || M <= jj)
							continue loop;
						
						sum += map[ii][jj];
					}
					
					ans = ans < sum ? sum : ans;
				}
			}
		}
		
		System.out.println(ans);
	}

}
