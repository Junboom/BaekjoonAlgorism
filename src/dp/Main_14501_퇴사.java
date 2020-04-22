package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {

	public static void main(String[] args) throws IOException {
		int ans = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		int[] T = new int[N+1];
		int[] P = new int[N+1];
		int[] dp = new int[N+1];
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
			dp[i] = P[i];
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j < i; ++j) {
				if (T[j] <= i - j) {
					int sum = P[i] + dp[j];
					dp[i] = dp[i] < sum ? sum : dp[i];
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			if (i + T[i] <= N)
				ans = ans < dp[i] ? dp[i] : ans;
		}
		
		System.out.println(ans);
	}

}
