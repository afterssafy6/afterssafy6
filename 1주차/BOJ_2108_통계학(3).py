import sys
from collections import Counter

N = int(sys.stdin.readline())
num_box = []
total = 0
for i in range(N):
    new_num = int(sys.stdin.readline())
    total += new_num
    num_box.append(new_num)

num_box.sort()

cal_avg = total/N   # 산술 평균
# 산술 평균에서 -0 방지용
if cal_avg >-0.5 and cal_avg<0:
    cal_avg = 0

nums_freq = Counter(num_box).most_common()

print('{:.0f}'.format(cal_avg))   #최빈값
print(num_box[N//2])   # 중앙값

# 최빈값
if len(nums_freq) > 1:
    if nums_freq[0][1] == nums_freq[1][1]:
        print(nums_freq[1][0])
    else:
        print(nums_freq[0][0])
else:
    print(nums_freq[0][0])

print(num_box[-1] - num_box[0])   # 범위