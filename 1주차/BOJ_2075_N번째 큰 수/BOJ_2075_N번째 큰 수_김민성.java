import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        ArrayList arr = new ArrayList();
        for(int i = 0; i < T; i++) {
            String str[] = br.readLine().split(" ");
            for(int j = 0; j < str.length; j++) {
                arr.add(Integer.parseInt(str[j]));
            }
        }
        Collections.sort(arr, Collections.reverseOrder());

        System.out.println(arr.get(T - 1));
    }


}
