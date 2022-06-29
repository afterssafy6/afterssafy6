import java.util.*;
import java.util.stream.IntStream;

class Solution {
    static int [] answer;
    static int maxNum = 0;
    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
        int wrong[] = {-1};
        boolean []visited = new boolean[11];
        int[] score = new int[n];
        // 11개의 과녁에 쏴졌느냐 쏴지지 않았느냐
        subSet(0, 11, n ,visited, info);

        if(IntStream.of(answer).sum() == 0) {
            return wrong;
        }

        return answer;
    }
    public static void subSet(int num, int rst, int end, boolean visited[], int[] info) {
        if(num == rst) {
            int arr[] = new int[11];
            int total = 0;
            int arrow = end;
            int lion = 0;
            int peach = 0;
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) {
                    // 쏘아졌는데 남은 화살보다 어피치보다 작다면
                    if(arrow < info[i] + 1) {
                        // 남은화살 소진
                        arr[i] = arrow;
                        // 쏘아진 화살을 합한다
                        total += arrow;
                        // 화살은 없다
                        arrow = 0;
                        // 어피치 점수 더하기
                        peach += 10-i;
                    } else {
                        // 쏘아졌는데 남은화살이 어피치를 이길 화살 수면
                        arr[i] = info[i] + 1;
                        // 화살 빼주기
                        arrow -= info[i] + 1;
                        // 쏘아진 화살의 합
                        total += info[i] + 1;
                        // 라이언 점수 더하기
                        lion += 10 - i;
                    }
                } else {
                    // 쏘아지지않은 과녘
                    arr[i] = 0;
                    // 어피치가 하나라도 쐈다면
                    if(info[i] > 0) {
                        // 어피치 점수 합
                        peach += 10 - i;
                    }
                }

            }

            // 부분집합 중에 사용한 화살의 수가 정해진 화살 수와 같고  피치를 이겼다면
            if(total == end && peach < lion) {
                // 피치와 라이언의 점수 차이가 max값과 같을때
                if(maxNum == lion - peach) {
                    maxNum = lion - peach;
                    boolean flag = false;
                    // 낮은 점수를 더 많이 쏜 과녘 값으로 교체 로직
                    for(int i = arr.length - 1; i >= 0; i--) {
                        if(arr[i] > answer[i]) {
                            flag = true;
                            break;
                        } else if(arr[i] < answer[i]) {
                            break;
                        }
                    }
                    // flag를 건드렸다면 낮은 점수를 더많이 쏜 과녘으로 갱신
                    if(IntStream.of(answer).sum() == 0 || flag) {
                        answer = arr;
                    }
                    // 피치와 라이언의 점수 차이가 max값 보다 클 때
                } else if(maxNum < lion - peach) {
                    maxNum = lion - peach;
                    // 과녁을 갱신
                    answer = arr;
                }
            }
            return;
        }

        visited[num] = true;
        DFS(num + 1, rst, end, visited, info);

        visited[num] = false;
        DFS(num + 1, rst, end,  visited, info);
    }
}