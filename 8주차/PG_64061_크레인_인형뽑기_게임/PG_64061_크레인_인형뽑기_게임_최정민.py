def solution(board, moves):
    answer = 0
    r=len(board)
    c=len(board[0])
    basket=[]

    #크레인에서 집기
    for m in moves:
        m-=1
        for nr in range(r):
            if board[nr][m]!=0:
                if basket and basket[-1]==board[nr][m]: #일치하는 거 있으면 터지기 #쌓을때마다 끝부분 비교
                    basket.pop()
                    answer+=2
                else:
                    basket.append(board[nr][m])#바구니에 쌓기
                board[nr][m]=0
                break

    return answer

print(solution([[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]], [1,5,3,5,1,2,1,4]))

## 다른 방법 : 0이 아닌 값의 인덱스 저장해놓기