import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// BOJ 5430. AC
public class BOJ_5430_재시도 {
    static int T;  // 테이스 케이스 개수
    static String p;  // 수행할 함수
    static int n;  // 배열 원소 개수

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
            for (int j = 0; j < n; j++) {
                deque.add(Integer.valueOf(st.nextToken()));
            }
            AC(deque);
        }
    }

    public static void AC(Deque<Integer> deque){
        boolean reverse = false;

        for(int i=0; i<p.length(); i++){
            if(p.charAt(i)=='R') reverse = !reverse;  // R 인 경우
            else{  // D인 경우
                if(deque.size() == 0) {
                    System.out.println("error");
                    return;
                }
                if(reverse) deque.removeLast();  // false인 경우 뒤 지우기
                else deque.removeFirst();  // true인 겨우 앞 지우기
            }
        }

        System.out.println(print(deque, reverse));
    }

    public static String print(Deque<Integer> deque, boolean reverse){
        StringBuilder sb = new StringBuilder("[");
        while(!deque.isEmpty()){
            sb.append(reverse? deque.removeLast() : deque.removeFirst());  // 제거 후 해당 element 리턴
            if(deque.size() != 0) sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }
}
