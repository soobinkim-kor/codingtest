package 외부코테;

import java.util.HashSet;

public class SmallestFirstOccurInteger {
    public int Solution(int[] A){
        HashSet<Integer> set = new HashSet<>();

        // 배열 A에 있는 양의 정수를 모두 HashSet에 저장
        for (int num : A) {
            if (num > 0) {
                set.add(num);
            }
        }

        // 1부터 순차적으로 set에 없는 가장 작은 정수를 찾음
        int smallestPositive = 1;
        while (set.contains(smallestPositive)) {
            smallestPositive++;
        }

        return smallestPositive;
    }
}
