import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int arr[] = new int[T];
        int [] count = new int[8001];
        // 최대값
        int max = 0;
        ArrayList<Integer> binArray = new ArrayList<>();
        for(int i = 0; i < T; i++) {
            arr[i] = sc.nextInt();
        }
        int sum = IntStream.of(arr).sum();

        // 첫번째 산술 평균
        System.out.println(Math.round((double) sum / T));

        Arrays.sort(arr);
        // 중간값
        System.out.println(arr[arr.length / 2]);

        // 최빈값
        for(int i = 0; i < arr.length; i++) {
            count[4000 + arr[i]]++;
        }
        for(int i = 0; i < count.length; i++) {
            if(count[i] > max) {
                max = count[i];
            }
        }
        for(int i = 0; i < count.length; i++) {
            if(max == count[i]) {
                binArray.add(i - 4000);
            }
        }

        if(binArray.size() == 1) {
            System.out.println(binArray.get(0));
        } else {
            Collections.sort(binArray);
            System.out.println(binArray.get(1));
        }
        // 범위
        System.out.println(arr[arr.length - 1] - arr[0]);

    }


}