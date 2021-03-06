//
// BOJ - 20055
// 컨베이어 벨트 위의 로봇
//
// 난이도: G5
// 제출자: wasuphj @Github
// 

#include <iostream>
using namespace std;

int N = 0, K = 0;
int ans = 0; // 정답 (진행한 단계)
int broken = 0; // 부서진 셀 개수
int cells_hp[201] = { 0 }; // 셀의 내구도
int cells_robot[201] = { 0 }; // 셀에 올라타있는 로봇 번호
int robot_idx_start = 1; // 가동 가능 로봇 번호의 시작점
int robot_idx_end = 0; // 가동 가능 로봇 번호의 종료점
int robot_pos[300001] = { 0 }; // 로봇의 현재 위치

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	// N과 K 입력
	cin >> N >> K;

	for (int i = 1; i <= 2 * N; i++) {
		// 각 칸의 내구도 A_i 입력
		cin >> cells_hp[i];
	}

	// 부서진 셀의 개수가 K개 이상이면 종료
	while (broken < K) {
		// 먼저 벨트 회전을 위해 마지막 칸의 정보를 기록
		int last_cell_hp = cells_hp[N * 2];
		int last_cell_robot = cells_robot[N * 2];

		// 순서 1. 벨트 회전
		for (int cell_idx = N * 2; cell_idx > 1; cell_idx--) {
			// 현재 칸의 내구도는 이전 칸의 내구도
			cells_hp[cell_idx] = cells_hp[cell_idx - 1];

			// 현재 칸의 로봇 번호는 이전 칸의 로봇 번호
			cells_robot[cell_idx] = cells_robot[cell_idx - 1];
		}

		// 만약, 이동한 칸이 내리는 칸(N번)이면 로봇이 내림
		if (cells_robot[N]) {
			robot_pos[cells_robot[N]] = 0; // N번 칸의 로봇의 위치를 0(내림)으로 설정
			cells_robot[N] = 0; // N번 칸에 올라타있는 로봇이 없음
			robot_idx_start += 1; // !!! 로봇이 내리면 가동 가능 범위 시작점을 1만큼 올림 !!!
		}

		// 마지막 칸이 첫째칸이 됨
		cells_hp[1] = last_cell_hp;
		cells_robot[1] = last_cell_robot;

		// 순서 2. 로봇 이동
		for (int i = robot_idx_start; i <= robot_idx_end; i++) {
			robot_pos[i] += 1; // 컨테이너가 1칸 씩 무조건 이동했으므로 현재 칸의 로봇 위치도 한칸 이동

			// 다음 칸에 이미 로봇이 있으면 이동 불가
			if (cells_robot[robot_pos[i] + 1]) continue;

			// 다음 칸의 내구도가 1 이상 남아 있지 않으면 이동 불가
			if (!cells_hp[robot_pos[i] + 1]) continue;

			cells_robot[robot_pos[i]] = 0; // 로봇이 현재 올라 타있는 칸을 비움
			cells_hp[robot_pos[i] + 1] -= 1; // 다음 칸의 내구도를 1만큼 감소
			cells_robot[robot_pos[i] + 1] = i; // 로봇이 다음 칸으로 이동

			robot_pos[i]++; // 로봇의 현재 위치를 이동한 칸(다음 칸)으로 변경

			// !!! 만약, 이동한 칸의 내구도가 다 닳았으면 체크 !!!
			if (!cells_hp[robot_pos[i]]) broken++;

			// 만약, 이동한 칸이 내리는 칸이면 로봇 내림
			if (robot_pos[i] == N) {
				cells_robot[N] = 0; // 로봇이 내렸으므로 로봇이 타고 있던 N번째 칸은 비어 있음
				robot_pos[i] = 0; // 로봇이 내렸으므로 로봇의 현재 위치를 0(내림)으로 설정
				robot_idx_start += 1; // !!! 로봇이 내리면 가동 가능 범위 시작점을 1만큼 올림 !!!
			}
		}

		// 순서 3. 로봇 올림
		if (cells_hp[1]) {
			cells_hp[1] -= 1; // 로봇을 올리는 칸의 내구도를 1만큼 감소
			cells_robot[1] = ++robot_idx_end; // 로봇을 올리는 칸에 새로 올릴 로봇의 번호를 표시
			robot_pos[robot_idx_end] = 1; // 로봇의 현재 위치는 올리는 칸 (1번칸) 으로 설정

			// !!! 만약, 올리는 칸의 내구도가 다 닳았으면 체크 !!!
			if (!cells_hp[1]) broken++;
		}

		ans++; // 단계를 마치고 단계를 1만큼 올림

		// 순서 4. 내구도가 0인 칸의 개수가 K 이상이면 과정 종료, 아니면 계속
	}

	cout << ans << "\n";

	return 0;
}

//
// 코멘트:
//
// 간단한 문제라고 생각했는데 의외의 곳에서 틀려서 당황한 문제
// 문제에서 제시한 최대 조건으로 N, K, A_i 설정시
// robot_pos가 25만까지 필요하다
// 
// 또한 단순 구현문제라고 생각했는데 의외로 시간 초과가 발생해서
// if 문을 최소화나 로직 순서 변경 등으로 최적화를 진행했다.
// 그래도 안되서 마지막으로 했던게 for문 범위를 확인해 봤다.
//
// 원래 코드에서는 무조건 0부터 robot_idx까지 다 돌게 했지만 (if-continue로 탈출)
// 위에서 언급했듯 robot_idx가 25만까지 가는 바람에
// 매단계마다 for문이 25만번 돌아서 시간초과 발생
// 아무리 바로 continue로 탈출하더라도 if문이 25만번씩 도는건 어쩔수가 없었다.
// 
// 나중에 출발한 로봇이 먼저 내리는 일은 절대 없으므로
// for문 시작 범위 변수를 추가하고 로봇이 내릴때마다 증가시켜 for문 범위를 좁혀 해결했다.
// 