package 프로그래머스;

public class 타겟넘버 {
    static int[] mult = new int[]{-1,1};
    static int count=0;
    public int solution(int[] numbers, int target) {
        calc(0,0,numbers,target);
        return count;
    }

    public void calc(int index, int sum, int[] numbers, int target){

        if(index==numbers.length){
            if(sum!=target){
                return;
            }else{
                count++;
            }
        }else{
            for(int i=0;i<2;i++){
                calc(index+1,sum+mult[i]*numbers[index],numbers,target);
            }
        }

    }

}
