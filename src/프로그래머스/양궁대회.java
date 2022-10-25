package 프로그래머스;

import java.util.ArrayList;

public class 양궁대회 {
    static ArrayList<int[]> shoots = new ArrayList<>();
    public static void main(String[] args) {
        int n = 5;
        int[] info = new int[]{2,1,1,1,0,0,0,0,0,0,0};   // 어피치의 정보
        dfs(0, n, 0, new int[11]);

        int[] answer = new int[11];
        int max=0;
        for(int[] ryan : shoots){      // 라이언의 정보

            int ryanPoint = 0;
            int apeachPoint = 0;
            for(int i=0;i<11;i++){

                // 둘 다 맞히지 못한 과녁 = 둘 다 0점
                if(ryan[i]==0 && info[i]==0){
                    continue;
                }
                // 과녁의 점수에서 라이언이 더 많이 맞췄다면
                if(info[i]<ryan[i]){
                    ryanPoint+=10-i;
                }

                // 과녁의 점수에서 어피치가 더 많거나 같게 맞췄다면
                else{
                    apeachPoint+=10-i;
                }
            }

            int diff = ryanPoint-apeachPoint; // 해당 조합에서 라이언과 어피치의 점수차이
            System.out.print("차: "+diff);
            System.out.println();
            if(diff<=0){
                continue;
            }
            if(max<diff){
                max=diff;
                System.out.println(diff);
                answer=ryan.clone();
            }
        }
        for(int data : answer){
            System.out.print(data+" ");
        }

    }

    // shot = 지금까지 쏜 화살 갯수,
    // result = 만들고 있는 중복 조합
    // n = 총 화살 갯수
    // index = 화살갯수를 업데이트 할 인덱스
    public static void dfs(int shot, int n, int index, int[] result){
        if(shot == n){

            int[] data = result.clone();
            shoots.add(data);
            return;
        }

        // 현재 인덱스에 몇개의 화살을 쏠 것인지
        for(int i = 0 ; i <= n ; i++){
            if(index==result.length){
                return;
            }
            if(shot>n){
                return;
            }

            // 해당 인덱스에 i 개를 쏨
            int prev = result[index];
            result[index]=i;
            dfs(shot+i,n,index+1,result);
            result[index]=prev;
        }
    }
}


    // 쏜 화살 갯수, 지금까지 만든 조합, 화살 갯수, 시작





/*
10  2   10/3+10
9   1   9/2+9     2
8   1   8/2+8     2
7   1   7/2+7
6   0   6/1+6     1
5   0   5/1+5
4   0   4/1+4
3   0   3/1+3
2   0   2/1+2
1   0   1/1+1
0   0   0/1+0

10  0   10/1    1
9   0   9/1     1
8   1   8/2     2
7   2   7/3
6   0   6/1     1
5   1   5/2     2
4   1   4/2     4
3   1   3/2
2   1   2/2
1   1   1/2
0   1   0/2


*/

/*
10  0   10/1    1
9   0   9/1     1
8   0   8/1     1
7   0   7/1     1
6   0   6/1     1
5   0   5/1     1
4   0   4/1     1
3   0   3/1     1
2   3   2/4     0
1   4   1/5     0
0   3   0/4     2

*/