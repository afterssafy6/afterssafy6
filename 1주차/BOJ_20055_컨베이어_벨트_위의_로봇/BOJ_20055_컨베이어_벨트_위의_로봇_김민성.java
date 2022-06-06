import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
    // 수열의 합을 최대로
    static int K;
    static int N;
    static int rst;
    static Integer[] durability;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String str [] = bf.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        // 내구도 개수 한계 (trigger)
        K = Integer.parseInt(str[1]);

        String conv [] = bf.readLine().split(" ");
        // 내구도 배열
        durability = Stream.of(conv).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        move(durability);
        System.out.println(rst);
    }

    static void move(Integer[] arr) {
        // 컨베이어 벨트 ArrayList
        ArrayList al = new ArrayList<Convey>();
        // 마지막에 추출할 단계 변수
        int step = 0;
        // 초기에 로봇이 들어가는 index
        int start = 0;
        // 로봇의 위치정보를 기록할 PQ
        PriorityQueue pq = new PriorityQueue();
        // 초기에 로봇이 나가는 index
        int exit = (int) (arr.length - 1) / 2;
        // 컨베이어벨트 정보를 기록할 ArrayList 초기화
        for(int i = 0; i < arr.length; i++) {
            // 컨베이어 객체 생성
            Convey dt = new Convey(arr[i], false);
            al.add(dt);
        }


        while(true) {
            // 단계 변수 + 1 (1단계 -> 2단계 -> 등등)
            step++;

            // 인덱스 회전
            if(exit != 0) exit--;
            else exit = arr.length - 1;
            if(start == 0) start = arr.length - 1;
            else start--;


            // 마지막에 체크할 변수
            int check = 0;


            // 다음 PQ에 넣을 정보를 담을 임시 ArrayList
            ArrayList robo = new ArrayList<Robot>();

            // PQ에서 로봇정보와 그에 해당하는 컨베이어벨트 정보를 추출
            while(pq.size() != 0) {
                Robot tm = (Robot) pq.poll();
                Convey dt = (Convey) al.get(tm.index);
                // 나가는 index에 도달했다면?
                if(tm.index == exit) {
                    // Convey의 정보를 false로 변환
                    dt.b = false;
                } else {
                    // 나가는 index가 아닐때 로봇의 인덱스가 마지막에 도달한다면 처음으로 이동해야한다.
                    if(tm.index == al.size() - 1) {

                        // 내구도 1이상이고  로봇이없을 때
                        if (0 < ((Convey) al.get(0)).dur && !((Convey) al.get(0)).b) {
                            // 로봇 이동
                            dt.b = false;

                            // 0 인덱스가 나가는 index가 아닐때만 컨베이를 비활성화 해주고 로봇데이터를 생성한다.
                            if(exit != 0) {
                                ((Convey) al.get(0)).b = true;

                                // 새로운 로봇데이터
                                Robot rb = new Robot(0, tm.order);
                                robo.add(rb);
                            }
                            // 나가는 곳이 아니더라도 내구도는 감소한다.
                            ((Convey) al.get(0)).dur -= 1;
                        } else {
                            // 이동할 수 없을 때 기존의 로봇데이터
                            Robot rb = new Robot(tm.index, tm.order);
                            robo.add(rb);
                        }
                    } else {
                        // 내구도 1이상이고 다음칸에 로봇이 없을 때 이동
                        if (((Convey) al.get(tm.index + 1)).dur > 0 && !((Convey) al.get(tm.index + 1)).b) {
                            // 로봇 이동
                            dt.b = false;
                            if(exit != tm.index + 1) {
                                ((Convey) al.get(tm.index + 1)).b = true;
                                // 새로운 로봇 데이터
                                Robot rb = new Robot(tm.index + 1, tm.order);
                                robo.add(rb);
                            }
                            // 나가는 곳이 아니더라도 내구도는 감소한다.
                            ((Convey) al.get(tm.index + 1)).dur -= 1;
                        } else {
                            // 이동할 수 없을 때 기존의 로봇데이터
                            Robot rb = new Robot(tm.index, tm.order);
                            robo.add(rb);
                        }
                    }
                }

            }
            // 새로운 로봇 정보를 PQ에 저장
            for(int i = 0; i < robo.size(); i++) {
                pq.add(robo.get(i));
            }


            // 로봇을 올린다.
            Convey tmp = (Convey) al.get(start);
            if(tmp.dur != 0 && !tmp.b) {
                // 새로운 로봇 정보
                Robot nd = new Robot(start, step);
                nd.order = step;
                nd.index = start;
                // Convey의 정보를 조정한다.
                tmp.dur--;
                tmp.b = true;
                // 로봇을 PQ에 넣는다
                pq.add(nd);
            }

            // 내구도 확인
            for(int i = 0; i < al.size(); i++) {
                Convey dt = (Convey) al.get(i);
                if(dt.dur == 0) {
                    check++;
                }
            }
            // 다음단계로 넘어갈지 체크
            if(check >= K) {
                break;
            }
        }
        rst = step;
    }

    static class Robot implements Comparable<Robot> {
        // 로봇이 존재하는 index
        int index;
        int order;

        public Robot (int index, int order) {
            this.index = index;
            this.order = order;
        }


        @Override
        public int compareTo(Robot o) {
            return this.order - o.order;
        }
    }

    static class Convey {
        // 내구도
        int dur;
        // 로봇의 존재
        boolean b;

        public Convey (int dur, boolean b) {
            this.dur = dur;
            this.b = b;
        }
    }
}
