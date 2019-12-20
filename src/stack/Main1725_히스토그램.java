package stack;

import java.io.*;
import java.util.Stack;

public class Main1725_히스토그램 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int ans = 0;
		int n = Integer.parseInt(br.readLine());
		int[] graph = new int[n+2];
		for (int i = 1; i <= n; i++)
			graph[i] = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i <= n+1; i++) {
			while (!stack.isEmpty() && graph[i] < graph[stack.peek()])
				ans = Math.max(graph[stack.pop()]*(i-stack.peek()-1), ans);
			stack.push(i);
		}
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}
}
