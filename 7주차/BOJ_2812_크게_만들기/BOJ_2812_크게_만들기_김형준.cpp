//
// BOJ - 2821
// 크게 만들기
//
// 난이도: G3
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N, K;
	cin >> N >> K;

	string S;
	cin >> S;

	vector<char> ans;
	for (int i = 0; i < N; i++) {
		// 출력 후보의 맨 끝보다 현재 숫자가 더 크면 앞에 애를 빼야함
		while (!ans.empty() && ans.back() < S[i]) {
			if (K) { // 삭제 가능
				ans.pop_back(); // 출력 후보 중 맨 뒤의 요소를 삭제한다
				K--; // K를 1카운트 빼준다
			}
		}
		ans.push_back(S[i]); // 계속 숫자를 채운다
	}

	for (int i = 0; i < K; i++) { // K가 남았으면 뒤에서 부터 빼준다
		ans.pop_back();
	}

	int L = ans.size();
	for (int i = 0; i < L; i++) { // 정답 길이 L 만큼 출력
		cout << ans[i];
	}

	return 0;
}

//
// 코멘트:
//
// 처음엔 LIS 비슷하게 접근했다
// 
// 스택을 써서 LIS를 구하고 그 앞애들을 다 날리는 식으로 구현하려 했다
// 
// 근데 K가 남았을때 이걸 다시 그 자리에 채워 넣으려니 머리가 아파서 벡터로 바꿨다
//