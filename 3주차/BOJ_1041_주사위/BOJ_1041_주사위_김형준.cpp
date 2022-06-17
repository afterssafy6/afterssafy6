//
// BOJ - 1041
// 주사위
//
// 난이도: G5
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int dice[6];
int used[6];

int dfs(int lev, int sum) {
	if (lev == 0) {
		return sum;
	}

	int ans = 151; // 50 + 49 + 48이 최대이나 귀찮아서 그냥 50 * 3 + 1
	for (int i = 0; i < 6; i++) {
		if (used[i]) continue;
		used[i] = 1;
		used[5 - i] = 1;
		int buf = dfs(lev - 1, sum + dice[i]);
		ans = ans > buf ? buf : ans;
		used[i] = 0;
		used[5 - i] = 0;
	}

	return ans;
}

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	long long N; // N 자체는 int 범위 내, N * N 계산용으로 자료형 변경
	cin >> N;

	if (N == 1) { // N이 1일 때만 점화식이 다름
		priority_queue<int, vector<int>, less<>> q;

		for (int i = 0; i < 6; i++) {
			cin >> dice[i];
			q.push(dice[i]);
		}

		q.pop();

		int ans = 0;
		while (!q.empty()) {
			ans += q.top();
			q.pop();
		}

		cout << ans << "\n"; // 주사위 모든 눈금의 합 - 최대 눈금(최대 눈금을 바닥에 깐다)
	}
	else {
		for (int i = 0; i < 6; i++) {
			cin >> dice[i];
		}

		long long one = dfs(1, 0); // 1개 면을 골랐을 때의 최소 눈금, 마찬가지로 정답 계산용으로 자료형 변경
		long long two = dfs(2, 0); // 2개 면을 골랐을 때의 최소 눈금, 마찬가지로 정답 계산용으로 자료형 변경
		long long three = dfs(3, 0); // 3개 면을 골랐을 때의 최소 눈금, 마찬가지로 정답 계산용으로 자료형 변경

        // 이 공식은 직접 그려볼 수 밖에 없음
        // 3면이 노출되는 주사위 4개 [윗면 모서리]
        // 2면이 노출되는 주사위 (N - 1) * 4 [옆면 가장자리] + (N - 2) * 4 [윗면 가장자리]
        // 1면이 노출되는 주사위 (N - 1) * (N - 2) * 4 [옆면 중앙] + (N - 2) * (N - 2) [윗면 중앙]
        // (5N^2 - 16N + 12) * (3면 최소 눈금) + (8N - 12) * (2면 최소 눈금) + 4 * (1면 최소 눈금)
		cout << 4LL * three + (8LL * N - 12LL) * two + (5LL * N * N - 16LL * N + 12LL) * one << "\n";
	}

	return 0;
}
//
// 코멘트:
// 
// 필기 없이는 풀 수 없는 수학 문제
//
// 블로그 참조하였음
//
// 난이도 적당함
// 
// 취향 아님
//
