n, k = map(int, input().split())
number=input()

#스택의 위의 값이 크면 살아남기

stack=[number[0]]
cnt=0
for i in range(1, n):
    while cnt<k and stack:
        if stack[-1]<number[i]:
            stack.pop()
            cnt+=1
        else:
            break
    stack.append(number[i])

ret=''
for s in stack[:n-k]:
    ret+=str(s)

print(ret)