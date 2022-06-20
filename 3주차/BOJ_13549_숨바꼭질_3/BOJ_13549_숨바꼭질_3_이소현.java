package BOJ.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 13549. 숨바꼭질 3
public class BOJ_13549 {

	static class Node{
		int num;
		int time;
		public Node(int num, int sum) {
			super();
			this.num = num;
			this.time = sum;
		}
	}
	static boolean[] isVisited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		bfs(N, K);
	}
	// 너비 우선 탐색
	public static void bfs(int N, int K) {
		int min = Integer.MAX_VALUE;
		Queue<Node> queue = new LinkedList();
		isVisited = new boolean[100_001]; 
		queue.add(new Node(N, 0));
		isVisited[N] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			isVisited[cur.num] = true;
			if(cur.num==K) {
				min = Math.min(min, cur.time);
				break;
			}
			int n1 = cur.num*2;  // 순간이동
			int n2 = cur.num-1;  // X-1 걷기
			int n3 = cur.num+1;  // X+1 걷기
			if(n1<=100000 && !isVisited[n1]) queue.add(new Node(n1, cur.time));
			if(0<=n2 && n2<=100000 && !isVisited[n2]) queue.add(new Node(n2, cur.time+1));
			if(n3<=100000 && !isVisited[n3]) queue.add(new Node(n3, cur.time+1));
		}
		System.out.println(min);
	}

}
// 수빈이는 걷거나 순간이동을 할 수 있다.
// 수빈 위치 X -> 걷는 경우 : 1초후 X-1 또는 X+1로 이동. 
//                순간이동하는 경우 : 0초 후에 2*X로 이동.

