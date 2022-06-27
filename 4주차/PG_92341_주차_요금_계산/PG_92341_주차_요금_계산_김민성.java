import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        int basicTime = fees[0];
        int basicPrice = fees[1];
        int unitTime = fees[2];
        int unitPrice  = fees[3];

        HashMap<String, ArrayList<String>> record = new HashMap<>();
        // hashMap 초기화

        for(String s: records) {
            String sArr[] = s.split(" ");
            record.put(sArr[1], new ArrayList<>());
        }

        for(String s: records) {
            String sArr[] = s.split(" ");
            record.get(sArr[1]).add(sArr[0] + " " + sArr[2]);
        }
        // 순서를 맞출 배열
        Object mapKey[] = record.keySet().toArray();
        Arrays.sort(mapKey);
        answer = new int[mapKey.length];

        for(Map.Entry<String, ArrayList<String>> map :record.entrySet()) {
            // 홀수면 23:59분에 출차
            if(map.getValue().size() % 2 != 0) {
                record.get(map.getKey()).add("23:59 OUT");
            }

            for(int i = 0; i < mapKey.length; i++) {
                if(mapKey[i].equals(map.getKey())) {
                    int rst = basicPrice;
                    rst += cal(map.getValue(), basicTime, unitTime, unitPrice);
                    answer[i] = rst;
                    break;
                }
            }

        }


        return answer;
    }

    static double cal(ArrayList<String> list, int basicTime, int unitTime, int unitPrice) {
        double totalPrice = 0.0;
        double totalTime = 0;
        for(int i = 0; i < list.size(); i += 2) {
            String out[] = list.get(i+1).split(" ");
            String in[] = list.get(i).split(" ");

            String outTime[] = out[0].split(":");
            String inTime[] = in[0].split(":");
            // 60으로 변경해서
            if(outTime[1].equals("00")) {
                outTime[0] = Integer.toString(Integer.parseInt(outTime[0]) - 1);
                outTime[1] = "60";
            }

            // 계산
            totalTime += (Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0])) * 60;
            totalTime += Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);

        }
        // 올림처리
        totalPrice = Math.ceil((totalTime - basicTime) / unitTime) * unitPrice;
        // basictime이하이면 기본요금
        if(totalTime <= basicTime) {
            return 0;
        }

        return totalPrice;
    }
}