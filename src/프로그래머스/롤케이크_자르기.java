package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 롤케이크_자르기 {
    public static void main(String[] args) {
        int[] test1 = new int[]{1, 2, 1, 3, 1, 4, 1, 2};
        int[] test2 = new int[]{1, 2, 3, 1, 4};
        System.out.println(solution2(test1));
        System.out.println(solution2(test2));
    }


    public static int solution2(int[] topping) {
        int answer = 0;

        // topping 의 원소는 10000 이하 이므로 배열의 길이는 10001
        int[] left = new int[10001];
        int[] right = new int[10001];

        // 왼쪽의 토핑 갯수
        int ls = 0;
        // 오른쪽의 토핑 갯수
        int rs = 0;

        // i 번 토핑이 나타난 횟수
        for(int i : topping){
            right[i]++;
        }

        // 오른쪽에 모든 토핑 있다고 가정
        for(int i : right){
            if(i>0){
                rs+=1;
            }
        }

        // 어차피 왼쪽에만 토핑 정보가 추가됨
        // 토핑이 i면, 오른쪽에 있는 토핑 정보에서 i번째 인덱스를 감소시켜줌 (토핑 갯수 하나 줄어듬)
        // 감소시킬 때, right[i] 가 0이면 오른쪽에도 i 토핑이 없는 것. -> right 가 갖고있는 토핑 종류가 하나 줄어듬
        // left[i] 가 0이였으면, left 에 해당 토핑이 없었다는 것임. 따라서 토핑 갯수 1개 늘어남
        // left[i] 에 i 토핑이 1개 추가됨

        // left 토핑 갯수 == right 토핑 갯수 이면 카운트 증가가

        for(int i : topping) {
            right[i]--;
            if (right[i] == 0) {
                rs--;
            }
            if (left[i] == 0) {
                ls++;
            }
            left[i]++;
            if (rs == ls) {
                answer++;
            }
        }
        return answer;
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

        int youngSize=young.size();
        int oldSize=0;
        int count=0;

        // i 번째 토핑을 왼쪽에 더하고 오른쪽에선 뺌
        for(int i=0;i<topping.length;i++){
            //System.out.println(old+" "+ young);
            int top = topping[i];
            if(old.add(top)){
                oldSize+=1;
            }
            boolean flag = false;
            for(int j=i+1;j<topping.length;j++){
                if(topping[j]==top){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                young.remove(top);
                youngSize-=1;
            }
            if(oldSize==youngSize){
                count++;
            }
        }

        return count;
    }
}
