package 프로그래머스;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 택배상자 {
    public static void main(String[] args) throws InterruptedException {
        int[] test = new int[]{4,3,1,2,5};
        int[] test1 = new int[]{1,2,3,4,5};

        System.out.println(solution(test));
        //System.out.println(solution(test1));

    }
    public static int solution(int[] order) throws InterruptedException {
        int answer = 0;

        // 1부터 n 까지
        int boxes = order.length;
        Stack<Integer> subBelt = new Stack<>();
        int count = 0; // 트럭에 실은 택배의 갯수 & 현재 완성해야 하는 택배 순서
        // i 는 순서
        Queue<Integer> container = new LinkedList<>();
        for(int i=1;i<=boxes;i++){
            container.add(i);
        }

        while(!container.isEmpty()|| subBelt.peek()==order[count]){
            Thread.sleep(500);
            System.out.println();
            System.out.println("현재 "+(count+1)+" 번 택배");
            System.out.println("컨테이너: "+container);
            System.out.println("보조 벨트: "+subBelt);
            if(!container.isEmpty() && !subBelt.isEmpty()){
                if(order[count]!=container.peek() && order[count]!=subBelt.peek() && container.isEmpty()){
                    System.out.println("둘다 안돼서 끝남");
                    break;
                }
            }
            if(container.isEmpty()){
                if(subBelt.peek()==order[count]){
                    continue;
                }else{
                    break;
                }
            }
            int box = container.poll();


            System.out.println("현재 컨테이너벨트에서 내려온 박스: "+box);
            if(box==order[count]){
                System.out.println("순서가 딱 맞음");
                count++;
                continue;
            }
            System.out.println("순서가 맞지 않아 보조 벨트에 "+box+" 추가");

            subBelt.push(box);

            int subBox = subBelt.peek();
            if(subBox==order[count]){
                count++;
                subBelt.pop();
            }
        }

        return count+1;
        /*
        for(int i=1;i<=boxes;i++){
            System.out.println("현재 트럭에 실린 택배 갯수: "+count);
            System.out.println("현재 보조 벨트: "+ subBelt);
            System.out.println(i+" 번 택배 들어옴");

            // 아직 트럭에 실을 차례가 아닌 애는 보조 벨트에 넣어놓음
            if(order[count]!=i){
                System.out.println(i+" 번 택배 가 들어올 순서가 아님");
                if(!subBelt.isEmpty()){
                    System.out.println("우선 보조 벨트에 들어있는지 확인");
                    int box = subBelt.peek();
                    if(box==count){
                        System.out.println("보조 벨트에 들어있음 "+box+"빼면 됨");
                        subBelt.pop();
                        count++;
                        continue;
                    }
                    System.out.println("보조 벨트의 순서가 맞지 않음");
                }
                System.out.println("일단 "+i+" 번 택배를 보조 벨트로 옮김");
                subBelt.push(i);
            }
            else{
                System.out.println(i+" 번 택배 가 들어올 순서임");
                count++;
            }

        }*/

    }
}

/*
*  1,2,3,4,5
*  4,5    1,2,3
*  5      1,2,3
*  5      1,2
*
*  4,3,1,2,5
*
*
*
* */