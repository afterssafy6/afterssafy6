//
// BOJ - 1744
// 수 묶기
//
// 난이도: G4
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	int minus = 0; // 수열 내의 음수 개수
	int zero = 0; // 수열 내의 0 개수
	int A[50] = { 0 }; // 수열을 저장할 배열

	for (int i = 0; i < N; i++) {
		cin >> A[i];
		if (A[i] < 0) minus++; // 입력된 수가 음수이면 음수 카운트가 1만큼 증가
		if (A[i] == 0) zero++; // 입력된 수가 0이면 0 카운트가 1만큼 증가
	}

	sort(A, A + N); // 수열을 오름차순 정렬

	int ans = 0;

	// 수열 시작부터 음수 끝 - 1 까지 묶기
	for (int i = 0; i < minus - 1; i += 2) {
		ans += A[i] * A[i + 1];
	}

	// 음수가 홀수 개이고 0이 없으면 가장 작은 음수를 더한다
	if (minus % 2 != 0 && zero == 0) 
		ans += A[minus - 1];

	// 수열 끝부터 양수 시작 + 1까지 묶기
	for (int i = N - 1; i > minus + zero; i -= 2) {
		// 만약 곱할 수 중 1이 있으면 더하고, 없으면 곱한다
		if (A[i] == 1 || A[i - 1] == 1) {
			ans += A[i] + A[i - 1];
		}
		else {
			ans += A[i] * A[i - 1];
		}
	}

	// 양수가 홀수 개이면 가장 작은 양수를 더한다
	if ((N - minus - zero) % 2 != 0) 
		ans += A[minus + zero];

	cout << ans << "\n";
	
	return 0;
}

//
// 코멘트:
//
// 처음엔 0을 곱해서 음수를 다 없애는 건 줄 알았다
// 
// 치킨을 먹으며 생각해보니 그게 아니었다
// (음수) x (음수) = (양수)
//
