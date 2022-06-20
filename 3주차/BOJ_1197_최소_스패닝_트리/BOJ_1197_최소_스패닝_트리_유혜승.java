package boj.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1197 {

    static int V,E;
    static int[] parent;

    static class Info implements Comparable<Info> {
        int start;
        int end;
        int cost;
        public Info(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost-o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long answer = 0;

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int e=0; e<E; e++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Info(start, end, cost));
        }

        parent = new int[V+1];
        for(int i=0; i<=V; i++) {
            parent[i] = i;
        }

        while(!pq.isEmpty()) {
            Info now = pq.poll();
            if(find(now.start) != find(now.end)) {
                answer += now.cost;
                union(now.start, now.end);
            }
        }

        System.out.println(answer);
    }

    public static int find(int v) {
        if(parent[v]==v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    public static void union(int a, int b) {
        int a_parent = find(a);
        int b_parent = find(b);

        if(a_parent<b_parent) {
            parent[b_parent] = a_parent;
        }
        else parent[a_parent] = b_parent;
    }
}
