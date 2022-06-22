n = int(input())
h = list(map(int, input().split()))

arrow_height = [0] * 1000001
answer = 0

for i in range(n):
    if arrow_height[h[i]] == 0:
        arrow_height[h[i] - 1] += 1
        answer += 1
    else:
        arrow_height[h[i]] -= 1
        arrow_height[h[i] - 1] += 1

print(answer)
