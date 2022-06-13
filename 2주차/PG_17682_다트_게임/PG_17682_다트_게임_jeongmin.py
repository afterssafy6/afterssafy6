import re

def solution(dartResult):
    bonus = {'S' : 1, 'D' : 2, 'T' : 3}
    option = {'' : 1, '*' : 2, '#' : -1}
    # p = re.compile('\d+[SDT][*#]?') #['1S', '2D*', '3T']
    p = re.compile('(\d+)([SDT])([*#]?)') #[('1', 'S', ''), ('2', 'D', '*'), ('3', 'T', '')]
    dart = p.findall(dartResult)
    for i in range(len(dart)):
        if dart[i][2] == '*' and i > 0:
            dart[i-1] *= 2
        dart[i] = int(dart[i][0]) ** bonus[dart[i][1]] * option[dart[i][2]]

    answer = sum(dart)
    return answer