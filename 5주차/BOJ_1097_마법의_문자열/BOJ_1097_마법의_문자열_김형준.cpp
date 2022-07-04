//
// BOJ - 1097
// 마법의 문자열
//
// 난이도: G1
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int N;
int K;
vector<string> dict;
vector<int> used;
vector<int> path;
int answer;

// 문자열 원형 순환
string rotate(string str, int offset) {
	int len = str.length();
	return str.substr(offset, len - offset) + str.substr(0, offset);
}

// 매직 문자열 체크
int magicstring(string str, int K) {
	int len = str.length();
	int ret = 0;

	for (int i = 0; i < len; i++) {
		if (str == rotate(str, i)) ret++;
	}

	return ret == K;
}

// DFS
void run(int lev) {
	if (lev >= N) {
		string buf = "";

		for (int i = 0; i < N; i++) {
			buf += dict[path[i]];
		}

		answer += magicstring(buf, K);
		return;
	}

	for (int i = 0; i < N; i++) {
		if (used[i]) continue;
		used[i] = 1;
		path[lev] = i;
		run(lev + 1);
		path[lev] = -1;
		used[i] = 0;
	}
}

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	dict = vector<string>(N);
	used = vector<int>(N);
	path = vector<int>(N);

	for (int i = 0; i < N; i++) {
		cin >> dict[i];
	}

	cin >> K;

	run(0);

	cout << answer << "\n";

	return 0;
}
//
// 코멘트:
// 
// 이게 왜 골드 1인지...
//
// 문제를 이상하게 써놔서 사람들이 이해를 못해서 골드 1인듯
//
