from heapq import heappush, heappop

V, E = map(int, input().split())
graph = [[] for _ in range(V + 1)]
for _ in range(E):
    v1, v2, d = map(int, input().split())
    graph[v1].append([v2, d])
    graph[v2].append([v1, d])

def prim(start, weight):
    visited = [0] * (V + 1)
    q = [[weight, start]]
    dist = 0
    cnt = 0
    while cnt < V:
        d, v2 = heappop(q)

        if visited[v2]:
            continue
        visited[v2] = True
        dist += d
        cnt += 1
        for i in graph[v2]:
            heappush(q, [i[1], i[0]]) # 힙에 넣어줌
    return dist

print(prim(1, 0))