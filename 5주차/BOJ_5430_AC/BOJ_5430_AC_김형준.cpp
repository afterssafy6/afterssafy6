//
// BOJ - 5430
// AC
//
// 난이도: G5
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

#define OP_NONE 0
#define OP_R 1
#define OP_D 2

int main() {
	cin.tie(NULL);
	cout.tie(NULL);
	ios::sync_with_stdio(false);

	int T;
	cin >> T;

	while (T--) {
		int error = 0;

		string p;
		cin >> p;

		int N;
		cin >> N;

		int start = 0; // 출력 시작점
		int end = N - 1; // 출력 종료
		int reverse = 0; // 뒤집힘 여부

		vector<int> arr; // 정수 배열

		string S;
		cin >> S;

		S = S.substr(1, S.length() - 2);

		string buf;
		istringstream iss(S);
		while (getline(iss, buf, ',')) {
			arr.push_back(stoi(buf));
		}

        // 함수 파싱
		int op = 0, cnt = 0;
		int len = p.length();
		for (int i = 0; i < len; i++) {
			if (op) {
                // #1 함수가 기존과 같으면 계속 카운트
				if (op == OP_R && p[i] == 'R') {
					cnt++;
					continue;
				} else if (op == OP_D && p[i] == 'D') {
					cnt++;
					continue;
				}

                // #2 함수가 기존과 다르면 종료 처리
				if (op == OP_R && cnt % 2) {
					reverse = !reverse;
				}
				else if (op == OP_D) {
					if (end - start + 1 < cnt) { // 에러 처리
						error = 1;
						break;
					}

					if (reverse) end -= cnt; // 뒤집혀있으면 뒤에서 삭제
					else start += cnt; // 아니면 앞에서 삭제
				}
			}

			if (p[i] == 'R') { // #3 새로 함수 카운트 시작
				op = OP_R;
				cnt = 1;
			}
			else if (p[i] == 'D') {
				op = OP_D;
				cnt = 1;
			}
		}

        // 종료 후에 마지막으로 저장되어있던 함수 처리
		if (op == OP_R && cnt % 2) {
			reverse = !reverse;
		}
		else if (op == OP_D) {
			if (end - start + 1 < cnt) {
				error = 1;
			}

			if (reverse) end -= cnt;
			else start += cnt;
		}

        // 에러났으면 에러 표시
		if (error) {
			cout << "error" << "\n";
		}
		else
		{
            // 아니면 수열 표시
			cout << "[";
			if (reverse) {
				for (int i = end; i >= start; i--) {
					cout << arr[i];
					if (i != start) cout << ",";
				}
			}
			else {
				for (int i = start; i <= end; i++) {
					cout << arr[i];
					if (i != end) cout << ",";
				}
			}
			cout << "]";
			cout << "\n";
		}
	}
	
	return 0;
}
//
// 코멘트:
// 
// reverse 함수 속도 문제라고는 생각을 못했다
//
// 단번에 풀 줄 알았는데 ㅠ
//
