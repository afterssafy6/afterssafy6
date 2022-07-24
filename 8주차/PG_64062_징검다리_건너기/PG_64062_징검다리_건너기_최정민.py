# 이진탐색
# 브루트포스로 하기엔 시간복잡도가 안 되는 문제
# 최적의 답을 찾는 문제


def solution(stones, k):
    answer = 0

    left = 1
    right = 200000000
    n = len(stones)

    def check(num):
        cnt = 0
        for i in range(n):
            if stones[i] < num:
                cnt += 1
                if cnt >= k:
                    return False
            else:
                cnt = 0

        return True

    while left <= right:
        mid = (left + right) // 2
        if check(mid):
            left = mid + 1
            answer = mid
        else:
            right = mid - 1


    return answer

print(solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3))
