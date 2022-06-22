import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static long rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        // 주사위 6개의 수를 담을 배열
        int arr[] = new int[6];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 가장 큰 수
        int maxFirst = 0;

        // 작은 수 3가지 종류
        // 1. 한 면일 때 가장 작은 합
        // 2. 두 면일 때 가장 작은 합
        // 3. 세 면일 때 가장 작은 합
        long minFirst = Integer.MAX_VALUE;
        long minSecond = Integer.MAX_VALUE;
        long minThird = Integer.MAX_VALUE;
        for(int i = 0; i < 6; i++) {
            if(minFirst > arr[i]) {
                minFirst = arr[i];
            }
            if(maxFirst < arr[i]) {
                maxFirst = arr[i];
            }
            for(int j = 0; j < 6; j++) {
                // 두 수의 합이 5면 함께 할 수 없는 사이
                if(i == j || i + j == 5) {
                    continue;
                }
                if(minSecond > arr[i] + arr[j]) {
                    minSecond = arr[i] + arr[j];
                }
                for(int k = 0; k < 6; k++) {
                    // 마찬 가지
                    if (i == k || j == k || i + k == 5 || j + k == 5) {
                        continue;
                    }
                    if (minThird > arr[i] + arr[j] + arr[k]) {
                        minThird = arr[i] + arr[j] + arr[k];
                    }
                }
            }
        }
        // 한 층에 존재 할 수 있는 개수
        long oneCnt = (N - 2) * 4l;
        // 모서리
        long edgeCnt = 4l;
        // 지붕 카운트
        long roofCnt = (long) Math.pow(N - 2, 2);

        if(N == 1) {
            System.out.println(IntStream.of(arr).sum() - maxFirst);
        } else {
            rst += (N-1) * (edgeCnt * minSecond + oneCnt * minFirst);
            rst += edgeCnt * minThird + minSecond * oneCnt + minFirst * roofCnt;
            System.out.println(rst);
        }

    }

}