def solution(progresses, speeds):
    rest_days = []   # 남은 일자를 계산해서 담을 곳
    # 계산식 : (100 - 진행된 업무량(progress)) / 엄무 속도(speed)
    # main : 계산식의 몫
    # side : 계산식의 나머지
    # 나머지가 0보다 크다면 다음 날까지 넘겨서 main에 반영하고,
    # 최종 main을 rest_days에 넣어주는 방식
    for x, y in zip(progresses, speeds):
        main = (100-x)//y
        side = (100-x)%y
        if side > 0:
            main += 1
        rest_days.append(main)

    # 이제 return을 돌릴 차례
    # answer : 정답을 담을 곳
    # stack : 한 번에 나갈 업무들을 담는 곳
    # standard : 기준점(첫번째 업무의 작업 일자)
    answer = []
    stack = []
    for idx in range(len(rest_days)):
        if idx < len(rest_days)-1:   # 마지막 인덱스가 아닐 때
            if stack == []:  # 만약 stack이 비었다면 = 기준점(standard)도 아직 없음(가장 처음 시작할 때 때문에 넣어줌)
                stack.append(rest_days[idx])
                standard = stack[0]  # 기준점 지정
            else:   # stack이 비지 않았을 때
                if standard >= rest_days[idx]:   # 현재 작업일이 기준보다 더 짧다면
                    stack.append(rest_days[idx])  # stack에 넣어 반영해줌
                else:  #현재 작업일이 기준점보다 더 길다면
                    answer.append(len(stack))   # 지금까지의 stack 길이를 answer에 넣어주고
                    stack = []                  # stack을 비워주고
                    stack.append(rest_days[idx])  # 다시 stack에 현재 인덱스의 값을 넣어주고 standard로 지정
                    standard = stack[0]
        else:   # 마지막 인덱스일 때
            if stack == []:
                answer.append(1)
            else:
                if stack == []:
                    answer.append(1)
                else:
                    if standard >= rest_days[idx]:
                        stack.append(rest_days[idx])
                        answer.append(len(stack))
                    else:
                        answer.append(len(stack))
                        answer.append(1)
    return answer

# p = [93, 30, 55]
# s = [1, 30, 5]
#
# result = solution(p, s)
# print(result)