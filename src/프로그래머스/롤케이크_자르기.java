package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 롤케이크_자르기 {
    public static void main(String[] args) {
        int[] test1 = new int[]{1, 2, 1, 3, 1, 4, 1, 2};
        int[] test2 = new int[]{1, 2, 3, 1, 4};
        System.out.println(solution(test1));
        System.out.println(solution(test2));
    }

    public static int solution(int[] topping){
        // 처음에 young 에 모두 추가
        // i번째 껄 old 에 추가
        // i번째 토핑이 i번째 이후에 등장하면 그대로 두고
        // 등장하지 않으면 young 에서 해당 토핑도 빼줌
        Set<Integer> old = new HashSet<>();
        Set<Integer> young = new HashSet<>();
        for(int top : topping){
            young.add(top);
        }

        int count=0;

        // i 번째 토핑을 왼쪽에 더하고 오른쪽에선 뺌
        for(int i=0;i<topping.length;i++){
            //System.out.println(old+" "+ young);
            int top = topping[i];
            old.add(top);
            boolean flag = false;
            for(int j=i+1;j<topping.length;j++){
                if(topping[j]==top){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                young.remove(top);
            }
            if(old.size()==young.size()){
                count++;
            }
        }

        return count;
    }
}
