//
// PG - 60057
// ���ڿ� ����
//
// ���̵�: L2
// ������: wasuphj @Github
// 

#include <iostream>
#include <string>
#include <vector>

#pragma warning(disable:4996)

using namespace std;

int solution(string s) {
	int N = s.length(); // ���ڿ� ����
	int answer = N; // �ִ�� ���ڿ� ����

	// �ݺ� ��ū ���̴� 1���� ���ڿ� ������ ���ݱ����� (������ ������ �ݺ� �Ұ�)
	for (int k = 1; k <= N / 2; k++) {
		string buf;
		int i = 0;

		// ���ڿ� ������ �ݺ�
		while (i < N) {
			string origin = s.substr(i, k); // �� ����
			int cnt = 1;

			// ���� ��ū ���̸�ŭ�� �ȳ������� �׳� ���� ���ڿ��� ���̰� ������
			if (i >= N - k) {
				buf += s.substr(i, N - i);
				break;
			}

			string target = s.substr(i + k, k); // �� ���

			// ���ڿ� ������ �ݺ�
			while (i < N) {
				target = s.substr(i + k, k); // �� ���
				i += k; // k ��ŭ �� ������ ��������(�ε���) ����
				if (origin != target) break; // ��ġ���� ������ Ż��
				cnt++; // ��ġ�ϸ� �ݺ��̹Ƿ� ī��Ʈ ����
			}

			// ���� �ݺ��̶�� �ݺ� ���Ŀ� �°� ���ۿ� �߰�, �ݺ��� �ƴϸ� �� ���� ���ڿ��� ���ۿ� �߰�
			char numBuf[5];
			sprintf(numBuf, "%d", cnt);
			buf += (cnt > 1) ? numBuf + origin : origin;
		}

		answer = answer > buf.length() ? buf.length() : answer;
	}

	return answer;
}

int main() {
	string ss[] = { "aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd" };

	for (string s : ss)
		cout << solution(s) << "\n";

	return 0;
}

//
// �ڸ�Ʈ:
// 
// �׽�Ʈ ���̽��� �ȵǸ� �����ϴ� ����...
//
