package graph;

import java.io.*;
import java.util.*;

class Main_1753_최단경로 {

    public static int INF = 987654321;
    public static List<Integer[]> graph;
    public static int[] dist;
    public static boolean[] find;

    public static int choose(int n) {
        int min = INF;
        int pos = 0;

        for (int i = 0; i < n; ++i) {
            if (dist[i] < min && !find[i]) {
                min = dist[i];
                pos = i;
            }
        }

        return pos;
    }

    public static void solve(int start, int n) {
        for (int i = 0; i < n; ++i) {
            dist[i] = graph[start][i];
            find[i] = false;
        }

        find[start] = true;

        for (int i = 0; i < n-1; ++i) {
            int u = choose(i);
            find[u] = true;

            for (int w = 0; w < n; ++w) {
                if (!find[w] && dist[u] + graph[u][w] < dist[w])
                    dist[w] = dist[u] + graph[u][w];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer( br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        dist = new int[V];
        find = new boolean[V];

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.set(u, v[w]);
        }

        solve(0, V);

        for (int i = 0; i < V; ++i) {
            if (dist[(i+K-1)%V] == INF) System.out.println("INF");
            else                        System.out.println(dist[(i+K-1)%V]);
        }
    }

}
