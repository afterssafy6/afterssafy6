package boj.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Boj_2108 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        int[] cnt = new int[8001];

        int maxCnt = 0;

        double sum = 0;

        for(int n=0; n<N; n++) {
            int el = Integer.parseInt(br.readLine());
            list.add(el);
            sum+=el;
            cnt[el+4000]++;
            if(maxCnt<cnt[el+4000]) {
                maxCnt = cnt[el+4000];
            }
        }

        LinkedList<Integer> sortList = new LinkedList<>();
        for(int i=0; i<8001; i++) {
            if(cnt[i]==maxCnt) {
                sortList.add(i-4000);
            }
        }

        Collections.sort(sortList);
        Collections.sort(list);

        // 산술평균
        System.out.println(Math.round(sum/N));
        // 중앙값
        System.out.println(list.get(N/2));
        // 최빈값
        System.out.println(sortList.size()>1 ? sortList.get(1) : sortList.get(0));
        // 범위
        System.out.println(list.get(N-1)-list.get(0));
    }
}
