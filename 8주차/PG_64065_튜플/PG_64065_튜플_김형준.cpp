//
// PG - 64065
// 튜플
//
// 난이도: L2
// 제출자: wasuphj @Github
// 

#include <iostream>
#include <string>
#include <vector>
#include <set>

using namespace std;

vector<int> solution(string s) {
	int sLen = s.size();

	int cnts[100000] = { 0 };
	set<int> items;

	string buf;
	for (int i = 0; i < sLen; i++) {
		if (s[i] == '{') continue;
		if (s[i] == '}' || s[i] == ',') {
			if (buf.empty()) continue;
			int item = stoi(buf);
			items.insert(item);
			cnts[item]++;
			buf = "";
			continue;
		}
		buf += s[i];
	}

	vector<int> answer(items.size());

	for (int item : items) {
		int cnt = cnts[item];
		answer[items.size() - cnt] = item;
	}

	return answer;
}

int main() {
	solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");

	return 0;
}

//
// 코멘트:
//
// 다른 알고리즘이나 문자열 문제 풀듯이 생각하면 어렵고
//
// 그냥 제일 단순하게 길이 N만큼 돌고
//
// set 이용하면 깔끔한 것 같다
//
