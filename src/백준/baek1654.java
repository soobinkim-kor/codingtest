package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int k = Integer.parseInt(info[0]);
        int n = Integer.parseInt(info[1]);
        int[] lan = new int[k];

        int max = 0;
        for(int i=0;i<k;i++){
            lan[i]=Integer.parseInt(br.readLine());
            max = Math.max(lan[i],max);
        }

        max++;
        int min = 0;
        int mid = 0;

        while(min<max){
            mid = (min+max)/2;

            int count=0;
            for (int j : lan) {
                count += (j / mid);
            }
            if(count<n){
                max=mid;
            }
            else{
                min=mid+1;
            }
        }
        System.out.println(min-1);

    }
}
