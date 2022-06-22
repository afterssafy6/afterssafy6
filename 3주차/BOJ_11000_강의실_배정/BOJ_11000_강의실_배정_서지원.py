# 2022.06.20

import heapq

# 입력
N = int(input())

box = []
for _ in range(N):
    S, T = map(int, input().split())
    box.append([S, T])

print(box)
# 순서대로 정렬
box.sort()
print(box)

# rooms : 강의실 갯수 세어줄거임
rooms = []
heapq.heappush(rooms, box[0][1])

for i in range(1, N):
    if box[i][0] < rooms[0]:  # 현재 회의실 끝나는 시간보다 다음 회의 시작시간이 빠르면
        heapq.heappush(rooms, box[i][1])  # 새로운 회의실 개설
    else:
        heapq.heappop(rooms)  # 새로운 회의로 시간 변경을 위해 pop후 새 시간 push
        heapq.heappush(rooms, box[i][1])

print(len(rooms))