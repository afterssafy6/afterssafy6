package BOJ.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ 1013 Contact
public class BOJ_1013 {
    static int test_case;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        test_case = Integer.parseInt(br.readLine());

        for(int i=0; i<test_case; i++){
            String str = br.readLine();
            String pattern = "(100+1+|01)+";
            System.out.println(str.matches(pattern)? "YES" : "NO");
        }
    }
}
