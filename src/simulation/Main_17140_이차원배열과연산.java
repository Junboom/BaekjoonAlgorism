package simulation;

import java.io.*;
import java.util.*;

public class Main_17140_이차원배열과연산 {

	public static int xMax, yMax;
	public static boolean isSwap;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int[] sort(int[] line) {
		int[] newLine = new int[100];
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < 100; ++i) {
			if (line[i] == 0)
				continue;
			
			if (map.containsKey(line[i]))
				map.put(line[i], map.get(line[i]) + 1);
			else
				map.put(line[i], 1);
			
			if (isSwap) yMax = yMax < cnt ? cnt : yMax;
			else		xMax = xMax < cnt ? cnt : xMax;
		}
		
		List<Integer> list = new ArrayList<>();
		list.addAll(map.keySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				int v1 = map.get(o1);
				int v2 = map.get(o2);
				if (v1 == v2)
					return (int) o1 - (int) o2;
				return v1 - v2;
			}
		});
		
		Iterator it = list.iterator();
		while (it.hasNext()) {
			int num = (int) it.next();
			newLine[cnt++] = num;
			newLine[cnt++] = map.get(num);
			if (isSwap) xMax = xMax < cnt ? cnt : xMax;
			else		yMax = yMax < cnt ? cnt : yMax;
		}
		
		return newLine;
	}
	
	public static int[][] swap(int[][] A) {
		int[][] newA = new int[100][100];
		
		for (int i = 0; i < 100; ++i) {
			for (int j = 0; j < 100; ++j)
				newA[i][j] = A[j][i];
		}

		isSwap = !isSwap;
		
		return newA;
	}
	
	public static void main(String[] args) throws IOException {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		int[][] A = new int[100][100];
		
		for (int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 3; ++j)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		xMax = 3;
		yMax = 3;
		
		while (A[r][c] != k) {
			if (100 < answer) {
				answer = -1;
				break;
			}
			
			if (xMax >= yMax) {
				for (int i = 0; i < 100; ++i)
					A[i] = sort(A[i]);
			}
			else {
				A = swap(A);
				for (int i = 0; i < 100; ++i)
					A[i] = sort(A[i]);
				A = swap(A);
			}
			
			++answer;
		}
		
		System.out.println(answer);
	}

}
