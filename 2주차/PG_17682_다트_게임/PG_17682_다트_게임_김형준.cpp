//
// PG - 17682
// 다트 게임
//
// 난이도: L1
// 제출자: wasuphj @Github
//

#include <iostream>
#include <string>

using namespace std;

int solution(string dartResult) {
    int N = dartResult.length(); // 문자열 길이
    int answer = 0; // 정답
    int score[3] = { 0 }; // 3회의 게임 점수를 저장할 배열
    int score_idx = -1; // 현재 점수 idx
    int score_proc = 0; // 현재 점수를 기록 중인지 여부 (10점 때문)
    string score_buf = "";

    for (int i = 0; i < N; i++) {
        char c = dartResult[i];

        // 만약 숫자이면 점수 기록
        if (c >= '0' && c <= '9') {
            // 점수 기록중이면 그냥 추가, 아니면 먼저 기록중으로 상태 전환 후 idx 1 증가 후 기록
            if (score_proc) {
                score_buf += c;
            }
            else {
                score_proc = 1;
                score_idx += 1;
                score_buf += c;
            }
        }
        // 만약 문자이면 보너스
        else if (c >= 'A' && c <= 'Z') {
            // 점수 기록 종료 처리
            if (score_proc) {
                score_proc = 0;
                score[score_idx] = stoi(score_buf, 0);
                score_buf = "";
            }

            // 보너스 제곱
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

            // 그럴일은 없겠지만, 1 이상이면 제곱수 만큼 점수 제곱해주기
            if (square) {
                int origin_score = score[score_idx];
                score[score_idx] = 1;
                for (int k = 1; k <= square; k++) {
                    score[score_idx] *= origin_score;
                }
            }
        }
        // 옵션 (상) 처리
        else {
            // 우수상
            if (c == '*') {
                score[score_idx] *= 2;
                if (score_idx - 1 >= 0) score[score_idx - 1] *= 2;
            }
            // 장려상
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
// 코멘트:
// 
// 그냥 물 흐르듯 구현하면 되는 문제
//
