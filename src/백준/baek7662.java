package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baek7662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<testcase;i++){

            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int dataCount = Integer.parseInt(br.readLine());
            for(int j=0;j<dataCount;j++){
                String[] operation = br.readLine().split(" ");
                String command = operation[0];
                int T = Integer.parseInt(operation[1]);

                // I 연산
                if(command.equals("I")){

                }

                // D 연산
                else{
                    if(tm.isEmpty()){
                        continue;
                    }
                    // 최솟값 삭제 연산
                    if(T==-1){

                    }

                    // 최댓값 삭제 연산
                    else{

                    }
                }
            }



        }
        System.out.println(sb);
    }
    // 큰 순서로 빠지는 애
    public static class Max implements Comparable<Max>{
        public int data;

        public Max(int data) {
            this.data = data;
        }

        public int getData(){
            return this.data;
        }

        @Override
        public int compareTo(Max o) {
            return o.data-this.data;
        }
    }

    // 작은 순서로 빠지는 애
    public static class Min implements Comparable<Min>{
        public int data;

        public Min(int data) {
            this.data = data;
        }

        public int getData(){
            return this.data;
        }

        @Override
        public int compareTo(Min o) {
            return this.data-o.data;
        }
    }

}
/*
I 16
I -5643
D -1
D 1
D 1
I 123
D -1

max : 123
min : 123

I -45
I 653
D 1
I -642
I 45
I 97
D 1
D -1
I 333
max : -45 -642, 45, 333
min : -45 653, 45 ,97, 333
*
* */