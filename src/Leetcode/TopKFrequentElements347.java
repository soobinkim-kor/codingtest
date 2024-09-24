package Leetcode;

import java.util.*;

public class TopKFrequentElements347 {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<HashMap<Integer,Integer>> hashMaps = new PriorityQueue<>((o1, o2) -> {
            Map.Entry<Integer, Integer> o1Key = o1.entrySet().iterator().next();
            Map.Entry<Integer, Integer> o2Key = o2.entrySet().iterator().next();
            return o2Key.getValue() - o1Key.getValue();
        });
        // 1. 각 숫자의 빈도를 저장할 HashMap 생성
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. 빈도를 기준으로 우선순위 큐 (최소 힙) 생성
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue() // 빈도가 낮은 것부터 오도록 정렬
        );

        // 3. HashMap의 각 엔트리를 우선순위 큐에 삽입
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) { // 큐의 크기가 k보다 커지면 최소값 제거
                pq.poll();
            }
        }

        // 4. 결과를 저장할 배열 생성
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = Objects.requireNonNull(pq.poll()).getKey();
        }

        return result;
    }
}
