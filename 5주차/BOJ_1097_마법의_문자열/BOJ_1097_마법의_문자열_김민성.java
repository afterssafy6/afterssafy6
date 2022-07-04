import java.io.*;
import java.util.*;


public class Main {
    static int K;
    static int N;
    static String strArr[];
    static String select[];
    static boolean bool[];
    static int rst = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        strArr = new String[N];
        select = new String[N];
        bool = new boolean[N];
        for(int i = 0; i < N+1; i++) {
            if(i != N) {
                strArr[i] = br.readLine();
            } else {
                K = Integer.parseInt(br.readLine());

            }
        }
        DFS(0);

        System.out.println(rst);

    }
    static void DFS(int num) {
        if(num == N) {
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < select.length; i++) {
                sb.append(select[i]);
            }
            if(isMagic(sb.toString())) {
                rst++;
            }
        }

        for(int i = 0; i < N; i++) {
            if(bool[i]) {
                continue;
            }
            bool[i] = true;
            select[num] = strArr[i];
            DFS(num + 1);
            bool[i] = false;
        }
    }
    static boolean isMagic(String str) {
        int rst = 0;
        // T(i)가 T가 되는 i의 개수가 K개

        int cnt = 0;
        while(cnt < str.length()) {
            String main;
            String sub;
            StringBuilder plus = new StringBuilder("");
            main = str.substring(cnt);
            sub = str.substring(0, cnt);

            if(plus.append(main).append(sub).toString().equals(str)) {
                rst++;
            }
            cnt++;
        }

        if(rst == K) {
            return true;
        }
        return false;
    }

}

