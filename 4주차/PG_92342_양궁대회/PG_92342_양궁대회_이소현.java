package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Programmers_92342 {
    static int n;
    static int[] info;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        info = new int[11];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<11; i++){
            info[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[11];
        result = solution(n, info);
        for(int i=0; i<11; i++){
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public static int[] solution(int n, int[] info){
        int[] answer = new int[11];
        int[] tmp = new int[11];
        int maxDiff = 0;  // 최대차이

        for(int subset = 1; subset < (1 << 10); subset++){
            int ryan = 0, apeach = 0, cnt = 0;  // 점수, 라이언이 몇개의 화살을 사용했는지
            for(int i=0; i<10; i++){
                if((subset & (1 << i)) != 0){  // i번째 bit를 1로 켜기
                    ryan += 10 -i;  // 라이언이 가져가는 점수
                    tmp[i] = info[i] + 1;
                    cnt += tmp[i];
                }else {  // 비기거나 어피치가 이기는 경우
                    tmp[i] = 0;
                    if(info[i] > 0){  // apeach가 점수 가져감
                        apeach += 10 - i;
                    }
                }
            }

            if(cnt > n) continue;

            tmp[10] = n - cnt;  // 전체 화살 - 현재 사용 화살 개수

            if(ryan - apeach == maxDiff){
                for(int i= 10; i >= 0; i--){  // 0점부터 확인
                    if(tmp[i] > answer[i]){
                        maxDiff = ryan - apeach;
                        answer = Arrays.copyOf(tmp, tmp.length);
                        break;
                    }else if(tmp[i] < answer[i]){
                        break;
                    }
                }
            }else if(ryan - apeach > maxDiff){
                maxDiff = ryan - apeach;
                answer = Arrays.copyOf(tmp, tmp.length);
            }
        }

        if(maxDiff == 0){
            return new int[]{-1};
        }

        return answer;
    }
}
