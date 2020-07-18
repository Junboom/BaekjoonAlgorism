package simulation;

import java.io.*;
import java.util.*;

public class Main_19235_모노미노도미노 {

	public static int answer;
	public static int[][] rightMap, downMap;
	
	public static void rightMove() {
		for (int j = 4; j >= 0; --j) {
			for (int i = 0; i < 4; ++i) {
				if (rightMap[i][j] == 1 || rightMap[i][j] == 2) {
					int jj = j;
					
					while (jj + 1 < 6 && rightMap[i][jj + 1] == 0) ++jj;
					
					if (jj != j) {
						rightMap[i][jj] = rightMap[i][j];
						rightMap[i][j] = 0;
					}
				}
				else if (i < 3 && rightMap[i][j] == 3 && rightMap[i + 1][j] == 3) {
					int jj = j;
					
					while (jj + 1 < 6 && rightMap[i][jj + 1] == 0 && rightMap[i + 1][jj + 1] == 0) ++jj;
					
					if (jj != j) {
						rightMap[i][jj] = rightMap[i][j];
						rightMap[i + 1][jj] = rightMap[i + 1][j];
						rightMap[i][j] = 0;
						rightMap[i + 1][j] = 0;
					}
				}
			}
		}
	}
	
	public static void downMove() {
		for (int i = 4; i >= 0; --i) {
			for (int j = 0; j < 4; ++j) {
				if (downMap[i][j] == 1 || downMap[i][j] == 3) {
					int ii = i;
					
					while (ii + 1 < 6 && downMap[ii + 1][j] == 0) ++ii;
					
					if (ii != i) {
						downMap[ii][j] = downMap[i][j];
						downMap[i][j] = 0;
					}
				}
				else if (j < 3 && downMap[i][j] == 2 && downMap[i][j + 1] == 2) {
					int ii = i;
					
					while (ii + 1 < 6 && downMap[ii + 1][j] == 0 && downMap[ii + 1][j + 1] == 0) ++ii;
					
					if (ii != i) {
						downMap[ii][j] = downMap[i][j];
						downMap[ii][j + 1] = downMap[i][j + 1];
						downMap[i][j] = 0;
						downMap[i][j + 1] = 0;
					}
				}
			}
		}
	}
	
	public static boolean rightBoom() {
		boolean isBoom = false;
		
		loop:for (int j = 5; j >= 0; --j) {
			for (int i = 0; i < 4; ++i) {
				if (rightMap[i][j] == 0)
					continue loop;
			}
			
			for (int i = 0; i < 4; ++i)
				rightMap[i][j] = 0;
			
			++answer;
			isBoom = true;
		}
		
		return isBoom;
	}
	
	public static boolean downBoom() {
		boolean isBoom = false;
		
		loop:for (int i = 5; i >= 0; --i) {
			for (int j = 0; j < 4; ++j) {
				if (downMap[i][j] == 0)
					continue loop;
			}
			
			for (int j = 0; j < 4; ++j)
				downMap[i][j] = 0;
			
			++answer;
			isBoom = true;
		}
		
		return isBoom;
	}
	
	public static void rightSolve(int t, int x) {
		int y;
		
		switch(t) {
		case 1:
			y = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (rightMap[x][i] != 0) {
					rightMap[x][i - 1] = t;
					y = i;
					break;
				}
			}
			
			if (y == -1)
				rightMap[x][5] = t;
			
			break;
			
		case 2:
			y = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (rightMap[x][i] != 0) {
					rightMap[x][i - 1] = t;
					rightMap[x][i - 2] = t;
					y = i;
					break;
				}
			}
			
			if (y == -1) {
				rightMap[x][5] = t;
				rightMap[x][4] = t;
			}
			
			break;
			
		case 3:
			y = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (rightMap[x][i] != 0 || rightMap[x + 1][i] != 0) {
					rightMap[x][i - 1] = t;
					rightMap[x + 1][i - 1] = t;
					y = i;
					break;
				}
			}
			
			if (y == -1) {
				rightMap[x][5] = t;
				rightMap[x + 1][5] = t;
			}
			
			break;
		}
		
		rightBoom();
		
		do {
			boolean z = false;
			boolean o = false;
			
			for (int i = 0; i < 4; ++i) {
				if (rightMap[i][0] != 0) z = true;
				if (rightMap[i][1] != 0) o = true;
			}
			
			if (z) {
				for (int j = 5; j >= 4; --j) {
					for (int i = 0; i < 4; ++i)
						rightMap[i][j] = 0;
				}
			}
			else if (o) {
				for (int i = 0; i < 4; ++i)
					rightMap[i][5] = 0;
			}
			
			rightMove();
			
		} while (rightBoom());
	}
	
	public static void downSolve(int t, int y) {
		int x;
		
		switch(t) {
		case 1:
			x = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (downMap[i][y] != 0) {
					downMap[i - 1][y] = t;
					x = i;
					break;
				}
			}
			
			if (x == -1)
				downMap[5][y] = t;
			
			break;
			
		case 2:
			x = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (downMap[i][y] != 0 || downMap[i][y + 1] != 0) {
					downMap[i - 1][y] = t;
					downMap[i - 1][y + 1] = t;
					x = i;
					break;
				}
			}
			
			if (x == -1) {
				downMap[5][y] = t;
				downMap[5][y + 1] = t;
			}
			
			break;
			
		case 3:
			x = -1;
			
			for (int i = 2; i < 6; ++i) {
				if (downMap[i][y] != 0) {
					downMap[i - 1][y] = t;
					downMap[i - 2][y] = t;
					x = i;
					break;
				}
			}
			
			if (x == -1) {
				downMap[5][y] = t;
				downMap[4][y] = t;
			}
			
			break;
		}
		
		downBoom();
		
		do {
			boolean z = false;
			boolean o = false;
			
			for (int i = 0; i < 4; ++i) {
				if (downMap[0][i] != 0) z = true;
				if (downMap[1][i] != 0) o = true;
			}
			
			if (z) {
				for (int i = 5; i >= 4; --i) {
					for (int j = 0; j < 4; ++j)
						downMap[i][j] = 0;
				}
			}
			else if (o) {
				for (int i = 0; i < 4; ++i)
					downMap[5][i] = 0;
			}
			
			downMove();
			
		} while (downBoom());
	}
	
	public static void move(List<int[]> dominos) {
		for (int i = 0; i < dominos.size(); ++i) {
			int[] domino = dominos.get(i);
			
			rightSolve(domino[0], domino[1]);
			downSolve(domino[0], domino[2]);
		}
	}
	
	public static int solve() {
		int answer = 0;
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 2; j < 6; ++j) {
				if (rightMap[i][j] != 0)
					++answer;
			}
		}
		
		for (int i = 2; i < 6; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (downMap[i][j] != 0)
					++answer;
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		answer = 0;
		rightMap = new int[4][6];
		downMap = new int[6][4];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		List<int[]> dominos = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			dominos.add(new int[] {t, x, y});
		}
		
		move(dominos);
		
		System.out.println(answer);
		System.out.println(solve());
	}

}
