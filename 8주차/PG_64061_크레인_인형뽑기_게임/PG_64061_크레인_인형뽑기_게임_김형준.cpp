//
// PG - 64061
// 크레인 인형뽑기 게임
//
// 난이도: L1
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    int h = board.size();
    int w = board[0].size();
    
    vector<int> head(w); // 각 줄(column)의 제일 윗부분 idx를 저장
    for (int x = 0; x < w; x++) {
        for (int y = 0; y < h; y++) {
            if (board[y][x]) {
                head[x] = y;
                break;
            }
        }
    }
    
    int top = -1;
    vector<int> st(w * h); // 크레인 스택 (두개 모이면 터짐)
    
    for (int move : moves) {
        if (head[--move] >= h) continue; // 그 줄에 아무것도 없으면 다음 이동
        
        int now = board[head[move]++][move]; // 크레인이 잡은 프렌즈를 선택하고 head를 한칸 내림 (값은 +1)

        if (top == -1) st[++top] = now; // 스택에 암것도 없으면 그냥 넣고
        else if (st[top] == now) top--, answer += 2; // 스택 제일 위와 현재 크레인이 잡은 프렌즈가 같으면 터트림
        else st[++top] = now; // 다 아니면 그냥 쌓음
    }
    
    return answer;
}

int main() {
    cout << solution({{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}}, {1, 5, 3, 5, 1, 2, 1, 4}) << "\n";
}

//
// 코멘트:
//
// 주석을 달 필요가 있을까?
//