//
// BOJ - 2731
// 1379와 세제곱
//
// 난이도: P5
// 제출자: wasuphj @Github
// 
#include <iostream>
using namespace std;

using ll = unsigned long long;

ll ten[11], ans;

ll mul(ll a, ll b, ll max_digit) {
    int apos = 0, bpos = 0; // a의 자리수와 b의 자리수 저장 
    ll adigit, bdigit; // a의 자리수 값과 b의 자리수 값을 저장
    ll ret = 0;

    //  123
    // x123
    // ----
    // 1234
    while (b / ten[bpos]) {  // b의 끝자리부터 처음까지 계산
        // b에서 현재 계산할 자리수만 빼냄
        bdigit = b / ten[bpos];
        bdigit %= 10;

        // a 자리수 0으로 초기화
        apos = 0;
        while (bpos + apos <= max_digit) {
            // a에서 현재 계산할 자리수만 빼냄
            adigit = a / ten[apos];
            adigit %= 10;

            // b의 자리수 값과 a의 자리수 값을 곱하고 10자리 보정 후 더함
            ret += bdigit * adigit * ten[bpos + apos] % ten[max_digit];
            ret %= ten[max_digit]; // 최대 자리수를 넘는 값은 버림
            apos++;
        }
        bpos++;
    }

    return ret % ten[max_digit];
}

int dfs(ll N, int L, int lev, ll buf) {
    // L 자리를 넘으면 전부 탐색한 것
    if (lev >= L) {
        ans = buf;
        return 1;
    }

    ll tmp, basis;

    for (ll i = 0; i < 10; i++) {
        basis = buf + i * ten[lev]; // 먼저 기존 값에 자리수 * i(0~9)를 곱해준다 (basis)
        tmp = mul(basis, basis, lev + 1); // 필요한 자리수까지만 제곱 계산해준다
        tmp %= ten[lev + 1]; // 현재 자리수 까지만 뗀다 (문제 없나? 현재 자리수 위는 현재 자리수의 계산에 영향을 미치지 않음)
        tmp = mul(tmp, basis, lev + 1); // 세제곱진행
        tmp %= ten[lev + 1]; // 현재 자리수 까지만 뗀다 
        if (N % ten[lev + 1] != tmp) continue; // 세제곱 계산값의 현재 자리수와 N의 현재 자리수를 계산해 일치하는지 확인
        if (dfs(N, L, lev + 1, basis)) return 1; // 어차피 여기서는 떼지 않기 때문에 괜찮다
    }

    return 0;
}

int main() {
    cin.tie(NULL);
    ios::sync_with_stdio(false);

    int T;
    cin >> T;

    ten[0] = 1;
    for (int i = 1; i <= 10; i++)
        ten[i] = ten[i - 1] * 10;

    while (T--) {
        ans = 0;

        ll N = 0;
        cin >> N;

        int L = 0; // 입력된 숫자의 자리수 저장
        ll tmp = N;

        // 자리수 카운트
        while (tmp) {
            tmp /= 10;
            L++;
        }

        dfs(N, L, 0, 0);

        cout << ans % ten[L] << "\n";
    }
    return 0;
}


//
// 코멘트:
//
// 처음 접근할때 0-9까지를 미리 계산하고 dfs 실행시마다 캐리를 넘겨줘서 각 자리수에 대해 계산하면 될줄 알았다
// 
// 해봤더니 1의 자리 말곤 하나도 맞지 않았다
//
// 고민하다 결국 블로그 답을 보고 이해하는 걸로 진행했다
//
// 남의 코드라 이해하는데 시간이 걸렸다
// 
// 아직 플레 레벨은 안되는듯 ㅠㅠ
//