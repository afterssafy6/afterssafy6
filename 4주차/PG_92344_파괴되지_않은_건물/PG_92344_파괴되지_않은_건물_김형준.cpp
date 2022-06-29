//
// PG - 92344
// 파괴되지 않은 건물
//
// 난이도: L3
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <string>
#include <vector>

#define ATK 1
#define HEAL 2

using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
	int yl = board.size();
	int xl = board[0].size();
	int ss = skill.size();

	vector<vector<int>> dp(1001, vector<int>(1001)); // 스킬 데미지 & 범위 배열
	vector<vector<int>> cum(1001, vector<int>(1001)); // 누적합 배열

	int answer = 0;
	
	for (int i = 0; i < ss; i++) {
		int type = skill[i][0];
		int ys = skill[i][1];
		int xs = skill[i][2];
		int ye = skill[i][3];
		int xe = skill[i][4];
		int degree = ((type == ATK) ? skill[i][5] : -skill[i][5]);

        // 2차원 누적합
		dp[ys][xs] -= degree; // 박스 시작부(왼쪽 위)를 N만큼 빼고
		dp[ye + 1][xe + 1] -= degree; // 박스 종료부(오른쪽 아래)를 N만큼 빼고
		dp[ys][xe + 1] += degree; // 그 외 꼭지(오른쪽 위)에서 N만큼 더해준다. 이를 누적합하면 그 사이가 다 누적됨
		dp[ye + 1][xs] += degree; // 그 외 꼭지(왼쪽 아래)에서 N만큼 더해준다. 이를 누적합하면 그 사이가 다 누적됨
	}

	copy(dp.begin(), dp.end(), cum.begin());

	for (int x = 0; x < xl; x++) { // 스킬 데미지 누적합 세로 더하고
		for (int y = 1; y < yl; y++) {
			cum[y][x] += cum[y - 1][x];
		}
	}

	for (int y = 0; y < yl; y++) { // 스킬 데미지 누적합 가로 더하기
		for (int x = 1; x < xl; x++) {
			cum[y][x] += cum[y][x - 1];
		}
	}

	for (int y = 0; y < yl; y++) { // 스킬 데미지 누적합과 원래 보드 더하기
		for (int x = 0; x < xl; x++) {
			board[y][x] += cum[y][x];
			answer += (board[y][x] > 0);
		}
	}

	return answer;
}

int main() {
	for (int t = 1; t <= 2; t++) {
		vector<vector<int>> board[] = { { {5,5,5,5,5,}, {5,5,5,5,5,}, {5,5,5,5,5,}, {5,5,5,5,5,}, }, { {1,2,3,}, {4,5,6,}, {7,8,9,}, } };
		vector<vector<int>> skill[] = { { {1,0,0,3,4,4,}, {1,2,0,2,3,2,}, {2,1,0,3,1,2,}, {1,0,1,3,3,1,}, }, { {1,1,1,2,2,4,}, {1,0,0,1,1,2,}, {2,2,0,2,0,100} } };
		vector<int> answer = { 10, 6 };

		int result = solution(board, skill);

		cout << "#" << t << " " << (result == answer[t - 1] ? "OK" : "NO") << "\n";
	}

	return 0;
}

//
// 코멘트:
// 
// DP나 메모이제이션이라고 생각했는데 누적합이었다
//
// 2차원 누적합이라는 방법을 알게 되었다
//
// 카카오 해설 참고
//
