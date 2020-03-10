package binarysearch;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1517_버블소트 {

	public static long result(int[] A1, int[] A2, int start, int end) {
		if (start == end)
			return 0;
		
		int mid = (start + end) / 2;
		long res = result(A1, A2, start, mid) + result(A1, A2, mid+1, end);
		
		int s = start;
		int m = mid + 1;
		int idx = 0;
		
		while (s <= mid || m <= end) {
			if (s <= mid && (end < m || A1[s] <= A1[m]))
				A2[idx++] = A1[s++];
			else {
				res += (mid - s + 1) * 1L;
				A2[idx++] = A1[m++];
			}
		}
		
		for (int i = start; i <= end; ++i)
			A1[i] = A2[i - start];
		
		return res;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int[] A = new int[N];
		for (int n = 0; n < N; ++n)
			A[n] = Integer.parseInt(st.nextToken());
		
		System.out.println(result(A, new int[N], 0, N-1));
	}

}
