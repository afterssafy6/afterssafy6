import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        String match = "[\\[\\[\\]]";
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            String strArr[] = br.readLine().split("");
            int N = Integer.parseInt(br.readLine());

            String [] intArr = br.readLine().replaceAll(match, "").split(",");
            Deque<String> deque = new ArrayDeque<>();
            if(!intArr[0].equals("")) {
                deque.addAll(Arrays.asList(intArr));
            }
            boolean flag = true;
            boolean error = false;
            for(String s: strArr) {
                if(s.equals("D")) {
                    if(deque.size() == 0) {
                        System.out.println("error");
                        error = true;
                        break;
                    }
                    if(flag) {
                        deque.removeFirst();
                    } else {
                        deque.removeLast();
                    }
                }
                if(s.equals("R")) {
                    flag = !flag;
                }
            }

            if(!error) {
                StringBuilder sb = new StringBuilder("[");
                if(flag) {
                    Iterator<String> ev = deque.iterator();
                    while(ev.hasNext()) {
                        sb.append(ev.next());
                        if(ev.hasNext()) {
                            sb.append(",");
                        }
                    }
                } else {
                    Iterator<String> rev = deque.descendingIterator();
                    while(rev.hasNext()) {
                        sb.append(rev.next());
                        if(rev.hasNext()) {
                            sb.append(",");
                        }
                    }
                }
                wr.write(sb.toString().concat("]\n"));
                wr.flush();
            }

        }
        wr.close();

    }
}