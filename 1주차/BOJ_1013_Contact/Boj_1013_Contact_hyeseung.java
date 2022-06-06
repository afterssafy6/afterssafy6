package boj.정규표현식;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1013 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String pattern = "((100+1+)|(01))+";

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            bw.write(str.matches(pattern) ? "YES\n":"NO\n");
        }
        bw.flush();
    }
}
