package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1068_트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] tree = new int[N][];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            tree[n][0] = Integer.parseInt(st.nextToken());
        }

    }
}
