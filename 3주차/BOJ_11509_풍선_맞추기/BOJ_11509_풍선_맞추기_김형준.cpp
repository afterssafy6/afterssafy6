//
// BOJ - 11509
// 풍선 맞추기
//
// 난이도: G5
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
using namespace std;

int arrow[1000000]; // 그 높이에 화살이 떠있는지 확인

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	vector<int> BL(N);
	for (int i = 0; i < N; i++) {
		cin >> BL[i];
	}

	int ans = 0; // 사용한 화살 개수

	for (int i = 0; i < N; i++) { // 왼쪽부터 탐색
		int bl_ht = BL[i]; // 풍선의 높이
		if (arrow[bl_ht] == 0) { // 풍선의 높이에 해당하는 화살이 없으면
			arrow[bl_ht - 1]++; // 풍선의 높이 - 1에 화살을 한발 추가한다
			ans++; // 한발 쐈으므로 ans에 1 더해준다
		}
		else // 풍선 높이에 해당하는 화살이 있으면
		{
			arrow[bl_ht]--; // 해당 높이의 화살 한개 줄여주고
			arrow[bl_ht - 1]++; // 풍선 높이 - 1에 화살을 한발 추가한다
		}
	}

	cout << ans << "\n"; // 사용한 화살의 개수를 출력

	return 0;
}

//
// 코멘트:
// 
// 순수 O(N) 그리디로 풀어야 하는 문제
//
// 습관적으로 조건만 보고 NlogN 이분탐색같은걸 썼다간 N*N*logN에 허우적대게 된다
//
// 좀만 고민하면 쉽다
//
