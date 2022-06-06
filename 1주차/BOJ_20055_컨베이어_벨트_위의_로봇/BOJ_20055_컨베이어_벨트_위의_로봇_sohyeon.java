import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 20055 컨베이어 벨트 위의 로봇
public class BOJ_20055 {
    static int N,K;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2*N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(conveyor_belt(0));

    }

    public static int conveyor_belt(int cnt){
        while(isOK()){
            // 벨트 한 칸씩 회전
            int temp = belt[belt.length-1];
            for(int i=belt.length-1; i>0; i--){
                belt[i] = belt[i-1];
            }
            belt[0] = temp;

            // 로봇도 벨트와 같이 회전
            for(int i=robot.length-1; i>0; i--){
                robot[i] = robot[i-1];
            }
            robot[N-1] = false;  // 내리는 위치에서 로봇을 내린다.
            robot[0] = false;  // => 올리는 위치는 로봇이 존재하지 않게 된다.

            // 로봇 이동가능하면 이동
            for(int i=N-1; i>0; i--){
                // 로봇 존재 && 이동할 칸에 로봇 X && 내구도 1이상
                if(robot[i-1] && !robot[i] && belt[i]>=1){
                     robot[i] = true;
                     robot[i-1] = false;
                     belt[i]--;  // 로봇이 이동할 칸의 내구도 감소
                }
            }

            // 올라가는 위치에 로봇 올리기
            if (belt[0] != 0) {
                robot[0] = true;
                belt[0]--;
            }

            cnt++;
        }

       return cnt;
    }

    public static boolean isOK(){
        int cnt=0;  // 내구도 0인 칸 개수

        for(int i=0; i<belt.length; i++){
            if(belt[i]==0) cnt++;
            if(cnt >= K) return false;
        }
        return true;
    }


}
