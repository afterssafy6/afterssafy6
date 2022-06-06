import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ 2075 N번째 큰 수
public class BOJ_2075 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 높은 숫자 순 우선순위 큐
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                priorityQueue.add(Integer.parseInt(st.nextToken()));
            }
        }

        // N-1개 out
        for (int i = 0; i < N - 1; i++) {
            priorityQueue.remove();
        }

        // N번째 숫자는?
        System.out.println(priorityQueue.peek());

    }
}
