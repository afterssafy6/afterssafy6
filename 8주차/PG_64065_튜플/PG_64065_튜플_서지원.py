## 튜플
## s가 문자열로 들어오는군...


def solution(s):
    mylist = []   # 문자열로 들어오는 s를 mylist라는 숫자 리스트로 바꿀거임
    inst_list = []   # 사실 이 줄 없어도 되긴 함(임시로 담아놓는 역할임)
    complete_number = ''   # 완결된(?) 숫자를 만들어주기 위한 string

    for i in range(1, len(s)-1):  #............. 맨 처음과 끝은 필요없어서 1부터 마지막 직전 인덱스까지만 돌거임
        if s[i] == '{':           #............. 1. '{'가 개행의 역할
            inst_list = []
        elif s[i] == '}':         #............. 4. '}' 하나의 인스턴트 리스트의 완결 의미
            inst_list.append(int(complete_number))
            complete_number = ''
            mylist.append(inst_list)
        elif s[i] == ',' and s[i+1] != '{':  #.. 3. 콤마가 많아서 구분좀 해줘야 했음... 여기 콤마는 숫자 완결 의미
            inst_list.append(int(complete_number))
            complete_number = ''
        elif s[i] == ',' and s[i+1] == '{':  #.. 여기 콤마는 의미 없음
            pass
        else:   #............................... 2. 숫자가 들어오는 대로 complete_number에 축적시켜준다.
            complete_number += s[i]

    mylist.sort(key=len)   # 길이순으로 정렬

    # [[2], [2, 1], [1, 2, 3], [1, 2, 4, 3]]
    # [[111], [20, 111]]

    answer = []
    for sublist in mylist:
        if answer == []:       # 만약 첫 시작인 경우
            answer.append(sublist[0])
        else:
            for k in answer:  # 현재 sublist에서 answer에 들어간 걸 다 빼면 하나만 남을테니까
                sublist.remove(k)
            answer.append(sublist[0])
    # print(answer)

    return answer

# solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")
# solution("{{20,111},{111}}")