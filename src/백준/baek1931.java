package 백준;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/*
11
1 4
3 5
0 6
7 7
5 7
3 8
5 9
6 10
8 11
8 12
12 14
for(int[] data : times){
            for(int d : data){
                System.out.print(d+" ");
            }
            System.out.println();
        }
*/
public class baek1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        int[][] times = new int[num][2];

        for(int i=0;i<num;i++){
            String[] time = br.readLine().split(" ");
            times[i][0]=Integer.parseInt(time[0]);
            times[i][1]=Integer.parseInt(time[1]);
        }
        // 정렬 -> 1. 회의 끝나는 시간
        Arrays.sort(times, (o1, o2) -> {
            if(o1[1]==o2[1]){
                return o1[0]-o2[0];
            }
            else{
                return o1[1]-o2[1];
            }
        });


        int count=0;
        int time=0;
        for(int i=0;i<num;i++){
            if(time<=times[i][0]){
                count++;
                time=times[i][1];
            }
        }
        System.out.println(count);
    }
}
// 2,3  3,3
