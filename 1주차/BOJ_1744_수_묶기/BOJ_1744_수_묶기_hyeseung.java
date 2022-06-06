package boj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Boj_1744 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> plusList = new ArrayList<>(); // 1 ~ 1000
        ArrayList<Integer> minusList = new ArrayList<>(); // -1000 ~ 0

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num<=0) minusList.add(num);
            else plusList.add(num);
        }

        Collections.sort(plusList, Comparator.reverseOrder());
        Collections.sort(minusList);

        long sum = getSum(plusList) + getSum(minusList);

        System.out.println(sum);
    }
    public static long getSum (ArrayList<Integer> list) {
        long sum = 0;
        int i = 0;
        while(true) {
            if(i==list.size()) {
                break;
            }
            else if(i == list.size()-1) {
                sum += list.get(i);
                break;
            }

            int temp = list.get(i) * list.get(i+1);

            if(list.get(i)+list.get(i+1) > temp) {
                sum += list.get(i)+list.get(i+1);
            }
            else {
                sum += temp;
            }
            i+=2;
        }
        return sum;
    }
}
