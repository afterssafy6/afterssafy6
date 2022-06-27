# 2022.06.27



def solution(id_list, report, k):
    members = {}
    malo = {}
    for id in id_list:
        members[id] = []
        malo[id] = 0
    print(members)

    # 불량 사용자 : malo에 담아주자
    for info in report:
        subject, object = info.split()
        if object not in members[subject]:
            members[subject].append(object)
            malo[object] += 1
    print(members)
    print(malo)

    ban = []    #금지당한 유저 모아보자
    for user in id_list:
        if malo[user] >= k:
            ban.append(user)
    print(ban)
    answer = []
    for id in id_list:
        cnt = 0
        for member in members[id]:
            if member in ban:
                cnt += 1
        answer.append(cnt)
    print(answer)
    return answer




solution(["con","ryan"],["ryan con","ryan con","ryan con","ryan con"],3)