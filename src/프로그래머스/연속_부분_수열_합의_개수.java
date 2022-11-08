package 프로그래머스;

import java.util.*;

public class 연속_부분_수열_합의_개수 {
    // 최대길이에서 시작해서 한 칸씩 빼고, 모든 칸에 대해서 합 재기?
    // 일단 완전탐색 ㄱㄱ
    public static void main(String[] args) {
        int[] test = new int[]{7,9,1,1,4};
        System.out.println(solution(test));
    }

    public static int solution(int[] elements){
        int total = 0;
        for(int e : elements){
            total+=e;
        }
        Set<Integer> set = new HashSet<>();
        set.add(total);
        // 뺄 수열의 길이
        for(int length=1;length<elements.length;length++){
            //System.out.println("뺄 수열의 길이: "+length);
            //
            for(int i = 0;i<elements.length;i++){
                //System.out.println("시작 인덱스: "+i+" 끝 인덱스: "+(i+length));

                // 이거 만큼 빼야함
                int out = 0;
                for(int j=i;j<i+length;j++){
                    if(j>=elements.length){
                        out+=elements[j-elements.length];
                        continue;
                    }
                    //System.out.print(j+" ");
                    out+=elements[j];
                    //System.out.println();
                }
                //System.out.println("뺄 수열의 합: "+out);
                set.add(total-out);
            }
        }
        return set.size();
    }
}
