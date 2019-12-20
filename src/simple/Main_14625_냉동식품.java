package simple;

import java.io.*;
import java.util.StringTokenizer;

public class Main_14625_냉동식품 {
    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] time = new int[2][2];
        for (int i = 0; i < 2; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; ++j) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int N = Integer.parseInt(br.readLine());

        while (true) {
                 if (time[0][0] / 10 == N)   ++ans;
            else if (time[0][0] % 10 == N)   ++ans;
            else if (time[0][1] / 10 == N)   ++ans;
            else if (time[0][1] % 10 == N)   ++ans;

            if (time[0][0] == time[1][0] && time[0][1] == time[1][1])
                break;

            if (60 == ++time[0][1]) {
                if (24 == ++time[0][0])
                    time[0][0] = 0;
                time[0][1] = 0;
            }
        }

        System.out.println(ans);
    }
}
