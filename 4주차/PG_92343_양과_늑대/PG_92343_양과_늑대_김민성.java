import java.util.*;
import java.util.stream.*;
class Solution {
    static int maxSheep = 0;
    static ArrayList<Integer>[] list;
    public int solution(int[] info, int[][] edges) {
        // 모든 노드를 탐색한다
        // 늑대 뒤에 양이 있을 수 있기 때문에
        list = new ArrayList[info.length];
        boolean []visited = new boolean[info.length];


        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            //  그래프 갱신
            if(list[from] == null) list[from] = new ArrayList<>();
            list[from].add(to);
        }
        // 갈 수 있는 노드의 list
        ArrayList<Integer> available = new ArrayList<>();
        available.add(0);
        // 양의 수, 늑대 수, 현재 위치
        DFS(0, 0, 0, available, info);


        return maxSheep;
    }
    static void DFS(int sheepNum, int wolfNum, int current, ArrayList<Integer> avail, int[] info) {
        // 현재 노드 위치에서 양의 수와 늑대의 수 갱신
        sheepNum += info[current] ^ 1; // 0 xor 1 = 1  , 1 xor 1 = 0;
        wolfNum += info[current];

        // 현재 시점에서 최대 양의 수보다 크면 갱신
        maxSheep = Math.max(sheepNum, maxSheep);

        // 현재 시점에서 양의 수가 늑대 수와 같거나 작으면 그 분기는 사망
        if(sheepNum <= wolfNum) return;

        // 현재 시점에서 갈 수 있는 리스트 갱신
        ArrayList<Integer> copyList = new ArrayList<>();
        copyList.addAll(avail);
        if(list[current] != null) copyList.addAll(list[current]);

        // 자기 자신은 제거
        copyList.remove(Integer.valueOf(current));

        // 현재 시점에서 갈 수 있는 리스트를 통해 다시 DFS
        for(Integer next : copyList) {

            DFS(sheepNum, wolfNum, next, copyList, info);
        }

    }

}