import java.io.*;
import java.util.*;

public class Main_17143_³¬½Ã¿Õ {
	
	public static int R, C, M;
	public static int[][] D = { {}, {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
	
	public static int catchShark(List<int[]> sharks, int c) {
		int[] rem = new int[5];
		rem[0] = R + 1;
		
		for (int[] shark : sharks) {
			if (shark[1] == c && shark[0] < rem[0])
				rem = shark;
		}
		
		if (rem[4] != 0)
			sharks.remove(rem);
		return rem[4];
	}
	
	public static void moveShark(List<int[]> sharks, int c) {		
		List<int[]>[][] map = new ArrayList[R+1][C+1];
		List<int[]> rem = new ArrayList<>();
		
		for (int[] shark : sharks) {
			for (int i = 0; i < shark[2]; ++i) {
				if (shark[3] == 1 && shark[0] == 1)
					shark[3] = 2;
				else if (shark[3] == 2 && shark[0] == R)
					shark[3] = 1;
				else if (shark[3] == 3 && shark[1] == C)
					shark[3] = 4;
				else if (shark[3] == 4 && shark[1] == 1)
					shark[3] = 3;
				
				shark[0] += D[shark[3]][0];
				shark[1] += D[shark[3]][1];
			}
			
			if (map[shark[0]][shark[1]] == null) {
				map[shark[0]][shark[1]] = new ArrayList<>();
				map[shark[0]][shark[1]].add(shark);
			}
			else {
				if (map[shark[0]][shark[1]].get(0)[4] < shark[4]) {
					rem.add(map[shark[0]][shark[1]].get(0));
					map[shark[0]][shark[1]].set(0, shark);
				}
				else {
					rem.add(shark);
				}
			}
		}
		
		for (int[] r : rem)
			sharks.remove(r);
	}
	
	public static void main(String[] args) throws IOException {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		List<int[]> sharks = new ArrayList<>();
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int[] shark = new int[5];
			for (int j = 0; j < 5; ++j) {
				shark[j] = Integer.parseInt(st.nextToken());
			}
			sharks.add(shark);
		}
		
		for (int c = 1; c <= C; ++c) {
			answer += catchShark(sharks, c);
			moveShark(sharks, c);
		}
		
		System.out.println(answer);
	}

}
