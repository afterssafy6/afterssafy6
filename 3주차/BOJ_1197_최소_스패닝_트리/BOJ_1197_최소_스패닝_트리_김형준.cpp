//
// BOJ - 1197
// 최소 스패닝 트리
//
// 난이도: G4
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>
using namespace std;
using T = tuple<int, int, int>;

int boss[10001]; // 정점 개수만큼 boss 배열을 생성

int findBoss(int x) {
	return (boss[x] == x) ? x : boss[x] = findBoss(boss[x]);
}

int setUnion(int a, int b) {
	a = findBoss(a);
	b = findBoss(b);

	if (a == b) return 0;

	int bigBoss = a > b ? a : b;
	int smallBoss = a < b ? a : b;

	boss[bigBoss] = smallBoss;

	return 1;
}

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int V, E;
	cin >> V >> E;

    // Union-find를 위해 각자 자기 자신을 boss로 설정
	for (int i = 1; i <= V; i++) {
		boss[i] = i;
	}

	vector<T> graph;
	for (int i = 0, a, b, t; i < E; i++) {
		cin >> a >> b >> t;
		graph.push_back({ t, a, b });
	}

	sort(graph.begin(), graph.end()); // 코스트, 시작점, 끝점 오름차순 정렬

	int ans = 0; // 코스트 저장용 변수 (정답)
	for (int i = 0, a, b, t; i < E; i++) {
		tie(t, a, b) = graph[i];
		if (setUnion(a, b)) ans += t; // 합쳐지면 더하고 아니면 말고
	}

	cout << ans << "\n";

	return 0;
}

//
// 코멘트:
// 
// MST 하면 바로 크루스칼 프림 나와야 되는데 이름만 나오고 알고리즘이 안나온다
//
// 블로그 참조하였고 (코드를 베끼진 않음) 크루스칼로 구현
//
