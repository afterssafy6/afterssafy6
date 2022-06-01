//
// BOJ - 1013 
// Contact
//
// 난이도: G5
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <regex>
#include <string>
using namespace std;

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	regex ex("(100+1+|01)+"); // 문제에 주어진 표현식 그대로 사용

	int T;
	cin >> T;

	string buf; // 입력 문자열을 받을 버퍼
	while (T--) {
		cin >> buf; // 확인할 문자열을 입력받고
		cout << (regex_match(buf, ex) ? "YES" : "NO") << "\n"; // 매칭되면 YES, 아니면 NO를 출력
	}

	return 0;
}

//
// 코멘트:
//
// 티어 산정이 잘못된 문제
// 
