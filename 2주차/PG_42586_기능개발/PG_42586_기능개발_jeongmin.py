def solution(progresses, speeds):
    answer = []
    day = []

    for i in range(len(speeds)):
        remain = 100 - progresses[i]
        add = remain // speeds[i]
        if remain % speeds[i]:
            add += 1
        day.append(add)

    cnt = 1
    maxv = day[0]
    for i in range(1, len(day)):
        if maxv < day[i]:
            answer.append(cnt)
            maxv = day[i]
            cnt = 1
        else:
            cnt += 1
    answer.append(cnt)

    return answer