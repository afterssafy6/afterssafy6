from heapq import heappop, heappush
import sys
input=sys.stdin.readline

N=int(input())
arr=[]
for _ in range(N):
    arr.append(list(map(int, input().split())))

arr.sort(key=lambda x: x[0]) #빨리 시작하는 강의부터 고려

q=[]
heappush(q, 0)
for a in arr:
    if q[0]<=a[0]:
        heappop(q)
        heappush(q, a[1])
    else: heappush(q, a[1]) #끝나는 시간 저장

print(len(q))