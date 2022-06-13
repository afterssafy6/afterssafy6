//
// PG - 81302
// �Ÿ��α� Ȯ���ϱ�
//
// ���̵�: L2
// ������: wasuphj @Github
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
        int result = 1; // ���� ������ ���� ó��
        vector<string> map = places[i]; // ����
        vector<pair<int, int>> men; // ������ ���
        int men_dat[25] = { 0 }; // ������ ����

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                // �������� ���
                if (map[y][x] == 'P') {
                    men.push_back({ y,x }); // ������ ��Ͽ� �߰�
                    men_dat[y * 5 + x] = 1; // ������ ���ε� ������ ����
                }
            }
        }

        // ��� �����ڿ� ���� ���ͽ�Ʈ�� Ž��
        for (auto man : men) {
            priority_queue<T, vector<T>, less<>> q;

            int dist[25]; // 5x5 �� �̹Ƿ� �� 25�� ���� ����
            for (int i = 0; i < 25; i++)
                dist[i] = INF; // ���� INF�� �ʱ�ȭ

            int sy = man.first, sx = man.second; // ���� ��ǥ ����

            q.push({ 0, sy, sx }); // ���� ������ �켱���� ť�� ����
            dist[sy * 5 + sx] = 0; // ���� ���� �Ÿ� �ʱ�ȭ

            while (!q.empty()) {
                int cd, cy, cx;
                tie(cd, cy, cx) = q.top();
                q.pop();

                // �����¿� Ž��
                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    int nd = cd + 1;
                    if (ny < 0 || nx < 0 || ny > 4 || nx > 4) continue;
                    if (map[ny][nx] == 'X') continue; // ��Ƽ���� ������ ���� ����
                    if (dist[ny * 5 + nx] < nd) continue; // �̹� �ִܰŸ� Ž���� �������� ���� ����
                    dist[ny * 5 + nx] = nd;
                    q.push({ nd, ny, nx });
                }
            }

            // 25���� �ٽ� ���캽
            for (int i = 0; i < 25; i++) {
                if (i == sy * 5 + sx) continue; // ���� �����̶�� �н�

                // �ٸ� �����ڿ��� �Ÿ��� 2 �����̸� ���� ó��
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
// �ڸ�Ʈ:
// 
// ����� ���ͽ�Ʈ�� ����
//
