#include <iostream>
#include <map>

using namespace std;

int INF = 987654321;
map<int, int> graph;
int dist[];
bool check[];

int find(int n)
{
    int min = INF;
    int pos = 0;

    for (int i = 0; i < n; ++i)
    {
        if (dist[i] < min && !check[i])
        {
            min = dist[i];
            pos = i;
        }
    }

    return pos;
}

void solve(int start, int n)
{
    for (int i = 0; i < n; ++i)
    {
        dist[i] = graph[20000 * start + i];
        check[i] = false;
    }

    check[start] = true;

    for (int i = 0; i < n - 1; ++i)
    {
        int u = find(i);
        check[u] = true;

        for (int w = 0; w < n; ++w)
        {
            if (!check[w] && dist[u] + graph[20000 * u + w] < dist[w])
                dist[w] = dist[u] + graph[20000 * u + w];
        }
    }
}

int main()
{
    int V, E, K;
    
    graph = new HashMap<>();
    dist = new int[V];
    check = new boolean[V];

    for (int i = 0; i < E; ++i)
    {
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken()) - 1;
        int v = Integer.parseInt(st.nextToken()) - 1;
        int w = Integer.parseInt(st.nextToken());
        graph.put(20000 * u + v, w);
    }

    solve(0, V);

    StringBuilder sb = new StringBuilder();
    sb.append("0\n");
    for (int i = 0; i < V - 1; ++i)
    {
        if (dist[(i + K) % V] == INF)
            sb.append("INF\n");
        else
            sb.append(dist[(i + K) % V]).append("\n");
    }
    bw.write(sb.toString());
}
