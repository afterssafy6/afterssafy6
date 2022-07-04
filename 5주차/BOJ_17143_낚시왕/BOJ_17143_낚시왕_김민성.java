import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 기록
        Node MAP[][] = new Node[R+1][C+1];
        // 잡아먹기 위한 기록 배열
        PriorityQueue<Node> sub[][] = new PriorityQueue[R+1][C+1];
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                sub[i][j] = new PriorityQueue<>();
            }
        }



        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            MAP[r][c] = new Node(d, s, z);
        }

        int rst = BFS(MAP, sub);

        System.out.println(rst);
    }
    static int BFS(Node map[][], PriorityQueue<Node> sub[][]) {
        int king = 1;
        int limit = map[0].length;
        int result = 0;
        while(king < limit) {
            // 상어왕이 상어잡는
            for(int i = 1; i < map.length; i++) {
                if(map[i][king] != null) {
                    result += map[i][king].size;
                    map[i][king] = null;
                    break;
                }
            }


            if(king == limit - 1) {
                break;
            }

            // 상어 이동
            // 현재 위치 기록

            for(int i = 1; i < map.length; i++) {
                for(int j = 1; j < map[1].length; j++) {
                    // 상어 발견
                    if(map[i][j] != null) {
                        Node tmp = map[i][j];
                        // 위 아래
                        if(tmp.dir == 1 || tmp.dir == 2) {
                            int pivot = i;
                            int cnt = 0;
                            while(cnt < tmp.speed) {
                                if(tmp.dir == 1) {
                                    if(pivot < 1) {
                                        pivot = 2;
                                        tmp.dir = 2;
                                    } else {
                                        pivot--;
                                        if(pivot < 1) {
                                            pivot = 2;
                                            tmp.dir = 2;
                                        }
                                    }
                                } else if(tmp.dir == 2) {
                                    if(pivot == map.length) {
                                        pivot = map.length - 2;
                                        tmp.dir = 1;
                                    } else {
                                        pivot++;
                                        if(pivot == map.length) {
                                            pivot = map.length - 2;
                                            tmp.dir = 1;
                                        }
                                    }
                                }
                                cnt++;
                            }


                            sub[pivot][j].add(new Node(tmp.dir, tmp.speed, tmp.size));

                            // 3 오른쪽 4 왼쪽
                        } else if(tmp.dir == 3 || tmp.dir == 4) {
                            int pivot = j;
                            int cnt = 0;
                            while(cnt < tmp.speed) {
                                if(tmp.dir == 3) {
                                    if(pivot == map[1].length) {
                                        pivot = map[1].length - 2;
                                        tmp.dir = 4;
                                    } else {
                                        pivot++;
                                        if(pivot == map[1].length) {
                                            pivot = map[1].length - 2;
                                            tmp.dir = 4;
                                        }
                                    }
                                } else if(tmp.dir == 4) {
                                    if(pivot == 0) {
                                        pivot = 2;
                                        tmp.dir = 3;
                                    } else {
                                        pivot--;
                                        if(pivot == 0) {
                                            pivot = 2;
                                            tmp.dir = 3;
                                        }
                                    }
                                }
                                cnt++;
                            }

                            sub[i][pivot].add(new Node(tmp.dir, tmp.speed, tmp.size));

                        }
                    }
                }
            }

            for(int i = 1; i < map.length; i++) {
                for(int j = 1; j < map[1].length; j++) {
                    // 잡아먹기
                    if(sub[i][j].size() > 1) {
                        Node tmp = sub[i][j].poll();
                        map[i][j] = tmp;
                        sub[i][j] = new PriorityQueue<>();
                        // 한마리만 있을 때 기록
                    } else if(sub[i][j].size() == 1) {
                        Node tmp = sub[i][j].poll();
                        map[i][j] = tmp;
                    } else {
                        map[i][j] = null;
                    }
                }
            }


            king++;
        }

        return result;
    }



    static class Node implements Comparable<Node> {
        int dir;
        int speed;
        int size;

        public Node( int dir, int speed, int size) {
            this.dir = dir;
            this.speed = speed;
            this.size = size;

        }

        @Override
        public int compareTo(Node o) {
            return o.size - this.size;
        }
    }
}