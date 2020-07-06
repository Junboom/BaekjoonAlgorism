#include <iostream>
#include <vector>
#include <queue>

#define endl    '\n'
#define INF     987654321
#define MAX     20010

using namespace std;

struct node
{
    int next, weight;

    bool operator<(const node& r) const
    {
        return r.weight < weight;
    }
};

int ans, V, E, K;
int dist[MAX];
bool visit[MAX];
vector<pair<int, int>> vec[MAX];
priority_queue<node> pq;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> V >> E >> K;

    fill(dist + 1, dist + V + 1, INF);
    dist[K] = 0;

    for (int i = 0; i < E; ++i)
    {
        int u, v, w;
        cin >> u >> v >> w;
        vec[u].push_back({ v, w });
    }

    pq.push({ K, 0 });

    while (!pq.empty())
    {
        int next = pq.top().next;
        int weight = pq.top().weight;
        pq.pop();

        if (visit[next])
            continue;

        visit[next] = true;

        for (auto next_node : vec[next])
        {
            if (dist[next] + next_node.second < dist[next_node.first])
            {
                dist[next_node.first] = dist[next] + next_node.second;
                pq.push({ next_node.first, dist[next_node.first] });
            }
        }
    }

    for (int i = 1; i <= V; ++i)
    {
        if (dist[i] == INF)
            cout << "INF" << endl;
        else
            cout << dist[i] << endl;
    }
}
