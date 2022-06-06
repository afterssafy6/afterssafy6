import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ 2470 두 용액
public class BOJ_2470 {
    static int N;
    static int[] solutions;
    static int solution1, solution2;  // 용액1, 용액2
    static int sum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        solutions = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);  // 오름차순 정렬

        int cmp, left = 0, right = N - 1;
        while (left != right) {
            cmp = solutions[left] + solutions[right]; // -1

            if (Math.abs(cmp) < sum) {
                sum = Math.abs(cmp);
                solution1 = solutions[left];
                solution2 = solutions[right];
            }

            if(cmp<0){  // 음수인 경우 0에 가까워지려면 값이 커져야 한다.
                left++;  // left를 증가시킨다.
            }else if(cmp>0){  // 양수인 경우 0에 가까워지려면 값이 작아져야 한다.
                right--;  // right를 감소시킨다.
            }else{  // 0이 되어버린 경우
                break;
            }

        }
        System.out.println(solution1 + " " + solution2);
    }
}
