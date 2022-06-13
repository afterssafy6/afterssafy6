import java.util.*;


class Solution {
    public int solution(int n ,int[][] results) {
        int INF = n * n + 1;
        int answer = 0;
        int check[][] = new int[n+1][n+1];
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                check[i][j] = INF;
            }
            check[i][i] = 0;
        }

        for(int i = 0; i < results.length; i++) {
            check[results[i][0]][results[i][1]] = 1;
        }

        for(int k= 1; k < n+1; k++) {
            for(int i = 1; i < n+1; i++) {
                for(int j = 1; j < n+1; j++) {
                    // 최단 거리 갱신 (k를 경유해서 가는 것이 직선거리보다 빠르다면)
                    if(check[i][j] > check[i][k] + check[k][j]) {
                        check[i][j] = check[i][k] + check[k][j];
                    }
                }
            }
        }
        // 플로이드 탐색
        for(int i = 1; i < n+1; i++ ) {
            boolean flag = false;
            for(int j = 1; j < n+1; j++) {
                if(i != j && check[i][j] != INF && check[j][i] != INF) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                answer++;
            }
        }


        return answer;
    }
}