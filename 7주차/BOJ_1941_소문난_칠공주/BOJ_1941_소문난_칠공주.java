import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int rst;
    static boolean visited[];
    static char Map[][];
    static int tx[] = {0,0, 1, -1 };
    static int ty[] = {1, -1, 0, 0};
    static int selected[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // S가 Y보다 더많아야한다
        // x 축과 Y축으로 y가 3개 이하인 행에서만 함수 실행

        Map = new char [5][5];
        visited = new boolean[25];
        selected = new int[7];
        for(int i = 0; i < 5; i++) {
            Map[i] = br.readLine().toCharArray();
        }
        DFS(0, 0);


        System.out.println(rst);
    }


    static void DFS(int start, int depth) {
        if(depth == 7) {

            if(isValid()) {
                rst++;
            }
            return;
        }

        for(int i = start; i < 25;i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            selected[depth] = i;
            DFS(i+1, depth+1);
            visited[i] = false;
        }


    }


    static boolean isValid() {
        // 그 좌표가 붙어있는 유효한 좌표인지 확인
        Queue<Integer> queue = new LinkedList<>();
        int Y = 0;
        for(int i: selected) {
            if(Map[i / 5][i % 5] == 'Y') Y++;
        }
        if(Y > 3) return false;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int s: selected) temp.add(s);
        queue.offer(selected[0]);

        while(!queue.isEmpty()) {
            int tmp = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = tx[i] + (tmp / 5);
                int ny = ty[i] + (tmp % 5);
                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                if(temp.contains(nx * 5 + ny)) {
                    temp.remove(Integer.valueOf(nx*5 + ny));
                    queue.offer(nx*5 + ny);
                }
            }
        }
        if(!temp.isEmpty()) return false;

        return true;
    }
}
