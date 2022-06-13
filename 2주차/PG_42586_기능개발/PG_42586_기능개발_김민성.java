import java.util.*;
class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] prog = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++) {
            int day = 0;
            while(progresses[i] < 100) {
                day++;
                progresses[i] += speeds[i];
            }
            prog[i] = day;
            System.out.println(prog[i]);
        }
        int idx = 0;
        while(idx < prog.length) {
            int cnt = 1;
            for(int i = idx + 1; i < prog.length; i++) {
                if(prog[idx] >= prog[i]){
                    if(i == prog.length - 1) {
                        idx = prog.length;
                    }
                    cnt++;
                }
                else {
                    if(i == prog.length - 1) {
                        idx = prog.length - 1;
                    } else {
                        idx = i;
                    }
                    break;
                }
            }
            answer.add(cnt);
            if(idx == prog.length - 1) {
                answer.add(1);
                break;
            }
        }


        return answer;
    }


}