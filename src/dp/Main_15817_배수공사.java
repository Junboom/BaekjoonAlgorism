package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15817_배수공사 {

    public static int ans, N, x;
    public static int[][] LC, dp;

    public static int rec(int step, int remain) {
        if (step == N) {
            if (remain == 0)    return 1;
            else                return 0;
        }

        int ret = dp[step][remain];
        if (ret != -1)  return ret;

        ret = 0;
        for (int i = 0; i <= LC[step][1]; ++i) {
            int tmp = remain - LC[step][0] * i;
            if (tmp >= 0) {
                ret += rec(step+1, tmp);
            }
        }

        dp[step][remain] = ret;
        return ret;
    }

    public static void main (String[] args) throws IOException {
        ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        LC = new int[N][2];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; ++j) {
                LC[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+5][10005];
        for (int i = 0; i <= N+1; ++i) {
            for (int j = 0; j <= 10004; ++j) {
                dp[i][j] = -1;
            }
        }

        System.out.println(rec(0, x));
    }
}
