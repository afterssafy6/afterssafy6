n=int(input())

number=list(map(int, input().split()))

# print(number)
if(n==1):
    number.sort()
    print(sum(number[0:5]))
else:
    one = min(number)*(5*n**2-16*n+12)

# print("one", one)

    min_two=number[4]+number[5]
    for i in range(4):
        if(i==0):
            for j in range(1, 5):
                if(min_two>number[i]+number[j]):
                    min_two = number[i] + number[j]
        if (i == 1):
            for j in [2, 3, 5]:
                if (min_two > number[i] + number[j]):
                    min_two = number[i] + number[j]
        if (i == 2):
            for j in [4, 5]:
                if (min_two > number[i] + number[j]):
                    min_two = number[i] + number[j]
        if (i == 3):
            for j in [4, 5]:
              if (min_two > number[i] + number[j]):
                  min_two = number[i] + number[j]
# print(min_two)
    two = (min_two)*(8*n-12)

# print("two", two)

    min_three=number[3]+number[4]+number[5]
    for i in range(4):
        if(i==0):
            for j in [[4, 3],[4, 2],[2,1],[1,3]]:
                if(min_three>number[i]+number[j[0]]+number[j[1]]):
                    min_three = number[i] +number[j[0]]+number[j[1]]
        if (i == 1):
            for j in [[2,5],[5,3]]:
                if (min_three >number[i]+number[j[0]]+number[j[1]]):
                    min_three = number[i]+number[j[0]]+number[j[1]]
        if (i == 2):
            for j in [[5,4]]:
                if (min_three >number[i]+number[j[0]]+number[j[1]]):
                    min_three = number[i]+number[j[0]]+number[j[1]]
        if (i == 3):
            for j in [[4, 5]]:
                if (min_three >number[i]+number[j[0]]+number[j[1]]):
                    min_three = number[i]+number[j[0]]+number[j[1]]
# print(min_three)
    three = min_three*4

    # print(min(number), min_two, min_three)
    # print(one, two, three)
# print("three", three)

    print(one+two+three)