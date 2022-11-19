package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class baek1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] number = new int[n];
        String[] numbers = br.readLine().split(" ");
        for(int i=0;i<n;i++){
            number[i]= Integer.parseInt(numbers[i]);
        }
        String skip = br.readLine();
        String[] targets = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        Arrays.sort(number);
        for(String target : targets){
            int intTarget = Integer.parseInt(target);
            if(Arrays.binarySearch(number,intTarget)>=0){
                sb.append("1\n");
            }
            else{
                sb.append("0\n");
            }
        }


        System.out.println(sb.toString());

    }
}
