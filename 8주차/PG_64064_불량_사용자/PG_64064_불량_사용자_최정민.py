# 모든 경우의 수를 구하고 맞는지 확인하는 방법

from itertools import permutations

def solution(user_id, banned_id):
    n = len(user_id)
    m = len(banned_id)

    def check(target_list):
        t = len(target_list)

        for i in range(t):
            if len(target_list[i]) != len(banned_id[i]):
                return False

            b = len(banned_id[i])

            for k in range(b):
                if target_list[i][k] == banned_id[i][k] or banned_id[i][k] == '*':
                    continue
                else:
                    return False

        return True


    answer=[]

    for com in permutations(user_id, m):
        if check(com):  # 밴 사용자가 맞는지
            users=set(com)
            if users not in answer:
                answer.append(users)

    return len(answer)


print(solution(["frodo", "fradi", "crodo", "abc123", "frodoc"],	["fr*d*", "abc1**"]))
