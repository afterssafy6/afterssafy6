from collections import deque

n, m = map(int, input().split())

graph=[]

for _ in range(n):
    graph.append(list(input()))

def bfs():
    q = deque()
    q.append([1, 0, 0, 0])  # dist, r, c, 벽
    visited=set()
    visited.add((0, 0, 0))

    while q:
        dist, r, c, wall_cnt = q.popleft()

        if r==n-1 and c==m-1:
            return dist

        for dr, dc in [-1, 0], [1, 0], [0, 1], [0, -1]:
            nr=r+dr
            nc=c+dc
            if 0<=nr<n and 0<=nc<m:
                if graph[nr][nc]=='1' and wall_cnt==0 and (nr, nc, wall_cnt+1) not in visited: #벽을 한 번은 부숴도 됨
                    q.append([dist+1, nr, nc, wall_cnt+1])
                    visited.add((nr, nc, wall_cnt+1))
                elif graph[nr][nc]=='0' and (nr, nc, wall_cnt) not in visited:
                    q.append([dist+1, nr, nc, wall_cnt])
                    visited.add((nr, nc, wall_cnt))

    return -1

print(bfs())