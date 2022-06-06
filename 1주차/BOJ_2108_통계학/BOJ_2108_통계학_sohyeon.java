import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// BOJ 2108 통계학
public class BOJ_2108 {
    static int N;
    static int[] array;
    static int sansul=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        array = new int[N];

        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(br.readLine());
            sansul += array[i];
        }

        System.out.println(sansul());
        System.out.println(joongang());
        System.out.println(choebin());
        System.out.println(range());
    }

    // 산술평균
    public static int sansul(){
        return (int)Math.round((double)sansul/N);
    }

    // 중앙값
    public static int joongang(){
        Arrays.sort(array);
        return array[N/2];  // 중앙값
    }

    // 최빈값
    public static int choebin(){
        int cnt[] = new int[8001];
        ArrayList<Integer> choebinValueList = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        // 빈도 수 count
        for(int i=0; i<N; i++){
            if(array[i]<0){
                cnt[Math.abs(array[i]) + 4000]++;
            }else cnt[array[i]]++;
        }

        int index = 0;
        for(int i=0; i<cnt.length; i++){  // 최빈값과 그 빈도수 찾기
            if(cnt[i]>max && cnt[i]!=0){
                max = cnt[i];
                index = i;
            }
        }

        // 최빈값에 해당하는 값들 choebinValueList에 저장
        for(int i=0; i<cnt.length; i++){
            if(cnt[i]==max){
                if(i>4000){  // 음수
                    choebinValueList.add((-1)*(i-4000));
                }else choebinValueList.add(i);  // 양수수
           }
        }
        Collections.sort(choebinValueList);
        if(choebinValueList.size()>1) return choebinValueList.get(1);
        else return choebinValueList.get(0);
    }

    // 범위
    private static int range() {
        Arrays.sort(array);
        return array[N-1] - array[0];
    }
}
