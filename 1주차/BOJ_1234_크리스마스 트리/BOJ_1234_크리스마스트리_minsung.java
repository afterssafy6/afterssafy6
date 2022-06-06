import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, red, green, blue;
    static long[][][][] RGB = new long[11][101][101][101];
    static long[][] dup = new long[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        red = Integer.parseInt(st.nextToken());
        green = Integer.parseInt(st.nextToken());
        blue = Integer.parseInt(st.nextToken());

        System.out.println(havingNumber(N, red, green, blue));

    }

    public static long havingNumber(int level, int red, int green, int blue) {
        // 색을 사용할 수 없다면 재귀함수 종료 (0보다 작은 함수는 가지치기);
        if (red < 0 || green < 0 || blue < 0) return 0;
        // 레벨에 끝까지 도달했고 (색이 남았거나 모두 사용했거나 올바른 값이 왔다면 1을 Return);
        if (level <= 0) return 1;

        // Memo 된 값이 있다면 메모된 값을 사용한다.
        if (RGB[level][red][green][blue] != 0) return RGB[level][red][green][blue];

        // Level N 에서 1가지만 쓰는 경우
        RGB[level][red][green][blue] += havingNumber(level - 1, red - level, green, blue);
        RGB[level][red][green][blue] += havingNumber(level - 1, red, green - level, blue);
        RGB[level][red][green][blue] += havingNumber(level - 1, red, green, blue - level);

        // Level N 에서 2가지 쓰는 경우
        // 레벨이 짝수일 때만 2가지 쓸 수있다
        // 2가지를 쓸 때  두 색의 수가 같아야 하므로
        if (level % 2 == 0) {

            // 색깔의 비율은 2분의 1이 되어야하고
            int select = level / 2;
            // 총 선택해야할 수, 색깔당 할당 된 수, 색깔종류의 개수(중복 순열의 개수를 구한다)
            long dupCombi = dupCombination(level, select, 2);
            RGB[level][red][green][blue] += dupCombi * havingNumber(level - 1, red - select, green - select, blue);
            RGB[level][red][green][blue] += dupCombi * havingNumber(level - 1, red - select, green, blue - select);
            RGB[level][red][green][blue] += dupCombi * havingNumber(level - 1, red, green - select, blue - select);

        }
        // 3개의 색깔을 사용해야 할 때
        if (level % 3 == 0) {
            int select = level / 3;

            long dupCombi = dupCombination(level, select, 3);
            RGB[level][red][green][blue] += dupCombi * havingNumber(level - 1, red - select, green - select, blue - select);
        }

        return RGB[level][red][green][blue];
    }

    static long dupCombination(int level, int select, int k) {
        //memo
        if(dup[level][select] != 0) return dup[level][select];
        long num = 1L;

        // 전체 순열의 개수
        for(int i = 1; i <= level; i++) {
            num *= i;
        }

        // 중복된 색깔을 나눠준다 (색깔의 종류만큼 중복된 색깔의 개수를 divide)
        for(int i = 0; i < k; i++) {
            for(int j = 1; j <= select; j++) {
                num /= j;
            }
        }
        return dup[level][select] = num;
    }
}

