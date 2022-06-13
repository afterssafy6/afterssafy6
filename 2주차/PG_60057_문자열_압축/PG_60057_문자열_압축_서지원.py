def solution(s):
    max_len = len(s)
    ans = []
    if len(s) == 1:
        return 1
    for idx in range(1,max_len):
        inst = []
        for i in range(0,max_len,idx):
            a = s[i:i+idx]
            inst.append(a)
        # 같은 거 카운팅
        result = ''
        cnt = 1
        for n in range(len(inst)-1):
            if inst[n] == inst[n+1]:
                cnt += 1
            else:
                if cnt == 1:
                    result += inst[n]
                else:
                    result += str(cnt)
                    result += inst[n]
                    cnt = 1
        if cnt != 1:
            result += str(cnt)
        result += inst[-1]
        ans.append(len(result))
        answer = min(ans)
    return answer