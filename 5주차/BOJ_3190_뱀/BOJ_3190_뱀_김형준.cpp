//
// BOJ - 3190
// 뱀
//
// 난이도: G4
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	vector<vector<int>> map(N + 1, vector<int>(N + 1));
	map[1][1] = 1;

	int K;
	cin >> K;
	
	for (int k = 0, y, x; k < K; k++) {
		cin >> y >> x;
		map[y][x] = 2;
	}

	int L;
	cin >> L;

	priority_queue<pair<int, char>, vector<pair<int, char>>, greater<>> dirQ;
	for (int i = 0; i < L; i++) {
		int time;
		char dir;
		cin >> time >> dir;
		dirQ.push({ time, dir });
	}

	const int dy[] = { -1,0,1,0, };
	const int dx[] = { 0,1,0,-1, };

	queue<pair<int, int>> snake;
	int sy = 1;
	int sx = 1;
	int sDir = 1;
	snake.push({ sy, sx });

	int answer = 0;
	while (++answer) {
		sy += dy[sDir];
		sx += dx[sDir];
		
		if (sy < 1 || sx < 1 || sy > N || sx > N) { // 벽에 부딪힘
			break;
		}

		if (map[sy][sx] == 1) { // 자기 몸 부딪힘
			break;
		}

		if (map[sy][sx] == 0) { // 사과 없는 곳으로 가면 길이 유지
			int ty = snake.front().first;
			int tx = snake.front().second;

			map[ty][tx] = 0;
			snake.pop();
		}

		map[sy][sx] = 1;
		snake.push({ sy, sx });

		if (dirQ.empty()) continue; // 더이상 방향 바꿀 필요 없으면 패스

		if (dirQ.top().first == answer) { // 혹시 방향 바꿔야하면 처리
			char nDir = dirQ.top().second;
			dirQ.pop();

			if (nDir == 'L') sDir--;
			else if (nDir == 'D') sDir++;

			if (sDir < 0) sDir += 4;
			else if (sDir >= 4) sDir %= 4;
		}
	}

	cout << answer << "\n";

	return 0;
}
//
// 코멘트:
// 
// 간단한 구현 문제
//