import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 방문 체크 배열
        boolean visited[] = new boolean [V+1];
        int sum = 0;
        HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
        // graph 초기화
        for(int i = 1; i <= V; i++) {
            graph.put(i, new ArrayList<>());
        }


        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 그래프
            graph.get(v).add(new Node(e, c));
            graph.get(e).add(new Node(v, c));
        }
        // 간선의 수가 많기 때문에 프림 알고리즘을 통해
        // 정점을 중심으로 탐색
        // 가중치를 최소로 두는 MST
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while(pq.size() != 0) {
            Node tmp = pq.poll();
            if(visited[tmp.vertex]) {
                continue;
            }
            visited[tmp.vertex] = true;
            sum += tmp.weight;
            for(Node tp : graph.get(tmp.vertex)) {
                if(visited[tp.vertex]) {
                    continue;
                }
                pq.add(new Node(tp.vertex, tp.weight));
            }
        }
        System.out.println(sum);
    }
    static class Node implements Comparable<Node> {
        int vertex;
        int weight;
        Node (int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}