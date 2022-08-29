import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class baek11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String[] numbs = br.readLine().split(" ");
        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i=0;i<length;i++){
            arrayList.add(Integer.parseInt(numbs[i]));
        }

        int[] goUp = new int[length];
        int[] goDown = new int[length];
        Arrays.fill(goUp,1);
        Arrays.fill(goDown,1);
        // 1 5 2 1 4
        // 1 2 2
        // 최대 길이 배열 찾기
        // 자기보다 작은 숫자 + 1
        for(int i=0;i<length;i++){
            goUp[i]=calcArrayUp(arrayList,i);
            goDown[i]=calcArrayDown(arrayList,i);
        }


        int max=0;
        for(int i=0;i<length;i++){
            max=Math.max(max,goUp[i]+goDown[i]);
        }

        // 겹치는 부분 한개 빼기
        System.out.println(max-1);
    }

    public static int calcArrayUp(ArrayList<Integer> arrayList, int index){
        int count=0;
        int a = arrayList.get(index);
        for(int i=0;i<index;i++){
            if(a>arrayList.get(index)){
                count++;
            }
        }
        return count;
    }

    // index 기준 뒤 탐색
    public static int calcArrayDown(ArrayList<Integer> arrayList, int index){
        int count=0;
        int a = arrayList.get(index);
        for(int i=index;i<arrayList.size();i++){
            if(a>arrayList.get(index)){
                count++;
            }
        }
        return count;
    }
}
