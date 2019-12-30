package graph;

import java.io.*;
import java.util.*;

class Main_1753_최단경로 {

    public static int INF = 987654321;
    public static Map<Integer, Integer> graph;
    public static int[] dist;
    public static boolean[] check;

    public static int find(int n) {
        int min = INF;
        int pos = 0;

        for (int i = 0; i < n; ++i) {
            if (dist[i] < min && !check[i]) {
                min = dist[i];
                pos = i;
            }
        }

        return pos;
    }

    public static void solve(int start, int n) {
        for (int i = 0; i < n; ++i) {
            dist[i] = graph.containsKey(20000 * start + i) ?
                    graph.get(20000 * start + i) : INF;
            check[i] = false;
        }

        check[start] = true;

        for (int i = 0; i < n-1; ++i) {
            int u = find(i);
            check[u] = true;

            for (int w = 0; w < n; ++w) {
                if (!check[w] && graph.containsKey(20000 * u + w) &&
                        dist[u] + graph.get(20000 * u + w) < dist[w])
                    dist[w] = dist[u] + graph.get(20000 * u + w);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer( br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        dist = new int[V];
        check = new boolean[V];

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.put(20000 * u + v, w);
        }

        solve(0, V);

        StringBuilder sb = new StringBuilder();
        sb.append("0\n");
        for (int i = 0; i < V - 1; ++i) {
            if (dist[(i+K)%V] == INF)   sb.append("INF\n");
            else                        sb.append(dist[(i+K)%V]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
