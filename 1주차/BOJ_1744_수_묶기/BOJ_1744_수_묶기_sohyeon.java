import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ 1744 수 묶기
public class BOJ_1744 {
    static int N;
    static int[] positive, negative;
    static int sum=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        positive = new int[1001];
        negative = new int[1001];
        Arrays.fill(negative, -1001);

        int positivecnt=0;
        for(int i=0; i<N; i++){
            int value = Integer.parseInt(br.readLine());
            if(value > 0) {
                positive[i] = value;
                positivecnt++;
            }
            else negative[i] = value;
        }

        Arrays.sort(positive);
        Arrays.sort(negative);

        // 양수
        for(int i=1000; i>0; i--){
            if(positive[i]>0) {
                if(positive[i] * positive[i-1] != 0 && positive[i]*positive[i-1]>positive[i]+positive[i-1]) {
                    sum += positive[i] * positive[i - 1];
                    i--;
                }else{
                    sum += positive[i];
                }
            }else break;
        }
        
        // 음수
        for(int i=1001-(N-positivecnt); i<1001-1; i++){
            if(negative[i]!=-1001){
                sum += negative[i] * negative[i+1];
                negative[i]=-1001; negative[i+1]=-1001;
                i++;
            }
        }
        if(negative[1000]!=-1001) sum += negative[1000];

        System.out.println(sum);
    }
}
/*
   51line 추가한 이유 : 반례 발생

   5
   -537
   81
   -435
   257
   157
   
   0이 없고 음수만 두개 있을 때 0에 가장 가까운 수 중복 더함 발생, 55 line
 */
