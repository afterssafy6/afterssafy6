import java.util.stream.IntStream;
import java.util.*;

class Solution {
    public int solution(String dartResult) {
        char[] dartArr = dartResult.toCharArray();
        int score[] = new int[3];
        int order = 0;
        for(int i =0; i < dartArr.length; i++) {
            if(dartArr[i] == 'S') {
                if(dartArr[i-1] == '0' && i-2 >= 0 && dartArr[i-2] == '1') {
                    score[order] = 10;
                } else {
                    score[order] = Character.getNumericValue(dartArr[i - 1]);
                }
                order++;
            } else if(dartArr[i] == 'D') {
                if(dartArr[i-1] == '0' && i-2 >= 0 && dartArr[i-2] == '1') {
                    score[order] = (int) Math.pow(10, 2);
                } else {
                    score[order] = (int) Math.pow(Character.getNumericValue(dartArr[i - 1]), 2);
                }
                order++;
            } else if(dartArr[i] == 'T') {
                if(dartArr[i-1] == '0' && i-2 >= 0 && dartArr[i-2] == '1') {
                    score[order] = (int) Math.pow(10, 3);
                } else {
                    score[order] = (int) Math.pow(Character.getNumericValue(dartArr[i - 1]), 3);
                }
                order++;
            }
        }

        order = 2;
        for(int i = dartArr.length - 1; i >= 0; i--) {
            if(dartArr[i] == '*' || dartArr[i] == '#') {
                if(dartArr[i] == '*') {
                    if(order == 0) {
                        score[order] *= 2;
                    } else {
                        score[order] *= 2;
                        score[order - 1] *= 2;
                    }
                } else if(dartArr[i] == '#') {
                    score[order] *= -1;
                }
            }
            if(dartArr[i] == 'S' || dartArr[i] == 'D' || dartArr[i] == 'T') {
                order--;
            }

        }



        return IntStream.of(score).sum();
    }
}