//
// PG - 42586
// ��� ����
//
// ���̵�: L2
// ������: wasuphj @Github
// 

#include <iostream>
#include <string>
#include <vector>
#include <queue>

#define READY 0
#define WAIT 1
#define DONE 2

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    int N = progresses.size(); // ��� ����

    vector<int> answer;
    priority_queue<int, vector<int>, greater<>> wait; // �Ϸ� ��� ���� ��ɵ�
    vector<int> state(N); // ��� ���� ����

    int done_idx = -1; // ���� �ֱ� �Ϸ�� ����� �ε���
    for (int t = 1; t <= 100; t++) { // 0%�� ���� 1%�� 100�ϰ��� �ִ��̹Ƿ� t = 100�� �ִ�
        for (int i = 0; i < N; i++) { // N���� ��ɿ� ���� Ȯ�� ����
            if (state[i] == READY) continue; // �غ� ������ ��ɵ鸸 Ȯ��(���� ��� ������ ���� ��õ�������ٸ� �ٸ� ��� ��� �ʿ�)

            int progress = progresses[i] + speeds[i] * t; // �ð� t������ ���� ���൵

            // ��� ���� �Ϸ� ���¶�� ��� ó��
            if (progress >= 100) { 
                wait.push(i);
                state[i] = WAIT;
            }
        }

        int cnt = 0; // ���� ó���� ��� ���� ī����
        while (!wait.empty()) { // ���ο��� break �ɾ��ֹǷ� ��� 1�� ó���ص� ����
            int fore_idx = wait.top() - 1; // ������ ��� �ٷ� �� ����� �ε���

            // �ٷ� �� ��ɱ��� ��� ������ �������� ���� ó��
            if (fore_idx == done_idx) { 
                cnt++;
                done_idx++;
                state[wait.top()] = DONE;
                wait.pop();
            }
            else {
                break;
            }
        }

        // ���� ó���� ����� 1�� �̻� �ִٸ� �������� �߰�
        if (cnt)
            answer.push_back(cnt);
    }

    return answer;
}

int main()
{
    auto ans = solution({ 93, 30, 55 }, { 1, 30, 5 });
    cout << "[";
    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i];
    }
    cout << ", ";
    cout << "]" << "\n";
}

//
// �ڸ�Ʈ:
// 
// ���α׷��ӽ��� Python�� �°� ��� ������ ������ �־ ���� �ƽ���
//
