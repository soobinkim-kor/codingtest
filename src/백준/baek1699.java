package 백준;

import java.util.ArrayList;
import java.util.Scanner;

public class baek1699 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=1;i<=317;i++){
            arrayList.add((int) Math.pow(i,2));
        }

        int count=0;

        while(n>0){
            int index = find(arrayList,n);
            System.out.print(arrayList.get(index)+" ");
            n=n-arrayList.get(index);
            count++;
        }
        System.out.println("\n"+count);
    }

    public static int find(ArrayList<Integer> arrayList, int target){
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i)==target){
                return i;
            }
            else if(arrayList.get(i)>target){
                return i-1;
            }
        }

        return -1;
    }

}
