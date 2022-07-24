def solution(s):
    answer = []
    s = s.strip('{}').split('},{')

    s.sort(key=lambda x: len(x))

    for ss in s:
        ss=ss.split(',')
        for sss in ss:
            if sss not in answer:
                answer.append(sss)

    return list(map(int, answer))

print(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"))
