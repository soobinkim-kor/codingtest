package 백준;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class baek1699 {
    public static void main(String[] args) {
        int[] dp = new int[100001];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();


        scan.close();
        ArrayList<Integer> squares = new ArrayList<>();

        for(int i=1;i<=316;i++){
            squares.add((int) Math.pow(i,2));
            dp[(int)Math.pow(i,2)]=1;
        }


        for(int i=1;i<dp.length;i++){

            for(int square : squares){
                int newIndex = i+square;

                if(newIndex>dp.length-1){
                    break;
                }
                dp[newIndex] = Math.min(dp[i]+1,dp[newIndex]);
            }
        }
        System.out.println(dp[n]);

    }

}
