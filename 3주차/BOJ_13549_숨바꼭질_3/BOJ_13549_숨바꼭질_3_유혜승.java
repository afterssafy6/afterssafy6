package boj.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_13549 {
    static class Hist {
        int pos;
        int time;
        public Hist(int pos, int time) {
            this.pos= pos;
            this.time=time;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] visited = new int[100001];
        visited[N] = 1; // 1로 시작 -> 나중에 시간에서 1빼서 출력 (방문하지 않은 곳과 구별하기 위해)

        Queue<Hist> q = new LinkedList<>();
        q.add(new Hist(N, 1));

        while(!q.isEmpty()) {
            Hist now = q.poll();

            // 뒤로 걷기
            if(now.pos-1>=0) {
                // 방문하지 않은 곳이거나, 지금 방문하는게 전에 기존 시간보다 빠르다면
                if(visited[now.pos-1]==0 || visited[now.pos-1] > now.time+1) {
                    visited[now.pos-1] = now.time+1;
                    q.add(new Hist(now.pos-1, now.time+1));
                }
            }

            // 앞으로 걷기
            if(now.pos+1<100001) {
                if(visited[now.pos+1]==0 || visited[now.pos+1] > now.time+1) {
                    visited[now.pos+1] = now.time+1;
                    q.add(new Hist(now.pos+1, now.time+1));
                }
            }

            // 순간이동
            if(now.pos*2 < 100001) {
                if(visited[now.pos*2]==0 || visited[now.pos*2] > now.time) {
                    visited[now.pos*2] = now.time;
                    q.add(new Hist(now.pos*2, now.time));
                }
            }
        }

        System.out.println(visited[K]-1);
    }

}
