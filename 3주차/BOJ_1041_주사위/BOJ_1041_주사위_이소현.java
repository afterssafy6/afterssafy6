package BOJ.gold;

import java.io.*;
import java.util.StringTokenizer;

// BOJ 1041. 주사위
public class BOJ_1041 {
    private static long N;
    private static long one, two, three;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<6; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // 마주보는 면 중 최소값 찾기
        long minAF = Math.min(dice[0], dice[5]);
        long minBE = Math.min(dice[1], dice[4]);
        long minCD = Math.min(dice[2], dice[3]);

        // 1,2,3면 구성
        one = Math.min(minAF, Math.min(minBE, minCD));
        two = Math.min(minAF + minBE, Math.min(minAF + minCD, minBE + minCD));
        three = minAF + minBE + minCD;

        long sum = minSum(dice);

        System.out.println(sum);
    }
    private static long minSum(int[] dice) {
        long sum = 0;
        if (N == 1) {
            long max = Integer.MIN_VALUE;
            for (int i = 0; i < 6; i++) {
                max = Math.max(max,dice[i]);
                sum += dice[i];
            }
            return sum-max;
        }

        // 3개의 면이 보이는 주사위는 항상 4개
        // 2개의 면이 보이는 주사위는 4(N-1) + 4(N-2)
        // 1개의 면이 보이는 주사위는 4(N-1)(N-2) + (N-2)(N-2)
        // int * int값을 long에 저장하면 int값을 가지기 때문에 오버플로우 발생
        long oneSide = 4 * (N - 1) * (N - 2) + (N - 2) * (N - 2);
        long secondSide = 4 * (N - 1) + 4 * (N - 2);
        long thirdSide = 4;

        sum += one * oneSide + two * secondSide + three * thirdSide;
        return sum;
    }
}
// N은 1000000보다 작거나 같은 자연수
// int -2147483648 ~ 2147483647
// long -9223372036854775808 ~ 9223372036854775807
