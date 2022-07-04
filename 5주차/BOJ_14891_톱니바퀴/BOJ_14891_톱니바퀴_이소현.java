import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 14891. 톱니바퀴
public class BOJ_14891 {
    static int[][]  Gears;  // 톱니바퀴 상태
    static int K;  // 회전 횟수
    static int[] direction;  // 회전 방향 상태 저장
    static int score=0;  // 점수

    public static void main(String[] args) throws NumberFormatException, IOException {
        Gears = new int[4][8];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++){
            String gear = br.readLine();
            for(int j=0; j<8; j++){
                Gears[i][j] = gear.charAt(j) - '0';
            }
        }
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            // 톱니바퀴 번호 | 회전 방향
            int num; int dir;
            StringTokenizer st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            checkDir(num, dir);
            rotation();
        }
        calculation();
        System.out.println(score);
    }

    // 톱니바퀴 방향 검사
    public static void checkDir(int num, int dir){  // 3번, 반시계 -1
        direction = new int[4];
        num -= 1;  // 3번이면 2번 톱니바퀴임
        direction[num] = dir;

        // 왼쪽 확인
        for(int i=num; i>0; i--){
            if(Gears[i][6] != Gears[i-1][2]){  // 극이 다르면
                direction[i-1] = direction[i] * -1;  // 반대방향으로 회전 상태 저장
            }else{  // 극이 같으면
                direction[i-1] = 0;  // 회전 안하니까 끝!
                break;
            }
        }

        // 오른쪽 확인
        for(int i=num; i<3; i++){
            if(Gears[i][2] != Gears[i+1][6]) {  // 극이 다르면
                direction[i+1] = direction[i] * -1;
            }else {
                direction[i+1] = 0;
                break;
            }
        }
    }

    // 톱니바퀴 번호, 방향 인자로 주고 회전하는 함수
    public static void rotation(){
        for(int i=0; i<4; i++){
            if(direction[i] == 1){  // 시계방향
                clockwise(i);
            }else if(direction[i] == -1){  // 반시계방향
                counterclockwise(i);
            }
        }
    }

    // 시계방향 회전
    public static void clockwise(int num){  // 톱니바퀴 번호(인덱스)
        int tmp = Gears[num][7];
        for(int i=6; i>=0; i--){
            Gears[num][i+1] = Gears[num][i];
        }
        Gears[num][0] = tmp;
    }

    // 반시계방향 회전
    public static void counterclockwise(int num){
        int tmp = Gears[num][0];
        for(int i=0; i<7; i++){
            Gears[num][i] = Gears[num][i+1];
        }
        Gears[num][7] = tmp;
    }

    // 점수 계산
    public static void calculation(){
        for(int i=0; i<4; i++){
            if(Gears[i][0] == 1){  // S극 -> 2의 i승
                score += Math.pow(2, i);
            }else if(Gears[i][0] == -1){  // N극 - > 0
                score += 0;
            }
        }
    }
}
