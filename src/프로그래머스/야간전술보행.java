package 프로그래머스;

import java.util.*;

public class 야간전술보행 {
    // 정렬하면 어렵지 않을 듯
    // SCOPE 와 TIME 을 기반으로 근무 상태 확인
    // 거리 + 휴식 시간의 나머지 로
    static int current;
    public static void main(String[] args) {
        int[][] scope = {{3,4},{5,8}};
        int[][] times = {{2,5},{4,3}};

        int[][] scope2 = {{7,8},{4,6},{11,10}};
        int[][] times2 = {{2,2},{2,4},{3,3}};
        //System.out.println(solution(10,scope,times));
        System.out.println(solution(12,scope2,times2));
    }
    public static int solution(int distance, int[][] scope, int[][] times) {
        HashMap<Integer,int[]> guards = new HashMap<>();
        for(int i=0;i<scope.length;i++){
            guards.put(i,scope[i]);
        }

        // 각 경비원들의 감시 구간을 시작 순으로 정렬
        ArrayList<Integer> sortedTimes = sortArray(guards);
        //System.out.println("시작 시간 순 정렬된 경비병 번호: "+sortedTimes);
        for(int d : sortedTimes){
            //System.out.println(Arrays.toString(guards.get(d)));
        }
        // 각 경비병의 시작 순으로 정렬된 번호

        for(int guard : sortedTimes){
            // guard = 경비병의 번호
            //System.out.println("경비병 번호: "+guard);

            current= scope[guard][0];
            int end = scope[guard][1];
            //System.out.println("목표 위치 : "+end);
            //System.out.println("여기서 시작: "+current);
            int[] guardData = guards.get(guard);
            if(!isPresent(guardData,guard,times)){
                return current;
            }
            // 감시 시작 구간
        }


        return distance;
    }

    // 정해진 시간에, 그 경비병이 휴식하지 않는 시간이 있는지 확인하는 메소드
    public static boolean isPresent(int[] guardData, int guard, int[][] times){
        // 우선 진입 시간으로 확인
        // ex current 10초, 경비병 휴식 [근무: 2초, 휴식: 4초] -> 10/6 = 1세트 , 나머지 = 4 = 현재 시간에 경비 근무/휴식의 어느 부분에 있는지.
        // 나머지 - 근무 > 0 -> 근무 시간 지난 거. 휴식 중
        // 나머지 - 근무 <= 0 -> 근무 시간임 . -> 잡힘
        // 휴식 + 근무 - 나머지 = 경비병 근무 시작 까지 남은 시간
        // 경비병 근무 시작 까지 남은 시간 < 구간을 지날 때 걸리는 시간 -> 안잡힘 -> 경비병의 마지막 구간으로 이동
        // 경비병 근무 시작 까지 남은 시간 >= 구간을 지날 때 걸리는 시간 -> 잡힘 -> 현재 위치 = current + 경비병 근무 시작까지 남은 시간


        // 4초에 근무 시작 , 4초에 있으면 잡힘

        // 감시 시작 구간
        int guardStart = guardData[0];
        // 감시 끝 구간
        int guardEnd = guardData[1];

        int pass = guardEnd-guardStart;
        //System.out.println("화랑이가 통과하려면 "+pass+" 초 지나가야함");
        // 근무 시간
        int guardActive = times[guard][0];
        // 휴식 시간
        int guardRest = times[guard][1];
        //System.out.println("해당 경비병의 근무 시간: "+guardActive+" 휴식 시간: "+guardRest);
        // 경비원이 (휴식+근무를) 몇 세트 했는지
        //int startRest = current/(guardActive+guardRest);
        // 현재 시간에 경비 근무/휴식의 어느 부분에 있는지.
        int time = current%(guardActive+guardRest);
        //System.out.println("현재 경비병은 의 위치: "+time);
        if(time-guardActive<0){
            //System.out.println("진입 시 경비병이 근무 중");
            return false;
        }

        int activeLeft = guardActive+guardRest-time+1;
        //System.out.println("현재 경비병은 "+activeLeft+" 초 후 근무 시작");
        if(activeLeft<=pass){
            //System.out.println("지나는 중 잡힘");
            current+=activeLeft;
            return false;
        }
        else{
            //System.out.println("무사히 통과");
            return true;
        }
    }

    public static ArrayList<Integer> sortArray(HashMap<Integer,int[]> guards){
        ArrayList<Integer> guardsTime = new ArrayList<>(guards.keySet());
        guardsTime.sort(Comparator.comparingInt(g -> guards.get(g)[0]));
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
