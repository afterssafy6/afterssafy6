## union-find 거꾸로 + dict
import sys
sys.setrecursionlimit(10000000)

def solution(k, room_number):
    answer = []
    parent = {} # 다음 방값으로 계속 연결

    def find(x):
        if x not in parent:
            parent[x]=x+1
            return x

        parent[x]=find(parent[x])+1
        return parent[x]-1

    for num in room_number:
        empty=find(num)
        answer.append(empty)

    return answer


print(solution(10, [1, 3, 4, 1, 3, 1]))
