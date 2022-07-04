//
// BOJ - 14891
// 톱니바퀴
//
// 난이도: G5
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <string>
using namespace std;

#define ABS(x) (x >= 0 ? x : -x)

vector<string> gear(4);

string _rotate(string str, int direction) {
	int len = str.length();
	return direction > 0 ? str.substr(len - 1, 1) + str.substr(0, len - 1) : str.substr(1, len - 1) + str.substr(0, 1);
}

void rotate(int start, int direction) {
	int mLeft = gear[start - 1][6] - '0'; // 기준 톱니 왼쪽
	int mRight = gear[start - 1][2] - '0'; // 기준 톱니 오른쪽
	gear[start - 1] = _rotate(gear[start - 1], direction);

	int lDirection = direction;
	for (int i = start - 1; i >= 1; i--) {
		int sLeft = gear[i - 1][6] - '0'; // 돌릴 톱니 왼쪽
		int sRight = gear[i - 1][2] - '0'; // 돌릴 톱니 오른쪽
		if (mLeft != sRight) {
			mLeft = sLeft; // 다음 기준 톱니를 현재 톱니로 변경
			lDirection *= -1; // 전파시마다 방향 매번 바꿔줘야함
			gear[i - 1] = _rotate(gear[i - 1], lDirection);
		}
		else { // 극이 같으면 더이상 돌리지 않음
			break;
		}
	}

	int rDirection = direction;
	for (int i = start + 1; i <= 4; i++) {
		int sLeft = gear[i - 1][6] - '0'; // 돌릴 톱니 왼쪽
		int sRight = gear[i - 1][2] - '0'; // 돌릴 톱니 오른쪽
		if (mRight != sLeft) {
			mRight = sRight; // 다음 기준 톱니를 현재 톱니로 변경
			rDirection *= -1; // 전파시마다 방향 매번 바꿔줘야함
			gear[i - 1] = _rotate(gear[i - 1], rDirection);
		}
		else { // 극이 같으면 더이상 돌리지 않음
			break;
		}
	}
}

int main() {
	for (int i = 0; i < 4; i++) {
		cin >> gear[i];
	}

	int K;
	cin >> K;

	for (int i = 0; i < K; i++) {
		int idx, direction;
		cin >> idx >> direction;
		rotate(idx, direction);
	}

	int answer = 0;
	for (int i = 0; i < 4; i++) {
		answer += (gear[i][0] - '0') * (1 << i); // 점수 계산
	}

	cout << answer << "\n";

	return 0;
}
//
// 코멘트:
// 
// 톱니 회전 처리가 좌우로 전파될 때 톱니 상태랑 방향만 신경쓰면 된다.
//
// 풀어봤으니 쉽지 사실 처음 풀면 놓치기 쉬운 문제
//
