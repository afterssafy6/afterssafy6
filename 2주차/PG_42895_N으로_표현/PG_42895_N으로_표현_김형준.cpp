//
// PG - 42895
// N으로 표현
//
// 난이도: L3
// 제출자: wasuphj @Github
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
    if (N == number) return 1; // N과 number가 같으면 1 반환

    // DP배열, 0번은 미사용, 1~8번까지 사용
    // N개 사용해서 만들 수 있는 수를 저장
    vector<unordered_set<int>> DP(9);

    DP[1].insert(N); // N 1개를 사용해서 만들 수 있는 수는 N 뿐

    for (int k = 2; k <= 8; k++) { // N 2개~8개를 이용해서 만들 수 있는 수를 구한다
        DP[k].insert(concat(N, k)); // N을 k개씩 붙여 만드는 케이스 ex. 55555

        // N을 k개 사용해서 만들 수 있는 수는 N을 k-t개 사용해 만들 수 있는수 +-*/ N을 t개 사용해 만들 수 있는 수 (1 < t < k)
        for (int i = 1; i < k; i++) {
            int j = k - i; // i = 1, j = k - 1

            // 사칙연산에 대해 계산 후 추가
            for (int A : DP[i]) {
                for (int B : DP[j]) {
                    DP[k].insert(A + B);
                    if (A - B != 0) DP[k].insert(A - B);
                    DP[k].insert(A * B);
                    if (B != 0 && A / B != 0) DP[k].insert(A / B);
                }
            }
        }

        // 만약 k개로 만들 수 있는 수에 number가 포함되어있으면 리턴
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
// 코멘트:
//
// 프로그래머스에서 처음 접한 형태의 문제
// 
// Set을 이용하는 문제는 처음 접한 것 같다
// 
// DP 너무 어렵다
// 
// 블로그 참고 하였음
//
