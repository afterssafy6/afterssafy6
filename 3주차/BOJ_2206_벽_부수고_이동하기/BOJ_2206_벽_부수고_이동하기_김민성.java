import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int rst = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int MAP[][] = new int[N][M];
        for(int i = 0; i < N; i++) {
            String [] arr = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(arr[j]);
            }
        }
        BFS(MAP, N, M);

        System.out.println(rst);

    }


    static void BFS(int map[][], int N, int M) {
        Queue<Node> arr = new LinkedList<>();
        int nx[] = {-1, 1, 0, 0};
        int ny[] = {0, 0, -1, 1};


        boolean visited[][][] = new boolean[N][M][2];
        visited[0][0][0] = true;


        arr.offer(new Node(0, 0, 1, 1));
        while(!arr.isEmpty()) {
            Node tmp = arr.poll();
            if(tmp.x == N-1 && tmp.y == M-1) {
                rst = tmp.move;
                break;
            }
            for(int i = 0; i < 4; i++) {
                int tx = nx[i] + tmp.x;
                int ty = ny[i] + tmp.y;
                if(tx < 0 || ty < 0 || tx >= N || ty >= M) {
                    continue;
                }
                // 벽이 없을 때
                if(map[tx][ty] == 0) {
                    // 벽을 부수고 이동한 경우
                    if(tmp.limit == 0 && !visited[tx][ty][1]) {
                        visited[tx][ty][1] = true;
                        arr.add(new Node(tx, ty, tmp.move+1, 0));
                        // 벽을 부수지 않은 경우
                    } else if(tmp.limit == 1 && !visited[tx][ty][0]) {
                        visited[tx][ty][0] = true;
                        arr.add(new Node(tx, ty, tmp.move + 1, tmp.limit));
                    }
                    // 벽이 있을 때
                } else {
                    // 1번의 기회가 있다면
                    if(tmp.limit == 1) {
                        // 벽을 부수고 이동했을때의 기록
                        visited[tx][ty][1] = true;
                        arr.add(new Node(tx, ty, tmp.move + 1, 0));
                    }
                }

            }
        }
    }
    static class Node {
        int x;
        int y;
        int move;
        int limit;
        Node(int x, int y, int move, int limit) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.limit = limit;
        }
    }
}