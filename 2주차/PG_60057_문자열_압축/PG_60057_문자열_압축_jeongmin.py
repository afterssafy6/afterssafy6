def solution(s):
    answer = 0
    n = len(s)
    st = []

    for i in range(1, (n // 2) + 1):  # 압축 갯수
        string = ''
        num = 1
        for j in range(0, n, i):  # 시작 인덱스
            if s[j:j + i] == s[j + i:j + 2 * i]:  # 압축이 될 때
                num += 1
            else:
                if num != 1:  # 압축 된 값을 문자열에 더할 때
                    string += str(num) + s[j:j + i]
                    num = 1

                else:  # 그냥 문자열에 더할 때
                    string += s[j:j + i]
        st.append(string)

    for s in st:
        if len(s) < n:
            n = len(s)

    answer = n

    return answer