package 프로그래머스;

import java.util.*;

public class 야간전술보행 {
    public static void main(String[] args) {
        int[][] scope = {{5,8},{3,4}};
        int[][] times = {{2,5},{4,3}};
        System.out.println(solution(10,scope,times));
    }
    public static int solution(int distance, int[][] scope, int[][] times) {
        int answer = 0;
        HashMap<Integer,int[]> guards = new HashMap<>();
        for(int i=0;i<scope.length;i++){
            guards.put(i+1,scope[i]);
        }
        int a = 1_000_000;
        System.out.println(a);
//        Set set = guards.keySet();
//        Collections.sort(set,(g1,g2)->guards.get(g1)[0]-guards.get(g2)[1]);
        Arrays.sort(scope, Comparator.comparingInt(o -> o[0]));
        print(scope);
        return answer;
    }

    public static void print(int[][] data){
        for(int[] d : data){
            for(int a : d){
                System.out.print(a+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
