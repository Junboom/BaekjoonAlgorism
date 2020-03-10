package graph;

import java.io.*;
import java.util.*;

class Node {
	int next;
	int val;
	
	Node(int next, int val) {
		this.next = next;
		this.val = val;
	}
}

public class Main_1753_최단경로_List {

	public static int INF = 987654321;
	public static int ans, V, E, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine().trim());
		
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		List<Node>[] lines = new List[V+1];
		for (int i = 1; i <= V; ++i)
			lines[i] = new ArrayList<Node>();
		
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			lines[u].add(new Node(v, w));
		}
		
		Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.val - n2.val;
			}
		});
		dist[K] = 0;
		pq.add(new Node(K, 0));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			
			for (Node next : lines[node.next]) {
				if (dist[next.next] > dist[node.next] + next.val) {
					dist[next.next] = dist[node.next] + next.val;
					pq.add(new Node(next.next, dist[next.next]));
				}
			}
		}
		
		for (int i = 1; i <= V; ++i)
			bw.write(dist[i] == INF ? "INF\n" : dist[i] + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}
