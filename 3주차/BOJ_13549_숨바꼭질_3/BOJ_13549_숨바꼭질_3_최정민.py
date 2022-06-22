import heapq

INF = int(1e9)

start, end = map(int, input().split())

distance = [INF] * (10**5+1)

# 시간이 가중치

def dijkstra(start, end):
    q = []

    heapq.heappush(q, (0, start)) #시간, 시작점
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist:
            continue

        for i in range(3): #3방향 탐색 독특하게 하신분??
            if i==0:
                cost = dist +1 #1초후
                next=now-1 #x-1로 이동

            elif i==1:
                cost = dist +1 #1초후
                next=now+1

            else:
                next = now*2
                cost=dist

            if next>10**5 or next<0:
                continue

            if now == end:
                return dist

            if cost < distance[next]:
                distance[next] = cost
                heapq.heappush(q, (cost, next))

print(dijkstra(start, end))