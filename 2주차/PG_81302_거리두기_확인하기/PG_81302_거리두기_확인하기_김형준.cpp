//
// PG - 81302
// 거리두기 확인하기
//
// 난이도: L2
// 제출자: wasuphj @Github
//
#include <iostream>
#include <string>
#include <queue>
#include <tuple>
#include <vector>

#define INF 1e9

using namespace std;
using T = tuple<int, int, int>;

const int dy[] = { -1,1,0,0 };
const int dx[] = { 0,0,-1,1 };

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;

    for (int i = 0; i < 5; i++) {
        int result = 1; // 문제 없으면 성공 처리
        vector<string> map = places[i]; // 지도
        vector<pair<int, int>> men; // 지원자 목록
        int men_dat[25] = { 0 }; // 지원자 여부

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                // 지원자인 경우
                if (map[y][x] == 'P') {
                    men.push_back({ y,x }); // 지원자 목록에 추가
                    men_dat[y * 5 + x] = 1; // 지원자 여부도 참으로 변경
                }
            }
        }

        // 모든 지원자에 대해 다익스트라 탐색
        for (auto man : men) {
            priority_queue<T, vector<T>, less<>> q;

            int dist[25]; // 5x5 맵 이므로 총 25곳 존재 가능
            for (int i = 0; i < 25; i++)
                dist[i] = INF; // 전부 INF로 초기화

            int sy = man.first, sx = man.second; // 시작 좌표 설정

            q.push({ 0, sy, sx }); // 시작 지점을 우선순위 큐에 삽입
            dist[sy * 5 + sx] = 0; // 시작 지점 거리 초기화

            while (!q.empty()) {
                int cd, cy, cx;
                tie(cd, cy, cx) = q.top();
                q.pop();

                // 상하좌우 탐색
                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    int nd = cd + 1;
                    if (ny < 0 || nx < 0 || ny > 4 || nx > 4) continue;
                    if (map[ny][nx] == 'X') continue; // 파티션을 만나면 진행 안함
                    if (dist[ny * 5 + nx] < nd) continue; // 이미 최단거리 탐색이 끝났으면 진행 안함
                    dist[ny * 5 + nx] = nd;
                    q.push({ nd, ny, nx });
                }
            }

            // 25곳을 다시 살펴봄
            for (int i = 0; i < 25; i++) {
                if (i == sy * 5 + sx) continue; // 시작 지점이라면 패스

                // 다른 지원자와의 거리가 2 이하이면 실패 처리
                if (men_dat[i] && dist[i] <= 2) {
                    result = 0;
                    break;
                }
            }
        }

        answer.push_back(result);
    }

    return answer;
}

int main() {
    vector<vector<string>> map = { {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"} };

    vector<int> results = solution(map);
    for (int result : results)
        cout << result << "\n";

    return 0;
}

//
// 코멘트:
// 
// 평범한 다익스트라 문제
//
