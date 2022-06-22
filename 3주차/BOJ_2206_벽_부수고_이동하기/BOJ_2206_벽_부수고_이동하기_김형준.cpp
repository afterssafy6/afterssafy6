//
// BOJ - 2206
// 벽 부수고 이동하기
//
// 난이도: G4
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
using T = tuple<int, int, int, int>;

#define INF 1e9
#define MIN(a,b) ((a) < (b) ? (a) : (b))

int dist[1001][1001][2]; // 최소 거리 배열
int map[1001][1001]; // 지도 저장

const int dy[] = { -1,1,0,0 };
const int dx[] = { 0,0,-1,1 };

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N, M;
	cin >> N >> M;
	
	for (int y = 1; y <= N; y++) {
		char map_row[1001]; 
		cin >> map_row;
		for (int x = 1; x <= M; x++) {
			map[y][x] = (map_row[x - 1] == '1'); // 문자열을 맵으로 사용해도 되지만, 사용하기 좋게 int map으로 변환
			for (int t = 0; t < 2; t++) {
				dist[y][x][t] = INF; // 입력과 동시에 모든 좌표의 최소 거리 값을 INF로 초기화
			}
		}
	}

	priority_queue<T, vector<T>, greater<>> q; // 거리가 짧고, 벽뿌 기회가 있는 것 우선으로 탐색 진행
	q.push({ 1, -1, 1, 1 }); // 벽뿌 기회 1개, (1, 1)에서 시작, 비교자 greater 사용을 위해 벽뿌 기회를 음수로 사용
	dist[1][1][1] = 1; // (1, 1)까지 거리를 1로 초기화 (문제 조건)
	dist[1][1][0] = 1; // (1, 1)까지 거리를 1로 초기화

	while (!q.empty()) {
		int c, t, y, x; // c: 거리, t: 벽뿌 기회, (y,x): 좌표
		tie(c, t, y, x) = q.top();
		q.pop();

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i]; // (ny, nx): 이동할 좌표, nc: (ny,nx) 까지의 이동 거리
			int nx = x + dx[i];
			int nc = c + 1;
			if (ny < 1 || nx < 1 || ny > N || nx > M) continue;
			if (map[ny][nx] && t) { // (ny, nx)가 벽이고, 벽을 부술 수 있다면
				if (dist[ny][nx][t + 1] <= nc) continue; // 최소 거리 갱신 불가능 시 패스
				dist[ny][nx][t + 1] = nc; // 최소 거리 갱신
				q.push({ nc, t + 1, ny, nx }); // (ny, nx)로 이동 시 벽 부수는 경우 추가
			}
			else if (!map[ny][nx]) // (ny, nx)가 벽이 아니면
			{
			    if (dist[ny][nx][-t] <= nc) continue; // 최소 거리 갱신 불가능 시 패스
				dist[ny][nx][-t] = nc; // 최소 거리 갱신
				q.push({ nc, t, ny, nx }); // (ny, nx)로 이동 하는 경우 추가
			}
		}
	}
	
	int ans = MIN(dist[N][M][0], dist[N][M][1]); // (N, M) 도착시 벽 부수는 경우와 안 부수는 경우 중 작은 값 선택
	cout << (ans >= INF ? -1 : ans) << "\n"; // 둘다 무한 거리면 -1

	return 0;
}

//
// 코멘트:
// 
// 조건을 잘못 주면 교차 지점이 큐에 중복으로 들어가서 메모리 초과가 발생한다
//
// 이를테면 (2,1) -> (2,2)로 가는 케이스와 (1,2) -> (2,2)로 가는 케이스 등이 큐에 계속 추가되서 메모리 초과가 발생한다
//
//
