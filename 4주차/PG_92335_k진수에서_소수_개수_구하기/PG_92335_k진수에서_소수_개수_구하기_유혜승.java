package programmers.kakao2022;

public class P2 {
    // k진수에서 소수 개수 구하기
    // https://programmers.co.kr/learn/courses/30/lessons/92335?language=java

    public static void main(String[] args) {
        int n = 437674;
        int k = 3;

        System.out.println(solution(n,k));
    }

    public static int solution(int n, int k) {
        int result = 0;

        // n을 k진수로 바꾸기
        String kn = Integer.toString(n,k);

        String[] parsekn = kn.split("0");

        for(int i=0; i<parsekn.length; i++) {
            if(parsekn[i].equals("")) continue;

            long ele = Long.parseLong(parsekn[i]);
            if(isPrime(ele)) result++;
        }
        return result;
    }

    // v가 소수인지 확인하는 함수
    public static boolean isPrime(long v) {
        if(v==1) return false;
        for(long i=2; i<=Math.sqrt(v); i++) {
            if(v%i == 0) return false;
        }
        return true;
    }
}
