//
// PG - 60057
// 문자열 압축
//
// 난이도: L2
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <string>
#include <vector>

#pragma warning(disable:4996)

using namespace std;

int solution(string s) {
	int N = s.length(); // 문자열 길이
	int answer = N; // 최대는 문자열 길이

	// 반복 토큰 길이는 1부터 문자열 길이의 절반까지만 (절반을 넘으면 반복 불가)
	for (int k = 1; k <= N / 2; k++) {
		string buf;
		int i = 0;

		// 문자열 끝까지 반복
		while (i < N) {
			string origin = s.substr(i, k); // 비교 원본
			int cnt = 1;

			// 만약 토큰 길이만큼도 안남았으면 그냥 남은 문자열을 붙이고 끝낸다
			if (i >= N - k) {
				buf += s.substr(i, N - i);
				break;
			}

			string target = s.substr(i + k, k); // 비교 대상

			// 문자열 끝까지 반복
			while (i < N) {
				target = s.substr(i + k, k); // 비교 대상
				i += k; // k 만큼 비교 원본의 시작지점(인덱스) 증가
				if (origin != target) break; // 일치하지 않으면 탈출
				cnt++; // 일치하면 반복이므로 카운트 증가
			}

			// 만약 반복이라면 반복 형식에 맞게 버퍼에 추가, 반복이 아니면 비교 원본 문자열만 버퍼에 추가
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
// 코멘트:
// 
// 테스트 케이스가 안되면 생각하는 연습...
//
