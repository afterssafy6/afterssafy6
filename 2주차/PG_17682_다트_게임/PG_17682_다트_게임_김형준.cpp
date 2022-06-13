//
// PG - 17682
// ��Ʈ ����
//
// ���̵�: L1
// ������: wasuphj @Github
//

#include <iostream>
#include <string>

using namespace std;

int solution(string dartResult) {
    int N = dartResult.length(); // ���ڿ� ����
    int answer = 0; // ����
    int score[3] = { 0 }; // 3ȸ�� ���� ������ ������ �迭
    int score_idx = -1; // ���� ���� idx
    int score_proc = 0; // ���� ������ ��� ������ ���� (10�� ����)
    string score_buf = "";

    for (int i = 0; i < N; i++) {
        char c = dartResult[i];

        // ���� �����̸� ���� ���
        if (c >= '0' && c <= '9') {
            // ���� ������̸� �׳� �߰�, �ƴϸ� ���� ��������� ���� ��ȯ �� idx 1 ���� �� ���
            if (score_proc) {
                score_buf += c;
            }
            else {
                score_proc = 1;
                score_idx += 1;
                score_buf += c;
            }
        }
        // ���� �����̸� ���ʽ�
        else if (c >= 'A' && c <= 'Z') {
            // ���� ��� ���� ó��
            if (score_proc) {
                score_proc = 0;
                score[score_idx] = stoi(score_buf, 0);
                score_buf = "";
            }

            // ���ʽ� ����
            int square = 0;
            if (c == 'S') {
                square = 1;
            }
            else if (c == 'D') {
                square = 2;
            }
            else if (c == 'T') {
                square = 3;
            }

            // �׷����� ��������, 1 �̻��̸� ������ ��ŭ ���� �������ֱ�
            if (square) {
                int origin_score = score[score_idx];
                score[score_idx] = 1;
                for (int k = 1; k <= square; k++) {
                    score[score_idx] *= origin_score;
                }
            }
        }
        // �ɼ� (��) ó��
        else {
            // �����
            if (c == '*') {
                score[score_idx] *= 2;
                if (score_idx - 1 >= 0) score[score_idx - 1] *= 2;
            }
            // �����
            else if (c == '#') {
                score[score_idx] *= -1;
            }
        }
    }

    for (int i = 0; i < 3; i++) {
        answer += score[i];
    }

    return answer;
}

int main() {
    string ss[] = { "1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*" };

    for (string s : ss)
        cout << solution(s) << "\n";

    return 0;
}

//
// �ڸ�Ʈ:
// 
// �׳� �� �帣�� �����ϸ� �Ǵ� ����
//
