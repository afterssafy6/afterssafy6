import java.io.*;
import java.util.*;


public class Main {
    static Queue<SnakeInfo> list;
    static int MAP[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 크기
        int N = Integer.parseInt(br.readLine());
        // 사과의 개수
        int K = Integer.parseInt(br.readLine());
        // 맵
        MAP = new int[N+1][N+1];
        MAP[1][1] = -1;
        for(int i = 0; i < K; i++) {
            String xy[] = br.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            // 사과 위치
            MAP[x][y] = 1;
        }
        // 뱀의 방향 변환 횟수
        int L = Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        for(int i = 0; i < L; i++) {
            String snake[] = br.readLine().split(" ");
            // L이면 왼쪽 D면 오른 90도 방향
            list.offer(new SnakeInfo(Integer.parseInt(snake[0]), snake[1]));
        }
        int exit = gameStart();

        System.out.println(exit);

    }

    static int gameStart() {
        // 위 아래 왼 오른
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        int time = 1;
        //초기 방향 오른쪽
        int dir = 3;
        Deque<Snake> deq = new ArrayDeque<>();
        Snake head = new Snake(1, 1);
        deq.addFirst(head);
        while(true) {
            head = deq.peek();
            int nx = head.x + dx[dir];
            int ny = head.y + dy[dir];
            if(nx < 1 || ny < 1 || nx >= MAP.length || ny >= MAP[1].length || MAP[nx][ny] == -1) {
                return time;
            }
            if(MAP[nx][ny] == 1) {
                MAP[nx][ny] = -1;
                MAP[head.x][head.y] = -1;
                deq.addFirst(new Snake(nx, ny));
            } else if(MAP[nx][ny] == 0) {
                deq.addFirst(new Snake(nx, ny));
                Snake sna = deq.pollLast();
                MAP[nx][ny] = -1;
                MAP[sna.x][sna.y] = 0;
            }




            if(list.peek() != null && list.peek().second == time) {
                // 방향 전환
                String direction = list.poll().dir;

                if(dir == 0) {
                    if(direction.equals("L")) {
                        dir = 2;
                    } else if(direction.equals("D")) {
                        dir = 3;
                    }
                }
                else if(dir == 1) {
                    if(direction.equals("L")) {
                        dir = 3;
                    } else if(direction.equals("D")) {
                        dir = 2;
                    }
                }
                else if(dir == 2) {
                    if(direction.equals("L")) {
                        dir = 1;
                    } else if(direction.equals("D")) {
                        dir = 0;
                    }
                }
                else if(dir == 3) {
                    if(direction.equals("L")) {
                        dir = 0;
                    } else if(direction.equals("D")) {
                        dir = 1;
                    }
                }
            }

            time++;
        }
    }

    static class Snake {
        int x;
        int y;
        public Snake (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class SnakeInfo {
        int second;
        String dir;
        public SnakeInfo(int second, String dir) {
            this.second = second;
            this.dir = dir;
        }
    }


}

