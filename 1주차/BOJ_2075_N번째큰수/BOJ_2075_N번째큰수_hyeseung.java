package boj.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_2075 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N번째 큰 수 찾기
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int c=0; c<N; c++) {
            for(int r=N-1; r>=0; r--) {
                // pq의 사이즈가 N보다 작으면 그냥 add
                if(pq.size()<N) pq.add(map[r][c]);
                // pq의 사이즈가 N과 같다면
                else if(pq.size()==N) {
                    int ele = pq.peek(); // 꺼내진 않고 확인
                    if(ele>map[r][c]) break; // pq의 맨 위가 map[r][c]보다 크다면 그 줄은 이제 그만보기
                    else { // map[r][c]가 ele보다 작다면, ele은 빼고 map[r][c]넣기
                        pq.poll();
                        pq.add(map[r][c]);
                    }
                }
            }
        }
        System.out.println(pq.poll());
    }
}
