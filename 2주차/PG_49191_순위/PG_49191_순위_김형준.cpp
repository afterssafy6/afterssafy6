//
// PG - 49191
// 순위
//
// 난이도: L3
// 제출자: wasuphj @Github
//
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#define MIN(a,b) (a < b ? a : b)

using namespace std;

int graph[101][101];

int solution(int n, vector<vector<int>> results) {
    int answer = 0;

    // 매치 그래프 초기화
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            graph[i][j] = 0;

    // 매치 결과 등록
    for (vector<int> result : results)
    {
        int winner = result[0];
        int loser = result[1];
        graph[winner][loser] = 1;
    }

    // 간접적으로라도 매칭되는 경우 1로 등록
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            for (int k = 1; k <= n; k++)
                graph[j][k] = (graph[j][i] && graph[i][k]) ? 1 : graph[j][k];

    // 다른 모든 유저들과의 매칭 결과가 있으면 +1, 한명이라도 빠지면 0
    for (int i = 1; i <= n; i++) {
        int count = 0;
        for (int j = 1; j <= n; j++) {
            if (graph[i][j] || graph[j][i]) count++;
        }
        if (count == n - 1) answer++;
    }

    return answer;
}

int main() {
    cout << solution(5, { {4,3}, {4,2}, {3,2}, {1,2}, {2,5} }) << "\n";

    return 0;
}

//
// 코멘트:
// 
// 플루이드 와샬을 잘못 써서 마무리에서 많이 헤맸다
// 
// 블로그 참고 하였음
//
