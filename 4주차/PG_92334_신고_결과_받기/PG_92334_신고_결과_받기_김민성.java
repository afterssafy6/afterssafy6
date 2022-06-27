import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        String [] array = new String[id_list.length];
        int [] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> hm = new HashMap<>();
        // 신고기록
        HashMap<String, Integer> rep = new HashMap<>();


        for(String s : id_list) {
            hm.put(s, new HashSet<String>());
            rep.put(s, 0);
        }

        for(String s : report) {
            String attack[] = s.split(" ");
            hm.get(attack[0]).add(attack[1]);
        }

        for(Map.Entry<String, HashSet<String>> entry : hm.entrySet()) {
            //실제 리폿 횟수
            for(String s : entry.getValue()) {
                int cnt = rep.get(s);
                rep.put(s, cnt + 1);
            }
        }
        // 신고 목록

        for(int i = 0; i < id_list.length; i++) {
            for(Map.Entry<String, HashSet<String>> mp :hm.entrySet()) {
                // 신고자 -> 신고받은 아이디들
                if(mp.getKey().equals(id_list[i])) {
                    for(String str :mp.getValue()){
                        int cnt = rep.get(str);
                        if(cnt >= k) {
                            answer[i]++;
                        }
                    }
                }

            }
        }


        return answer;
    }
    static class Node implements Comparable<Node> {
        int x;
        int y;

        @Override
        public int compareTo(Node o) {
            if(o.x == this.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}