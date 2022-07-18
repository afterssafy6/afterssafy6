import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static double rst;
    static double values[];
    static boolean visited[][];
    static int tx[] = {0,0, 1, -1 };
    static int ty[] = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        values = Arrays.stream(arr).mapToDouble(Double ::parseDouble).map(item -> item / 100 ).toArray();

        N = Integer.parseInt(arr[0]);
        visited = new boolean[31][31];
        // 1. 로봇은 네방향 중 하나로 이동한다.
        // 2. 중간 지점에서 단순하지 않다면 더이상 이동하지 않는다. (가지치기)
        // 3. N번 이동하였다면 rst에 더해준다

        DFS( 1, 0, 15, 15);


        System.out.println(rst);
    }

    static void DFS(double chance, int move, int x, int y) {
        if(move == N) {
            rst += chance;
            return;
        }


        for(int i = 0; i < 4; i++) {
            if(values[i+1] == 0) {
                continue;
            }
            int nx = x + tx[i];
            int ny = y + ty[i];
            if(visited[nx][ny]) {
                continue;
            }
            visited[x][y] = true;
            DFS(chance * values[i+1], move + 1,  nx, ny);
            visited[x][y] = false;
        }

    }



}