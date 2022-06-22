//
// BOJ - 13549
// 숨바꼭질 3
//
// 난이도: G5
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

using P = pair<int, int>;

#define MAX 100000
#define ABS(x) ((x) >= 0 ? (x) : -(x))

struct comp {
	int operator()(P &I, P &C) {
		if (I.first != I.first) return I.first < C.first; // 위치 X는 내림차순 (X값이 클수록 우선)
		return I.second > C.second; // 거리는 오름차순 (cost 값이 작을수록 우선)
	}
};

int main() {
	int N, K;
	cin >> N >> K;

	int dist[MAX + 1];
	for (int i = 0; i <= MAX; i++) {
		dist[i] = ABS(N - i); // 거리 배열을 술래 위치 N으로부터의 거리로 초기화
	}

	priority_queue<P, vector<P>, comp> q;
	q.push({ N, 0 });

	while (!q.empty()) {
		int pos = q.top().first;
		int cost = q.top().second;
		q.pop();

        // 동생 위치에 도착하면 더이상 탐색 X
		if (pos == K) {
			continue;
		}

		// 3가지 경우의 수 (X * 2, X + 1, X - 1) 고려
		for (int i = 0; i < 3; i++) {
			int new_cost, new_pos;
			switch (i) {
			case 0:
				new_cost = cost;
				new_pos = pos * 2;
				break;
			case 1:
				new_cost = cost + 1;
				new_pos = pos + 1;
				break;
			case 2:
				new_cost = cost + 1;
				new_pos = pos - 1;
				break;
			default:
				break;
			}
			if (new_pos == pos) continue; // X == 0인 경우 무한 반복, 새로운 위치와 현재 위치가 같은 경우 탐색 X
			if (new_pos > 2 * K || new_pos > MAX || new_pos < 0) continue; // 동생 위치 K의 두배 또는 100000이 넘으면 더이상 탐색할 필요 X, 음수 위치 값도 탐색 종료
			if (new_cost > dist[new_pos]) continue; // Dijkstra 최단거리 탐색 (최단거리보다 더 큰 cost 발생 시 탐색 종료)
			dist[new_pos] = new_cost; // 최단 거리(cost) 갱신
			q.push({ new_pos, new_cost });
		}
	}

	cout << dist[K] << "\n";

	return 0;
}

//
// 코멘트:
// 
// 탐색 종료 조건을 생각하는게 중요하다.
// 
// 특히 L67의 X == 0인 경우 무한 반복되는 케이스와 2 * K가 100000이 넘을 수 있다는 것을 고려하면 된다.
//
// 난이도는 중간 정도
//
