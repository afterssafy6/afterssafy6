import sys

N = int(input())

total = 0          # 산술 평균용
num_box = []       # 숫자 담으려구
dict_nums = {}     # 최빈값용

for _ in range(N):
    new_num = int(sys.stdin.readline())
    total += new_num
    if new_num not in num_box:
        dict_nums[new_num] = 1
    elif new_num in num_box:
        dict_nums[new_num] += 1
    num_box.append(new_num)

num_box.sort()   # 순서대로 정렬
cal_avg = total/N   # 산술 평균
# 산술 평균에서 -0 방지용
if cal_avg >-0.5 and cal_avg<0:
    cal_avg = 0
center_num = N//2   # 중앙값

# 최빈값
cnt = 1
numbers = []
num_cnt = []
max_num = 0

for i in range(N):
    if i < N-1:
        if num_box[i] == num_box[i+1]:
            cnt += 1
        else:
            numbers.append(num_box[i])
            num_cnt.append(cnt)
            if cnt > max_num:
                max_num = cnt
            cnt = 1
    elif i == N-1:
        numbers.append(num_box[i])
        num_cnt.append(cnt)
        if cnt > max_num:
            max_num = cnt


idx = num_cnt.index(max_num)


try:
    second_idx = num_cnt.index(max_num, idx+1)
    idx = second_idx
except:
    pass

freq_num = numbers[idx]

print('{:.0f}'.format(cal_avg))   # 산술평균
print('{}'.format(num_box[center_num]))   # 중앙값
print('{}'.format(freq_num))   # 최빈값
print('{}'.format(num_box[-1]-num_box[0]))   # 범위