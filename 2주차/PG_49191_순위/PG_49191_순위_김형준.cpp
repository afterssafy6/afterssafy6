//
// PG - 49191
// ����
//
// ���̵�: L3
// ������: wasuphj @Github
//
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#define MIN(a,b) (a < b ? a : b)

using namespace std;

int graph[101][101];

int solution(int n, vector<vector<int>> results) {
    int answer = 0;

    // ��ġ �׷��� �ʱ�ȭ
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            graph[i][j] = 0;

    // ��ġ ��� ���
    for (vector<int> result : results)
    {
        int winner = result[0];
        int loser = result[1];
        graph[winner][loser] = 1;
    }

    // ���������ζ� ��Ī�Ǵ� ��� 1�� ���
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            for (int k = 1; k <= n; k++)
                graph[j][k] = (graph[j][i] && graph[i][k]) ? 1 : graph[j][k];

    // �ٸ� ��� ��������� ��Ī ����� ������ +1, �Ѹ��̶� ������ 0
    for (int i = 1; i <= n; i++) {
        int count = 0;
        for (int j = 1; j <= n; j++) {
            if (graph[i][j] || graph[j][i]) count++;
        }
        if (count == n - 1) answer++;
    }

    return answer;
}

int main() {
    cout << solution(5, { {4,3}, {4,2}, {3,2}, {1,2}, {2,5} }) << "\n";

    return 0;
}

//
// �ڸ�Ʈ:
// 
// �÷��̵� �ͼ��� �߸� �Ἥ ���������� ���� ��̴�
// 
// ��α� ���� �Ͽ���
//
