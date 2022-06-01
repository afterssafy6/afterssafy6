import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 정규 표현식에서 +는 문자가 하나 이상 여러개 있는 패턴
        // 정규 표현식에서 |는 or 패턴
        // 하나의 문자로 인식하게 만들어주는 ()
        String vega = "(100+1+|01)+";
        int T = sc.nextInt();

        for(int i = 0; i < T; i++) {
            String spread = sc.next();
            // 전체 문자가 패턴을 충족한다면 YES
            if(spread.matches(vega)) System.out.println("YES");
            else System.out.println("NO");
        }

    }
}
