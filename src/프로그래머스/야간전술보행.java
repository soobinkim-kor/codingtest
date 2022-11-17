package 프로그래머스;

import java.util.*;

public class 야간전술보행 {
    // 정렬하면 어렵지 않을 듯
    // SCOPE 와 TIME 을 기반으로 근무 상태 확인
    // 거리 + 휴식 시간의 나머지 로
    public static void main(String[] args) {
        int[][] scope = {{5,8},{3,4}};
        int[][] times = {{2,5},{4,3}};

        int[][] scope2 = {{7,8},{4,6},{11,10}};
        int[][] times2 = {{2,2},{2,4},{3,3}};
        System.out.println(solution(10,scope,times));
        System.out.println(solution(10,scope2,times2));
    }
    public static int solution(int distance, int[][] scope, int[][] times) {
        int answer = 0;
        HashMap<Integer,int[]> guards = new HashMap<>();
        for(int i=0;i<scope.length;i++){
            guards.put(i,scope[i]);
        }

        // 각 경비원들의 감시 구간을 시작 순으로 정렬
        ArrayList<Integer> sortedTimes = sortArray(guards);

        // 각 경비병의 시작 순으로 정렬된 번호
        for(int guard : sortedTimes){


            int current = scope[sortedTimes.get(guard)][0];

            int[] guardData = guards.get(guard);

            // 감시 시작 구간
            int guardStart = guardData[0];
            // 감시 끝 구간
            int guardEnd = guardData[1];

            // 근무 시간
            int guardActive = times[guard][0];
            // 휴식 시간
            int guardRest = times[guard][1];


        }


        return distance;
    }

    // 정해진 시간에, 그 경비병이 휴식하지 않는 시간이 있는지 확인하는 메소드
    public static int isPresent(int[] guardData, int guard, int[][] times, int current){
        // ex current 10초, 경비병 휴식 [근무: 2초, 휴식: 4초] -> 10/6 = 1세트 , 나머지 = 4 = 현재 시간에 어느 위치에 있는지.
        // 나머지 - 근무 > 0 -> 근무 시간 지난 거. 휴식 중
        // 나머지 - 2 = 경비병 근무 시작 까지 남은 시간
        // 경비병 근무 시작 까지 남은 시간 <= 0 현재 근무 중 -> 잡힘 -> false
        // 나머지 - 2 > 0 이면 (나머지-2) 초 뒤에 근무 시작 (이땐 current 까진 온 거임)
        // 그 안에 화랑이가 통과할 수 있으면 true -> 화랑이가 통과하는 데 걸리는 시간< (나머지-2) 여야 함
        // 아니면 false
        // current 일때 경비는

        // 감시 시작 구간
        int guardStart = guardData[0];
        // 감시 끝 구간
        int guardEnd = guardData[1];
        // 근무 시간
        int guardActive = times[guard][0];
        // 휴식 시간
        int guardRest = times[guard][1];

        // 경비원이 (휴식+근무를) 몇 세트 했는지
        int startRest = current/(guardActive+guardRest);
        // 경비원이 휴식을 시작하는 시간
        int time = current%(guardActive+guardRest);

        if(time-guardRest<=0){
            return current+Math.abs(time-guardRest);
        }




        // 화랑이가 지나가야 하는 시간대
        int start = current;
        int end = guardEnd;

        return 1;
    }

    public static ArrayList<Integer> sortArray(HashMap<Integer,int[]> guards){
        ArrayList<Integer> guardsTime = new ArrayList<>(guards.keySet());
        guardsTime.sort((g1, g2)
                -> guards.get(g1)[0] - guards.get(g2)[1]);
        return guardsTime;
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
