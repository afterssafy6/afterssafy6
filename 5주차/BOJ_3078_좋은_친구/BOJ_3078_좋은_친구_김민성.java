import java.util.*;
import java.io.*;
public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Node array[] = new Node[N];
        for(int i = 0 ; i < N; i++) {
            String str = br.readLine();

            array[i] = new Node(str.length(), i);
            // 1부터 N까지
        }
        Arrays.sort(array);
        long rst = 0;
        Queue<Node> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if(queue.isEmpty()) {
                queue.add(array[i]);
            } else if(queue.peek().len == array[i].len && array[i].index - queue.peek().index <= K) {
                rst += queue.size();
                queue.add(array[i]);
            } else {
                queue.poll();
                i--;
            }
        }





        System.out.println(rst);
    }

    static class Node implements Comparable<Node> {
        int len;
        int index;
        Node (int len, int index) {
            this.len = len;
            this.index = index;
        }
        @Override
        public int compareTo(Node o) {
            if(this.len == o.len) {
                return this.index - o.index;
            }

            return this.len - o.len;
        }
    }
}
