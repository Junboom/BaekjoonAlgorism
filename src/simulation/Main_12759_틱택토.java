package simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Main_12759_틱택토 {
    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[3][3];
        int p1 = Integer.parseInt(br.readLine());
        int p2 = p1 == 1 ? 2 : 1;
        for (int i = 0; i < 9; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = i % 2 == 0 ? p1 : p2;

            if ((map[x][0] == map[x][1] && map[x][1] == map[x][2]) ||
                    (map[0][y] == map[1][y] && map[1][y] == map[2][y]) ||
                    (map[1][1] != 0 && map[0][0] == map[1][1] && map[1][1] == map[2][2]) ||
                    (map[1][1] != 0 && map[0][2] == map[1][1] && map[1][1] == map[2][0])) {
                ans = map[x][y];
                break;
            }
        }
        System.out.println(ans);
    }
}
