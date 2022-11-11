package 프로그래머스;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 택배상자 {
    public static void main(String[] args) throws InterruptedException {
        int[] test = new int[]{4,3,1,2,5};
        int[] test1 = new int[]{5,4,3,2,1};
        int[] test2 = new int[]{1,2,3,4,5};
        int[] test3 = new int[]{2,3,4,5,1};
        int[] test4 = {2, 1, 4, 3, 6, 5, 8, 7, 10, 9};
        System.out.println(solution(test)==2);
        //System.out.println(solution(test1)==5);
        //System.out.println(solution(test2)==5);
        //System.out.println(solution(test3)==5);
        //System.out.println(solution(test4)==10);
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


        for(int i=1;i<=boxes;i++){
            System.out.println("보조 벨트: "+subBelt);
            System.out.println(i+" 번 박스 들고있음");
            int box = order[i-1];
            System.out.println(box+" 번 박스가 들어올 차례");
            if(box==i){
                System.out.println("이 택배가 바로 쌓으면 되는 택배");
                count++;
            }
            else{
                System.out.println("순서가 맞지 않으니 보조 컨테이너 봐야함");
                if(subBelt.isEmpty()){
                    System.out.println("보조 컨테이너가 비어있으므로 그냥 추가하면 됨");
                    subBelt.add(i);
                }

                if(order[count]==subBelt.peek()){
                    System.out.println("보조 컨테이너에 있는 애 넣으면 됨");
                    while(order[count]!=subBelt.peek()){
                        i++;
                        subBelt.pop();
                        count++;
                    }


                }
            }
            System.out.println();
        }

        // 모든 택배를 컨테이너 벨트에 올려놓을 때 까지 실행
//        while(!container.isEmpty()){
//            Thread.sleep(500);
//            System.out.println();
//            System.out.println("컨테이너 벨트: "+container);
//
//            System.out.println("보조 벨트: "+subBelt);
//            System.out.println("트럭에 넣은 택배 갯수: "+count);
//            System.out.println("이번에 트럭에 넣어야 하는 택배 번호: "+order[count]);
//
//            if(!subBelt.isEmpty()){
//                if(subBelt.peek()==order[count]){
//                    System.out.println("보니까 보조 벨트에서 바로 꺼낼 수 있음ㅎㅎ");
//                    subBelt.pop();
//                    count++;
//                    continue;
//                }
//            }
//            // 컨테이너 벨트에서 받은 박스
//            int box = container.poll();
//            System.out.println("현재 받은 택배: "+box);
//            // 현재 받은 택배가 내릴 순서이면
//            if(order[count]==box){
//                System.out.println("현재 받은 택배 그냥 바로 트럭에 실음");
//                count++;
//            }
//
//            // 현재 컨테이너에서 받은 택배가 내릴 순서가 아니면
//            // 1. 보조 컨테이너에 넣음
//            else{
//                System.out.println("보조 벨트에서 꺼낼 수 없음 ㅠㅠ 그냥 보조 벨트에 올려");
//                subBelt.add(box);
//
//
//            }
//
//
//
//        }

        System.out.println("보조 컨테이너 벨트 확인");
        while(!subBelt.isEmpty()){
            System.out.println(subBelt);
            if(subBelt.peek()==order[count]){
                System.out.println("보조 컨테이너에서 pop");
                subBelt.pop();
                count++;
            }
            else{
                System.out.println("보조 컨테이너의 peek 이 맞지 않음.");
                break;
            }
        }
        return count;
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