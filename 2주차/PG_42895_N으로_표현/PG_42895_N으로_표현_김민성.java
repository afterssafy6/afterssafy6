import java.util.*;
class Solution {
    public static int solution(int N, int Number) {
        if(N == Number) {
            return 1;
        }
        HashSet<Integer> dp[] = new HashSet[9];
        dp[1] = new HashSet<>();
        dp[1].add(N);

        for(int i = 2; i <= 8; i++) {
            dp[i] = dpcal(i, dp, N);

            if(dp[i].contains(Number)) {
                return i;
            }
        }


        return -1;
    }

    static HashSet<Integer> dpcal(int nth, HashSet<Integer> [] dp, int N) {
        HashSet<Integer> hs = new HashSet<>();
        String Nstring = "";
        int number = 0;
        int number2 = 0;
        for(int i = 1; i <= nth; i++) {
            Nstring += N;
        }
        hs.add(Integer.parseInt(Nstring));

        for(int i = 1; i < nth; i++) {
            for(int j = 0; j < 4; j++) {
                for(int num : dp[i]) {
                    for(int num2 : dp[nth - i]) {
                        number = calculate(j, num, num2);
                        number2 = calculate(j, num2, num);
                        hs.add(number);
                        hs.add(number2);
                    }
                }
            }
        }
        return hs;
    }
    static int calculate(int pivot, int num1, int num2) {
        int ans = 0;
        if(pivot == 0) {
            ans = num1 + num2;
        }
        if(pivot == 1) {
            ans = num1 - num2;
        }
        if(pivot == 2) {
            ans = num1 * num2;
        }
        if(pivot == 3) {
            if(num2 != 0) {
                ans = num1 / num2;
            }
        }

        return ans;
    }



}