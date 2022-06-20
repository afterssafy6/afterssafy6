//
// PG - 42586
// 기능 개발
//
// 난이도: L2
// 제출자: wasuphj @Github
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
    int N = progresses.size(); // 기능 개수

    vector<int> answer;
    priority_queue<int, vector<int>, greater<>> wait; // 완료 대기 중인 기능들
    vector<int> state(N); // 기능 개발 상태

    int done_idx = -1; // 가장 최근 완료된 기능의 인덱스
    for (int t = 1; t <= 100; t++) { // 0%로 시작 1%씩 100일간이 최대이므로 t = 100이 최대
        for (int i = 0; i < N; i++) { // N개의 기능에 대해 확인 진행
            if (state[i] == READY) continue; // 준비 상태인 기능들만 확인(만약 기능 개수가 수백 수천만개였다면 다른 방법 사용 필요)

            int progress = progresses[i] + speeds[i] * t; // 시간 t에서의 개발 진행도

            // 기능 개발 완료 상태라면 대기 처리
            if (progress >= 100) { 
                wait.push(i);
                state[i] = WAIT;
            }
        }

        int cnt = 0; // 종료 처리할 기능 개수 카운터
        while (!wait.empty()) { // 내부에서 break 걸어주므로 사실 1로 처리해도 가능
            int fore_idx = wait.top() - 1; // 종료할 기능 바로 앞 기능의 인덱스

            // 바로 앞 기능까지 기능 개발이 끝났으면 종료 처리
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

        // 종료 처리할 기능이 1개 이상 있다면 정답으로 추가
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
// 코멘트:
// 
// 프로그래머스가 Python에 맞게 출력 형식이 정해져 있어서 조금 아쉽다
//
