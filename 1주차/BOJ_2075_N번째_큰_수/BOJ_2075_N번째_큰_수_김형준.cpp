//
// BOJ - 2075
// N번째 큰 수
//
// 난이도: S1
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	// 최소 힙 우선순위 큐 사용
	priority_queue<int, vector<int>, greater<>> pq;

	int t = 0;
	for (int y = 0; y < N; y++) {
		// 각 줄마다의 N개의 요소가 추가된다.
		for (int x = 0; x < N; x++) {
			cin >> t;
			pq.push(t);
		}

		// 첫 줄만 0개 ~ N개이고 다음 줄 부터는 N개 ~ 2N개로 힙 사이즈 유지
		if (y) {
			// 매 줄마다 힙의 크기가 N이 넘지 않도록
			// N개 씩 pop 하여 메모리 제한 통과
			for (int i = 0; i < N; i++) {
				pq.pop();
			}
		}
	}

	// 최소 힙 이므로 현재 top을 취하면 N번째 큰 수
	cout << pq.top() << "\n";

	return 0;
}

//
// 코멘트:
//
// 뭔가 이렇게 푸는 문제는 아닌 것 같지만
// 
// 이게 훨씬 간단한 방법이라 우선순위 큐를 사용했다
//
