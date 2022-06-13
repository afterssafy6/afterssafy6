//
// PG - 42895
// N���� ǥ��
//
// ���̵�: L3
// ������: wasuphj @Github
// 

#include <iostream>
#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int concat(int X, int N) {
    int ret = 0;
    for (int i = 1; i <= N; i++)
        ret = ret * 10 + X;
    return ret;
}

int solution(int N, int number) {
    if (N == number) return 1; // N�� number�� ������ 1 ��ȯ

    // DP�迭, 0���� �̻��, 1~8������ ���
    // N�� ����ؼ� ���� �� �ִ� ���� ����
    vector<unordered_set<int>> DP(9);

    DP[1].insert(N); // N 1���� ����ؼ� ���� �� �ִ� ���� N ��

    for (int k = 2; k <= 8; k++) { // N 2��~8���� �̿��ؼ� ���� �� �ִ� ���� ���Ѵ�
        DP[k].insert(concat(N, k)); // N�� k���� �ٿ� ����� ���̽� ex. 55555

        // N�� k�� ����ؼ� ���� �� �ִ� ���� N�� k-t�� ����� ���� �� �ִ¼� +-*/ N�� t�� ����� ���� �� �ִ� �� (1 < t < k)
        for (int i = 1; i < k; i++) {
            int j = k - i; // i = 1, j = k - 1

            // ��Ģ���꿡 ���� ��� �� �߰�
            for (int A : DP[i]) {
                for (int B : DP[j]) {
                    DP[k].insert(A + B);
                    if (A - B != 0) DP[k].insert(A - B);
                    DP[k].insert(A * B);
                    if (B != 0 && A / B != 0) DP[k].insert(A / B);
                }
            }
        }

        // ���� k���� ���� �� �ִ� ���� number�� ���ԵǾ������� ����
        if (DP[k].find(number) != DP[k].end())
            return k;
    }
    return -1;
}

int main()
{
    int N, number;
    cin >> N >> number;

    std::cout << solution(N, number) << "\n";
}

//
// �ڸ�Ʈ:
//
// ���α׷��ӽ����� ó�� ���� ������ ����
// 
// Set�� �̿��ϴ� ������ ó�� ���� �� ����
// 
// DP �ʹ� ��ƴ�
// 
// ��α� ���� �Ͽ���
//
