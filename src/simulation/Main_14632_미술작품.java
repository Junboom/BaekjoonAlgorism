package simulation;

import java.io.*;
import java.util.*;

public class Main_14632_미술작품 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                map[i][j] = '.';
            }
        }

        int[][] HW = new int[K+1][2];
        char[][][] k = new char[K+1][][];
        for (int i = 1; i <= K; ++i) {
            st = new StringTokenizer(br.readLine());
            HW[i][0] = Integer.parseInt(st.nextToken());
            HW[i][1] = Integer.parseInt(st.nextToken());
            k[i] = new char[HW[i][0]][HW[i][1]];
            for (int j = 0; j < HW[i][0]; ++j) {
                k[i][j] = br.readLine().toCharArray();
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = 0; j < HW[n][0]; ++j) {
                for (int jj = 0; jj < HW[n][1]; ++jj) {
                    map[x+j][y+jj] = k[n][j][jj];
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
