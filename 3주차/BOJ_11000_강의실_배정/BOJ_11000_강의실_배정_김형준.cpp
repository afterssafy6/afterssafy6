//
// BOJ - 11000
// 강의실 배정
//
// 난이도: G5
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <queue>
using namespace std;
using P = pair<int, int>;

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	int room_count = 0; // 방 개수 (정답)
	priority_queue<P, vector<P>, greater<>> q, rooms; // 강의와 방을 저장할 우선순위 큐
	for (int i = 0; i < N; i++) {
		P ST;
		cin >> ST.first >> ST.second;
		q.push(ST);
	}

	rooms.push({ -1, ++room_count }); // 맨 처음 사용한 방을 추가, 기존 강의 종료시간과 방번호를 오름차순으로 정렬 (사실 방번호는 정렬 필요 X)
	while (!q.empty()) {
		int S = q.top().first; // 강의 시작시간
		int T = q.top().second; // 강의 종료시간
		q.pop();

		int room_available = rooms.top().first; // 방이 사용 가능해지는 시간
		int room_idx = rooms.top().second; // 방 번호

		if (room_available <= S) { // 강의 시작시간이 방을 사용가능한 시간보다 크거나 같으면
			rooms.pop();
			rooms.push({ T, room_idx }); // 그 방을 사용하고 강의 종료시간 갱신 후 push
		}
		else {
			rooms.push({ T, ++room_count }); // 제일 빠른 방을 사용 불가능하다면 더이상 탐색 할 필요 없이 방 추가
		}
	}

	cout << room_count << "\n"; // 사용한 방 총 개수 출력

	return 0;
}

//
// 코멘트:
// 
// 우선순위 큐를 잘 활용할 줄 안다면 난이도는 쉽다.
//
// 사실 방번호도 굳이 큐에 넣을 필요없긴한데 그냥 냅뒀다
//
