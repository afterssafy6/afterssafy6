# 백준
# 크게 만들기
# 그리디
# N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오.

# 아이디어 : 스택 두 개 쌓기...?

N, K = map(int, input().split())
origin = input()
# 파이썬은 string 안의 숫자 대소 비교 가능합니당...
# 그래서 굳이 int를 붙이지 않고 string으로 둠

result = []
cnt = K   # cnt : 카운트용

for n in range(N):
    while cnt > 0 and result and result[-1] < origin[n]:
        result.pop()   # 마지막거 대소비교했을 때 result에 들어간 게 더 작으니까
        cnt -= 1
    result.append(origin[n])

answer = ''.join(result[:N-K])
print(answer)