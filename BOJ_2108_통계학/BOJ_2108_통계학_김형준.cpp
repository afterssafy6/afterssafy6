//
// BOJ - 2108
// 통계학
//
// 난이도: S3
// 제출자: wasuphj @Github
// 
#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
using namespace std;

#define MIN(a,b) (a < b ? a : b)
#define MAX(a,b) (a > b ? a : b)

vector<int> arr;
int count_arr[8001];

int main() {
	// 표준 입력 속도 증가 코드
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int N;
	cin >> N;

	long long sum = 0;
	int max_val = -4001;
	int min_val = 4001;
	for (int i = 0, n; i < N; i++) {
		cin >> n;
		arr.push_back(n); // 2. 중앙값 배열
		count_arr[n + 4000]++; // 3. 최빈값 배열
		sum += n; // 1. 산술평균용 합계
		min_val = MIN(min_val, n); // 4. 범위를 구하기 위한 최소값
		max_val = MAX(max_val, n); // 4. 범위를 구하기 위한 최대값
	}

	sort(arr.begin(), arr.end()); // 2. 중앙값을 위해 오름차순 정렬

  // 3. 최빈값을 구하기 위한 귀찮은 방법 (같을 경우 2번째 작은 수를 취하라는 조건때문)
	int max_freq_val = 0;
	int max_freq = 0;
	int max_freq_refresh_chance = 1;
	for (int i = 0; i <= 8000; i++)
	{
		if (max_freq < count_arr[i])
		{
			max_freq = count_arr[i];
			max_freq_val = i - 4000;
			max_freq_refresh_chance = 1;
		}
		else if (max_freq == count_arr[i])
		{
			if (max_freq_refresh_chance) {
				max_freq_refresh_chance = 0;

				max_freq_val = i - 4000;
			}
		}

		count_arr[i] += count_arr[i - 1];
	}

	double avg = (double)sum / (double)N;
	if (-0.5 <= avg && avg <= 0) avg = 0;
	cout << fixed << setprecision(0) << avg << "\n"; // 1. 산술평균

	cout << arr[(N - 1) / 2] << "\n"; // 2. 중앙값
	cout << max_freq_val << "\n"; // 3. 최빈값
	cout << max_val - min_val << "\n"; // 4. 범위

	return 0;
}

//
// 코멘트:
//
// 우습게알고 덤볐다가 큰코다쳤던 문제
// 
// 특히 3. 최빈값 조건때문에 곤란했는데 좀 더 좋은 코드였음 좋았는데
//
