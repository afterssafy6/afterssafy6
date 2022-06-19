import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        System.out.println(BFS(N, M));
    }


    static int BFS(int n, int m) {
        LinkedList<Node> arr = new LinkedList<>();
        arr.add(new Node(0, n));
        int min = 0;
        // 존재할 수 있는 위치 체크
        boolean visited[] = new boolean[100001];
        visited[n] = true;
        while(arr.size() != 0) {
            Node tmp = arr.remove(0);
            visited[tmp.num] = true;
            if(tmp.num == m) {
                // 해당 위치에 도달한다면
                min = tmp.time;
                break;
            }
            // 뒤로가는 분기
            if(tmp.num > 0 && !visited[tmp.num - 1]) {
                arr.add(new Node(tmp.time + 1, tmp.num - 1));
            }

            // 앞으로 가는 분기
            if(tmp.num < m && !visited[tmp.num + 1]) {
                arr.add(new Node(tmp.time + 1, tmp.num + 1));
            }
            // 순간이동 분기(순간이동은 가장 먼저 처리해야 할 분기 - 가장 빨리 처리해야함)
            if(tmp.num * 2 < 100001 && !visited[tmp.num * 2]) {
                arr.addFirst(new Node(tmp.time, tmp.num * 2));
            }
        }
        return min;
    }
    static class Node {
        int time;
        int num;
        Node(int time, int num) {
            this.time = time;
            this.num = num;
        }
    }
}