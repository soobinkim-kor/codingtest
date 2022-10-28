package 외부코테;

import java.util.*;

public class num2delivery {

    public static void main(String[] args) {
        System.out.println(solution(4,5,new int[]{1,0,3,1,2},new int[]{0,3,0,4,0}));
    }
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        ArrayList<Integer> delivery = new ArrayList<>();
        ArrayList<Integer> pickup = new ArrayList<>();

        for(int i=0;i<deliveries.length;i++){
            delivery.add(deliveries[i]);
            pickup.add(pickups[i]);
        }
        System.out.println(delivery);


        //while(true){
        int capacity=cap;
        //while(capacity>0){                          // 배달

            for(int i=delivery.size()-1;i>=0;i--){  // 맨 뒤 인덱스부터
                System.out.println("index: "+i);
                int num = delivery.get(i);          // 해당 인덱스의 배달량
                if(num>0){              // 배달을 해야되는 집이면
                    System.out.println(i+" 에 배달 해야함");
                    System.out.println("현재 갖고있는 택배 갯수: "+capacity);
                    if(num>capacity){   // 배달해야 되는 양이 갖고있는 양보다 크면
                        System.out.println(i+" 에 택배 다 줌: "+capacity+" 개");
                        delivery.set(i,num-capacity);       // 있는거 다 배달
                        capacity-=num;

                    }else{              // 배달해야 되는 양이 갖고있는 양과 같거나 작으면
                        System.out.println(i+" 에 택배 배달: "+delivery.get(i)+" 개");
                        delivery.set(i,0);      // 해당 인덱스는 배달 완료
                        capacity-=delivery.get(i);      // 갖고있는 박스 개수 차감
                    }
                }
                //System.out.println("현재 갖고있는 택배 갯수: "+capacity);
            }

        //}

        //break;
        //}

        return answer;
    }

    public static ArrayList<Integer> howMany(ArrayList<Integer> delivery, int cap){   // 배달을 완료할 수 있는(deliveries를 0으로 바꿔도 되는) 인덱스 구하는 메소드
        // arraylist의 뒤부터 탐색
        ArrayList<Integer> array = new ArrayList<>();
        int sum=0;
        for(int i=array.size()-1;i>=0;i--){
            if(sum+delivery.get(i)<=cap){
                sum+=delivery.get(i);
                array.add(i);
            }else{
                break;
            }
        }
        return array;
    }
}
