import sys

N = int(input())

total = 0          # 산술 평균용
num_box = []
dict_nums = {}

for _ in range(N):
    new_num = int(sys.stdin.readline())
    total += new_num
    if new_num not in num_box:
        dict_nums[new_num] = 1
    elif new_num in num_box:
        dict_nums[new_num] += 1
    num_box.append(new_num)

arr_box = sorted(num_box)   # 순서대로 정렬
cal_avg = total/N   # 산술 평균
if cal_avg >-0.5 and cal_avg<0:
    cal_avg = 0
center_num = N//2   # 중앙값
# 최빈값
# freq_nums = 최빈값
candidate = list(dict_nums.values())
pre_freq = max(candidate)

# 수 중복 좀 줄여주려고
only_box = []
for n in arr_box:
    if n in only_box:
        pass
    elif n not in only_box:
        only_box.append(n)

# print(only_box)

if candidate.count(pre_freq) > 1 :
    temp = []
    for n in only_box:
        if dict_nums[n] == pre_freq and temp == []:
            temp.append(n)
        elif dict_nums[n] == pre_freq and temp != []:
            freq_num = n
            break
elif candidate.count(pre_freq) == 1:
    for n in only_box:
        if dict_nums[n] == pre_freq:
            freq_num  = n
            break


# print(arr_box)
print('{:.0f}'.format(cal_avg))   # 산술평균
print('{}'.format(arr_box[center_num]))   # 중앙값
print('{}'.format(freq_num))   # 최빈값
print('{}'.format(arr_box[-1]-arr_box[0]))   # 범위
