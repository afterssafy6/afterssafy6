class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder prime = new StringBuilder("");
        // 소수를 구해준다
        while(n / k != 0) {
            prime.append(n % k);
            n = n / k;
            if(n < k) {
                prime.append(n);
            }
        }
        prime = prime.reverse();
        boolean flag = false;
        int idx = 0;
        String tmp = prime.toString();

        // 규칙에 해당하는 소수인지 확인한다
        for(int i = 0; i < prime.length(); i++) {
            if(prime.charAt(i) == '0' && prime.charAt(i - 1) != '0') {
                String inner = tmp.substring(idx, i);
                if(isPrime(Long.parseLong(inner))) {
                    answer++;
                }
                idx = i+1;
            }
            if(prime.charAt(i) == '0') {
                flag = true;
            }
        }
        if(idx != prime.length()) {
            String outer = tmp.substring(idx);
            if(isPrime(Long.parseLong(outer))) {
                answer++;
            }
        }


        if(!flag) {
            // 0이없는 경우
            if(isPrime(Long.parseLong(tmp))) {
                return 1;
            }
            return 0;
        }

        return answer;
    }

    public static boolean isPrime(Long N){

        if( N == 1 ) return false;
        else if( N == 2 ) return true;
        else if( N % 2 == 0 ) return false;

        int count = 0;
        for(int i = 3; i <= Math.sqrt(N); i += 2){
            if( N % i == 0 ){
                count++;
                // break를 걸거나, return false로 빠져나와도 된다.
            }
        }
        return count == 0;
    }
}