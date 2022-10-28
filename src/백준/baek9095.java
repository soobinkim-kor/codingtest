package 백준;

import java.io.*;

public class baek9095 {
    static final int[] num = {1,2,3};
    // Dynamic Programming
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cases = Integer.parseInt(br.readLine());

        // dp[1] => 1의 조합 갯수
        int[] dp = new int[11];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=4;

        for(int i=4;i<=10;i++){
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }

        for(int i=0;i<cases;i++){
            int target = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[target])+"\n");
        }

        bw.close();
    }


}
