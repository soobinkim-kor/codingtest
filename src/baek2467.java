import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek2467 {
    /*
10
-99 -77 -66 -12 -2 -1 4 7 9 12
    */

    // -99 -50 -33 -2 -1 4 26 59 98
    // -99, 98 -> -1
    // -50 -33 -2 -1 4 26 59
    // -50, 59 확인
    // -50,26 확인
    // -33,59 확인
    // 셋 다 작으니까 둘다 삭제

    // -33 -2 -1 4 26
    // -33,26 확인
    //

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] data = new int[num];
        String[] str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++){
            data[i]=Integer.parseInt(str[i]);
        }

        int left=0;
        int right=data.length-1;

        int small = data[left];     // 정답 숫자1
        int big = data[right];    // 정답 숫자2

        int sum = Math.abs(small+big); // 숫자1과 숫자2의 합의 절댓값

        int[] answer = new int[2];
        answer[0]=data[left];        //초기값
        answer[1]=data[right];          //초기값

        while(left<right){
            if(Math.abs(data[left]+data[right])<=sum){
                answer[0]=data[left];
                answer[1]=data[right];
                sum=Math.abs(data[left]+data[right]);
            }

            //System.out.println(data[left]+" 와 "+data[right]+" 비교 중");

            if(data[left]+data[right]>0){         // 합이 0 보다 크면 우측 숫자를 한칸 작게 (작게 만들어야 하므로)
                //System.out.println("합이 양수. right 감소 "+left+" "+right);

                right--;
            }

            if(data[left]+data[right]<0){                                 // 합이 0보다 작으면 좌측 숫자를 한칸 크게 (크게 만들어야 하므로)
                //System.out.println("합이 음수. left 증가 "+left+" "+right);
                left++;
            }

            if(data[left]+data[right]==0){
                //System.out.println("합이 0. 바로 끝");
                System.out.println(data[left]+" "+data[right]);
                return;
            }
        }

        System.out.println(answer[0]+" "+answer[1]);
        // 양 쪽 값부터 시작해서, 특성값 비교

    }
}
