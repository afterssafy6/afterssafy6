import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Node arr [] = new Node[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            arr[i] = new Node(X, Y);
        }
        // 시작 시간이 빠른 순 정렬 (먼저 시작을 빨리해야 강의실 사용을 최소화 할 수 있다. 시작시간이 같으면 마감 시간도 빠른 강의를 먼저 배정해야한다)
        // 시작 시간이 같으면 마감 시간이 빠른 순 정렬
        Arrays.sort(arr);
        pq.offer(arr[0].y);

        for(int i = 1; i < N; i++) {
            // 시작시간이 마침시간보다 더 크면 그 강의실에 강의를 추가한다.
            if(arr[i].x >= pq.peek()) pq.poll();


            // 시작시간이 마침시간보다 빠르면 그냥 강의실을 추가한다.

            pq.offer(arr[i].y);
        }

        System.out.println(pq.size());

    }
    static class Node implements Comparable<Node> {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if(this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }

}