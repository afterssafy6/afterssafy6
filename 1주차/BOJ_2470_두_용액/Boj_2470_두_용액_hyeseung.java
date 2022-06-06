package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2470 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long[] values = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);

        int left = 0;
        int right = N-1;

        long minV = 2000000001;
        long ans1 = values[left];
        long ans2 = values[right];

        while(left<right) {
            long sum = values[left]+values[right];
            long absSum = Math.abs(sum);

            if(absSum<minV) {
                minV = absSum;
                ans1 = values[left];
                ans2 = values[right];
            }

            if(sum>0) {
                right--;
            }
            else left++;
        }

        System.out.println(ans1+" "+ans2);
    }
}
