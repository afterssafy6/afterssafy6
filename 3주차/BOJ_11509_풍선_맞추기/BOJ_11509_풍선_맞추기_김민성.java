import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        // 풍선 배열
        int check[] = new int[1000001];
        String[] balloons = br.readLine().split(" ");
        // 처음 풍선을 터뜨린 것을 체크
        check[Integer.parseInt(balloons[0]) - 1]++;
        rst++;
        for(int i = 1; i < N; i++) {
            // 해당 높이에 바늘이 존재한다면
            if(check[Integer.parseInt(balloons[i])] >= 1) {
                // 해당 높이의 바늘 제거
                check[Integer.parseInt(balloons[i])]--;
                // 0보다 큰 높이에 존재한다면
                if(Integer.parseInt(balloons[i]) - 1 > 0) {
                    // 다음 높이의 바늘 추가
                    check[Integer.parseInt(balloons[i]) - 1]++;
                }

            } else {
                // 바늘이 존재하지않으면
                check[Integer.parseInt(balloons[i]) - 1]++;
                rst++;
            }
        }
        System.out.println(rst);
    }

}