import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    // 수열의 합을 최대로
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int rst = 0;
        ArrayList<Integer> minusZero = new ArrayList<Integer>(); // 타입 지정
        ArrayList<Integer> plus = new ArrayList<Integer>();
        for(int i = 0; i < T; i++) {
            int tmp = sc.nextInt();
            // 0 보다 작을 때
            if(tmp <= 0) {
                minusZero.add(tmp);
                // 1일 땐 곱하는게 손해
            } else if (tmp == 1) {
                rst += 1;
                // 1보다 클 때
            } else {
                plus.add(tmp);
            }
        }
        // 얼마나 값이 들어올지 몰라서 ArrayList사용
        minusZero.sort(Comparator.naturalOrder());
        plus.sort(Comparator.reverseOrder());

        for(int i = 0; i < minusZero.size(); i += 2) {
            // 홀수개여서 마지막에 남을 때는 어쩔 수 없이 더해준다.
            if(i == minusZero.size() - 1) rst += minusZero.get(i);
            // 아니면 두개의 값을 곱해주는것이 이득
            else rst += minusZero.get(i) * minusZero.get(i+1);
        }
        for(int i = 0; i < plus.size(); i+= 2) {
            if(i == plus.size() - 1) rst += plus.get(i);
            else rst += plus.get(i) * plus.get(i+1);
        }


        System.out.println(rst);
    }
}

