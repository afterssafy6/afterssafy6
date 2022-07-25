# 2022.06.27
# 주차 요금 계산
import math

def solution(fees, records):
    # fees = [기본시간(분), 기본요금(원), 단위시간(분), 단위요금(원)]
    # records = ["시각 차량번호 내역"]
    default_t = fees[0]
    default_f = fees[1]
    unit_t = fees[2]
    unit_f = fees[3]

    # come은 들어온 차량
    come = {}
    total_fee = {}
    for record in records:
        time, num, memo = record.split()
        if memo == "IN":
            # HH:MM
            time = int(time[:2])*60 + int(time[3:5])
            come[num] = time
            if num not in total_fee.keys():
                total_fee[num] = []
        elif memo == "OUT":
            time = int(time[:2])*60 + int(time[3:5])
            used = time - come[num]
            total_fee[num].append(used)
            come[num] = -1
    print(come)
    print(total_fee)

    for car in come.keys():
        if come[car] == -1:
            if sum(total_fee[car]) <= default_t:
                total_fee[car] = default_f
            else:
                extra_f = unit_f * math.ceil((sum(total_fee[car])-default_t)/unit_t)
                total_fee[car] = default_f + extra_f
        else:
            total_fee[car].append(1439 - come[car])
            if sum(total_fee[car]) <= default_t:
                total_fee[car] = default_f
            else:
                extra_f = unit_f * math.ceil((sum(total_fee[car])-default_t)/unit_t)
                total_fee[car] = default_f + extra_f
    # print(total_fee)
    arr_fee = sorted(total_fee.items())
    # print(arr_fee)
    answer = []
    for idx in range(len(arr_fee)):
        answer.append(arr_fee[idx][1])
    # print(answer)
    return answer





solution([180, 5000, 10, 600],["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"])