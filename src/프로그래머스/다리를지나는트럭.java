package 프로그래머스;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class 다리를지나는트럭 {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights= new int[]{7,4,5,6};
        int result = solution(bridge_length,weight,truck_weights);
        System.out.println(result);
    }

    @Test
    public void case1(){
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights= new int[]{7,4,5,6};
        int result = solution(bridge_length,weight,truck_weights);
        Assert.assertEquals(result,8);
    }

    @Test
    public void case2(){
        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights= new int[]{10};
        int result = solution(bridge_length,weight,truck_weights);
        Assert.assertEquals(result,101);
    }

    @Test
    public void case3(){
        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights= new int[]{10,10,10,10,10,10,10,10,10,10};
        int result = solution(bridge_length,weight,truck_weights);
        Assert.assertEquals(result,110);
    }
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Queue<Integer> waiting = new LinkedList<>();
        for(int data : truck_weights){
            waiting.add(data);
        }
        Queue<Integer> bridge = new LinkedList<>();
        for(int i=0;i<bridge_length-1;i++){
            bridge.add(0);
        }
        bridge.add(waiting.poll());
        int sum = truck_weights[0];
        while (waiting.size()!=0) {
            System.out.println(answer+" 초");
            System.out.println("bridge: "+bridge);
            System.out.println("waiting: "+waiting);
            sum-=bridge.poll();

            if (sum + waiting.peek() <= weight) {
                int truck = waiting.poll();
                bridge.add(truck);
                sum+=truck;
            } else {
                bridge.add(0);
            }

            answer++;
            System.out.println();
        }
        return answer+bridge.size();
    }
}
/*
[0,0] 0
[0,7] 1
[7,0] 2
[0,4] 3
[4,5] 4
[5,0] 5
[0,6] 6
[6,0] 7
[0,0] 8
* */