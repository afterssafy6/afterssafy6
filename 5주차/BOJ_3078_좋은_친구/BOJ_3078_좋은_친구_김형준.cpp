//
// BOJ - 3078
// 좋은 친구
//
// 난이도: G4
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <string>
#include <queue>
#include <unordered_map>
using namespace std;

int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N, K;
	cin >> N >> K;

	long long answer = 0;

	queue<int> names;
	unordered_map<int, int> name_lengthes;
	for (int i = 0; i < N; i++) {
		string name;
		cin >> name;

        int len = name.length();
		
		answer += name_lengthes[len];

		names.push(len);
		name_lengthes[len]++;
		
		if (i >= K) {
			name_lengthes[names.front()]--;
			names.pop();
		}
	}

	cout << answer << "\n";

	return 0;
}
//
// 코멘트:
// 
// 그냥 슬라이딩 윈도우 문제
//