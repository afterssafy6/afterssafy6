//
// PG - 64064
// 불량 사용자
//
// 난이도: L3
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <string>
#include <vector>
#include <regex>
#include <algorithm>
#include <set>

using namespace std;

int solution(vector<string> user_id, vector<string> banned_id) {
    int user_id_size = user_id.size(); // 사용자 아이디 배열 길이
    int banned_id_size = banned_id.size(); // 불량 사용자 패턴 배열 길이

    vector<int> selection(user_id_size - banned_id_size, 0); // 순열 구현용 벡터
    for (int i = 0; i < banned_id_size; i++) selection.push_back(i + 1); // ex. N=3, 0 0 0 ... 1 2 3 형태, 1 이상은 banned_id의 idx

    set<set<string>> answer; // 정답은 set으로 중복되지 않도록 한다
    do {
            int flag = 1;
            set<string> selected_user; // 중복된 값이 들어가지 앙ㄶ도록 한다. 사실 vector여도 상관은 없을테지만..?

            for (int i = 0; i < user_id_size; i++) { // 사용자 아이디 배열을 돌면서
                    if (selection[i]) { // 선택된 아이디에 대해서만 패턴 확인 시작
                            string pair_banned_id = banned_id[selection[i] - 1]; // 해당 유저 아이디에 대응되는 불량 아이디
                            string pattern = regex_replace(pair_banned_id, regex("\\*"), "."); // 불량 아이디 문자열의 *를 .으로 변환
                            regex re(pattern); // 이를 바탕으로 정규식 객체를 만들어준다
                            if (!regex_match(user_id[i], re)) { flag = 0; break; } // 만약 일치하지 않으면 더이상 탐색않고 break

                            selected_user.insert(user_id[i]); // 일치한다면 set에 넣어준다
                    }
            }

            if (flag) answer.insert(selected_user); // flag = 1이면 정답에 넣어준다. selected_user.size() == 4 등으로도 체크는 가능
    } while(next_permutation(selection.begin(), selection.end())); // selection을 다음 순서의 순열값으로 재생성

    return answer.size();
}

int main() {
        cout << solution({"frodo", "fradi", "crodo", "abc123", "frodoc"}, {"fr*d*", "*rodo", "******", "******"}) << "\n";

        return 0;
}

//
// 코멘트:
//
// 전에 풀었는데 파이썬이라 C++로 다시 풀었다
//
// 생각보다 푸는데 오래 걸렸다
//
// 풀고나니 엄청 어려운 문제는 아닌데
//
// 난관을 하나하나 어떻게 헤쳐나갈지가 관건인 문제인 듯
//
// next_permutation 함수를 처음 써봤다
//
// 파이썬만 못하지만 유용하게 쓸 것 같다
//