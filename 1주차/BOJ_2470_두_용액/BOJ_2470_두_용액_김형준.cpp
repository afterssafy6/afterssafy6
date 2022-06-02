//
// BOJ - 2470
// 두 용액
//
// 난이도: G5
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;

#define ABS(a) ((a) < 0 ? -(a) : (a))

int A[100000];

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> A[i];
	}

	sort(A, A + N);

	int min_gap = 2e9; // 0과 최소 차이 특성값
	int min_a, min_b; // 왼쪽 요소와 오른쪽 요소
	// 0 ~ N까지 왼쪽 요소 순차 탐색
	for (int i = 0; i < N; i++) {
		int l = i + 1;
		int r = N - 1;
		int m;

		// i + 1(왼쪽 요소 Idx + 1) ~ N까지 오른쪽 요소 이분 탐색
		while (l <= r) {
			m = floor((l + r) / 2.0);

			// 새로운 특성값이 0과 더 가까우면 갱신
			if (ABS(A[i] + A[m]) < ABS(min_gap)) {
				min_a = A[i];
				min_b = A[m];
				min_gap = min_a + min_b;

				// 둘을 합쳐 0이면 이보다 더 좋은 결과는 있을 수 없으므로 종료
				if (min_gap == 0) break;
			// 새로운 특성값이 음수면 왼쪽을 증가
			} else if (A[i] + A[m] < 0) {
				l = m + 1;
			// 새로운 특성값이 양수면 오른쪽을 감소
			} else {
				r = m - 1;
			}
		}
	}

	cout << min_a << " " << min_b << "\n";

	return 0;
}

//
// 코멘트:
//
// STL lower_bound로 풀려고 했지만 실패
// 
// 투포인터로 줄여나가면서 풀거나 한개 고정 후 남은 것 이분 탐색
//
